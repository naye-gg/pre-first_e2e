package org.e2e.labe2e01.coordinate.infrastructure;

import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.driver.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {
}
