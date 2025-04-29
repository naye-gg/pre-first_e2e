package org.e2e.labe2e01.vehicle.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.e2e.labe2e01.driver.domain.Driver;

@Data
@Entity
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"licensePlate"})
})
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @JoinColumn(nullable = false)
    private int capacity;

    // @JoinColumn(name = "fabrication_year", nullable = false)
    private int fabricationYear;

    // @JoinColumn(nullable = false)
    private String brand;

    // @JoinColumn(name = "license_plate", nullable = false)
    @Column(unique = true)
    private String licensePlate;

    //@JoinColumn(nullable = false)
    private String model;

    /*@OneToOne
    @JoinColumn(name = "driver_id")
    @JsonBackReference
    private Driver driver;

     */
}
