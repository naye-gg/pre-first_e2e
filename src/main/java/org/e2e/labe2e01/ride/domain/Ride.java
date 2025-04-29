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

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Status status;

    @Column(name = "arrival_date", nullable = false)
    private ZonedDateTime ArrivalDate;

    @Column(name = "departure_date", nullable = false)
    private ZonedDateTime DepartureDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_coordinates_id", nullable = false)
    private Coordinate DestinationCoordinates;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_coordinates_id", nullable = false)
    private Coordinate OriginCoordinates;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver Driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger Passenger;

    @Column(name = "destination_name", nullable = false)
    private String DestinationName;

    @Column(name = "origin_name", nullable = false)
    private String OriginName;


    @OneToMany(mappedBy = "ride", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

}
