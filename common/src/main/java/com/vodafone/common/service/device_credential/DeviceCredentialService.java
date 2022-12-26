package com.vodafone.common.service.device_credential;


import com.vodafone.common.model.entity.DeviceCredential;

import java.util.List;
import java.util.UUID;

public interface DeviceCredentialService {
    void saveOrUpdate(DeviceCredential deviceCredential);

    void remove(DeviceCredential deviceCredential);

    DeviceCredential findByID(UUID uuid);

//    <T> DeviceCredential findByObject(T object);
    DeviceCredential findByForeignKey(UUID foreignKey);

    List<DeviceCredential> findAll();
}
