package org.e2e.labe2e01.ride.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.driver.domain.Driver;
import org.e2e.labe2e01.passenger.domain.Passenger;
import org.e2e.labe2e01.review.domain.Review;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.ORDINAL)
    //@Column(nullable = false)
    private Status status;

    //@Column(name = "arrival_date", nullable = false)
    private ZonedDateTime arrivalDate;

    //@Column(name = "departure_date", nullable = false)
    private ZonedDateTime departureDate;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "destination_coordinates_id", nullable = false)
    private Coordinate destinationCoordinates;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "origin_coordinates_id", nullable = false)
    private Coordinate originCoordinates;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    //@JoinColumn(name = "destination_name", nullable = false)
    private String destinationName;

    //@JoinColumn(name = "origin_name", nullable = false)
    private String originName;


    @OneToMany(mappedBy = "ride", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
}
