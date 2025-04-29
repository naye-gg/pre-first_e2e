package org.e2e.labe2e01.userLocations.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.passenger.domain.Passenger;

@Data
@Entity
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class UserLocation {

    @EmbeddedId
    private PassengerCoordinateId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("passengerId")
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("coordinateId")
    @JoinColumn(name = "coordinate_id", nullable = false)
    private Coordinate coordinate;

    @Column(length = 500) // Aumentar longitud para descripciones largas
    private String description;

    public UserLocation(Passenger passenger, Coordinate coordinate, String description) {
        this.passenger = passenger;
        this.coordinate = coordinate;
        this.description = description;
        this.id = new PassengerCoordinateId(passenger.getId(), coordinate.getId());
    }

    // Método para sincronizar ambas partes de la relación
    public void synchronizeRelations() {
        if (this.passenger != null) {
            this.passenger.getPlaces().add(this);
        }
        if (this.coordinate != null) {
            this.coordinate.getPassengers().add(this);
        }
    }
}