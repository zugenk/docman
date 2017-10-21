if [ -z "$1" ]; then
echo 'Stopping All'
docker stop tomcat-docman
docker stop mongo-docman
docker stop postgres-docman
else 
echo 'Stopping '$1
docker stop $1-docman
fi
