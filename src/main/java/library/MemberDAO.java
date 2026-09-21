package library;
import java.sql.*;
import java.util.*;

public class MemberDAO {
    public void add(Member m)throws SQLException{
        String q="INSERT INTO members(name,email,phone) VALUES(?,?,?)";
        try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(q)){
            p.setString(1,m.getName());p.setString(2,m.getEmail());p.setString(3,m.getPhone());p.executeUpdate();
        }
    }
    public List<Member> all()throws SQLException{
        List<Member> out=new ArrayList<>();
        try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM members");ResultSet r=p.executeQuery()){
            while(r.next())out.add(new Member(r.getInt("member_id"),r.getString("name"),r.getString("email"),r.getString("phone")));
        }
        return out;
    }
}
