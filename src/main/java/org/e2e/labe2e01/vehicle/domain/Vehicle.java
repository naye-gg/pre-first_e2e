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

    @JoinColumn(nullable = false)
    private Integer capacity;

    @JoinColumn(name = "fabrication_year", nullable = false)
    private Integer FabricationYear;

    @JoinColumn(nullable = false)
    private String brand;

    @JoinColumn(name = "license_plate", nullable = false)
    @Column(unique = true)
    private String LicensePlate;

    @JoinColumn(nullable = false)
    private String Model;

    @OneToOne
    @JoinColumn(name = "driver_id")
    @JsonBackReference
    private Driver driver;
}
