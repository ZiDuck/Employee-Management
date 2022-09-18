package qkit.backend.employeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qkit.backend.employeemanager.exception.UserFoundException;
import qkit.backend.employeemanager.model.Employee;
import qkit.backend.employeemanager.repository.EmployeeRepository;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Add Employee
    public Employee create(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    // Find Employee By Id
    public Employee findById(Long id) {
        return employeeRepository.findEmployeeById(id).orElseThrow(() -> new UserFoundException("User by id " + id + " was not found"));
    }

    // List all employees
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    // Update employee
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Delete Employee
    @Transactional
    public void delete(Long id) {
        employeeRepository.deleteEmployeeById(id);
    }
}
