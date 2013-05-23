SERVERPATH=$1

NOW=`date +"%Y%m%d%H%M"`

echo stopping server
$SERVERPATH/crx-quickstart/bin/stop

echo Taking bakup
cp -rf $SERVERPATH/crx-quickstart ~/Downloads/$NOW

echo start server
nohup $SERVERPATH/crx-quickstart/bin/start

