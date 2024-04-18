//Prepared Statement

import java.sql.*;
import java.util.Scanner;


public class Main {
    private static final  String url ="jdbc:mysql://localhost:3306/jdbcp1";
    private static final String username = "root";
    private static final String password = "12345";
   // static String query = "select * from emp where name = ? and job_title=?";//reteriving data using prepared statement
    static String query = "Insert into emp values(?,?,?,?);";//Inserting data using prepared statement


    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successfully!!");

            PreparedStatement ps = con.prepareStatement(query);
            Scanner sc = new Scanner(System.in);

            int id = sc.nextInt();
            sc.nextLine();
            String name = sc.nextLine();
            String job_title = sc.nextLine();
            Double salary = sc.nextDouble();

            ps.setInt(1,id);
            ps.setString(2, name);
            ps.setString(3,job_title);
            ps.setDouble(4,salary);

            int rowAffected = ps.executeUpdate();

            if(rowAffected > 0 ){
                System.out.println(rowAffected+" row(S) effected");
            }else{
                System.out.println("row(s) not affected."+"\n give valid query");
            }


           /*reteriving data
            ps.setString(1, "Rajesh");
            ps.setString(2,"SD");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                //| id | name   | job_title | salary
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String job_title = rs.getString("job_title");
                double salary = rs.getDouble("salary");

                System.out.println(" Employee Id:" +id+ "\n Employee name:" +name+ "\n Employee Job Title:"
                                    +job_title+ "\n Employee salary:" +salary);
            }*/

            con.close();
            ps.close();
            sc.close();
           // rs.close();
            System.out.println("connection closed!!");

        }catch (SQLException e){
            System.out.println(e.getMessage());

        }
    }
}