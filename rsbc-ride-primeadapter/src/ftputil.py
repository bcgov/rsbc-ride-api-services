import paramiko
import os
import logging

class FTPUtil:

    def __init__(self, host, user, user_password, port, priv_key_file, pub_key_file, passphrase=None, known_hosts=None, base_path=None, clean_up='Y'):
        self.host = host
        self.user = user
        self.user_password = user_password
        self.port = port
        self.priv_key_file = priv_key_file
        self.pub_key_file = pub_key_file
        self.passphrase = passphrase
        self.known_hosts = known_hosts
        self.base_path = base_path or ""
        self.clean_up = clean_up

        self.logger = logging.getLogger(__name__)
        self.sftp = None
        self.session = None

    def acquire_sftp_channel(self):
        try:
            # Initialize SSH client
            ssh = paramiko.SSHClient()
            self.logger.debug("Paramiko SSH client initiated")

            # Set known hosts if specified
            if self.known_hosts and os.path.exists(self.known_hosts):
                ssh.load_host_keys(self.known_hosts)
                self.logger.debug("Loaded known hosts")
                ssh.set_missing_host_key_policy(paramiko.RejectPolicy())
            else:
                ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
                self.logger.debug("Auto-adding unknown hosts")

            # Load private key if specified
            private_key = None
            if self.priv_key_file:
                private_key = paramiko.RSAKey.from_private_key_file(self.priv_key_file, password=self.passphrase)
                self.logger.debug("Private key loaded")

            # Connect to the SFTP server
            ssh.connect(hostname=self.host, port=self.port, username=self.user, password=self.user_password, pkey=private_key)
            self.logger.debug("SSH connection established")

            # Open SFTP session
            self.sftp = ssh.open_sftp()
            self.logger.debug("SFTP session started")
            return self.sftp

        except Exception as e:
            self.logger.error(f"Exception during SFTP connection: {e}")
            raise e

    def release_sftp_channel(self):
        if self.sftp:
            try:
                # Disconnect SFTP session
                self.sftp.close()
                self.logger.debug("SFTP session closed")

                # Disconnect SSH session
                self.session.close()
                self.logger.debug("SSH session closed")

            except Exception as e:
                self.logger.error(f"Exception during SFTP disconnection: {e}")

    def get_base_path(self):
        return self.base_path

    def get_clean_up(self):
        return self.clean_up if self.clean_up in ['Y', 'N'] else 'Y'

    def encode_spaces(self, filename):
        return filename.replace(" ", "\\ ")

