FROM maven:3.6.3-jdk-13
MAINTAINER <rroart@gmail.com>

RUN mkdir /app
WORKDIR /app
COPY . .
RUN mvn install
RUN cp -p main/core/target/catwarebank-main-core-0.1-SNAPSHOT.jar /usr/local/bin
WORKDIR /usr/local/bin
RUN ln -s /tmp /usr/local/logs
USER root
CMD java -jar /usr/local/bin/catwarebank-main-core-0.1-SNAPSHOT.jar