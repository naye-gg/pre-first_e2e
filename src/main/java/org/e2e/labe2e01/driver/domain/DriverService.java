package org.e2e.labe2e01.driver.domain;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.coordinate.infrastructure.CoordinateRepository;
import org.e2e.labe2e01.driver.infrastructure.DriverRepository;
import org.e2e.labe2e01.vehicle.domain.Vehicle;
import org.e2e.labe2e01.vehicle.infrastructure.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class DriverService {
    private final DriverRepository driverRepository;
    private final CoordinateRepository coordinateRepository;
    private final VehicleRepository vehicleRepository;


    public Driver getDriverById(Long id) {
        return driverRepository.findById(id).
                orElseThrow(()->new RuntimeException("Driver not found with id: "+ id));
    }


    public Driver saveDriver(Driver driver) {
        // Validar campos requeridos
        if (driver.getEmail() == null || driver.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        // Manejar vehículo
        if (driver.getVehicle() != null) {
            Vehicle vehicle = driver.getVehicle();
            if (vehicle.getLicensePlate() == null) {
                throw new IllegalArgumentException("Vehicle license plate is required");
            }
            driver.setVehicle(vehicleRepository.save(vehicle));
        }

        // Manejar coordenadas
        if (driver.getCoordinate() != null) {
            driver.setCoordinate(coordinateRepository.save(driver.getCoordinate()));
        }



        return driverRepository.save(driver);
    }

    public void deleteDriver(Long id) {
        Driver driver = getDriverById(id); // Esto ya lanza excepción si no existe

        // Eliminar referencias primero si es necesario
        driver.setVehicle(null);
        driver.setCoordinate(null);

        driverRepository.delete(driver);
    }

    public Driver updateDriver(Long id, Driver driverDetails) {
        Driver existingDriver = getDriverById(id);

        // Actualizar solo campos permitidos
        existingDriver.setFirstName(driverDetails.getFirstName());
        existingDriver.setLastName(driverDetails.getLastName());
        existingDriver.setPhoneNumber(driverDetails.getPhoneNumber());
        existingDriver.setCategory(driverDetails.getCategory());

        return driverRepository.save(existingDriver);
    }

    public Driver updateDriverLocation(Long id, Double latitude, Double longitude) {
        Driver driver = getDriverById(id);

        if (driver.getCoordinate() == null) {
            Coordinate newCoordinate = new Coordinate();
            newCoordinate.setLatitude(latitude);
            newCoordinate.setLongitude(longitude);
            driver.setCoordinate(newCoordinate);
        } else {
            driver.getCoordinate().setLatitude(latitude);
            driver.getCoordinate().setLongitude(longitude);
        }

        return driverRepository.save(driver);
    }

    public Driver updateDriverCar(Long id, Vehicle newVehicle) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + id));

        Vehicle savedVehicle = vehicleRepository.save(newVehicle);
        driver.setVehicle(savedVehicle);

        return driverRepository.save(driver);
    }


}
