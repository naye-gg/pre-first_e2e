package org.e2e.labe2e01.vehicle.domain;

import org.e2e.labe2e01.vehicle.infrastructure.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


}
