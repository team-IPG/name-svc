package org.ipg.namesvc.repo;

import com.yugabyte.data.jdbc.repository.YsqlRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameYsqlRepository extends YsqlRepository<Employee, String> {

}
