package org.e2e.labe2e01.ride.domain;

import jakarta.persistence.Column;


public enum Status {
    REQUESTED, ACCEPTED, IN_PROGRESS, COMPLETED, CANCELED
}
