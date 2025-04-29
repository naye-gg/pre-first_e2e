package org.e2e.labe2e01.ride.domain;

public enum Status {
    REQUESTED,  // Estado inicial cuando se crea un ride
    ACCEPTED,   // Cuando el conductor acepta el viaje
    IN_PROGRESS,// Cuando el viaje está en curso
    COMPLETED,  // Cuando el viaje finaliza correctamente
    CANCELED    // Cuando el viaje es cancelado
}
