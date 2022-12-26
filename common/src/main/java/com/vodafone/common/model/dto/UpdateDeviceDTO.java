package com.vodafone.common.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class UpdateDeviceDTO implements Serializable {
    private UUID deviceID;

    private String secretCode;

    private int temperature;

    private boolean isActive;

    private boolean isSell;
}
