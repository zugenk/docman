docker run --net docman-nw --name mongo-docman --rm -it -p 0.0.0.0:27017:27017 -v ${PWD}/mongo/data:/data/db -d mongo 
