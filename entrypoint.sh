#! /bin/bash
# Define o caminho para a pasta
MVN_DIR="/app/.mvn"

# Verifica se a pasta existe e está vazia
if [ -d "$MVN_DIR" ] && [ -z "$(ls -A $MVN_DIR)" ]; then
  echo "A pasta $MVN_DIR está vazia. Executando mvn install..."
  # Comando para executar mvn install
  mvn install
else
  echo "A pasta $MVN_DIR não está vazia ou não existe. Pulando mvn install."
fi
mvn spring-boot:run