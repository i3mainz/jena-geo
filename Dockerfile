FROM tomcat:9-jdk11

COPY target/*.war /use/local/tomcat/webapps
