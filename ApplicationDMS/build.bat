set JAVA=%JAVA_HOME%\bin
:: set M2_HOME=%IDE%\maven\maven3
:: set M2=%M2_HOME%\bin
:: set MAVEN_OPTS=-Xms256m -Xmx512m -Duser.language=en -Dfile.encoding="UTF-8"
:: set Path=%JAVA_HOME%\jre\bin;%M2%;
mvn clean install
pause


