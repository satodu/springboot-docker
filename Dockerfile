FROM maven:3.8.5-openjdk-17

WORKDIR /app

#Adding configuration files
COPY entrypoint.sh /entrypoint.sh
ADD src ./src

#entrypoint
RUN chmod +x /entrypoint.sh
ENTRYPOINT [ "/entrypoint.sh" ]