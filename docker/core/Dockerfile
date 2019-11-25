FROM openjdk:12-jdk
MAINTAINER <rroart@gmail.com>
ADD files/catwarebank-main-core-0.1-SNAPSHOT.jar /usr/local/bin
RUN mkdir -p /home/roart/src/bank/key
ADD files/private.p12 /home/roart/src/bank/key
WORKDIR /usr/local/bin
RUN ln -s /tmp /usr/local/logs
USER root
CMD java -jar /usr/local/bin/catwarebank-main-core-0.1-SNAPSHOT.jar
