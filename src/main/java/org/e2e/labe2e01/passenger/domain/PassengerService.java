package org.e2e.labe2e01.passenger.domain;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.passenger.infrastructure.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PassengerService {
    private final PassengerRepository passengerRepository;

    public Passenger getPassengerById(Long id) {
        return passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
    }

    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }

    public Passenger updatePassenger(Long id, Passenger passengerDetails) {
        Passenger passenger = getPassengerById(id);
        // Actualizar campos permitidos
        passenger.setFirstName(passengerDetails.getFirstName());
        passenger.setLastName(passengerDetails.getLastName());
        passenger.setEmail(passengerDetails.getEmail());
        passenger.setPhoneNumber(passengerDetails.getPhoneNumber());
        return passengerRepository.save(passenger);
    }

    public List<Coordinate> getPlacesList(Long passengerId) {
        Passenger passenger = getPassengerById(passengerId);
        return passenger.getPlacesList();
    }

    public Passenger addPlace(Long passengerId, double latitude, double longitude, String description) {
        Passenger passenger = getPassengerById(passengerId);
        Coordinate coordinate = new Coordinate(latitude, longitude);
        passenger.addPlace(coordinate, description);
        return passengerRepository.save(passenger);
    }

    public void removePlace(Long passengerId, Long coordinateId) {
        Passenger passenger = getPassengerById(passengerId);
        passenger.removePlace(coordinateId);
        passengerRepository.save(passenger);
    }

    public Passenger patchPassenger(Long id, Map<String, Object> updates) {
        Passenger passenger = getPassengerById(id);

        // Actualiza solo los campos proporcionados
        if (updates.containsKey("firstName")) {
            passenger.setFirstName((String) updates.get("firstName"));
        }
        if (updates.containsKey("lastName")) {
            passenger.setLastName((String) updates.get("lastName"));
        }
        // ... otros campos

        return passengerRepository.save(passenger);
    }
}