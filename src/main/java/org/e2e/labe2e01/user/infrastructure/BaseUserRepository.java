package org.e2e.labe2e01.user.infrastructure;

import org.e2e.labe2e01.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseUserRepository<T extends User, L extends Number> extends JpaRepository<T, Long> {
}
