# For documentation see https://jboss-container-images.github.io/openjdk/
FROM registry.access.redhat.com/ubi9/openjdk-21-runtime:1.22-1.1753981256@sha256:06d60e73be11d96b4cedc4bd1807e503a6196e9b01be9e4405b6b6d3b816202d

# Copy runnable jar to deployments
COPY target/*.jar /deployments/application.jar

USER 0
RUN mkdir -p /deployments/eakte-external-config
USER $CONTAINER_USER_ID

# Definiere den Befehl zum Starten der Anwendung (passe dies an deine Anwendung an)
CMD ["java", "-jar", "/deployments/application.jar", "-Dhttp.proxyHost=c030-floating-pxint-vlan3024.net.muenchen.de", "-Dhttp.proxyPort=80", "--spring.config.additional-location=/deployments/eakte-external-config/application.yml" ]