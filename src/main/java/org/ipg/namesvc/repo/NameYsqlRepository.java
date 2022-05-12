package org.ipg.namesvc.repo;

import com.yugabyte.data.jdbc.repository.YsqlRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameYsqlRepository extends YsqlRepository<String, String> {
    String findByName(final String name);

    //TODO: This is just a dummy repo, we'll make this real
}
