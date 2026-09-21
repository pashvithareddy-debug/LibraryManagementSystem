package library;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class IssueDAO {
    private static final double FINE=5.0;

    public void issue(int bookId,int memberId)throws SQLException{
        try(Connection c=DatabaseConnection.getConnection()){
            c.setAutoCommit(false);
            try{
                int available;
                try(PreparedStatement p=c.prepareStatement("SELECT available_quantity FROM books WHERE book_id=?")){
                    p.setInt(1,bookId);try(ResultSet r=p.executeQuery()){
                        if(!r.next())throw new SQLException("Book not found.");available=r.getInt(1);
                    }
                }
                if(available<1)throw new SQLException("Book is not available.");
                LocalDate d=LocalDate.now();
                try(PreparedStatement p=c.prepareStatement("INSERT INTO issues(book_id,member_id,issue_date,due_date) VALUES(?,?,?,?)");
                    PreparedStatement u=c.prepareStatement("UPDATE books SET available_quantity=available_quantity-1 WHERE book_id=?")){
                    p.setInt(1,bookId);p.setInt(2,memberId);p.setDate(3,Date.valueOf(d));p.setDate(4,Date.valueOf(d.plusDays(14)));p.executeUpdate();
                    u.setInt(1,bookId);u.executeUpdate();
                }
                c.commit();System.out.println("Issued successfully. Due: "+d.plusDays(14));
            }catch(SQLException e){c.rollback();throw e;}finally{c.setAutoCommit(true);}
        }
    }

    public void returnBook(int issueId)throws SQLException{
        try(Connection c=DatabaseConnection.getConnection()){
            c.setAutoCommit(false);
            try{
                int bookId;Date due;String status;
                try(PreparedStatement p=c.prepareStatement("SELECT book_id,due_date,status FROM issues WHERE issue_id=?")){
                    p.setInt(1,issueId);try(ResultSet r=p.executeQuery()){
                        if(!r.next())throw new SQLException("Issue not found.");
                        bookId=r.getInt(1);due=r.getDate(2);status=r.getString(3);
                    }
                }
                if("RETURNED".equals(status))throw new SQLException("Already returned.");
                LocalDate today=LocalDate.now();
                long late=Math.max(0,ChronoUnit.DAYS.between(due.toLocalDate(),today));
                double fine=late*FINE;
                try(PreparedStatement p=c.prepareStatement("UPDATE issues SET return_date=?,status='RETURNED',fine=? WHERE issue_id=?");
                    PreparedStatement u=c.prepareStatement("UPDATE books SET available_quantity=available_quantity+1 WHERE book_id=?")){
                    p.setDate(1,Date.valueOf(today));p.setDouble(2,fine);p.setInt(3,issueId);p.executeUpdate();
                    u.setInt(1,bookId);u.executeUpdate();
                }
                c.commit();System.out.printf("Returned. Fine: Rs. %.2f%n",fine);
            }catch(SQLException e){c.rollback();throw e;}finally{c.setAutoCommit(true);}
        }
    }

    public void active()throws SQLException{
        String q="SELECT i.issue_id,b.title,m.name,i.issue_date,i.due_date FROM issues i "+
                 "JOIN books b ON i.book_id=b.book_id JOIN members m ON i.member_id=m.member_id "+
                 "WHERE i.status='ISSUED' ORDER BY i.issue_id";
        try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(q);ResultSet r=p.executeQuery()){
            System.out.printf("%-5s %-25s %-20s %-12s %-12s%n","ID","BOOK","MEMBER","ISSUED","DUE");
            while(r.next())System.out.printf("%-5d %-25s %-20s %-12s %-12s%n",
                r.getInt(1),r.getString(2),r.getString(3),r.getDate(4),r.getDate(5));
        }
    }
}
