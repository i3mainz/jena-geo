FROM tomcat:9-jdk11

COPY target/*.war /usr/local/tomcat/webapps
