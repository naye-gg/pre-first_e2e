package org.e2e.labe2e01.vehicle.domain;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.vehicle.infrastructure.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public Vehicle saveVehicle(Vehicle vehicle) {
        if (vehicle.getLicensePlate() == null || vehicle.getLicensePlate().isBlank()) {
            throw new IllegalArgumentException("License plate is required");
        }

        if (vehicleRepository.existsByLicensePlate(vehicle.getLicensePlate())) {
            throw new RuntimeException("Vehicle with this license plate already exists");
        }

        return vehicleRepository.save(vehicle);
    }
}