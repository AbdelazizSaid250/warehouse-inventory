package com.vodafone.common.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tracking_device")
public class TrackingDevice implements Serializable {

    @Id
    @Getter
    @Setter
    @Column(name = "id", nullable = false)
    private UUID id;

    @Getter
    @Setter
    @Column(name = "public_code", nullable = false, unique = true)
    private int publicCode;

    @Getter
    @Setter
    @Column(name = "temperature", nullable = false)
    private int temperature;

    @Getter
    @Setter
    @ToString.Include
    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Getter
    @Setter
    @ToString.Include
    @Column(name = "is_sell", nullable = false)
    private boolean isSell;

    public TrackingDevice(UUID id) {
        this.id = id;
    }

    public TrackingDevice() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackingDevice that = (TrackingDevice) o;
        return publicCode == that.publicCode
                && temperature == that.temperature
                && isActive == that.isActive
                && isSell == that.isSell
                && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publicCode, temperature, isActive, isSell);
    }
}
