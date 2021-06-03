docker image build -t aws .

docker container run -d --name aws-shop -p 8080:8080 aws

docker container ls -as

docker stop aws-shop



