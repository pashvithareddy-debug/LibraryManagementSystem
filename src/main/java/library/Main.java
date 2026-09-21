package library;
import java.sql.SQLException;
import java.util.*;
public class Main {
    static Scanner s=new Scanner(System.in);
    static BookDAO books=new BookDAO(); static MemberDAO members=new MemberDAO(); static IssueDAO issues=new IssueDAO();

    public static void main(String[] args){
        System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
        while(true){
            System.out.println("\n1 Add Book\n2 Remove Book\n3 Search Books\n4 View Books\n5 Add Member\n6 View Members\n7 Issue Book\n8 Return Book\n9 View Issued Books\n0 Exit");
            String ch=s.nextLine();
            try{
                switch(ch){
                    case "1"->addBook(); case "2"->books.remove(Integer.parseInt(input("Book ID: ")));
                    case "3"->printBooks(books.search(input("Keyword: ")));
                    case "4"->printBooks(books.search(""));
                    case "5"->addMember(); case "6"->listMembers();
                    case "7"->issues.issue(Integer.parseInt(input("Book ID: ")),Integer.parseInt(input("Member ID: ")));
                    case "8"->issues.returnBook(Integer.parseInt(input("Issue ID: ")));
                    case "9"->issues.active(); case "0"->{System.out.println("Goodbye!");return;}
                    default->System.out.println("Invalid choice.");
                }
            }catch(SQLException|NumberFormatException e){System.out.println("Error: "+e.getMessage());}
        }
    }

    static String input(String x){System.out.print(x);return s.nextLine();}
    static void addBook()throws SQLException{
        String t=input("Title: "),a=input("Author: "),c=input("Category: ");
        int q=Integer.parseInt(input("Quantity: ")); if(q<1)throw new NumberFormatException("Quantity must be positive.");
        books.add(new Book(t,a,c,q));System.out.println("Book added.");
    }
    static void printBooks(List<Book> list){
        if(list.isEmpty()){System.out.println("No books found.");return;}
        System.out.printf("%-5s %-25s %-20s %-15s %-7s %-9s%n","ID","TITLE","AUTHOR","CATEGORY","TOTAL","AVAILABLE");
        for(Book b:list)System.out.printf("%-5d %-25s %-20s %-15s %-7d %-9d%n",
            b.getId(),b.getTitle(),b.getAuthor(),b.getCategory(),b.getQuantity(),b.getAvailable());
    }
    static void addMember()throws SQLException{
        members.add(new Member(input("Name: "),input("Email: "),input("Phone: ")));System.out.println("Member added.");
    }
    static void listMembers()throws SQLException{
        System.out.printf("%-5s %-25s %-30s %-15s%n","ID","NAME","EMAIL","PHONE");
        for(Member m:members.all())System.out.printf("%-5d %-25s %-30s %-15s%n",m.getId(),m.getName(),m.getEmail(),m.getPhone());
    }
}
