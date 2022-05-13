package org.ipg.namesvc.svc;

import org.ipg.namesvc.dto.NameRecord;
import org.ipg.namesvc.repo.Employee;
import org.ipg.namesvc.repo.NameYsqlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class NameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NameService.class);

    private final NameYsqlRepository nameRepo;

    private final ParsingService parsingService;

    public NameService(ParsingService parsingService,
                       NameYsqlRepository nameRepo) {
        this.nameRepo = nameRepo;
        this.parsingService = parsingService;
    }

    public void processNamesFile(MultipartFile file) {
        // parse contents
        List<NameRecord> nameRecords = parsingService.parse(file);

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
    private Employee createOrUpdateEmployee(NameRecord name) {
        String id = name.firstName() + "-" + name.lastName();
        Optional<Employee> existingEmployee = nameRepo.findById(id);
        Employee employee = existingEmployee.orElse( new Employee(id,true, LocalDateTime.now()) );
        employee.setFirstName(name.firstName());
        employee.setLastName((name.lastName()));
        employee.setPreferredName(name.preferredName());
        employee.setPreferredSpeed(3);
        employee.setPreferredStyle("US");
        employee.setVoiceLink("tbd");
        employee.setUpdated(LocalDateTime.now());
        employee.setActive(true);
        LOGGER.info("Saving {} employee record = {}",
                existingEmployee.isPresent() ? "existing" : "new", employee);
        return nameRepo.save(employee);

    }


}
