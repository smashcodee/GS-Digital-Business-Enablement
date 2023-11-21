package br.com.smashcode.babycare.request;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceConnetionRequest {
    private UUID projectId;
    private String deviceId;

    public DeviceConnetionRequest() { super(); };
}
