package ru.garanin.ex2.api;

public record Specialist(
        String name,
        Specializations specialization
) {

    @Override
    public String toString() {
        return specialization.getTitle() + " " + name;

    }
}
