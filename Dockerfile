FROM azul/zulu-openjdk:17

RUN apt-get update \
  && apt-get install -y curl \
  && apt-get install -y wget \
  && apt-get install -y telnet

ARG UNAME=spring

ENV SPRING_PROFILES_ACTIVE default

RUN groupadd spring 
RUN useradd -g $UNAME -m -s /bin/bash $UNAME

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} jwt-demo.jar
RUN chown spring:spring /*.jar

USER $UNAME:$UNAME

ENV JAVA_TOOL_OPTIONS "-Dspring.jmx.enabled=true -Djava.rmi.server.hostname=0.0.0.0 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9999 -Dcom.sun.management.jmxremote.rmi.port=9999 -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"
ENTRYPOINT ["java","-jar","jwt-demo.jar"]