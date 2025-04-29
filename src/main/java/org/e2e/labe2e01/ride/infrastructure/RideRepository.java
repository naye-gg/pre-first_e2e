package org.e2e.labe2e01.ride.infrastructure;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.e2e.labe2e01.ride.domain.Ride;
import org.e2e.labe2e01.ride.domain.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride,Long> {

    Page<Ride> findByPassengerIdOrDriverId(Long passengerId, Long driverId, Pageable pageable);
    Page<Ride> findAllByPassengerIdAndStatus(Long passengerId, Status status, Pageable pageable);

    Page<Ride> findByPassengerId(Long passengerId, PageRequest of);
}
