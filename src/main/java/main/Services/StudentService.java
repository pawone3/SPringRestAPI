package main.Services;

import main.Model.GeneralMessage;
import main.Model.Student;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class  StudentService {

   private Connection con;

   public ArrayList<Student> getStudents(){
        return studentsQuery();
    }

    public Student getDetail(int id){
       return queryDetail(id);
    }

    public GeneralMessage getDeleteUser(int id) {
       return deleteStudent(id);
    }

    public GeneralMessage addStudent(Student student) {
       return addnewStudent(student);
    }

    public GeneralMessage updateStudentValues(Student student){
       return updateStudent(student);
    }

    private GeneralMessage updateStudent(Student student){

       String query="UPDATE Students SET fullname = '"+ student.getName() +"', rollno = '"+student.getRollno()+"', branch = '"+student.getBranch()+"', email = '"+student.getEmail()+"' WHERE id = "+student.getId();

       GeneralMessage obj;
        try
        {
            if(con==null)
                createConnection();

            Statement nstmt = con.createStatement();

            int x = nstmt.executeUpdate(query);
            if (x > 0){
                obj=new GeneralMessage("Student Updated");
            } else{
                obj=new GeneralMessage("Error Occured");
            }
        }
        catch(Exception e)
        {
            obj=new GeneralMessage("Errror Occured");
            System.out.println(e);
        }

        return obj;

    }

    private GeneralMessage addnewStudent(Student student){

       GeneralMessage result;
       String str="INSERT into Students (fullname,rollno,branch,email) Values ('"+student.getName()+"','"+student.getRollno()+"','"+student.getBranch()+"','"+student.getEmail()+"')";

        try
        {
            if(con==null)
                createConnection();

            Statement stmt = con.createStatement();

            int x = stmt.executeUpdate(str);
            if (x > 0){
                result=new GeneralMessage("Student Added");
            } else{
                result=new GeneralMessage("Error Occured");
            }
        }
        catch(Exception e)
        {
            result=new GeneralMessage("Errror Occured");
            System.out.println(e);
        }

        return result;
    }

    private GeneralMessage deleteStudent(int id){

        GeneralMessage msg;

       String str="DELETE from Students WHERE id='"+id+"'";

        try
        {
            if(con==null)
                createConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(str);

            msg=new GeneralMessage("Student Deleted");

        }
        catch(Exception e)
        {
            msg=new GeneralMessage("Error Occured");
            System.out.println(e);
        }

        return msg;

    }

    private Student queryDetail(int id){

       Student result=new Student();
       String query="SELECT * FROM Students WHERE id = '"+id+"'";

        try
        {
            if(con==null)
                createConnection();
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                Integer id2 = rs.getInt("id");
                String fullname = rs.getString("fullname");
                String branch = rs.getString("branch");
                Integer rollno = rs.getInt("rollno");
                String email = rs.getString("email");

                result = new Student(rollno,id2,fullname,branch,email);

            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return result;

    }

   private ArrayList<Student> studentsQuery(){

       String query="SELECT * FROM Students";
       ArrayList<Student> result=new ArrayList<>();

       try
       {
           createConnection();
           Statement stmt = con.createStatement();

           ResultSet rs = stmt.executeQuery(query);
           while (rs.next())
           {
               Integer id = rs.getInt("id");
               String fullname = rs.getString("fullname");
               String branch = rs.getString("branch");
               Integer rollno = rs.getInt("rollno");
               String email = rs.getString("email");

               result.add(new Student(rollno,id,fullname,branch,email));

           }

       }
       catch(Exception e)
       {
           System.out.println(e);
       }

       return result;
   }

   void createConnection(){

       try {
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection(
                   "jdbc:mysql://localhost:3306/ApiDataBase", "root", "");
       } catch (Exception e) {
           e.printStackTrace();
       }


   }


}
