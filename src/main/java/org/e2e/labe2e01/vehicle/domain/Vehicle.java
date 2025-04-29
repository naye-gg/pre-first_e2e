package org.e2e.labe2e01.vehicle.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.e2e.labe2e01.driver.domain.Driver;

@Data
@Entity
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer capacity;

    @Column(name = "fabrication_year", nullable = false)
    private Integer fabricationYear;

    @Column(nullable = false)
    private String brand;

    @Column(name = "license_plate", nullable = false, unique = true)
    private String licensePlate;

    @Column(nullable = false)
    private String model;

    @OneToOne(mappedBy = "vehicle")
    private Driver driver;
}
