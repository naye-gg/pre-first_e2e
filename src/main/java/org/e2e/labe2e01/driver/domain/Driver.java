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

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id", unique = true, nullable = false)
    private Vehicle vehicle;
}
