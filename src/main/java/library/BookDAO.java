package library;
import java.sql.*;
import java.util.*;

public class BookDAO {
    public void add(Book b)throws SQLException{
        String q="INSERT INTO books(title,author,category,quantity,available_quantity) VALUES(?,?,?,?,?)";
        try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(q)){
            p.setString(1,b.getTitle());p.setString(2,b.getAuthor());p.setString(3,b.getCategory());
            p.setInt(4,b.getQuantity());p.setInt(5,b.getQuantity());p.executeUpdate();
        }
    }
    public void remove(int id)throws SQLException{
        try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement("DELETE FROM books WHERE book_id=?")){
            p.setInt(1,id);System.out.println(p.executeUpdate()>0?"Book removed.":"Book not found.");
        }
    }
    public List<Book> search(String key)throws SQLException{
        List<Book> out=new ArrayList<>();
        String q="SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR category LIKE ? ORDER BY book_id";
        try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(q)){
            String k="%"+key+"%";p.setString(1,k);p.setString(2,k);p.setString(3,k);
            try(ResultSet r=p.executeQuery()){while(r.next())out.add(new Book(
                r.getInt("book_id"),r.getString("title"),r.getString("author"),r.getString("category"),
                r.getInt("quantity"),r.getInt("available_quantity")));}}
        return out;
    }
}
