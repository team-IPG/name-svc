package org.ipg.namesvc.svc;

import org.ipg.common.EmployeeDTO;
import org.ipg.namesvc.repo.Employee;
import org.ipg.namesvc.repo.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class EmployeeLoadingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeLoadingService.class);

    private final EmployeeRepository nameRepo;

    private final ParsingService parsingService;

    public EmployeeLoadingService(ParsingService parsingService,
                                  EmployeeRepository nameRepo) {
        this.nameRepo = nameRepo;
        this.parsingService = parsingService;
    }

    public void processNamesFile(MultipartFile file) {
        // parse contents
        List<EmployeeDTO> nameRecords = parsingService.parse(file);

        // create or update Employee records in DB
        List<Employee> employees = nameRecords
                .stream()
                .map(this::createOrUpdateEmployee)
                .collect(toList());

        // publish events
        //TODO:

    }

    /**
     * Given a NameRecord, create or update the Employee in the Database
     *
     * @param name data from CSV file
     * @return persisted Employee
     */
    private Employee createOrUpdateEmployee(EmployeeDTO name) {
        Optional<Employee> existingEmployee = nameRepo.findById(name.id());
        Employee employee = existingEmployee.orElse( new Employee(name.id(),true, LocalDateTime.now()) );
        employee.setFirstName(name.firstName());
        employee.setLastName((name.lastName()));
        employee.setPreferredName(name.preferredName());
        employee.setPreferredSpeed(name.preferredSpeed());
        employee.setPreferredPreset(name.preferredPreset());
        employee.setVoiceLink("TBD");
        employee.setUpdated(LocalDateTime.now());
        LOGGER.info("Saving {} employee record = {}",
                existingEmployee.isPresent() ? "existing" : "new", employee);
        return nameRepo.save(employee);

    }


}
