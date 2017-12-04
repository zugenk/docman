./stop.sh tomcat
rm -rdf tomcat/DocumentManager/*
unzip Release/DocumentManager.war -d tomcat/DocumentManager
echo 'New War Deployed'
ls -al Release/*.jar
rm tomcat/DocumentManager/WEB-INF/lib/DocumentManager.jar 
cp Release/*.jar tomcat/DocumentManager/WEB-INF/lib/
ls -al tomcat/DocumentManager/WEB-INF/lib/DocumentManager.jar
echo 'New Jar deployed'
./tomcatStart.sh
docker ps
