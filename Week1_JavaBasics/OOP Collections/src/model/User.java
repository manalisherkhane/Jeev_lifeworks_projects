package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String name;
    private List<String> issuedBookIsbns;  // ArrayList usage

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.issuedBookIsbns = new ArrayList<>();
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public List<String> getIssuedBookIsbns() { return issuedBookIsbns; }

    public void issueBook(String isbn) { issuedBookIsbns.add(isbn); }
    public void returnBook(String isbn) { issuedBookIsbns.remove(isbn); }
}