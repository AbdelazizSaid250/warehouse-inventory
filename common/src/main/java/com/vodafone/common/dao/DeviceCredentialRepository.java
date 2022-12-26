package com.vodafone.common.dao;

import com.vodafone.common.model.entity.DeviceCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "device_credential")
public interface DeviceCredentialRepository extends JpaRepository<DeviceCredential, UUID> {
    @Query(value = "SELECT * FROM device_credential WHERE tracking_device_id = :foreignKey", nativeQuery = true)
    DeviceCredential findByForeignKey(@Param("foreignKey") UUID foreignKey);
}
