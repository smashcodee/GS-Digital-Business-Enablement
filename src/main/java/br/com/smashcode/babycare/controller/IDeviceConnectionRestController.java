package br.com.smashcode.babycare.controller;

import br.com.smashcode.babycare.response.DeviceConnectionResponse;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface IDeviceConnectionRestController {
    ResponseEntity<DeviceConnectionResponse> connectDevice(UUID deviceId);
}
