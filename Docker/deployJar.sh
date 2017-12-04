./stop.sh tomcat
ls -al Release/*.jar
rm tomcat/DocumentManager/WEB-INF/lib/DocumentManager.jar 
cp Release/*.jar tomcat/DocumentManager/WEB-INF/lib/
ls -al tomcat/DocumentManager/WEB-INF/lib/DocumentManager.jar
./tomcatStart.sh
docker ps
