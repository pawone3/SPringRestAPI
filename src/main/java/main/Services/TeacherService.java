package main.Services;

import main.Model.EmployeeModel;
import main.Model.GeneralMessage;
import main.Model.TeacherModel;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Service
public class TeacherService {

    private Connection con;

    public ArrayList<TeacherModel> getTeachers(){
        return teachersQuery();
    }

    public TeacherModel getTeachersDetail(int id){
        return queryDetail(id);
    }

    public GeneralMessage deleteTeachers(int id) {
        return deleteTeachersQuery(id);
    }

    public GeneralMessage addTeachers(TeacherModel model) {
        return addnewTeachers(model);
    }

    public GeneralMessage updateTeachersValues(TeacherModel model){
        return updateTeachers(model);
    }

    private GeneralMessage updateTeachers(TeacherModel model){

        String query="UPDATE Teacher SET fullname = '"+ model.getName() +"', institute = '"+model.getInstitute()+"', subject = '"+model.getSubject()+"', email = '"+model.getEmail()+"' WHERE id = "+model.getId();

        GeneralMessage obj;
        try
        {
            if(con==null)
                createConnection();

            Statement nstmt = con.createStatement();

            int x = nstmt.executeUpdate(query);
            if (x > 0){
                obj=new GeneralMessage("Teacher Updated");
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

    private GeneralMessage addnewTeachers(TeacherModel model){

        GeneralMessage result;
        String str="INSERT into Teacher (fullname,institute,subject,email) Values ('"+model.getName()+"','"+model.getInstitute()+"','"+model.getSubject()+"','"+model.getEmail()+"')";

        try
        {
            if(con==null)
                createConnection();

            Statement stmt = con.createStatement();

            int x = stmt.executeUpdate(str);
            if (x > 0){
                result=new GeneralMessage("Teacher Added");
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

    private GeneralMessage deleteTeachersQuery(int id){

        GeneralMessage msg;

        String str="DELETE from Teacher WHERE id='"+id+"'";

        try
        {
            if(con==null)
                createConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(str);

            msg=new GeneralMessage("Teacher Deleted");

        }
        catch(Exception e)
        {
            msg=new GeneralMessage("Error Occured");
            System.out.println(e);
        }

        return msg;

    }

    private TeacherModel queryDetail(int id){

        TeacherModel result=new TeacherModel();
        String query="SELECT * FROM Teacher WHERE id = '"+id+"'";

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
                String institute = rs.getString("institute");
                String subject= rs.getString("subject");
                String email = rs.getString("email");

                result = new TeacherModel(id2,fullname,institute,subject,email);

            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return result;

    }

    private ArrayList<TeacherModel> teachersQuery(){

        String query="SELECT * FROM Teacher";
        ArrayList<TeacherModel> result=new ArrayList<>();

        try
        {
            createConnection();
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                Integer id2 = rs.getInt("id");
                String fullname = rs.getString("fullname");
                String institute = rs.getString("institute");
                String subject= rs.getString("subject");
                String email = rs.getString("email");

                result.add(new TeacherModel(id2,fullname,institute,subject,email));

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
