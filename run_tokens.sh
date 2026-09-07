#!/bin/bash
javac -d out src/scanner/*.java src/Main.java && \
java -cp out scanner.TokenTest
