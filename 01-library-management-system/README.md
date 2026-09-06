# Library Management System

Console-based Java application to manage books and members: add/search books, issue and return them, with rules enforced through custom exceptions.

## Concepts demonstrated
- **Encapsulation** — private fields with public getters/setters (`Book`, `Member`)
- **Inheritance & polymorphism** — `ReferenceBook extends Book`, overriding `getType()` so reference books are labeled and blocked from being issued
- **Custom exceptions** — `BookNotAvailableException` covers four failure cases (unknown book, unknown member, reference book, already issued)
- **Collections** — `HashMap<Integer, Book>` / `HashMap<Integer, Member>` keyed by ID for O(1) lookup, `ArrayList` for a member's issued books

## Run it
```bash
cd src
javac *.java
java LibraryManagementSystem
```
