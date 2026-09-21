# Library Management System

A simple **Java-based Library Management System** developed as a mini project to practice **Object-Oriented Programming, JDBC, Maven, and MySQL**.

The application provides a console-based interface for managing books, library members, and book issue/return operations.

## Features

* Add books
* Remove books
* Search books
* View all books
* Add library members
* View members
* Issue books
* Return books
* Track issued books
* Automatic due-date and fine handling
* MySQL database storage

## Tech Stack

* **Java**
* **JDBC**
* **MySQL**
* **Maven**
* **OOP**

## Project Structure

```text
LibraryManagementSystem/
│
├── database/
│   └── schema.sql
│
├── src/
│   └── main/
│       └── java/
│           └── library/
│               ├── Book.java
│               ├── Member.java
│               ├── BookDAO.java
│               ├── MemberDAO.java
│               ├── IssueDAO.java
│               ├── DatabaseConnection.java
│               └── Main.java
│
├── pom.xml
└── README.md
```

## Database

The project uses **MySQL** with a database named:

```text
library_management
```

The database schema is provided in:

```text
database/schema.sql
```

## Requirements

* Java 17 or later
* Maven
* MySQL

## How to Run

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/LibraryManagementSystem.git
cd LibraryManagementSystem
```

### 2. Create the database

Open MySQL:

```bash
mysql -u root -p
```

Run:

```sql
source database/schema.sql;
```

### 3. Configure MySQL

Open:

```text
src/main/java/library/DatabaseConnection.java
```

Update the MySQL username and password with your local database credentials.

### 4. Compile

```bash
mvn clean compile
```

### 5. Run

```bash
mvn exec:java
```

## Application Menu

```text
1 Add Book
2 Remove Book
3 Search Books
4 View Books
5 Add Member
6 View Members
7 Issue Book
8 Return Book
9 View Issued Books
0 Exit
```

## Example

```text
=== LIBRARY MANAGEMENT SYSTEM ===

1 Add Book
2 Remove Book
3 Search Books
4 View Books
5 Add Member
6 View Members
7 Issue Book
8 Return Book
9 View Issued Books
0 Exit
```

## What I Learned

This mini project helped me practice:

* Java OOP concepts
* JDBC connectivity
* MySQL database operations
* CRUD operations
* DAO-based code organization
* Maven project management
* Basic exception handling

## Future Improvements

* Add a graphical user interface
* Add user authentication
* Add advanced book search
* Add borrowing history
* Add library statistics

## Author

**Ashvitha Reddy**

Computer Science Engineering Student
