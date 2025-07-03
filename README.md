# 🧾 Java JDBC – Employee Database App

This is a Java application that connects to a MySQL database using **JDBC** and performs **CRUD operations** on an `employee` table.

## 🎯 Objective

- Learn Java-Database connectivity (JDBC)
- Use `Connection`, `PreparedStatement`, and `ResultSet` objects
- Perform Add, View, Update, and Delete operations via CLI

---

## 🛠️ Tools & Technologies

- Java (JDK 8+)
- MySQL
- JDBC (MySQL Connector/J)
- Command Line + Notepad (or IDE)

---

## 🗃️ Database Setup

Run this SQL in MySQL to create the database and table:

```sql
CREATE DATABASE company;

USE company;

CREATE TABLE employee (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    salary DOUBLE
);
```
## 🚀 How to Run

1. Compile: `javac -cp .;mysql-connector-j-9.1.0.jar EmployeeDBApp.java`
2. Run: `java -cp .;mysql-connector-j-9.1.0.jar EmployeeDBApp`

## 📦 Your folder should look like:

1. D:\Elevate Labs Internship - 2025
    ├── `EmployeeDBApp.java`
    ├── `mysql-connector-j-9.1.0.jar`
