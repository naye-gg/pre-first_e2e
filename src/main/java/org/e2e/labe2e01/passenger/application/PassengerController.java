package org.e2e.labe2e01.passenger.application;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.passenger.domain.Passenger;
import org.e2e.labe2e01.passenger.domain.PassengerService;
import org.e2e.labe2e01.passenger.infrastructure.PassengerRepository;
import org.e2e.labe2e01.user.domain.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/passenger")
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;
    private final PassengerRepository passengerRepository;

    @GetMapping ("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long id) {
        Passenger passenger =  passengerRepository.findById(id).orElse(null);
        if (passenger==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(passenger);
    }

    /*
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Passenger savePassenger(@RequestBody Passenger passenger) {
        passenger.setRole(Role.PASSENGER); // Asignar rol automáticamente
        return passengerService.savePassenger(passenger);
    }

     */

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deletePassengerById(@PathVariable Long id) {
        Passenger passenger = passengerRepository.findById(id).orElse(null);
        if (passenger == null) {
            return ResponseEntity.notFound().build();
        }
        passengerRepository.delete(passenger);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> updatePassenger(
            @PathVariable Long id,
            @RequestBody Passenger passenger) {
        Passenger updatedPassenger = passengerService.updatePassenger(id, passenger);
        return ResponseEntity.ok(updatedPassenger);
    }

    @GetMapping("/{id}/places")
    public ResponseEntity<List<Coordinate>> getPassengerPlaces(@PathVariable Long id) {
        Passenger passenger = passengerRepository.findById(id).orElse(null);
        if (passenger == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(passenger.getPlacesList());
    }

    /*
    @PostMapping("/{id}/places")
    public ResponseEntity<Passenger> addPlace(
            @PathVariable Long id,
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam String description) {
        Passenger passenger = passengerService.addPlace(id, latitude, longitude, description);
        return ResponseEntity.ok(passenger);
    }

     */

    @DeleteMapping("/{id}/places/{coordinateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlace(
            @PathVariable Long id,
            @PathVariable Long coordinateId) {
        passengerService.removePlace(id, coordinateId);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Passenger> patchPassenger(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        Passenger updatedPassenger = passengerService.patchPassenger(id, updates);
        return ResponseEntity.ok(updatedPassenger);
    }
}