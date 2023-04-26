package io.arrogantprogrammer.domain;

public record AddressValueObject(
        String streetAddress,
        String streetAddress2,
        String city,
        String stateOrProvince,
        String postalCode,
        String countryCode
) {
}
