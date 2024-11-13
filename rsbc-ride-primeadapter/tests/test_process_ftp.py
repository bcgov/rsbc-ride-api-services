import unittest
from unittest.mock import patch, MagicMock
from io import StringIO
import sys
import os
import logging
from datetime import datetime

# Add the project root directory to Python path
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from process_ftp import main  # Import from root directory

class TestProcessFTP(unittest.TestCase):
    def setUp(self):
        # Capture stdout and logging output for testing
        self.held_output = StringIO()
        self.stdout_patch = patch('sys.stdout', self.held_output)
        self.stdout_patch.start()
        
        # Clear any existing handlers
        logger = logging.getLogger('process_ftp')
        logger.handlers = []
        
        # Add a StringIO handler for capturing logs
        self.log_output = StringIO()
        handler = logging.StreamHandler(self.log_output)
        handler.setFormatter(logging.Formatter('%(asctime)s - %(levelname)s - %(message)s'))
        logger.addHandler(handler)

    def tearDown(self):
        self.stdout_patch.stop()
        self.held_output.close()
        self.log_output.close()

    @patch('socket.gethostname')
    @patch('process_ftp.datetime')
    def test_main_success(self, mock_datetime, mock_hostname):
        # Mock the hostname and datetime
        mock_hostname.return_value = "test-host"
        mock_datetime.now.return_value = datetime(2024, 1, 1, 12, 0, 0)

        # Run the main function
        main()

        # Check stdout output
        stdout_content = self.held_output.getvalue().strip()
        expected_message = "Hello World from test-host at 2024-01-01 12:00:00"
        self.assertEqual(stdout_content, expected_message)

if __name__ == '__main__':
    unittest.main(verbosity=2)