package org.e2e.labe2e01.user.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.review.domain.Review;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "first_name", nullable = false)
    private String firstName;

    @Column (name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column (nullable = false)
    private String password;

    @Column (name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column (name = "avg_rating")
    private double avgRating = 0.0;

    @Column (nullable = false)
    private Role role;

    private Integer trips = 0;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinate_id")
    private Coordinate coordinate;

    @Column (name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column (name = "updated_at")
    private ZonedDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

}