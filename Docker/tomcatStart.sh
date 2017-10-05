docker run --net docman-nw --name tomcat-docman --rm -it -p 127.0.0.1:8080:8080 -v ${PWD}/tomcat/DocumentManager:/usr/local/tomcat/webapps/DocumentManager tomcat:8.0
