package org.ipg.namesvc.repo;

import com.yugabyte.data.jdbc.repository.YsqlRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends YsqlRepository<Employee, String> {

    // See: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation

    List<Employee> findByIdStartingWithIgnoreCase(String id);
    List<Employee> findByLastNameStartingWithIgnoreCase(String lastName);
    List<Employee> findByFirstNameStartingWithIgnoreCase(String firstName);

}
