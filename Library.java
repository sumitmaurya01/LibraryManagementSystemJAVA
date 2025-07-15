import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Library {
    private final String FILE_NAME = "books.txt";

    public void addBook(Book book) {
        try {
            FileWriter writter = new FileWriter(FILE_NAME, true);
            writter.write(book.toFileString() + "\n");
            System.out.println("Successfully added to the file");
            writter.close();

        } catch (IOException e) {
            System.out.println(" Failed to write in file " + e.getMessage());

        }

    }

    private List<Book> loadBooks() {
        // load books from file
        List<Book> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String str;
            while ((str = reader.readLine()) != null) {
                String[] parts = str.split(" , ");
                if (parts.length == 3) {
                    Book b = new Book(parts[0], parts[1], parts[2]);
                    list.add(b);
                }
            }
        } catch (IOException e) {
            System.out.println(" Can't read file " + e.getMessage());
        }
        return list;
    }

    public void displayBook() {
        // display total numbers of books via loadBooks from the file
        List<Book> list = loadBooks();
        if (list.isEmpty())
            System.out.println("no books in the library");
        else {
            for (Book b : list) {
                System.out.println(b);
            }
        }
    }

    void updateBook(Scanner scan) {
        List<Book> list = loadBooks();
        if (list.isEmpty()) {
            System.out.println("No books to update..");
        } else {
//            Scanner scan = new Scanner(System.in);
            System.out.println("Enter isbn to update book");
            String isbn = scan.nextLine().trim();
            boolean found = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getIsbn().equals(isbn)) {
                    System.out.println("Enter  new name of book: ");
                    String name = scan.nextLine();
                    System.out.println("Enter  Author of the book: ");
                    String author = scan.nextLine();
                    System.out.println("Enter isbn of the book: ");
                    isbn = scan.nextLine();
                    list.set(i, new Book(name, author, isbn));
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Book not found");
            } else {
                try (FileWriter writter = new FileWriter(FILE_NAME)) {
                    for (int i = 0; i < list.size(); i++) {
                        Book b = list.get(i);
                        writter.write(b.toFileString() + "\n");
                    }
                    System.out.println("Book updated successfully ");
                } catch (IOException e) {
                    System.out.println(" Exception occur " + e.getMessage());
                }
            }
        }
    }
    // update a book

    public void issueBook(Scanner scan) {
        List<Book> books = loadBooks();
        if (books.isEmpty()) {
            System.out.println("No books to issue");
            return;
        }

        System.out.println("Enter the book name for issue: ");
        String name = scan.nextLine().trim();
        boolean found = false;

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getName().equalsIgnoreCase(name)) {
                books.remove(i);
                System.out.println("Book '" + name + "' issued successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Book not available in library.");
            return;
        }

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Book b : books) {
                writer.write(b.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error occurred " + e.getMessage());
        }
    }


    void returnBook(Scanner sc) {
      List<Book>books=loadBooks();
     System.out.println("BOOK RETURNING ");
     System.out.println("Enter book name ");
     String name=sc.nextLine().trim();
     System.out.println("Enter author name ");
     String author=sc.nextLine().trim();
     System.out.println("Enter isbn ");
     String isbn=sc.nextLine().trim();
     Book returnBook = new Book(name,author,isbn);
     try(FileWriter writer= new FileWriter(FILE_NAME,true)){
      writer.write(returnBook.toFileString());
     }catch (IOException e){
      System.out.println("Error occured "+e.getMessage());
     }

    }
}
