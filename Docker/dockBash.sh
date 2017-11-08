if [ -z "$1" ]; then
echo 'Please Select container ..'
else 
docker exec -it $1-docman bash
fi

