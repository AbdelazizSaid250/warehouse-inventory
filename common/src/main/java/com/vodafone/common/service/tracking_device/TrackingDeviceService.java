package com.vodafone.common.service.tracking_device;

import com.vodafone.common.model.entity.TrackingDevice;

import java.util.List;
import java.util.UUID;

// Note: All APIs may be applied by generic.
public interface TrackingDeviceService {
    void saveOrUpdate(TrackingDevice trackingDevice);

    void remove(TrackingDevice trackingDevice);

    TrackingDevice findByID(UUID uuid);

    List<TrackingDevice> findAll();

    List<TrackingDevice> listAvailableDevicesForSale();
}
