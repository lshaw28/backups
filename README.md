sears-partsdirect
=================

This is the repository for Sears PartsDirect migration project.

## Local Environment Setup for CQ Authoring (port 4502)
- Install JDK 1.6.x
- Install the latest version of GIT
- Setup AEM 5.6 on `localhost:4502`
- Install Apache Maven 3.0.3
- `git clone https://github.com/Siteworx/sears-partsdirect.git`
- Create a maven profile by adding or modifying your `settings.xml`, typically stored under your users folder. (e.g.: `~/.m2`)

		<?xml version="1.0" encoding="UTF-8"?>
		<settings 
			xmlns="http://maven.apache.org/SETTINGS/1.0.0" 
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
			xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
			<profiles>
				<profile>
					<id>gecb-localDevelopment</id>
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

