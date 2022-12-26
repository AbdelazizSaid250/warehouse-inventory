package com.vodafone.common.inmemory;

import com.vodafone.common.model.entity.DeviceCredential;
import com.vodafone.common.model.entity.TrackingDevice;
import com.vodafone.common.service.device_credential.DeviceCredentialService;
import com.vodafone.common.service.tracking_device.TrackingDeviceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CacheRepositoryTest {
    @InjectMocks
    private CacheRepository cacheRepository;

    @Mock
    private DeviceCredentialService deviceCredentialService;

    @Mock
    private TrackingDeviceService trackingDeviceService;


    @Test
    void testAddUpdateTrackingDevice2() {
        TrackingDevice trackingDevice = mock(TrackingDevice.class);
        when(trackingDevice.getId()).thenReturn(UUID.randomUUID());
        doNothing().when(trackingDevice).setActive(anyBoolean());
        doNothing().when(trackingDevice).setId(any());
        doNothing().when(trackingDevice).setPublicCode(anyInt());
        doNothing().when(trackingDevice).setSell(anyBoolean());
        doNothing().when(trackingDevice).setTemperature(anyInt());
        trackingDevice.setActive(true);
        trackingDevice.setId(UUID.randomUUID());
        trackingDevice.setPublicCode(1);
        trackingDevice.setSell(true);
        trackingDevice.setTemperature(1);
        CacheRepository.addUpdateTrackingDevice(trackingDevice);
        verify(trackingDevice).getId();
        verify(trackingDevice).setActive(anyBoolean());
        verify(trackingDevice).setId(any());
        verify(trackingDevice).setPublicCode(anyInt());
        verify(trackingDevice).setSell(anyBoolean());
        verify(trackingDevice).setTemperature(anyInt());
    }


    @Test
    void testAddUpdateDeviceCredential() {
        DeviceCredential deviceCredential = mock(DeviceCredential.class);
        when(deviceCredential.getId()).thenReturn(UUID.randomUUID());
        doNothing().when(deviceCredential).setId(any());
        doNothing().when(deviceCredential).setSecretCode(any());
        doNothing().when(deviceCredential).setTrackingDeviceID(any());
        deviceCredential.setId(UUID.randomUUID());
        deviceCredential.setSecretCode("Secret Code");
        deviceCredential.setTrackingDeviceID(UUID.randomUUID());
        CacheRepository.addUpdateDeviceCredential(deviceCredential);
        verify(deviceCredential).getId();
        verify(deviceCredential).setId(any());
        verify(deviceCredential).setSecretCode(any());
        verify(deviceCredential).setTrackingDeviceID(any());
    }


    @Test
    void testInit() {
        when(deviceCredentialService.findAll()).thenReturn(new ArrayList<>());
        when(trackingDeviceService.findAll()).thenReturn(new ArrayList<>());
        cacheRepository.init();
        verify(deviceCredentialService).findAll();
        verify(trackingDeviceService).findAll();
    }


    @Test
    void testInit2() {
        DeviceCredential deviceCredential = new DeviceCredential();
        deviceCredential.setId(UUID.randomUUID());
        deviceCredential.setSecretCode("Secret Code");
        deviceCredential.setTrackingDeviceID(UUID.randomUUID());

        ArrayList<DeviceCredential> deviceCredentialList = new ArrayList<>();
        deviceCredentialList.add(deviceCredential);
        when(deviceCredentialService.findAll()).thenReturn(deviceCredentialList);
        when(trackingDeviceService.findAll()).thenReturn(new ArrayList<>());
        cacheRepository.init();
        verify(deviceCredentialService).findAll();
        verify(trackingDeviceService).findAll();
    }


    @Test
    void testInit3() {
        when(deviceCredentialService.findAll()).thenReturn(new ArrayList<>());

        TrackingDevice trackingDevice = new TrackingDevice();
        trackingDevice.setActive(true);
        trackingDevice.setId(UUID.randomUUID());
        trackingDevice.setPublicCode(1);
        trackingDevice.setSell(true);
        trackingDevice.setTemperature(1);

        ArrayList<TrackingDevice> trackingDeviceList = new ArrayList<>();
        trackingDeviceList.add(trackingDevice);
        when(trackingDeviceService.findAll()).thenReturn(trackingDeviceList);
        cacheRepository.init();
        verify(deviceCredentialService).findAll();
        verify(trackingDeviceService).findAll();
    }
}

