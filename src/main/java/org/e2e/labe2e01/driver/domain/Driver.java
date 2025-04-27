package org.e2e.labe2e01.driver.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.e2e.labe2e01.user.domain.User;
import org.e2e.labe2e01.vehicle.domain.Vehicle;

@Entity
@Data
@NoArgsConstructor
public class Driver extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Category category;


    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL)
    @JoinColumn(name="vehicle_id")
    @JsonManagedReference
    private Vehicle vehicle;

}
