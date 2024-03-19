package ru.garanin.ex2.main;

import ru.garanin.ex2.api.AppointmentTimeInterval;
import ru.garanin.ex2.api.Specialist;
import ru.garanin.ex2.api.SpecialistWorkSchedule;
import ru.garanin.ex2.api.Specializations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class App {

    public static void main(String[] args) {

        //данные ввода
        String nameDoctor = "Dr. House";
        String titleSpecialization = "Доктор";
        int year = 2024;
        int month = 3;
        int dayOfMonth = 19;
        int hourStart = 8;
        int minuteStart = 0;
        int hourEnd = 16;
        int minuteEnd = 30;
        int appointmentDurationInMinutes = 30;
        //

        Specialist doctor = new Specialist(nameDoctor, Specializations.fromValue(titleSpecialization));

        Calendar calendarDate = new GregorianCalendar(year, month, dayOfMonth);
        LocalDateTime startTimeOfWork = LocalDateTime.of(year, month, dayOfMonth, hourStart, minuteStart);
        LocalDateTime endTimeOfWork = LocalDateTime.of(year, month, dayOfMonth, hourEnd, minuteEnd);

        SpecialistWorkSchedule specialistWorkSchedule = SpecialistWorkSchedule.createSpecialistWorkSchedule(doctor);

        specialistWorkSchedule.setWorkingHours(
                startTimeOfWork,
                endTimeOfWork,
                appointmentDurationInMinutes,
                calendarDate
        );

        // данные ввода
        int requestYear = 2024;
        int requestMonth = 3;
        int requestDayOfMonth = 19;
        int requestHourStart = 8;
        int requestMinuteStart = 0;
        int requestHourEnd = 11;
        int requestMinuteEnd = 10;
        //

        Calendar requestCalendarDate = new GregorianCalendar(requestYear, requestMonth, requestDayOfMonth);
        LocalDateTime requestStartTime = LocalDateTime.of(requestYear, requestMonth, requestDayOfMonth, requestHourStart, requestMinuteStart);
        LocalDateTime requestEndTime = LocalDateTime.of(requestYear, requestMonth, requestDayOfMonth, requestHourEnd, requestMinuteEnd);

        List<AppointmentTimeInterval> appointmentTimeIntervals =
                specialistWorkSchedule.getAvailableSlots(requestStartTime, requestEndTime, requestCalendarDate);

        System.out.println("Доступные интервалы приёма специалиста " + doctor.toString() + " на дату " + calendarDate.getTime());
        for (AppointmentTimeInterval appointmentTimeInterval : appointmentTimeIntervals) {
            System.out.println("- с "
                    + appointmentTimeInterval.startTimeInterval()
                    + " по "
                    + appointmentTimeInterval.endTimeInterval());
        }
    }

}
