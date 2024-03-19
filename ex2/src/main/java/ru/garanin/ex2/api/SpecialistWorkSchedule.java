package ru.garanin.ex2.api;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class SpecialistWorkSchedule {
    private Specialist specialist;
    private List<AppointmentTimeInterval> appointmentTimeIntervals;

    public static SpecialistWorkSchedule createSpecialistWorkSchedule(Specialist specialist) {
        return new SpecialistWorkSchedule(specialist);
    }

    private SpecialistWorkSchedule(Specialist specialist) {

        if (Objects.isNull(specialist)) {
            throw new IllegalArgumentException("Значение аргумента specialist не должно быть равным null");
        }
        this.specialist = specialist;
        this.appointmentTimeIntervals = List.copyOf(new ArrayList<>());
    }

    SpecialistWorkSchedule(Specialist specialist, List<AppointmentTimeInterval> appointmentTimeIntervals) {
        if (Objects.isNull(specialist)) {
            throw new IllegalArgumentException("Значение аргумента specialist не должно быть равным null");
        }
        this.specialist = specialist;

        if (Objects.nonNull(appointmentTimeIntervals) && appointmentTimeIntervals.size() > 0) {
            this.appointmentTimeIntervals = List.copyOf(appointmentTimeIntervals);
        } else
            this.appointmentTimeIntervals = List.copyOf(new ArrayList<>());
    }

    public void setWorkingHours(
            LocalDateTime start,
            LocalDateTime end,
            int appointmentDurationInMinutes,
            Calendar calendarDane) {

        LocalDateTime current = start;
        Duration appointmentDuration = Duration.ofMinutes(appointmentDurationInMinutes);

        List<AppointmentTimeInterval> intervals = new ArrayList<>();

        while (current.plus(appointmentDuration).isBefore(end) || current.plus(appointmentDuration).isEqual(end)) {

            intervals.add(new AppointmentTimeInterval(
                    current,
                    current.plus(appointmentDuration),
                    appointmentDurationInMinutes,
                    calendarDane));

            current = current.plus(appointmentDuration);

        }

        this.appointmentTimeIntervals = List.copyOf(intervals);
    }

    public void addWorkingHours(
            LocalDateTime start,
            LocalDateTime end,
            int appointmentDurationInMinutes,
            Calendar calendarDate) {

        LocalDateTime current = start;
        Duration appointmentDuration = Duration.ofMinutes(appointmentDurationInMinutes);

        List<AppointmentTimeInterval> intervals = new ArrayList<>(this.appointmentTimeIntervals);

        while (current.plus(appointmentDuration).isBefore(end) || current.plus(appointmentDuration).isEqual(end)) {

            //todo добавить проверку на уже существующий интервал приема

            intervals.add(new AppointmentTimeInterval(
                    current,
                    current.plus(appointmentDuration),
                    appointmentDurationInMinutes,
                    calendarDate));

            current = current.plus(appointmentDuration);

        }

        this.appointmentTimeIntervals = List.copyOf(intervals);
    }

    public List<AppointmentTimeInterval> getAvailableSlots(LocalDateTime from, LocalDateTime to, Calendar calendarDate) {

        List<AppointmentTimeInterval> availableSlots = new ArrayList<>();

        for (AppointmentTimeInterval appointmentTimeInterval : appointmentTimeIntervals) {

            if (isAvailableAppointmentTimeIntervals(appointmentTimeInterval, from, to, calendarDate)) {
                availableSlots.add(appointmentTimeInterval);
            }
        }
        return List.copyOf(availableSlots);
    }

    private boolean isAvailableAppointmentTimeIntervals(
            AppointmentTimeInterval appointmentTimeInterval,
            LocalDateTime from,
            LocalDateTime to,
            Calendar calendarDate
    ) {
        return (appointmentTimeInterval.startTimeInterval().isEqual(from)
                || appointmentTimeInterval.startTimeInterval().isAfter(from))
                && (appointmentTimeInterval.endTimeInterval().isEqual(to)
                || appointmentTimeInterval.endTimeInterval().isBefore(to))
                && appointmentTimeInterval.calendarDate().equals(calendarDate);
    }

}
