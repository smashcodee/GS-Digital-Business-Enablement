package br.com.smashcode.babycare.controller;

import br.com.smashcode.babycare.request.DeviceConnetionRequest;
import br.com.smashcode.babycare.response.DeviceConnectionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IDeviceConnectionRestController {
    ResponseEntity<DeviceConnectionResponse> connectDevice(
        @RequestBody DeviceConnetionRequest request);

    ResponseEntity<DeviceConnectionResponse> disconnectDevice();
}
