@echo off
setlocal enabledelayedexpansion
set JAVA_HOME=C:\Program Files\Java\jdk-17
echo JAVA_HOME=%JAVA_HOME%
mvn --version
cd /d D:\2026_Practice_WorkSpace\CucumbeAndTestNG
mvn clean install -DskipTests
