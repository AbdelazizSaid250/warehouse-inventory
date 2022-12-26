package com.vodafone.warehouseservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodafone.common.model.dto.AddDeviceDTO;
import com.vodafone.common.model.dto.UpdateDeviceDTO;
import com.vodafone.common.model.entity.DeviceCredential;
import com.vodafone.common.model.entity.TrackingDevice;
import com.vodafone.common.service.device_credential.DeviceCredentialService;
import com.vodafone.common.service.tracking_device.TrackingDeviceService;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {DeviceConfigurationController.class})
@ExtendWith(SpringExtension.class)
class DeviceConfigurationControllerTest {
    @Autowired
    private DeviceConfigurationController deviceConfigurationController;

    @MockBean
    private DeviceCredentialService deviceCredentialService;

    @MockBean
    private TrackingDeviceService trackingDeviceService;


    @Test
    void testActivateDevice() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/dcs/activateTrackingDevice/{deviceID}", "Uri Variables", "Uri Variables");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(deviceConfigurationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }


    @Test
    void testAddDevice() throws Exception {
        doNothing().when(trackingDeviceService).saveOrUpdate(any());
        doNothing().when(deviceCredentialService).saveOrUpdate(any());

        AddDeviceDTO addDeviceDTO = new AddDeviceDTO();
        addDeviceDTO.setPublicCode(1);
        addDeviceDTO.setSecretCode("Secret Code");
        String content = (new ObjectMapper()).writeValueAsString(addDeviceDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/dcs/addTrackingDevice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(deviceConfigurationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testDeleteDevice() throws Exception {
        doNothing().when(trackingDeviceService).remove(any());

        DeviceCredential deviceCredential = new DeviceCredential();
        deviceCredential.setId(UUID.randomUUID());
        deviceCredential.setSecretCode("Secret Code");
        deviceCredential.setTrackingDeviceID(UUID.randomUUID());
        when(deviceCredentialService.findByForeignKey(any())).thenReturn(deviceCredential);
        doNothing().when(deviceCredentialService).remove(any());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/dcs/deleteTrackingDevice/{deviceID}", "Uri Variables", "Uri Variables");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(deviceConfigurationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }


    @Test
    void testSellDevice() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/dcs/sellTrackingDevice/{deviceID}",
                "Uri Variables", "Uri Variables");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(deviceConfigurationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }


    @Test
    void testUpdateDevice() throws Exception {
        TrackingDevice trackingDevice = new TrackingDevice();
        trackingDevice.setActive(true);
        trackingDevice.setId(UUID.randomUUID());
        trackingDevice.setPublicCode(1);
        trackingDevice.setSell(true);
        trackingDevice.setTemperature(1);
        doNothing().when(trackingDeviceService).saveOrUpdate(any());
        when(trackingDeviceService.findByID(any())).thenReturn(trackingDevice);

        DeviceCredential deviceCredential = new DeviceCredential();
        deviceCredential.setId(UUID.randomUUID());
        deviceCredential.setSecretCode("Secret Code");
        deviceCredential.setTrackingDeviceID(UUID.randomUUID());
        doNothing().when(deviceCredentialService).saveOrUpdate(any());
        when(deviceCredentialService.findByForeignKey(any())).thenReturn(deviceCredential);

        UpdateDeviceDTO updateDeviceDTO = new UpdateDeviceDTO();
        updateDeviceDTO.setActive(true);
        updateDeviceDTO.setDeviceID(UUID.randomUUID());
        updateDeviceDTO.setSecretCode("Secret Code");
        updateDeviceDTO.setSell(true);
        updateDeviceDTO.setTemperature(1);
        String content = (new ObjectMapper()).writeValueAsString(updateDeviceDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/dcs/updateTrackingDevice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(deviceConfigurationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

