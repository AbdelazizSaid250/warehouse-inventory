package com.vodafone.common.dao;

import com.vodafone.common.model.entity.TrackingDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(path = "tracking_device")
public interface TrackingDeviceRepository extends JpaRepository<TrackingDevice, UUID> {
    @Query(value = "SELECT * FROM tracking_device WHERE is_sell = false", nativeQuery = true)
    List<TrackingDevice> listAvailableDevicesForSale();
}
