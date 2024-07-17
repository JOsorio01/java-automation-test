FROM bellsoft/liberica-openjdk-alpine:17.0.8

RUN apk add curl jq

WORKDIR /home/selenium-docker

ADD target/docker-resources/    ./
ADD entrypoint.sh               entrypoint.sh

ENTRYPOINT [ "sh", "entrypoint.sh" ]
