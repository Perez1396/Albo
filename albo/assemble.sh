#!/bin/bash

echo "Iniciando el proceso de construcción del proyecto Albo..."

./gradlew clean

./gradlew build

echo "Construcción del proyecto completada."