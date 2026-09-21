package library;

public class Member {
    private int id;
    private String name,email,phone;
    public Member(String name,String email,String phone){
        this.name=name;this.email=email;this.phone=phone;
    }
    public Member(int id,String name,String email,String phone){
        this.id=id;this.name=name;this.email=email;this.phone=phone;
    }
    public int getId(){return id;}
    public String getName(){return name;}
    public String getEmail(){return email;}
    public String getPhone(){return phone;}
}
