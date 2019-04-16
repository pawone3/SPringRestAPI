package main.Controller;

import main.Model.EmployeeModel;
import main.Model.GeneralMessage;
import main.Model.Student;
import main.Services.EmployeeService;
import main.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public List<EmployeeModel> getAllEmployees() {
        return service.getEmployees();
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public EmployeeModel getEmployeesDetails(@PathVariable int id) {
        return service.getEmployeeDetail(id);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public GeneralMessage deleteEmployee(@PathVariable int id) {
        return service.deleteEmployee(id);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public GeneralMessage postEmployee(@RequestBody EmployeeModel model) {
        return service.addEmployee(model);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.PUT)
    public GeneralMessage upadteEmployee(@RequestBody EmployeeModel model) {
        return service.updateEmployeeValues(model);
//    }
    }
}
