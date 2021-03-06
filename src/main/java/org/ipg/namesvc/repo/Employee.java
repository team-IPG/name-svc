package org.ipg.namesvc.repo;

import org.ipg.common.VoicePreset;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Table("employee_test")
public class Employee {

    public Employee() { }

    public Employee(String id, boolean active, LocalDateTime created) {
        this.id = id;
        this.active = active;
        this.created = created;
    }

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String preferredName;

    // Valid = in specific Enumeration list?
    private VoicePreset preferredPreset;

    // Valid = 1 to 5 ?
    private Double preferredSpeed;

    // URL Reference to object storage location of voice file
    private String voiceLink;

    private boolean active;

    private LocalDateTime created;

    private LocalDateTime updated;

    @Version
    private Integer version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public VoicePreset getPreferredPreset() {
        return preferredPreset;
    }

    public void setPreferredPreset(VoicePreset preferredPreset) {
        this.preferredPreset = preferredPreset;
    }

    public Double getPreferredSpeed() {
        return preferredSpeed;
    }

    public void setPreferredSpeed(Double preferredSpeed) {
        this.preferredSpeed = preferredSpeed;
    }

    public String getVoiceLink() {
        return voiceLink;
    }

    public void setVoiceLink(String voiceLink) {
        this.voiceLink = voiceLink;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
