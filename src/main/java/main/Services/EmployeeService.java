package main.Services;

import main.Model.EmployeeModel;
import main.Model.GeneralMessage;
import main.Model.Student;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Service
public class EmployeeService {

    private Connection con;

    public ArrayList<EmployeeModel> getEmployees(){
        return employeeQuery();
    }

    public EmployeeModel getEmployeeDetail(int id){
        return queryDetail(id);
    }

    public GeneralMessage deleteEmployee(int id) {
        return deleteEmployeeQuery(id);
    }

    public GeneralMessage addEmployee(EmployeeModel model) {
        return addnewEmployee(model);
    }

    public GeneralMessage updateEmployeeValues(EmployeeModel model){
        return updateEmployee(model);
    }

    private GeneralMessage updateEmployee(EmployeeModel model){

        String query="UPDATE Employee SET fullname = '"+ model.getName() +"', department = '"+model.getDepartment()+"', city = '"+model.getCity()+"', email = '"+model.getEmail()+"' WHERE id = "+model.getId();

        GeneralMessage obj;
        try
        {
            if(con==null)
                createConnection();

            Statement nstmt = con.createStatement();

            int x = nstmt.executeUpdate(query);
            if (x > 0){
                obj=new GeneralMessage("Employee Updated");
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

    private GeneralMessage addnewEmployee(EmployeeModel model){

        GeneralMessage result;
        String str="INSERT into Employee (fullname,department,city,email) Values ('"+model.getName()+"','"+model.getDepartment()+"','"+model.getCity()+"','"+model.getEmail()+"')";

        try
        {
            if(con==null)
                createConnection();

            Statement stmt = con.createStatement();

            int x = stmt.executeUpdate(str);
            if (x > 0){
                result=new GeneralMessage("Employee Added");
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

    private GeneralMessage deleteEmployeeQuery(int id){

        GeneralMessage msg;

        String str="DELETE from Employee WHERE id='"+id+"'";

        try
        {
            if(con==null)
                createConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(str);

            msg=new GeneralMessage("Employee Deleted");

        }
        catch(Exception e)
        {
            msg=new GeneralMessage("Error Occured");
            System.out.println(e);
        }

        return msg;

    }

    private EmployeeModel queryDetail(int id){

        EmployeeModel result=new EmployeeModel();
        String query="SELECT * FROM Employee WHERE id = '"+id+"'";

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
                String deprtment = rs.getString("department");
                String city= rs.getString("city");
                String email = rs.getString("email");

                result = new EmployeeModel(id2,fullname,deprtment,email,city);

            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return result;

    }

    private ArrayList<EmployeeModel> employeeQuery(){

        String query="SELECT * FROM Employee";
        ArrayList<EmployeeModel> result=new ArrayList<>();

        try
        {
            createConnection();
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                Integer id = rs.getInt("id");
                String fullname = rs.getString("fullname");
                String department = rs.getString("department");
                String city = rs.getString("city");
                String email = rs.getString("email");

                result.add(new EmployeeModel(id,fullname,department,email,city));

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
