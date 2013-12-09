WeatherFinder
=============

Weather Finder Project

Build Environment Requirements
------------------------------
1. JRE 7
2. Tomcat 7
3. Eclipse Kepler or any other new eclipse version

Tested Browser Versions
-----------------------
1. Chrome, Firefox, IE 11

Eclipse Project Set Up
-----------------------
1. GitHub Repository Link: https://github.com/hifaz1012/WeatherFinder.git

2. Import WeatherFinder Project from GitHub Repository Link to Eclipse as Java Project.

   i. Select File > Import > Projects From Git. 
   
   ii. Enter GitGitHub Repository Link and complete the remaining steps to import as Java Project.
   
3. Set the desired logging file path by editing "log4j.appender.com.File" property present in the "src/log4j.properties" file.

4. Run JUnit Test Cases present in file : "com.weatherfinder.test.WeatherFinderServiceTest.java" to make sure the project has been correctly setup.

Deployment Steps
----------------
WeatherFinder project can be deployed in either of the following two ways:-

I. Manually deploy WeatherFinder.war file to Tomcat
---------------------------------------------------

   1. Run ant file: build.xml (default target : war) of WeatherFinder project to generate "WeatherFinder.war" in dist folder of WeatherFinder Project.
   
   2. Start Tomcat
   
   3. Deploy the WeatherFinder.war using tomcat manager or manually copy the WeatherFinder.war in tomcat webapps folder.
   
   4. Visit the application url in a browser : http://[Tomcat HostName]:[Tomcat Port Number]/WeatherFinder/ 
   
   5. Enter zip code to view weather details.
   
II. Autodeploy WeatherFinder.war file to Tomcat
-----------------------------------------------

   1. Edit build.properties file in WeatherFinder Project to set hostname, port, username and password of Tomcat Manager.
   2. Edit TOMCAT_HOME\conf\tomcat-users.xml to assign "manager-script" role to tomcat manager user.
	  e.g. <user name="admin" password="admin" roles="manager-gui,manager-script" />

   3. Start Tomcat
   
   4. Run ant file: build.xml of WeatherFinder project with target : "build-and-deploy"
   
   5. Visit the application url in a browser : http://[Tomcat HostName]:[Tomcat Port Number]/WeatherFinder/ 
   
   6. Enter zip code to view weather details. 

