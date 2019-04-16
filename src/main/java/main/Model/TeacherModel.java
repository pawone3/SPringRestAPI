package main.Model;

public class TeacherModel {

    int id;
    String name;
    String institute;
    String subject;
    String email;

    public TeacherModel(int id, String name, String institute, String subject, String email) {
        this.id = id;
        this.name = name;
        this.institute = institute;
        this.subject = subject;
        this.email = email;
    }

    public TeacherModel(String name, String institute, String subject, String email) {
        this.name = name;
        this.institute = institute;
        this.subject = subject;
        this.email = email;
    }

    public TeacherModel() {
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

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
