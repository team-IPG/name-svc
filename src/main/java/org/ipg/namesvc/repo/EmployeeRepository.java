package org.ipg.namesvc.repo;

import com.yugabyte.data.jdbc.repository.YsqlRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends YsqlRepository<Employee, String> {

}
