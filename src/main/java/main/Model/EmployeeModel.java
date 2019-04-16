package main.Model;

public class EmployeeModel {

    int id;
    String name;
    String department;
    String email;
    String city;

    public EmployeeModel() {
    }

    public EmployeeModel(int id, String name, String department, String email, String city) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.email = email;
        this.city = city;
    }

    public EmployeeModel(String name, String department, String email, String city) {
        this.name = name;
        this.department = department;
        this.email = email;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
