docker run --net docman-nw --name tomcat-docman --rm -it -p 0.0.0.0:8080:8080 -v ${PWD}/tomcat/logs:/usr/local/tomcat/logs  -v ${PWD}/tomcat/DocumentManager:/usr/local/tomcat/webapps/DocumentManager -d tomcat:8.5-jre8