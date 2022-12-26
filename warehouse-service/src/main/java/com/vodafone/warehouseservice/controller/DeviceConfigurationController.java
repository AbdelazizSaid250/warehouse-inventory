package com.vodafone.warehouseservice.controller;

import com.vodafone.common.inmemory.CacheRepository;
import com.vodafone.common.model.dto.AddDeviceDTO;
import com.vodafone.common.model.dto.UpdateDeviceDTO;
import com.vodafone.common.model.entity.DeviceCredential;
import com.vodafone.common.model.entity.TrackingDevice;
import com.vodafone.common.service.device_credential.DeviceCredentialService;
import com.vodafone.common.service.tracking_device.TrackingDeviceService;
import com.vodafone.common.util.Util;
import com.vodafone.common.util.Util.DeviceState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("dcs")
public class DeviceConfigurationController {

    private final TrackingDeviceService trackingDeviceService;

    private final DeviceCredentialService deviceCredentialService;

    @Autowired
    public DeviceConfigurationController(
            TrackingDeviceService trackingDeviceService,
            DeviceCredentialService deviceCredentialService
    ) {
        this.trackingDeviceService = trackingDeviceService;
        this.deviceCredentialService = deviceCredentialService;
        log.info("Successfully Injected the Services into the DeviceConfigurationController!!");
    }


    @PostMapping("addTrackingDevice")
    void addDevice(@RequestBody AddDeviceDTO addDeviceDTO) {
        TrackingDevice trackingDevice = new TrackingDevice(UUID.randomUUID());
        trackingDevice.setPublicCode(addDeviceDTO.getPublicCode());
        // it's not the best practice to create enum here, but I just did it to show it for you.
        trackingDevice.setActive(DeviceState.READY.state());
        trackingDevice.setTemperature(1);
        trackingDevice.setSell(false);

        DeviceCredential deviceCredential = new DeviceCredential(UUID.randomUUID());
        deviceCredential.setTrackingDeviceID(trackingDevice.getId());
        deviceCredential.setSecretCode(addDeviceDTO.getSecretCode());

        // save the initialized objects into the database.
        trackingDeviceService.saveOrUpdate(trackingDevice);
        deviceCredentialService.saveOrUpdate(deviceCredential);

        // add the saved objects into the cache layer.
        CacheRepository.addUpdateTrackingDevice(trackingDevice);
        CacheRepository.addUpdateDeviceCredential(deviceCredential);

        log.info("Successfully Added the Device into our repository.");
    }


    @PatchMapping("updateTrackingDevice")
    void updateDevice(@RequestBody UpdateDeviceDTO updateDeviceDTO) {

        TrackingDevice foundTrackingDevice = trackingDeviceService.findByID(updateDeviceDTO.getDeviceID());
        foundTrackingDevice.setActive(updateDeviceDTO.isActive());
        foundTrackingDevice.setTemperature(updateDeviceDTO.getTemperature());
        foundTrackingDevice.setSell(updateDeviceDTO.isSell());

        DeviceCredential foundDeviceCredential = deviceCredentialService.findByForeignKey(updateDeviceDTO.getDeviceID());
        foundDeviceCredential.setSecretCode(updateDeviceDTO.getSecretCode());

        // update the modified objects into the database.
        trackingDeviceService.saveOrUpdate(foundTrackingDevice);
        deviceCredentialService.saveOrUpdate(foundDeviceCredential);

        // Update the Objects into the Cache layer.
        CacheRepository.addUpdateTrackingDevice(foundTrackingDevice);
        CacheRepository.addUpdateDeviceCredential(foundDeviceCredential);

        log.info("Successfully Updated the Device.");
    }

    /**
     * Note that Sending the deviceID in the header of the request is not a secured, and it's better to
     * be sent inside the JWT. I used this way to let you see that I can use all form of requests like PathVariable.
     * <p>
     * Also, I prefer not to physically delete the objects from the database, but I prefer to add additional column named
     * archived with type boolean to set it with true when the object is deleted and false when the object is not deleted.
     * This approach will increase the performance rather than the physical delete.
     * */
    @DeleteMapping("deleteTrackingDevice/{deviceID}")
    void deleteDevice(@PathVariable UUID deviceID) {

        // Fetch the Tracking Device from the Cache Repository to increase the performance.
        // instead of fetch the object from the database.
        TrackingDevice foundTrackingDevice = CacheRepository.getTrackingDeviceMap().get(deviceID);

        // I created this method to prove that I can create a custom query using Spring from the database.
        DeviceCredential foundDeviceCredential = deviceCredentialService.findByForeignKey(deviceID);

        // remove the found objects into the database.
        deviceCredentialService.remove(foundDeviceCredential);
        trackingDeviceService.remove(foundTrackingDevice);

        // remove the found objects into the Cache Repository.
        CacheRepository.removeDeviceCredential(foundDeviceCredential.getId());
        CacheRepository.removeTrackingDevice(foundTrackingDevice.getId());

        log.info("Successfully Deleted the Device.");
    }


    @PatchMapping("activateTrackingDevice/{deviceID}")
    void activateDevice(@PathVariable UUID deviceID) throws NoSuchAlgorithmException {

        // Find the Tracking Device from the cache layer to increase the performance.
        TrackingDevice foundTrackingDevice = CacheRepository.getTrackingDeviceMap().get(deviceID);
        foundTrackingDevice.setActive(true);
        foundTrackingDevice.setTemperature(Util.generateRandomNumber(10));

        // Update the modified object into the database.
        trackingDeviceService.saveOrUpdate(foundTrackingDevice);

        // Update the modified object into the cache layer.
        CacheRepository.addUpdateTrackingDevice(foundTrackingDevice);

        log.info("Successfully Activated the Device.");
    }


    /**
     * Sales person only who is responsible to use this API because he is the only has the permission to sell the device
     * */
    @PatchMapping("sellTrackingDevice/{deviceID}")
    void sellDevice(@PathVariable UUID deviceID) {

        // Find the Tracking Device from the cache layer to increase the performance.
        TrackingDevice foundTrackingDevice = CacheRepository.getTrackingDeviceMap().get(deviceID);
        foundTrackingDevice.setSell(true);

        // Update the modified object into the database.
        trackingDeviceService.saveOrUpdate(foundTrackingDevice);

        // Update the modified object into the cache layer.
        CacheRepository.addUpdateTrackingDevice(foundTrackingDevice);

        log.info("Successfully Sell the Device.");
    }

}
