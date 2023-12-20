package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        LibraryManager libraryManager = new LibraryManager(library);

        libraryManager.addBook("Harry Potter and the Goblet of Fire", "J.K.Rowling", 2000);
        libraryManager.addBook("The Hobbit", "J.R.R.Tolkien", 1937);
        library.displayBooks();

        //removing book
        libraryManager.removeBook("The Hobbit");
        System.out.println("\nAfter removing a book: ");
        library.displayBooks();

        //search by title
        List<Book> searchResults = libraryManager.searchBooksByTitle("Harry");
        System.out.println("\nSearch results: ");
        for (Book book : searchResults) {
            System.out.println(book);
        }

        //save to file
        libraryManager.saveBooksToFile();

        //load from file
        libraryManager.loadBooksFromFile();

        //View all books
        System.out.println("\nAll books in library after loading from file: ");
        library.displayBooks();
    }
}
