import pytest
import asyncio
from unittest.mock import Mock, AsyncMock, patch, MagicMock
from io import BytesIO
from typing import List

# Import the modules to test
from src.ftputil import FTPUtil
from src.reconFileProcessor import ProcessingResult, ProcessingStatus

# Set default loop scope to avoid warning
pytest.ini_options = {
    "asyncio_mode": "strict",
    "asyncio_default_fixture_loop_scope": "function"
}

# Mock the environment variables
@pytest.fixture
def mock_env_vars(monkeypatch):
    env_vars = {
        'PRIME_SFTP_SERVER': 'test.server.com',
        'PRIME_SFTP_SERVER_PORT': '22',
        'PRIME_SFTP_USER': 'testuser',
        'PRIME_SFTP_PASS': 'testpass',
        'PRIME_SFTP_PRIV_KEY_FILE': 'test_key',
        'PRIME_SFTP_PUB_KEY_FILE': 'test_pub_key',
        'PRIMERECON_FTP_FOLDER': 'primerecon',
        'PRIMERECON_ARCHIVE_FOLDER': 'primerecon_archive',
        'PRIME_ADAPTER_RECON_INCOMING_ENDPOINT': 'http://test/incoming',
        'PRIME_ADAPTER_RECON_OUTGOING_ENDPOINT': 'http://test/outgoing'
    }
    for key, value in env_vars.items():
        monkeypatch.setenv(key, value)
    return env_vars

# Mock SFTP client
class MockSFTP:
    def __init__(self, file_list: List[str], file_contents: dict):
        self.file_list = file_list
        self.file_contents = file_contents
        self.renamed_files = []
        self.removed_files = []
        self.stat = Mock()
        self.mkdir = Mock()
        # Initialize successful stat calls for base folders
        self.stat.side_effect = lambda path: Mock() if path in ['/primerecon', '/primerecon_archive'] else Mock()

    def listdir(self, path: str) -> List[str]:
        return [f for f in self.file_list if not f.endswith('_error.txt')]

    def open(self, path: str, mode: str):
        file_name = path.split('/')[-1]
        if file_name in self.file_contents:
            mock_file = Mock()
            mock_file.read.return_value = self.file_contents[file_name]
            return mock_file
        raise IOError(f"No such file: {path}")

    def rename(self, old_path: str, new_path: str):
        self.renamed_files.append((old_path, new_path))

    def remove(self, path: str):
        self.removed_files.append(path)

@pytest.fixture
def mock_sftp():
    return MockSFTP(
        file_list=['test1.txt', 'test2.txt'],  # Removed error.txt from list
        file_contents={
            'test1.txt': b'Mar 15 10:30, ticket1.txt, S, SERVER1',
            'test2.txt': b'Mar 15 11:30, ticket2.txt, F, SERVER2'
        }
    )

@pytest.fixture
def mock_ftp_util(mock_sftp):
    with patch('src.ftputil.FTPUtil', autospec=True) as MockFTPUtil:
        instance = MockFTPUtil.return_value
        instance.acquire_sftp_channel.return_value = mock_sftp
        instance.release_sftp_channel = Mock()
        yield instance

class AsyncProcessingResult:
    def __init__(self, status: ProcessingStatus, message: str = "", errors: List[str] = None):
        self.status = status
        self.message = message
        self.errors = errors or []
        self.is_success = status == ProcessingStatus.SUCCESS

    def __await__(self):
        yield from []
        return self

@pytest.mark.asyncio
async def test_successful_processing(mock_env_vars, mock_ftp_util, mock_sftp):
    """Test successful processing of FTP files"""
    with patch('src.reconFileProcessor.ReconFileProcessor') as MockProcessor:
        processor_instance = MockProcessor.return_value
        success_result = AsyncProcessingResult(
            ProcessingStatus.SUCCESS,
            "Successfully processed file",
            []
        )
        processor_instance.process_file = AsyncMock(return_value=success_result)
        
        from process_ftp import process_ftp_files
        result = await process_ftp_files()
        
        assert result is True
        assert len(mock_sftp.renamed_files) == 2
        assert processor_instance.process_file.call_count == 2
        
        archived_files = [path[1] for path in mock_sftp.renamed_files]
        assert 'dev/primerecon_archive/test1_processed.txt' in archived_files
        assert 'dev/primerecon_archive/test2_processed.txt' in archived_files


@pytest.mark.asyncio
async def test_no_files_to_process(mock_env_vars, mock_ftp_util):
    """Test behavior when no files are available to process"""
    empty_sftp = MockSFTP([], {})
    mock_ftp_util.acquire_sftp_channel.return_value = empty_sftp
    
    from process_ftp import process_ftp_files
    result = await process_ftp_files()
    
    assert result is True
    assert len(empty_sftp.renamed_files) == 0
