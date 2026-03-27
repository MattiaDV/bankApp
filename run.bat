@echo off
REM Compilazione
javac -cp ".;DataBase/postgresql-42.7.9.jar" DataBase/DBconnection.java
javac -cp ".;DataBase/postgresql-42.7.9.jar" Function/AccountFunction.java
javac -cp ".;DataBase/postgresql-42.7.9.jar" Function/BankFunction.java
javac -cp ".;DataBase/postgresql-42.7.9.jar" Pages/AccessPart.java
javac -cp ".;DataBase/postgresql-42.7.9.jar" Pages/AccountPage.java
javac -cp ".;DataBase/postgresql-42.7.9.jar" Pages/BankAccount.java
javac -cp ".;DataBase/postgresql-42.7.9.jar" Pages/GUI.java
javac -cp ".;DataBase/postgresql-42.7.9.jar" security/RegexInputValidator.java
javac -cp ".;DataBase/postgresql-42.7.9.jar" style/Colors.java
javac -cp ".;DataBase/postgresql-42.7.9.jar" Main.java

REM Esecuzione
java -cp ".;DataBase/postgresql-42.7.9.jar" Main
pause