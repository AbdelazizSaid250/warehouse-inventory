package com.vodafone.common.service.device_credential;

import com.vodafone.common.dao.DeviceCredentialRepository;
import com.vodafone.common.model.entity.DeviceCredential;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class DeviceCredentialServiceImpl implements DeviceCredentialService {

    private final DeviceCredentialRepository deviceCredentialRepository;

    @Autowired
    public DeviceCredentialServiceImpl(DeviceCredentialRepository deviceCredentialRepository) {
        this.deviceCredentialRepository = deviceCredentialRepository;
    }

    @Override
    public void saveOrUpdate(DeviceCredential deviceCredential) {
        deviceCredentialRepository.save(deviceCredential);
        log.info("Done inserted the device credential into the DB!! + {}", deviceCredential);
    }

    @Override
    public void remove(DeviceCredential deviceCredential) {
        deviceCredentialRepository.delete(deviceCredential);
        log.info("Done deleted the device credential from the DB!! + {}", deviceCredential);
    }

    @Override
    public DeviceCredential findByID(UUID uuid) {
        Optional<DeviceCredential> optionalDeviceCredential = deviceCredentialRepository.findById(uuid);
        return optionalDeviceCredential.orElse(null);
    }

    @Override
    public DeviceCredential findByForeignKey(UUID foreignKey) {
        DeviceCredential foundDeviceCredential = deviceCredentialRepository.findByForeignKey(foreignKey);
        log.info("Successfully found the DeviceCredential!!");
        return foundDeviceCredential;
    }


    @Override
    public List<DeviceCredential> findAll() {
        return deviceCredentialRepository.findAll();
    }
}
