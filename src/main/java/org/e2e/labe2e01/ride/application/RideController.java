package org.e2e.labe2e01.ride.application;


import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.driver.domain.Driver;
import org.e2e.labe2e01.ride.domain.Ride;
import org.e2e.labe2e01.ride.domain.Status;
import org.e2e.labe2e01.ride.infrastructure.RideRepository;
import org.e2e.labe2e01.driver.infrastructure.DriverRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ride")
@RequiredArgsConstructor
public class RideController {
    private final RideRepository rideRepository;
    private final DriverRepository driverRepository;

    @PostMapping
    public ResponseEntity<Ride> createRide(@RequestBody Ride ride) {
        Ride savedRide = rideRepository.save(ride);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRide);
    }

    @PatchMapping("/{rideId}/assign/{driverId}")
    public ResponseEntity<Ride> assignDriver(
            @PathVariable Long rideId,
            @PathVariable Long driverId) {

        Ride ride = rideRepository.findById(rideId).orElse(null);
        Driver driver = driverRepository.findById(driverId).orElse(null);

        if (ride == null || driver == null) {
            return ResponseEntity.notFound().build();
        }

        ride.setDriver(driver);
        ride.setStatus(Status.ACCEPTED);
        Ride updatedRide = rideRepository.save(ride);

        return ResponseEntity.ok(updatedRide);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRide(@PathVariable Long id) {
        Ride ride = rideRepository.findById(id).orElse(null);
        if (ride == null) {
            return ResponseEntity.notFound().build();
        }
        rideRepository.delete(ride);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{passengerId}")
    public ResponseEntity<Page<Ride>> getRidesByPassenger(
            @PathVariable Long passengerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Ride> rides = rideRepository.findByPassengerId(passengerId, PageRequest.of(page, size));

        return ResponseEntity.ok(rides);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ride> cancelRide(@PathVariable Long id) {
        Ride ride = rideRepository.findById(id).orElse(null);
        if (ride == null) {
            return ResponseEntity.notFound().build();
        }

        ride.setStatus(Status.CANCELED);
        Ride updatedRide = rideRepository.save(ride);

        return ResponseEntity.ok(updatedRide);
    }

}
