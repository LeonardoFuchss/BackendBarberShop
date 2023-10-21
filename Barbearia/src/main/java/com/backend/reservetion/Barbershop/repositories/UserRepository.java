package com.backend.reservetion.Barbershop.repositories;

import com.backend.reservetion.Barbershop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
