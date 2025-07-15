import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    private static String readNonEmptyLine(Scanner sc, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("⚠️ Input cannot be empty. Try again.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n-- Welcome to the library Management System---");
        Library l1=new Library();
        boolean flag = true;
        while(flag){
            System.out.println("--------------------------------");
            System.out.println("--------------------------------");
            System.out.println("Select Your choice");
            System.out.println("\t\t1 - Issue book");
            System.out.println("\t\t2 - Return book ");
            System.out.println("\t\t3 - Add book ");
            System.out.println("\t\t4 - Show Books ");
            System.out.println("\t\t5 - Update a Book ");
            System.out.println("\t\t6 - EXIT ");
            System.out.println("--------------------------------");
            System.out.println("--------------------------------");
            String input = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a number between 1-6.");
                continue; // restart the loop
            }
            switch(choice){
                case 1:{
                    l1.issueBook(sc);
                    break;
                }
                case 2:{
                    // return book
                    l1.returnBook(sc);
//                    sc.nextLine();
                    break;
                }case 3:{
                    // add book
                    //sc.nextLine();
                    System.out.println("Enter the book detail");
                    String name=readNonEmptyLine(sc,"Book Name: ");
                    String author =readNonEmptyLine(sc,"Author Name: ");;
                    String isbn = readNonEmptyLine(sc,"ISBN: ");

                    l1.addBook(new Book(name,author,isbn));
                    break;
                }case 4:{
                    //show book
                    l1.displayBook();
                    break;
                }case 5:{
                    //update book
                    l1.updateBook(sc);
                    break;
                }case 6:{
                    flag=false;
                    System.out.println("\n---SUCCESSFULLY EXITED FROM PROGRAM---");
                    break;
                }
                default:{
                    System.out.println("Invalid Input , Try again");
                }
            }

        }
        sc.close();
    }
}
