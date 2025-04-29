package org.e2e.labe2e01.review.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.ride.domain.Ride;
import org.e2e.labe2e01.user.domain.User;

import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private User target;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ride_id", nullable = false, unique = true)
    private Ride ride_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User user;


    public void setRide(Ride ride) {
        this.ride_id = ride;
        if (ride != null) {
            List<Review> reviews = ride.getReviews();
            if (!reviews.contains(this)) {
                reviews.add(this);
            }
        }
    }

    public Ride getRide() {
        return ride_id;
    }
}

