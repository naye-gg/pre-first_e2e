package org.e2e.labe2e01.ride.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.driver.domain.Driver;
import org.e2e.labe2e01.passenger.domain.Passenger;

import java.time.ZonedDateTime;

@Data
@Entity
@NoArgsConstructor
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originName;
    private String destinationName;
    private Double price;

    @Enumerated(EnumType.STRING)
    private Status status;

    private ZonedDateTime departureDate;
    private ZonedDateTime arrivalDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_coordinates_id")
    private Coordinate originCoordinates;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_coordinates_id")
    private Coordinate destinationCoordinates;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

}
