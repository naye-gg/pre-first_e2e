package org.e2e.labe2e01.user.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.driver.domain.Driver;
import org.e2e.labe2e01.passenger.domain.Passenger;

import java.time.ZonedDateTime;


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
@NoArgsConstructor
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Passenger.class, name = "passenger"),
        @JsonSubTypes.Type(value = Driver.class, name = "driver")
})
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@JoinColumn (name = "average_rating", nullable = false)
    private double averageRating;

    //@JoinColumn (nullable = false)
    private Role role;

    //@JoinColumn ( nullable = false)
    private Integer trips = 0;

    //@JoinColumn (name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    //@JoinColumn (name = "updated_at")
    private ZonedDateTime updatedAt;

    //@Column (unique = true)
    private String email;

    //@JoinColumn (name = "first_name", nullable = false)
    private String firstName;

    //@JoinColumn (name = "last_name", nullable = false)
    private String lastName;

   //@JoinColumn (name = "password", nullable = false)
    private String password;

    //@Column (unique = true)
    //@JoinColumn (name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinate_id")
    private Coordinate coordinate;




    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewsAsAuthor = new ArrayList<>();

    @OneToMany(mappedBy = "target", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewsAsTarget = new ArrayList<>();

}


/*
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

    @JoinColumn (name = "average_rating", nullable = false)
    private double averageRating = 0.0;

    @JoinColumn (nullable = false)
    private Role role;

    @JoinColumn ( nullable = false)
    private Integer trips = 0;

    @JoinColumn (name = "created_at", nullable = false)
    private ZonedDateTime CreatedAt;

    @JoinColumn (name = "updated_at")
    private ZonedDateTime UpdatedAt;

    @Column (unique = true)
    private String email;

    @JoinColumn (name = "first_name", nullable = false)
    private String FirstName;

    @JoinColumn (name = "last_name", nullable = false)
    private String LastName;

    @JoinColumn (name = "password", nullable = false)
    private String Password;

    @Column (unique = true)
    @JoinColumn (name = "phone_number", nullable = false)
    private String PhoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinate_id")
    private Coordinate coordinate;




    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewsAsAuthor = new ArrayList<>();

    @OneToMany(mappedBy = "target", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewsAsTarget = new ArrayList<>();

}
 */