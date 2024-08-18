import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private int bid;
    private int cost;
    private int copies;

    public Book(int bid, String title, String author, int cost, int copies) {
        this.bid = bid;
        this.title = title;
        this.author = author;
        this.cost = cost;
        this.copies = copies;
    }

    public int getBid() {
        return this.bid;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCopies(int avail) {
        this.copies = avail;
    }

    public int getCopies() {
        return this.copies;
    }

    public void Print() {
        System.out.println("Book ID: " + getBid());
        System.out.println("Book Title: " + getTitle());
        System.out.println("Book Author: " + getAuthor());
        System.out.println("Book Cost: " + getCost());
        System.out.println("No of copies: " + getCopies());
    }
}

class Member {
    private int id;
    private String name;
    private String designation;

    public Member(int id, String name, String designation) {
        this.id = id;
        this.name = name;
        this.designation = designation;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDesig() {
        return this.designation;
    }

    public void PrintDetails() {
        System.out.println("Person ID: " + getId());
        System.out.println("Person Name: " + getName());
        System.out.println("Designation: " + getDesig());
    }
}

public class Library {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Member> members = new ArrayList<>();
        String librarianPassword = "admin123";

        while (true)
         {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View Book Details");
            System.out.println("3. Add Member");
            System.out.println("4. View Member Details");
            System.out.println("5. Increase No of Copies (Librarian only)");
            System.out.println("6. Borrow Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int bid = sc.nextInt();
                    sc.nextLine(); // consume the leftover newline
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Book Author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter Book Cost: ");
                    int cost = sc.nextInt();
                    System.out.print("Enter Number of Copies: ");
                    int copies = sc.nextInt();
                    books.add(new Book(bid, title, author, cost, copies));
                    System.out.println("Book added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Book ID to view details: ");
                    int viewBid = sc.nextInt();
                    boolean bookFound = false;
                    for (Book book : books) {
                        if (book.getBid() == viewBid) {
                            book.Print();
                            bookFound = true;
                            break;
                        }
                    }
                    if (!bookFound) {
                        System.out.println("Book not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter Member ID: ");
                    int mid = sc.nextInt();
                    sc.nextLine(); // consume the leftover newline
                    System.out.print("Enter Member Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Designation: ");
                    String designation = sc.nextLine();
                    members.add(new Member(mid, name, designation));
                    System.out.println("Member added successfully!");
                    break;

                case 4:
                    System.out.print("Enter Member ID to view details: ");
                    int viewMid = sc.nextInt();
                    boolean memberFound = false;
                    for (Member member : members) {
                        if (member.getId() == viewMid) {
                            member.PrintDetails();
                            memberFound = true;
                            break;
                        }
                    }
                    if (!memberFound) {
                        System.out.println("Member not found!");
                    }
                    break;

                case 5:
                    System.out.print("Enter Librarian Password: ");
                    sc.nextLine(); // consume the leftover newline
                    String password = sc.nextLine();
                    if (password.equals(librarianPassword)) {
                        System.out.print("Enter Book ID to increase copies: ");
                        int increaseBid = sc.nextInt();
                        System.out.print("Enter number of copies to add: ");
                        int addCopies = sc.nextInt();
                        bookFound = false;
                        for (Book book : books) {
                            if (book.getBid() == increaseBid) {
                                book.setCopies(book.getCopies() + addCopies);
                                System.out.println("Copies updated successfully!");
                                bookFound = true;
                                break;
                            }
                        }
                        if (!bookFound) {
                            System.out.println("Book not found!");
                        }
                    } else {
                        System.out.println("Incorrect Password!");
                    }
                    break;

                case 6: // Borrow Book
                    System.out.print("Enter Book ID to borrow: ");
                    int borrowBid = sc.nextInt();
                    bookFound = false;
                    for (Book book : books) {
                        if (book.getBid() == borrowBid) {
                            if (book.getCopies() > 0) {
                                book.setCopies(book.getCopies() - 1);
                                System.out.println("Book borrowed successfully!");
                            } else {
                                System.out.println("Sorry, no copies available!");
                            }
                            bookFound = true;
                            break;
                        }
                    }
                    if (!bookFound) {
                        System.out.println("Book not found!");
                    }
                    break;

                case 7: // Return Book
                    System.out.print("Enter Book ID to return: ");
                    int returnBid = sc.nextInt();
                    bookFound = false;
                    for (Book book : books) {
                        if (book.getBid() == returnBid) {
                            book.setCopies(book.getCopies() + 1);
                            System.out.println("Book returned successfully!");
                            bookFound = true;
                            break;
                        }
                    }
                    if (!bookFound) {
                        System.out.println("Book not found!");
                    }
                    break;

                case 8:
                    System.out.println("Exiting the system...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}