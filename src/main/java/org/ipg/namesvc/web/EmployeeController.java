package org.ipg.namesvc.web;

import org.ipg.common.EmployeeDTO;
import org.ipg.namesvc.repo.Employee;
import org.ipg.namesvc.repo.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.StreamSupport;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employee/{id}")
    public EmployeeDTO getEmployee(@PathVariable String id) {
        return from(getEmployeeByIdOrThrow404(id));
    }

    @GetMapping("/employee/{id}/status/{active}")
    public void updateEmployeeStatus(@PathVariable String id, @PathVariable Boolean active) {
        LOGGER.info("Updating employee with id={}, status={}", id, active);
        // Update employee status and update date
        Employee employee = getEmployeeByIdOrThrow404(id);
        employee.setActive(active);
        employee.setUpdated(LocalDateTime.now());
        repository.save(employee);
    }

    @PostMapping("/employeePreferences/{id}")
    public EmployeeDTO updateEmployeePreferences(
            @PathVariable String id,
            @RequestBody EmployeeDTO employeeDTO) {
        LOGGER.info("Procedding preference update params={}", employeeDTO);
        Employee employee = getEmployeeByIdOrThrow404(id);
        Employee updatedEmployee = updatePreferences(employeeDTO, employee);
        updatedEmployee.setUpdated(LocalDateTime.now());
        Employee persistedEmployee = repository.save(updatedEmployee);
        return from(persistedEmployee);
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

    private Employee getEmployeeByIdOrThrow404(String id) {
        Optional<Employee> employee = repository.findById(id);
        if (employee.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find employee with id=" + id);
        }
        return employee.get();
    }

    private EmployeeDTO from(Employee emp) {
        EmployeeDTO dto = new EmployeeDTO(
                emp.getId(),
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
