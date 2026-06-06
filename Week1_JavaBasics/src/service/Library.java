package service;

import exception.LibraryException;
import model.Book;
import model.User;

import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private Map<String, Book> bookCatalog = new HashMap<>();
    private Map<String, User> registeredUsers = new HashMap<>();

    public void addBook(Book book) throws LibraryException {
        if (bookCatalog.containsKey(book.getIsbn())) {
            throw new LibraryException("Book with ISBN " + book.getIsbn() + " already exists.");
        }
        bookCatalog.put(book.getIsbn(), book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void removeBook(String isbn) throws LibraryException {
        if (!bookCatalog.containsKey(isbn)) {
            throw new LibraryException("Book not found: " + isbn);
        }
        bookCatalog.remove(isbn);
        System.out.println("Book removed: " + isbn);
    }

    public void registerUser(User user) throws LibraryException {
        if (registeredUsers.containsKey(user.getUserId())) {
            throw new LibraryException("User already registered: " + user.getUserId());
        }
        registeredUsers.put(user.getUserId(), user);
        System.out.println("User registered: " + user.getName());
    }

    public void issueBook(String userId, String isbn) throws LibraryException {
        User user = registeredUsers.get(userId);
        Book book = bookCatalog.get(isbn);

        if (user == null) throw new LibraryException("User not found: " + userId);
        if (book == null) throw new LibraryException("Book not found: " + isbn);
        if (!book.isAvailable()) throw new LibraryException("Book is already issued: " + isbn);

        book.setAvailable(false);
        user.issueBook(isbn);
        System.out.println(user.getName() + " issued: " + book.getTitle());
    }

    public void returnBook(String userId, String isbn) throws LibraryException {
        User user = registeredUsers.get(userId);
        Book book = bookCatalog.get(isbn);

        if (user == null) throw new LibraryException("User not found: " + userId);
        if (book == null) throw new LibraryException("Book not found: " + isbn);

        book.setAvailable(true);
        user.returnBook(isbn);
        System.out.println(user.getName() + " returned: " + book.getTitle());
    }


    public List<Book> searchByTitle(String title) {
        return bookCatalog.values().stream()
            .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<Book> getAvailableBooks() {
        return bookCatalog.values().stream()
            .filter(Book::isAvailable)
            .collect(Collectors.toList());
    }

    public Map<String, List<Book>> getBooksGroupedByGenre() {
        return bookCatalog.values().stream()
            .collect(Collectors.groupingBy(Book::getGenre));
    }

    public void printAllBooks() {
        System.out.println("\n--- Book Catalog ---");
        bookCatalog.values().forEach(book -> System.out.println(book));
    }
}