
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
    public String toString() {
        return String.format("Title: %s\n Author: %s\n Genre: %s\n Available Copies: %d", title, author, genre, copies);
    }
}

class Library {

    private List<Book> books = new ArrayList<>(); //ek list banai hai, books naam ki, jisme "Book" class ke objects store honge

    public void addBook(String title, String author, String genre, int copies) {
        books.add(new Book(title, author, genre, copies));
    }

    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void searchBooks(String keyword) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(keyword)
                    || book.getAuthor().equalsIgnoreCase(keyword)
                    || book.getGenre().equalsIgnoreCase(keyword)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    public void borrowBook(String title) {
        for (Book book : books) {
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
        for (Book book : books) {
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
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add New Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by Title/Author/Genre");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

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
        String keyword = sc.nextLine();
        library.searchBooks(keyword);
        sc.nextLine();
    }

    private static void borrowBook() {
        System.out.print("Enter Book Title to borrow: ");
        String title = sc.nextLine();
        library.borrowBook(title);
        sc.nextLine();
    }

    private static void returnBook() {
        System.out.print("Enter Book Title to return: ");
        String title = sc.nextLine();
        library.returnBook(title);
        sc.nextLine();
    }
}
