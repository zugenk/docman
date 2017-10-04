curdir=${PWD}
docker run --rm -it  --name postgres-docman -p 127.0.0.1:5432:5432  -v $curdir/postgres-Data:/var/lib/postgresql/data -e POSTGRES_ROOT_PASSWORD=admin -e POSTGRES_USER=docman -e POSTGRES_PASSWORD=docman -e POSTGRES_DB=docmanDB -e POSTGRES_INITDB_ARGS="--encoding=UTF8" -d postgres:9.5
