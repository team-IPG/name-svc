package org.ipg.namesvc.dto;

public record EmployeeDTO(
        String firstName,
        String lastName,
        String preferredName,
        String preferredPreset,
        Double preferredSpeed,
        boolean active) {

    public EmployeeDTO(String firstName, String lastName, String preferredName) {
        this(firstName, lastName, preferredName, null, null, false);
    }
}