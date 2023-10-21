package com.backend.reservetion.Barbershop.repositories;

import com.backend.reservetion.Barbershop.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
