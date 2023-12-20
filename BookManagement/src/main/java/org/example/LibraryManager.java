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
            if (containsAllWords(book.getTitle().toLowerCase(), title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }
    private boolean containsAllWords(String bookTitle, String query) {
        String[] queryWords = query.split("\\s+");
        for (String word : queryWords) {
            if (!bookTitle.contains(word)) {
                return false;
            }
        }
        return true;
    }
    public void saveBooksToFile(OutputStream outputStream) {

    }
    public void saveBooksToTextFile(OutputStream outputStream) {
        try (PrintWriter writer = new PrintWriter(outputStream)) {
            List<Book> books = library.getBooks();

            // Print the books before saving
            System.out.println("Books before saving: " + books);

            for (Book book : books) {
                writer.println(book.getTitle() + ", " + book.getAuthor() + ", " + book.getYear());
            }

            // Print a message after saving
            System.out.println("Books saved to file");
        }
    }
    public void loadBooksFromFile(InputStream inputStream) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            List<?> loadedBooks = (List<?>) objectInputStream.readObject();

            //Check if loadedBooks is actually a list of books
            if (loadedBooks.stream().allMatch(Book.class::isInstance)) {
                List<Book> books = (List<Book>) loadedBooks;
                library.getBooks().addAll(books);
                System.out.println("Books loaded from file");
            } else {
                System.out.println("Invalid data format in the file.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void loadBooksFromFile() {
        System.out.print("Podaj nazwę pliku do wczytania (np. library.ser): ");
        try (Scanner scanner = new Scanner(System.in)) {
            String fileName = scanner.nextLine();
            loadBooksFromFile(new FileInputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Library getLibrary() {
        return this.library;
    }
}
