sears-partsdirect
=================

This is the repository for Sears PartsDirect migration project.

## Local Environment Setup for CQ Authoring (port 4502)
- Install JDK 1.6.x
- Install the latest version of GIT
- Set up a working directory to house your local working copy of the GIT repository and AEM
- Setup AEM 5.6 on `localhost:4502`
	Available on the Dropbox
		Grab the AEM .jar and license.properties
		Mac - smb://ushofsvpnetapp1/ecomms/partsdirect/aem
		PC - //ushofsvpnetapp1/ecomms/partsdirect/aem/
	Copy the AEM .jar and license.properties file to your working directory and double-click on the AEM .jar
	AEM should install itself and open in a browser
- Install Apache Maven 3.0.3
- In another folder in your working directory, clone the GIT repo
	`git clone git@git301p.prod.ch3.s.com:aempd`
- Create a maven profile by adding or modifying your `settings.xml`, typically stored under your users folder. (e.g.: `~/.m2`)

		<?xml version="1.0" encoding="UTF-8"?>
		<settings 
			xmlns="http://maven.apache.org/SETTINGS/1.0.0" 
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
			xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
			<profiles>
				<profile>
					<id>spd-localDevelopment</id>
					<activation>
						<activeByDefault>true</activeByDefault>
					</activation>
					<properties>
						<crx_url>http://localhost:4502</crx_url>
						<crx_user>admin</crx_user>
						<crx_password>admin</crx_password>
					</properties>
				</profile>
			</profiles>
		</settings>

**Run CQ Parent**
- run `mvn install` on `sears-partsdirect/cq-parent`

**Run bare Sears Parts Direct Parent**
- !Important! comment out modules on `sears-partsdirect/sears-partsdirect-parent/pom.xml`
- run `mvn install -P<SEARS_PARTS_DIRECT_PROFILE_NAME>` on `sears-partsdirect/sears-partsdirect-parent`

**Run Sears Parts Direct Parent with Modules**
- uncomment out modules on `sears-partsdirect/sears-partsdirect-parent/pom.xml`
- run `mvn install -P<SEARS_PARTS_DIRECT_PROFILE_NAME>` on `sears-partsdirect/sears-partsdirect-parent`

**Run Sears Parts Direct File Vault**
- run `mvn clean package content-package:install` on `sears-partsdirect/sears-partsdirect-vault`

### Iterative testing
- run `mvn install -P<SEARS_PARTS_DIRECT_PROFILE_NAME>` on `sears-partsdirect/sears-partsdirect-common`
- run `clean package content-package:uninstall content-package:install` on `sears-partsdirect/sears-partsdirect-vault`