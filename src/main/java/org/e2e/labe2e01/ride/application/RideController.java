package org.e2e.labe2e01.ride.application;


import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.ride.domain.Ride;
import org.e2e.labe2e01.ride.domain.Status;
import org.e2e.labe2e01.ride.infrastructure.RideRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ride")
@RequiredArgsConstructor
public class RideController {
    private final RideRepository rideRepository;

    @PostMapping
    public ResponseEntity<Ride> createRide(@RequestBody Ride ride) {
        return ResponseEntity.status(201).body(rideRepository.save(ride));
    }

    @PatchMapping("/{rideId}/assign/{driverId}")
    public ResponseEntity<Ride> assignDriver(
            @PathVariable Long rideId,
            @PathVariable Long driverId) {

        Ride ride = rideRepository.findById(rideId).orElse(new Ride());
        ride.setStatus(Status.ACCEPTED);
        return ResponseEntity.ok(rideRepository.save(ride));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRide(@PathVariable Long id) {
        rideRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{passengerId}")
    public ResponseEntity<Page<Ride>> getRidesByPassenger(
            @PathVariable Long passengerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(rideRepository.findByPassengerId(
                passengerId, PageRequest.of(page, size)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ride> cancelRide(@PathVariable Long id) {
        Ride ride = rideRepository.findById(id).orElse(new Ride());
        ride.setStatus(Status.CANCELED);
        return ResponseEntity.ok(rideRepository.save(ride));
    }
}