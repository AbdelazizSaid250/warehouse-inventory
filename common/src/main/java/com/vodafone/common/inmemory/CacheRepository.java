package com.vodafone.common.inmemory;

import com.vodafone.common.model.entity.DeviceCredential;
import com.vodafone.common.model.entity.TrackingDevice;
import com.vodafone.common.service.device_credential.DeviceCredentialService;
import com.vodafone.common.service.tracking_device.TrackingDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Component
public class CacheRepository {

    private final DeviceCredentialService deviceCredentialService;
    private final TrackingDeviceService trackingDeviceService;

    @Autowired
    private CacheRepository(
            DeviceCredentialService deviceCredentialService,
            TrackingDeviceService trackingDeviceService
    ) {
        log.info("I am in the CacheRepository Constructor!!");
        this.deviceCredentialService = deviceCredentialService;
        this.trackingDeviceService = trackingDeviceService;
    }

    private static final Map<UUID, TrackingDevice> TRACKING_DEVICE_MAP = new HashMap<>();

    private static final Map<UUID, DeviceCredential> DEVICE_CREDENTIAL_MAP = new HashMap<>();

    public static void addUpdateTrackingDevice(TrackingDevice trackingDevice) {
        TRACKING_DEVICE_MAP.put(trackingDevice.getId(), trackingDevice);
    }

    public static void addUpdateDeviceCredential(DeviceCredential deviceCredential) {
        DEVICE_CREDENTIAL_MAP.put(deviceCredential.getId(), deviceCredential);
    }

    public static void removeTrackingDevice(UUID trackingDeviceID) {
        TRACKING_DEVICE_MAP.remove(trackingDeviceID);
    }

    public static void removeDeviceCredential(UUID deviceCredentialID) {
        DEVICE_CREDENTIAL_MAP.remove(deviceCredentialID);
    }

    public static Map<UUID, TrackingDevice> getTrackingDeviceMap() {
        return TRACKING_DEVICE_MAP;
    }

    private void loadTrackingDeviceMap() {
        TRACKING_DEVICE_MAP.clear();

        List<TrackingDevice> allTrackingDevices = trackingDeviceService.findAll();
        for (TrackingDevice trackingDevice : allTrackingDevices) {
            addUpdateTrackingDevice(trackingDevice);
        }
    }

    private void loadDeviceCredentialMap() {
        DEVICE_CREDENTIAL_MAP.clear();

        List<DeviceCredential> allDeviceCredentials = deviceCredentialService.findAll();
        for (DeviceCredential deviceCredential : allDeviceCredentials) {
            addUpdateDeviceCredential(deviceCredential);
        }
    }

    @PostConstruct
    public void init() {
        loadTrackingDeviceMap();
        loadDeviceCredentialMap();
    }
}