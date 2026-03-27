@echo off
REM Compilazione
set CP=.;DataBase/postgresql-42.7.9.jar;DataBase/jbcrypt-0.4.jar

javac -cp "%CP%" DataBase/DBconnection.java
javac -cp "%CP%" DataBase/Registration.java
javac -cp "%CP%" Function/AccountFunction.java
javac -cp "%CP%" Function/BankFunction.java
javac -cp "%CP%" Pages/AccessPart.java
javac -cp "%CP%" Pages/AccountPage.java
javac -cp "%CP%" Pages/BankAccount.java
javac -cp "%CP%" Pages/GUI.java
javac -cp "%CP%" security/RegexInputValidator.java
javac -cp "%CP%" style/Colors.java
javac -cp "%CP%" Main.java

REM Esecuzione
java -cp "%CP%" Main
pause