package org.e2e.labe2e01.driver.infrastructure;

import org.e2e.labe2e01.driver.domain.Category;
import org.e2e.labe2e01.driver.domain.Driver;
import org.e2e.labe2e01.user.infrastructure.BaseUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends BaseUserRepository<Driver,Long> {
    List<Driver> findAllByCategory(Category category);



}
