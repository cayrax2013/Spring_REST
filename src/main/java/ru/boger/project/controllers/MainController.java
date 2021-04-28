package ru.boger.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.boger.project.entity.Employee;
import ru.boger.project.exeption_handling.NoSuchEmployeeException;
import ru.boger.project.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null)
            throw new NoSuchEmployeeException("There is no employee with ID = in Data Base");
        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null)
            throw new NoSuchEmployeeException("There s no employee with id " + id);
        employeeService.deleteEmployee(id);
        return "Employee = " + id + " was deleted";
    }
}
