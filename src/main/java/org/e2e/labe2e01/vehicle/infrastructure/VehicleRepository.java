package org.e2e.labe2e01.vehicle.infrastructure;


import org.e2e.labe2e01.vehicle.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    List<Vehicle> findByBrandInAndFabricationYearGreaterThanEqual(List<String> brands, Integer year);
    boolean existsByLicensePlate(String licensePlate);

}
