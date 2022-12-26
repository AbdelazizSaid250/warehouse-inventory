package com.vodafone.common.service.tracking_device;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.vodafone.common.dao.TrackingDeviceRepository;
import com.vodafone.common.model.entity.TrackingDevice;

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
class TrackingDeviceServiceImplTest {
    @Mock
    private TrackingDeviceRepository trackingDeviceRepository;

    @InjectMocks
    private TrackingDeviceServiceImpl trackingDeviceServiceImpl;


    @Test
    void testSaveOrUpdate() {
        TrackingDevice trackingDevice = new TrackingDevice();
        trackingDevice.setActive(true);
        trackingDevice.setId(UUID.randomUUID());
        trackingDevice.setPublicCode(1);
        trackingDevice.setSell(true);
        trackingDevice.setTemperature(1);
        when(trackingDeviceRepository.save(any())).thenReturn(trackingDevice);

        TrackingDevice trackingDevice1 = new TrackingDevice();
        trackingDevice1.setActive(true);
        trackingDevice1.setId(UUID.randomUUID());
        trackingDevice1.setPublicCode(1);
        trackingDevice1.setSell(true);
        trackingDevice1.setTemperature(1);
        trackingDeviceServiceImpl.saveOrUpdate(trackingDevice1);
        verify(trackingDeviceRepository).save(any());
    }


    @Test
    void testRemove() {
        doNothing().when(trackingDeviceRepository).delete(any());

        TrackingDevice trackingDevice = new TrackingDevice();
        trackingDevice.setActive(true);
        trackingDevice.setId(UUID.randomUUID());
        trackingDevice.setPublicCode(1);
        trackingDevice.setSell(true);
        trackingDevice.setTemperature(1);
        trackingDeviceServiceImpl.remove(trackingDevice);
        verify(trackingDeviceRepository).delete(any());
    }


    @Test
    void testFindByID() {
        TrackingDevice trackingDevice = new TrackingDevice();
        trackingDevice.setActive(true);
        trackingDevice.setId(UUID.randomUUID());
        trackingDevice.setPublicCode(1);
        trackingDevice.setSell(true);
        trackingDevice.setTemperature(1);
        Optional<TrackingDevice> ofResult = Optional.of(trackingDevice);
        when(trackingDeviceRepository.findById(any())).thenReturn(ofResult);
        assertSame(trackingDevice, trackingDeviceServiceImpl.findByID(UUID.randomUUID()));
        verify(trackingDeviceRepository).findById(any());
    }


    @Test
    void testFindAll() {
        ArrayList<TrackingDevice> trackingDeviceList = new ArrayList<>();
        when(trackingDeviceRepository.findAll()).thenReturn(trackingDeviceList);
        List<TrackingDevice> actualFindAllResult = trackingDeviceServiceImpl.findAll();
        assertSame(trackingDeviceList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(trackingDeviceRepository).findAll();
    }


    @Test
    void testListAvailableDevicesForSale() {
        ArrayList<TrackingDevice> trackingDeviceList = new ArrayList<>();
        when(trackingDeviceRepository.listAvailableDevicesForSale()).thenReturn(trackingDeviceList);
        List<TrackingDevice> actualListAvailableDevicesForSaleResult = trackingDeviceServiceImpl
                .listAvailableDevicesForSale();
        assertSame(trackingDeviceList, actualListAvailableDevicesForSaleResult);
        assertTrue(actualListAvailableDevicesForSaleResult.isEmpty());
        verify(trackingDeviceRepository).listAvailableDevicesForSale();
    }
}

