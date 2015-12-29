FROM tomcat:jre8

ADD build/libs/docker-test.war /usr/local/tomcat/webapps/
