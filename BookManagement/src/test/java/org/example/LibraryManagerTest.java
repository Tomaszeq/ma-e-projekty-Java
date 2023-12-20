package org.example;

import org.junit.jupiter.api.Test;
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
}
