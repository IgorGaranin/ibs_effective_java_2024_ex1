package ru.garanin.ex2.api;

public enum Specializations {
    DOCTOR("Доктор"),
    BARBER("Парикмахер"),
    CAR_SERVICE_SPECIALIST("Автосервис"),
    NOT_FOUND("Not found");

    private String title;

    Specializations(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public static Specializations fromValue(String title) {
        for (final Specializations specialization : values()) {
            if (specialization.title.equalsIgnoreCase(title)) {
                return specialization;
            }
        }
        return NOT_FOUND;
    }

}
