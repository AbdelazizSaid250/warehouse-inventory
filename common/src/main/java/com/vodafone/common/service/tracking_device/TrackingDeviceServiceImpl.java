package com.vodafone.common.service.tracking_device;

import com.vodafone.common.dao.TrackingDeviceRepository;
import com.vodafone.common.model.entity.TrackingDevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class TrackingDeviceServiceImpl implements TrackingDeviceService {

    private final TrackingDeviceRepository trackingDeviceRepository;

    // It's not mandatory to set autowired annotation above the constructor.
    @Autowired
    public TrackingDeviceServiceImpl(TrackingDeviceRepository trackingDeviceRepository) {
        this.trackingDeviceRepository = trackingDeviceRepository;
    }

    @Override
    public void saveOrUpdate(TrackingDevice trackingDevice) {
        trackingDeviceRepository.save(trackingDevice);
        log.info("Done inserted the tracking device into the DB!! + {}", trackingDevice);
    }

    @Override
    public void remove(TrackingDevice trackingDevice) {
        trackingDeviceRepository.delete(trackingDevice);
        log.info("Done deleted the tracking device from the DB!! + {}", trackingDevice);

    }

    @Override
    public TrackingDevice findByID(UUID uuid) {
        Optional<TrackingDevice> optionalTrackingDevice = trackingDeviceRepository.findById(uuid);
        log.info("Found the Tracking Device from the database.");
        return optionalTrackingDevice.orElse(null);
    }

    @Override
    public List<TrackingDevice> findAll() {
        return trackingDeviceRepository.findAll();
    }

    @Override
    public List<TrackingDevice> listAvailableDevicesForSale() {
        return trackingDeviceRepository.listAvailableDevicesForSale();
    }

}
