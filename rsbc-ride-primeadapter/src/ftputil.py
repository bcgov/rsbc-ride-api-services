import io
import paramiko
import os
import logging
from typing import Optional

class FTPUtil:
    def __init__(self, host: str, user: str, user_password: str, port: int,
                 priv_key_str: Optional[str], pub_key_str: Optional[str],
                 passphrase: Optional[str] = None, known_hosts: Optional[str] = None,
                 base_path: str = "", clean_up: str = 'Y'):
        self.host = host
        self.user = user
        self.user_password = user_password
        self.port = port
        self.priv_key_str = priv_key_str
        self.pub_key_str = pub_key_str
        self.passphrase = passphrase
        self.known_hosts = known_hosts
        self.base_path = base_path
        self.clean_up = clean_up

        self.logger = logging.getLogger(__name__)
        self.sftp: Optional[paramiko.SFTPClient] = None
        self.ssh: Optional[paramiko.SSHClient] = None

    def acquire_sftp_channel(self) -> paramiko.SFTPClient:
        """
        Establishes an SFTP connection and returns the SFTP client.
        
        Returns:
            paramiko.SFTPClient: The SFTP client object.
        
        Raises:
            paramiko.SSHException: If there's an error during SSH connection.
            IOError: If there's an error during SFTP session creation.
        """
        try:
            self._initialize_ssh_client()
            self._load_known_hosts()
            private_key = self._load_private_key()
            self._connect_ssh(private_key)
            self._open_sftp_session()
            return self.sftp
        except (paramiko.SSHException, IOError) as e:
            self.logger.error(f"Failed to acquire SFTP channel: {str(e)}")
            self.release_sftp_channel()
            raise

    def _initialize_ssh_client(self) -> None:
        """Initializes the SSH client."""
        self.ssh = paramiko.SSHClient()
        self.logger.debug("Paramiko SSH client initiated")

    def _load_known_hosts(self) -> None:
        """Loads known hosts if specified, otherwise sets auto-add policy."""
        if self.known_hosts and os.path.exists(self.known_hosts):
            self.ssh.load_host_keys(self.known_hosts)
            self.logger.debug("Loaded known hosts")
            self.ssh.set_missing_host_key_policy(paramiko.RejectPolicy())
        else:
            self.ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
            self.logger.debug("Auto-adding unknown hosts")

    def _load_private_key(self) -> Optional[paramiko.PKey]:
        """
        Loads the private key from the provided string.
        
        Returns:
            Optional[paramiko.PKey]: The loaded private key object, or None if not provided.
        
        Raises:
            ValueError: If the key type is unsupported or the format is invalid.
        """
        if not self.priv_key_str:
            return None

        priv_key_io = io.StringIO(self.priv_key_str)
        key_types = [
            ('Ed25519Key', paramiko.Ed25519Key),
            ('RSAKey', paramiko.RSAKey)
        ]

        for key_name, key_class in key_types:
            try:
                priv_key_io.seek(0)
                private_key = key_class.from_private_key(priv_key_io, password=self.passphrase)
                self.logger.debug(f"Private key loaded as {key_name}")
                return private_key
            except paramiko.SSHException:
                continue

        raise ValueError("Unsupported key type or invalid key format")

    def _connect_ssh(self, private_key: Optional[paramiko.PKey]) -> None:
        """
        Establishes an SSH connection.
        
        Args:
            private_key (Optional[paramiko.PKey]): The private key to use for authentication.
        
        Raises:
            paramiko.SSHException: If there's an error during SSH connection.
        """
        try:
            self.ssh.connect(
                hostname=self.host,
                port=self.port,
                username=self.user,
                password=self.user_password,
                pkey=private_key
            )
            self.logger.debug("SSH connection established")
        except paramiko.SSHException as e:
            self.logger.error(f"Failed to establish SSH connection: {str(e)}")
            raise

    def _open_sftp_session(self) -> None:
        """
        Opens an SFTP session.
        
        Raises:
            IOError: If there's an error during SFTP session creation.
        """
        try:
            self.sftp = self.ssh.open_sftp()
            self.logger.debug("SFTP session started")
        except IOError as e:
            self.logger.error(f"Failed to open SFTP session: {str(e)}")
            raise

    def release_sftp_channel(self) -> None:
        """Closes the SFTP and SSH connections."""
        self._close_sftp()
        self._close_ssh()

    def _close_sftp(self) -> None:
        """Closes the SFTP session if it exists."""
        if self.sftp:
            try:
                self.sftp.close()
                self.logger.debug("SFTP session closed")
            except Exception as e:
                self.logger.error(f"Exception during SFTP disconnection: {str(e)}")
            finally:
                self.sftp = None

    def _close_ssh(self) -> None:
        """Closes the SSH connection if it exists."""
        if self.ssh:
            try:
                self.ssh.close()
                self.logger.debug("SSH session closed")
            except Exception as e:
                self.logger.error(f"Exception during SSH disconnection: {str(e)}")
            finally:
                self.ssh = None

    def get_base_path(self) -> str:
        """Returns the base path."""
        return self.base_path

    def get_clean_up(self) -> str:
        """Returns the clean up flag, defaulting to 'Y' if invalid."""
        return self.clean_up if self.clean_up in ['Y', 'N'] else 'Y'

    @staticmethod
    def encode_spaces(filename: str) -> str:
        """
        Encodes spaces in the filename.
        
        Args:
            filename (str): The filename to encode.
        
        Returns:
            str: The filename with spaces encoded.
        """
        return filename.replace(" ", "\\ ")