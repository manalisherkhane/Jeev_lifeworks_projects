package main;

import exception.LibraryException;
import model.Book;
import model.User;
import service.Library;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        try {
            library.addBook(new Book("ISBN001", "Effective Java", "Joshua Bloch", "Programming"));
            library.addBook(new Book("ISBN002", "Clean Code", "Robert Martin", "Programming"));
            library.addBook(new Book("ISBN003", "The Alchemist", "Paulo Coelho", "Fiction"));

            
            library.registerUser(new User("U001", "Alice"));
            library.registerUser(new User("U002", "Bob"));

            library.issueBook("U001", "ISBN001");

            library.printAllBooks();

            System.out.println("\n--- Search: 'clean' ---");
            library.searchByTitle("clean").forEach(System.out::println);

            System.out.println("\n--- Available Books ---");
            library.getAvailableBooks().forEach(System.out::println);

            library.returnBook("U001", "ISBN001");

            library.issueBook("U999", "ISBN001"); // Invalid user

        } catch (LibraryException e) {
            System.err.println("Library Error: " + e.getMessage());
        }
    }
}