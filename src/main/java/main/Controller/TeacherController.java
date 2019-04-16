package main.Controller;

import main.Model.EmployeeModel;
import main.Model.GeneralMessage;
import main.Model.TeacherModel;
import main.Services.EmployeeService;
import main.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    private TeacherService service;

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public List<TeacherModel> getAllEmployees() {
        return service.getTeachers();
    }

    @RequestMapping(value = "/teachers/{id}", method = RequestMethod.GET)
    public TeacherModel getEmployeesDetails(@PathVariable int id) {
        return service.getTeachersDetail(id);
    }

    @RequestMapping(value = "/teachers/{id}", method = RequestMethod.DELETE)
    public GeneralMessage deleteEmployee(@PathVariable int id) {
        return service.deleteTeachers(id);
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.POST)
    public GeneralMessage postEmployee(@RequestBody TeacherModel model) {
        return service.addTeachers(model);
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.PUT)
    public GeneralMessage upadteEmployee(@RequestBody TeacherModel model) {
        return service.updateTeachersValues(model);
//    }
    }

}
