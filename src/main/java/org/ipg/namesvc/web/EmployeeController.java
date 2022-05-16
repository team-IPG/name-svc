package org.ipg.namesvc.web;

import org.ipg.namesvc.dto.EmployeeDTO;
import org.ipg.namesvc.repo.Employee;
import org.ipg.namesvc.repo.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.StreamSupport;

@RestController
public class EmployeeController {

    EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employee/{id}")
    public EmployeeDTO getEmployee(@PathVariable String id) {
        Optional<Employee> employee = repository.findById(id);
        if (employee.isPresent()) {
            return from(employee.get());
        }
        //TODO: how do we return a 404
        throw new IllegalArgumentException("Employee does not exist with id = " + id);
    }

    @PostMapping("/employeePreferences/{id}")
    public EmployeeDTO updateEmployeePreferences(
            @PathVariable String id,
            @RequestBody EmployeeDTO employeeDTO) {
        Optional<Employee> employee = repository.findById(id);
        if (employee.isEmpty()) {
            throw new IllegalArgumentException("Employee does not exist with id = " + id);
        }
        Employee updatedEmployee = updatePreferences(employeeDTO, employee.get());
        updatedEmployee.setUpdated(LocalDateTime.now());
        repository.save(updatedEmployee);
        return from(updatedEmployee);
    }

    @GetMapping("/employee")
    Collection<EmployeeDTO> getAllEmployees() {
        Iterable<Employee> employees = repository.findAll();
        return StreamSupport.stream(employees.spliterator(), false)
                .map(emp -> this.from(emp))
                .toList();
    }

    @GetMapping("/findEmployee/{searchTerm}")
    Collection<EmployeeDTO> findEmployee(@PathVariable String searchTerm) {
        Set<Employee> employees = new HashSet<>();
        employees.addAll(repository.findByIdStartingWithIgnoreCase(searchTerm));
        employees.addAll(repository.findByFirstNameStartingWithIgnoreCase(searchTerm));
        employees.addAll(repository.findByLastNameStartingWithIgnoreCase(searchTerm));
        return employees.stream()
                .map(emp -> this.from(emp))
                .toList();
    }

    private EmployeeDTO from(Employee emp) {
        EmployeeDTO dto = new EmployeeDTO(
                emp.getFirstName(),
                emp.getLastName(),
                emp.getPreferredName(),
                emp.getPreferredPreset(),
                emp.getPreferredSpeed(),
                emp.isActive());
        return dto;
    }
    
    private Employee updatePreferences(EmployeeDTO dto, Employee employee) {
        employee.setPreferredName(dto.preferredName());
        employee.setPreferredPreset(dto.preferredPreset());
        employee.setPreferredSpeed(dto.preferredSpeed());
        return employee;
    }
}
