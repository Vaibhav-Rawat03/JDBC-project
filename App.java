import java.sql.*;
import java.util.Scanner;

interface A{
static void view(){};
static void insert(){};
static void update(){};
static void display(){};
}

public class App implements A {
    static Connection con;
    static Statement stml;
    App() throws SQLException
    {
        con=DriverManager.getConnection("jdbc:mysql://localhost:3307/oops","root","eGrwmL@1");
        stml=con.createStatement();
    }
    static void view() throws Exception
    {
        ResultSet rs=stml.executeQuery("select * from student;");
        System.out.println("Details -:");
        while(rs.next())
        {
            System.out.print(rs.getInt(1)+" ");
            System.out.print(rs.getString(2)+" ");
            System.out.print(rs.getInt(3)+" ");
            System.out.print(rs.getString(4)+" ");
            System.out.println(rs.getFloat(5));
        }
    }
    static void insert() throws Exception
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter SAP ID ->");
        int sap=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name ->");
        String name=sc.nextLine();
        System.out.print("Enter Age ->");
        int age=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Stream ->");
        String stream=sc.nextLine();
        System.out.print("Enter Percentage ->");
        float percentage=sc.nextFloat();
        sc.nextLine();
        String query="insert into student values("+sap+",'"+name+"',"+age+",'"+stream+"',"+percentage+");";
        stml.executeUpdate(query);
        System.out.println("Value Inserted...");
    }
    static void delete() throws Exception
    {
        System.out.print("Enter SAP ID to delete ->");
        Scanner sc=new Scanner(System.in);
        int sap=sc.nextInt();
        sc.nextLine();
        stml.executeUpdate("delete from student where `id`="+sap+";");
        System.out.println("Command Executed...");
    }
    static void update() throws Exception
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter SAP ID ->");
        int sap=sc.nextInt();
        System.out.println("Enter new Percentage ->");
        float percentage=sc.nextFloat();
        sc.nextLine();
        String query="update student set Percentage="+percentage+" where `id`="+sap+";";
        stml.executeUpdate(query);
        System.out.println("Updated Successfully...");
    }
}
    class Main extends App{
        Main() throws SQLException{

        }
    public static void main(String[] args) throws Exception {
        while(true)
        {
            Main obj=new Main();
            Scanner sc=new Scanner(System.in);
            try{ 
                System.out.println("******* Main Menu **********");
                System.out.println("1. Insert");
                System.out.println("2. Delete");
                System.out.println("3. Update percentage");
                System.out.println("4. Display");
                System.out.println("5.Exit");
                System.out.print("Enter your choice ->");
                int choice=sc.nextInt();
                sc.nextLine();
                switch(choice)
                {
                    case 1:
                        insert();
                        break;
                    case 2:
                        delete();
                        break;
                    case 3:
                        update();
                        break;
                    case 4:
                        view();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid Choice... Try Again...");
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                System.out.println("Connectivity Error...");
                break;
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            System.out.println("Enter any key to continue...");
            sc.nextLine();
        }
    }
}

