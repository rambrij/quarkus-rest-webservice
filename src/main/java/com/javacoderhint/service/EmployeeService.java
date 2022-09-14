package com.javacoderhint.service;

import com.javacoderhint.model.Employee;
import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class EmployeeService {
    private static Map<String, Employee> employeeMap =new HashMap();

    static
    {
        employeeMap.put("101", new Employee("101", "Ram Brij", "Developer", 34));
        employeeMap.put("102", new Employee("102", "Jack", "Developer", 34));
    }
    public Collection<Employee> findAll()    {
        return employeeMap.values();
    }
    public Employee findEmployeeById(String id){
        return employeeMap.get(id);
    }
    public Employee deleteEmployeeById(String id){
        return employeeMap.remove(id);
    }
    public void saveEmployee(Employee employee) {
        employeeMap.put(employee.getId(), employee);
    }
    public void updateEmployee(Employee employee) {
        employeeMap.put(employee.getId(),employee);
    }
}
