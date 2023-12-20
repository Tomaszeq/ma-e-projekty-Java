package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {
    private Library library;

    public LibraryManager(Library library) {
        this.library = library;
    }

    public void addBook(String title, String author, int year) {
        Book book = new Book(title, author, year);
        library.addBook(book);
    }

    public void removeBook(String title) {
        List<Book> books = library.getBooks();
        books.removeIf(book -> book.getTitle().equals(title));
    }

    public List<Book> searchBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : library.getBooks()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public void saveBooksToFile() {
        try {
            System.out.print("Enter the file name to save (e.g., library.ser): ");
            Scanner scanner = new Scanner(System.in);
            String fileName = scanner.nextLine();

            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
                outputStream.writeObject(library.getBooks());
                System.out.println("Books saved to file: " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadBooksFromFile() {
        try {
            System.out.print("Enter the file name to load (e.g., library.ser): ");
            Scanner scanner = new Scanner(System.in);
            String fileName = scanner.nextLine();

            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
                List<?> loadedBooks = (List<?>) inputStream.readObject();

                //Check if loadedBooks is actually a list of books
                if (loadedBooks.stream().allMatch(Book.class::isInstance)) {
                    List<Book> books = (List<Book>) loadedBooks;
                    library.getBooks().addAll(books);
                    System.out.println("Books loaded from file: " + fileName);
                } else {
                    System.out.println("Invalid data format in the file.");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
