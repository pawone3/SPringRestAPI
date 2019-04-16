package main.Model;

public class Student {

    Integer rollno;
    Integer id;
    String name;
    String branch;
    String email;

    public Student() {
    }

    public Student(Integer rollno, Integer id, String name, String branch, String email) {
        this.rollno = rollno;
        this.id = id;
        this.name = name;
        this.branch = branch;
        this.email = email;
    }

    public Integer getRollno() {
        return rollno;
    }

    public void setRollno(Integer rollno) {
        this.rollno = rollno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
