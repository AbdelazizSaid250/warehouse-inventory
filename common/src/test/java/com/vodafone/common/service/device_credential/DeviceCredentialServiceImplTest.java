package com.vodafone.common.service.device_credential;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.vodafone.common.dao.DeviceCredentialRepository;
import com.vodafone.common.model.entity.DeviceCredential;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeviceCredentialServiceImplTest {
    @Mock
    private DeviceCredentialRepository deviceCredentialRepository;

    @InjectMocks
    private DeviceCredentialServiceImpl deviceCredentialServiceImpl;

    @Test
    void testSaveOrUpdate() {
        DeviceCredential deviceCredential = new DeviceCredential();
        deviceCredential.setId(UUID.randomUUID());
        deviceCredential.setSecretCode("Secret Code");
        deviceCredential.setTrackingDeviceID(UUID.randomUUID());
        when(deviceCredentialRepository.save(any())).thenReturn(deviceCredential);

        DeviceCredential deviceCredential1 = new DeviceCredential();
        deviceCredential1.setId(UUID.randomUUID());
        deviceCredential1.setSecretCode("Secret Code");
        deviceCredential1.setTrackingDeviceID(UUID.randomUUID());
        deviceCredentialServiceImpl.saveOrUpdate(deviceCredential1);
        verify(deviceCredentialRepository).save(any());
    }


    @Test
    void testRemove() {
        doNothing().when(deviceCredentialRepository).delete(any());

        DeviceCredential deviceCredential = new DeviceCredential();
        deviceCredential.setId(UUID.randomUUID());
        deviceCredential.setSecretCode("Secret Code");
        deviceCredential.setTrackingDeviceID(UUID.randomUUID());
        deviceCredentialServiceImpl.remove(deviceCredential);
        verify(deviceCredentialRepository).delete(any());
    }


    @Test
    void testFindByID() {
        DeviceCredential deviceCredential = new DeviceCredential();
        deviceCredential.setId(UUID.randomUUID());
        deviceCredential.setSecretCode("Secret Code");
        deviceCredential.setTrackingDeviceID(UUID.randomUUID());
        Optional<DeviceCredential> ofResult = Optional.of(deviceCredential);

        when(deviceCredentialRepository.findById(any())).thenReturn(ofResult);
        assertSame(deviceCredential, deviceCredentialServiceImpl.findByID(UUID.randomUUID()));
        verify(deviceCredentialRepository).findById(any());
    }


    @Test
    void testFindByForeignKey() {
        DeviceCredential deviceCredential = new DeviceCredential();
        deviceCredential.setId(UUID.randomUUID());
        deviceCredential.setSecretCode("Secret Code");
        deviceCredential.setTrackingDeviceID(UUID.randomUUID());
        when(deviceCredentialRepository.findByForeignKey(any())).thenReturn(deviceCredential);
        assertSame(deviceCredential, deviceCredentialServiceImpl.findByForeignKey(UUID.randomUUID()));
        verify(deviceCredentialRepository).findByForeignKey(any());
    }


    @Test
    void testFindAll() {
        ArrayList<DeviceCredential> deviceCredentialList = new ArrayList<>();
        when(deviceCredentialRepository.findAll()).thenReturn(deviceCredentialList);
        List<DeviceCredential> actualFindAllResult = deviceCredentialServiceImpl.findAll();
        assertSame(deviceCredentialList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(deviceCredentialRepository).findAll();
    }
}

