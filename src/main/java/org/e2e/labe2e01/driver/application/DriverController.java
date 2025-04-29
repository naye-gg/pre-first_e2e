package org.e2e.labe2e01.driver.application;


import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.driver.domain.Driver;
import org.e2e.labe2e01.driver.infrastructure.DriverRepository;
import org.e2e.labe2e01.vehicle.domain.Vehicle;
import org.e2e.labe2e01.vehicle.infrastructure.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverController {
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    /*
        @GetMapping
        public ResponseEntity<List<Driver>> getAllDrivers() {
            List<Driver> drivers = driverRepository.findAll();
            return ResponseEntity.ok(drivers);  // HTTP 200 OK
        }
    */
    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriver(@PathVariable Long id) {
        return ResponseEntity.ok(driverRepository.findById(id).orElseThrow(()->new RuntimeException("No hay un driver con id "+ id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
        Driver savedDriver = driverRepository.save(driver);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDriver);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        Driver driver = driverRepository.findById(id).orElse(null);
        if (driver != null) {
            driverRepository.delete(driver);
            return ResponseEntity.noContent().<Void>build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable Long id, @RequestBody Driver new_driver) {
        Driver existingDriver = driverRepository.findById(id).orElse(null);
        if (existingDriver != null) {
            existingDriver.setFirstName(new_driver.getFirstName());
            existingDriver.setLastName(new_driver.getLastName());
            existingDriver.setEmail(new_driver.getEmail());
            existingDriver.setPhoneNumber(new_driver.getPhoneNumber());
            return ResponseEntity.ok(driverRepository.save(existingDriver));
        }

        return ResponseEntity.ok(null);
    }

    @PatchMapping("/{id}/location")
    public ResponseEntity<Driver> updateLocation(@PathVariable Long id, @RequestParam Double latitude, @RequestParam Double longitude) {

        Driver driver = driverRepository.findById(id).orElse(null);

        if (driver == null) {
            return ResponseEntity.notFound().build();
        }

        Coordinate coordinate = driver.getCoordinate();
        if (coordinate == null) {
            coordinate = new Coordinate();
            driver.setCoordinate(coordinate);
        }

        coordinate.setLatitude(latitude);
        coordinate.setLongitude(longitude);

        Driver updatedDriver = driverRepository.save(driver);
        return ResponseEntity.ok(updatedDriver);
    }


    @PatchMapping("/{id}/car")
    public ResponseEntity<Driver> updateCar(@PathVariable Long id, @RequestBody Vehicle new_vehicle) {

        Driver driver = driverRepository.findById(id).orElse(null);

        if (driver == null) {
            return ResponseEntity.notFound().build();
        }

        Vehicle updatedVehicle = new_vehicle;

        if (updatedVehicle != null) {
            updatedVehicle = new Vehicle();
            driver.setVehicle(updatedVehicle);
        }

        updatedVehicle.setBrand(new_vehicle.getBrand());
        updatedVehicle.setModel(new_vehicle.getModel());
        updatedVehicle.setFabricationYear(new_vehicle.getFabricationYear());
        updatedVehicle.setCapacity(new_vehicle.getCapacity());
        updatedVehicle.setLicensePlate(new_vehicle.getLicensePlate());

        Vehicle savedVehicle = vehicleRepository.save(updatedVehicle);
        driver.setVehicle(savedVehicle);


        Driver updatedDriver = driverRepository.save(driver);

        return ResponseEntity.ok(updatedDriver);
    }




}
