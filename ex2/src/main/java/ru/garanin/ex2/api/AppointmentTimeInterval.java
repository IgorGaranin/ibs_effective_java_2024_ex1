package ru.garanin.ex2.api;

import java.time.LocalDateTime;
import java.util.Calendar;

public record AppointmentTimeInterval(
        LocalDateTime startTimeInterval,
        LocalDateTime endTimeInterval,
        int DurationsInMinutes,
        Calendar calendarDate
) {
}

