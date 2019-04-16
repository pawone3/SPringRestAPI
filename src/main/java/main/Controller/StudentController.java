package main.Controller;

import main.Model.GeneralMessage;
import main.Model.Student;
import main.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    @RequestMapping(value = "/students",method = RequestMethod.GET)
    public List<Student> getAllStudents(){
        return service.getStudents();
    }

    @RequestMapping(value = "/students/{id}",method = RequestMethod.GET)
    public Student getDetails(@PathVariable int id){
        return service.getDetail(id);
    }

    @RequestMapping(value = "/students/{id}",method = RequestMethod.DELETE)
    public GeneralMessage deleteDetails(@PathVariable int id){
        return service.getDeleteUser(id);
    }

    @RequestMapping(value = "/students",method = RequestMethod.POST)
    public GeneralMessage postStudent(@RequestBody Student student){
        return service.addStudent(student);
    }

    @RequestMapping(value = "/students",method = RequestMethod.PUT)
    public GeneralMessage upadteStudent(@RequestBody Student student){
        return service.updateStudentValues(student);
    }
}
