export userDefFolder=/usr/share/maven

cd cq-parent

$userDefFolder/bin/mvn install

cd ../sears-partsdirect-parent

$userDefFolder/bin/mvn install

cd ../sears-partsdirect-common

$userDefFolder/bin/mvn install

cd ../sears-partsdirect-vault

$userDefFolder/bin/mvn clean package content-package:install

cd ..
