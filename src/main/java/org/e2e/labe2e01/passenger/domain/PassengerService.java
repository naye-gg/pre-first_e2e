package org.e2e.labe2e01.passenger.domain;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.passenger.domain.Passenger;
import org.e2e.labe2e01.passenger.infrastructure.PassengerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;

    // Obtener un pasajero por ID
    public Optional<Passenger> getPassenger(Long id) {
        return passengerRepository.findById(id);
    }

    // Eliminar un pasajero por ID
    public boolean deletePassenger(Long id) {
        if (passengerRepository.existsById(id)) {
            passengerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Actualizar la descripción y/o coordenada de un pasajero
    public Optional<Passenger> updatePassenger(Long id, String description, Coordinate newCoordinate) {
        return passengerRepository.findById(id)
                .map(passenger -> {
                    if (description != null) {
                        passenger.setDescription(description);
                    }
                    if (newCoordinate != null) {
                        Coordinate existingCoordinate = passenger.getCoordinate();
                        if (existingCoordinate == null) {
                            passenger.setCoordinate(newCoordinate);
                        } else {
                            if (Objects.nonNull(newCoordinate.getLatitude())) {
                                existingCoordinate.setLatitude(newCoordinate.getLatitude());
                            }
                            if (Objects.nonNull(newCoordinate.getLongitude())) {
                                existingCoordinate.setLongitude(newCoordinate.getLongitude());
                            }
                        }
                    }
                    return passengerRepository.save(passenger);
                });
    }

    // Obtener la lista de lugares favoritos de un pasajero
    public Optional<List<Coordinate>> getPassengerPlaces(Long id) {
        return passengerRepository.findById(id)
                .map(Passenger::getPlacesList);
    }

    // Eliminar un lugar favorito de un pasajero
    public boolean deletePassengerPlace(Long passengerId, Long coordinateId) {
        return passengerRepository.findById(passengerId)
                .map(passenger -> {
                    List<Coordinate> places = passenger.getPlacesList();
                    boolean removed = places.removeIf(c -> c.getId().equals(coordinateId));
                    if (removed) {
                        passengerRepository.save(passenger);
                    }
                    return removed;
                })
                .orElse(false);
    }
}
