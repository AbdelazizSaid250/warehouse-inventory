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


/**
 * Note: I see that the device_credential table must be located into a separate database
 * to secure the credential information from hacking if the main database has been hacked.
* */
@Slf4j
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "device_credential")
public class DeviceCredential implements Serializable {

    @Id
    @Getter
    @Setter
    @Column(name = "id", nullable = false)
    private UUID id;

    @Getter
    @Setter
    @Column(name = "tracking_device_id", nullable = false)
    private UUID trackingDeviceID;

    @Getter
    @Setter
    @ToString.Include
    @Column(name = "secret_code", nullable = false)
    private String secretCode;

    public DeviceCredential(UUID id) {
        this.id = id;
    }

    public DeviceCredential() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceCredential that = (DeviceCredential) o;
        return id.equals(that.id) && trackingDeviceID.equals(that.trackingDeviceID) && secretCode.equals(that.secretCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trackingDeviceID, secretCode);
    }
}
