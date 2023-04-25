package io.arrogantprogrammer.domain;

public record AttendeeInfoValueObject(
        String email,
        String firstName,
        String lastName,
        boolean student,
        boolean impactedByLayoffs,
        TShirtSize tShirtSize,
        MealPreference mealPreference
) {
}
