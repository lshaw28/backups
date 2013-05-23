#!/bin/bash
set -e
#DATETIME
NOW=`date +"%Y%m%d%H%M"`

#HOST
HOST=localhost

#PORT
PORT=4502

#URL
BACKUPURL="http://$HOST:$PORT/libs/granite/backup/content/admin/backups"

#The destination directory where to put backup zips
DEST_DIR="/adobe/bkp/online_$NOW"

#location of the cURL executable
CURL="curl"

#the user:password used for authentication on the CRX package manager
AUTH="admin:admin"

# data params
DATA="delay=3&force=false&target=$DEST_URL"

# Create a new folder 
mkdir -p $DEST_DIR

# Run backup command
echo $CURL -u $AUTH --data \"$DATA\" $BACKUPURL

echo "done!"

