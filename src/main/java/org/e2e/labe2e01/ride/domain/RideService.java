package org.e2e.labe2e01.ride.domain;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.ride.domain.Ride;
import org.e2e.labe2e01.ride.infrastructure.RideRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RideService {

    private final RideRepository rideRepository;

    public Ride createRide(Ride ride) {
        ride.setStatus(Status.REQUESTED);
        return rideRepository.save(ride);
    }

    public Ride updateRideStatus(Long rideId, Status status) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));
        ride.setStatus(status);
        return rideRepository.save(ride);
    }

    public Page<Ride> getRidesByUser(Long userId, Pageable pageable) {
        return rideRepository.findByPassengerIdOrDriverId(userId, userId, pageable);
    }
}