package org.e2e.labe2e01.passenger.application;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.passenger.domain.Passenger;
import org.e2e.labe2e01.passenger.infrastructure.PassengerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/passenger")
@RestController
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerRepository passengerRepository;

    @GetMapping ("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long id) {
        Passenger passenger =  passengerRepository.findById(id).orElse(null);
        if (passenger==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(passenger);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePassengerById(@PathVariable Long id) {
        Passenger passenger = passengerRepository.findById(id).orElse(null);
        if (passenger == null) {
            return ResponseEntity.notFound().build();
        }
        passengerRepository.delete(passenger);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity updatePassenger(@PathVariable Long id, @RequestParam String description, @RequestBody Coordinate new_coordinate) {
        Passenger passenger = passengerRepository.findById(id).orElse(null);
        if (passenger == null) {
            return ResponseEntity.notFound().build();
        }

        if (description != null) {
            passenger.setDescription(description);
        }

        Coordinate existingCoordinate = passenger.getCoordinate();
        if (existingCoordinate == null) {
            passenger.setCoordinate(new_coordinate);
        } else{
            if (Objects.nonNull(new_coordinate.getLatitude())) {
                existingCoordinate.setLatitude(new_coordinate.getLatitude());
            }
            if (Objects.nonNull(new_coordinate.getLongitude())) {
                existingCoordinate.setLongitude(new_coordinate.getLongitude());
            }
        }

        Passenger updatedPassenger = passengerRepository.save(passenger);
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

    @DeleteMapping("/{id}/places/{coordinateId}")
    public ResponseEntity<Void> deletePassengerPlace(
            @PathVariable Long id,
            @PathVariable Long coordinateId) {

        Passenger passenger = passengerRepository.findById(id).orElse(null);
        if (passenger == null) {
            return ResponseEntity.notFound().build();
        }

        List<Coordinate> places = passenger.getPlacesList();
        boolean removed = places.removeIf(c -> c.getId().equals(coordinateId));

        if (!removed) {
            return ResponseEntity.notFound().build();
        }

        passengerRepository.save(passenger);
        return ResponseEntity.noContent().build();
    }
}

