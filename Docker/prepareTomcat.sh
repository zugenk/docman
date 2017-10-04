curdir=${PWD}
mkdir tomcat
ln -s ../WebContent ./tomcat/DocumentManager
# docker run --rm -it -p 127.0.0.1:8080:8080 -v $curdir/tomcat:/usr/local/tomcat/webapps tomcat:8.0
