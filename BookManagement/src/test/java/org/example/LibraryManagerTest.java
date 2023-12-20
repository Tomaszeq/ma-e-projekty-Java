package org.example;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


class LibraryManagerTest {

    @Test
    void testAddBook() {
        Library library = new Library();
        LibraryManager libraryManager = new LibraryManager(library);

        //add new book
        libraryManager.addBook("Test Book", "Test Author", 2000);

        //Verify that the book was added
        assertEquals(1, library.getBooks().size());
        assertEquals("Test Book", library.getBooks().get(0).getTitle());
    }

    @Test
    void testRemoveBook() {
        Library library = new Library();
        LibraryManager libraryManager = new LibraryManager(library);

        //add new book
        libraryManager.addBook("Test Book", "Test Author", 2000);

        //Verify that the book was added
        assertEquals(1,library.getBooks().size());

        //remove book
        libraryManager.removeBook("Test Book");

        //Verify that the book was removed
        assertEquals(0, library.getBooks().size());
    }

    @Test
    void testSearchBooksByTitle() {
        Library library = new Library();
        LibraryManager libraryManager = new LibraryManager(library);

        //add few new books
        libraryManager.addBook("Harry Potter and the Philosopher's Stone", "J.K.Rowling", 1997);
        libraryManager.addBook("Harry Potter and the Chamber of Secrets", "J.K.Rowling", 2002);
        libraryManager.addBook("Harry Potter and the Order of the Phoenix", "J.K.Rowling", 2003);

        //search book by title
        List<Book> searchResults = libraryManager.searchBooksByTitle("Harry");

        //check search results
        assertEquals(3, searchResults.size());

        //Search for books by title (case-sensitive)
        searchResults = libraryManager.searchBooksByTitle("pHoEnIx");

        //check whether the search results are correct
        assertEquals(1, searchResults.size());

        //looking for books by title that is not available in the library
        searchResults = libraryManager.searchBooksByTitle("Lord of the Rings");

        //check whether the search results are correct
        assertEquals(0, searchResults.size());
    }
    @Test
    void testSaveBooksToFile() throws IOException {
        // Create a Library
        Library library = new Library();

        // Create a LibraryManager, passing the Library as an argument
        LibraryManager libraryManager = new LibraryManager(library);

        // Add books to the library
        libraryManager.addBook("Title 1", "Author 1", 2000);
        libraryManager.addBook("Title 2", "Author 2", 2020);

        // Create an output stream to capture the saved data
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Call saveBooksToTextFile
        libraryManager.saveBooksToTextFile(outputStream);

        // Convert the saved data to a string
        String savedData = outputStream.toString(StandardCharsets.UTF_8);

        // Check if the saved data contains the expected information
        String expectedData = "Title 1, Author 1, 2000\nTitle 2, Author 2, 2020\n";

        assertEquals(expectedData, savedData);
    }

    @Test
    void testLoadBooksFromFile() {
        // Utwórz obiekt LibraryManager
        LibraryManager libraryManager = new LibraryManager(new Library());

        // Dane testowe
        List<Book> mockBooks = List.of(
                new Book("Tytuł 1", "Autor 1", 2000),
                new Book("Tytuł 2", "Autor 2", 2020)
        );

        // Zapisz dane testowe do pliku
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {

            objectOutputStream.writeObject(mockBooks);

            // Wywołaj loadBooksFromFile z danymi testowymi
            libraryManager.loadBooksFromFile(new ByteArrayInputStream(outputStream.toByteArray()));

            // Sprawdź, czy wczytane książki są zgodne z danymi testowymi
            assertEquals(mockBooks, libraryManager.getLibrary().getBooks());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


