
import java.util.*;

class Book {

    private String title;
    private String author;
    private String genre;
    private int copies;
    private int maxcopies;

    public Book(String title, String author, String genre, int copies) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.maxcopies = copies;
        this.copies = copies;

    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getCopies() {
        return copies;
    }

    public void borrowBook() {
        if (copies > 0) {
            copies--;
        }
    }

    public boolean returnBook() {
        if (copies < maxcopies) {
            copies++;
            return true;
        } else {
            return false;
        }
    }

    public boolean isAvailable() {
        return copies > 0;
    }

    @Override
    //whenever printing instance directly, of the class, it is directly executing tostring method
    public String toString() {
        // return String.format("Title: %s\n Author: %s\n Genre: %s\n Available Copies: %d", title, author, genre, copies); //prints in next line
        return String.format("Title: %s || Author: %s || Genre: %s || Available Copies: %d", title, author, genre, copies); //divides data by ||
    }
}

class Library {

    private List<Book> libbooks = new ArrayList<>(); //ek list banai hai, libbooks naam ki, jisme "Book" class ke objects store honge

    public void addBook(String title, String author, String genre, int copies) {
        libbooks.add(new Book(title, author, genre, copies));
    }

    public void viewAllBooks() {
        if (libbooks.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        for (Book book : libbooks) {
            System.out.println(book);
        }
    }

    public void searchBooks(String bookvalue) {
        boolean found = false;
        for (Book book : libbooks) {
            if (book.getTitle().equalsIgnoreCase(bookvalue) || book.getAuthor().equalsIgnoreCase(bookvalue) || book.getGenre().equalsIgnoreCase(bookvalue)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    public void borrowBook(String title) {
        for (Book book : libbooks) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.isAvailable()) {
                    book.borrowBook();
                    System.out.println("Book borrowed successfully.");
                } else {
                    System.out.println("Book is currently not available.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void returnBook(String title) {
        for (Book book : libbooks) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.returnBook()) {
                    System.out.println("Book returned successfully.");
                    return;
                } else {
                    System.out.println("Book Was not Borrowed");
                    return;
                }
            }
        }
        System.out.println("Book not found.");
    }
}

public class LibraryManagementSystem {

    private static Scanner sc = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add a new book");
            System.out.println("2. View all library books");
            System.out.println("3. Search Book by Title/Author/Genre");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    library.viewAllBooks();
                    sc.nextLine();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    borrowBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting system.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void addNewBook() {
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        System.out.print("Enter Genre: ");
        String genre = sc.nextLine();
        System.out.print("Enter Number of Copies: ");
        int copies = sc.nextInt();
        sc.nextLine();

        library.addBook(title, author, genre, copies);
        System.out.println("Book added successfully.");
        sc.nextLine();
    }

    private static void searchBook() {
        System.out.print("Enter Title/Author/Genre to search: ");
        String bookvalue = sc.nextLine();
        library.searchBooks(bookvalue);
        sc.nextLine();
    }

    private static void borrowBook() {
        System.out.print("Enter book title to borrow: ");
        String title = sc.nextLine();
        library.borrowBook(title);
        sc.nextLine();
    }

    private static void returnBook() {
        System.out.print("Enter book title to return: ");
        String title = sc.nextLine();
        library.returnBook(title);
        sc.nextLine();
    }
}
