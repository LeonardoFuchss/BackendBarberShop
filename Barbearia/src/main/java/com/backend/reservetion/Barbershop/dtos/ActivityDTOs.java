package com.backend.reservetion.Barbershop.dtos;

import com.backend.reservetion.Barbershop.domain.ActivityType;
import com.backend.reservetion.Barbershop.domain.User;

import java.time.LocalDateTime;

public record ActivityDTOs(LocalDateTime dateTime, ActivityType activityType, User user) {
}
