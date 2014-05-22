export userDefFolder=/usr/share/maven

cd ../sears-partsdirect-common

$userDefFolder/bin/mvn clean install sling:install -Dmaven.test.skip=true --projects  sears-partsdirect-common

cd ../sears-partsdirect-vault

$userDefFolder/bin/mvn clean package content-package:install --projects  sears-partsdirect-vault

cd ..
