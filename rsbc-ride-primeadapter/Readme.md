# RIDE PRIME Adapter  



## Executable Scipt
```
process_ftp.py
```
This script will identify the Recon files in the provided SFTP directory and process them and send to the ETK prime adapter endpoints.
```
/primeadapter/v3/api/recon/incoming
/primeadapter/v3/api/recon/outgoing
```

This script will run as an Openshift Cron Job.