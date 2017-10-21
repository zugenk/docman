docker run --net docman-nw --name mongo-docman --rm -it -p 127.0.0.1:27017:27017 -v ${PWD}/mongo/data:/data/db mongo
