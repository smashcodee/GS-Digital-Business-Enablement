package br.com.smashcode.babycare.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.smashcode.babycare.controller.IDeviceConnectionRestController;
import br.com.smashcode.babycare.request.DeviceConnetionRequest;
import br.com.smashcode.babycare.response.DeviceConnectionResponse;
import br.com.smashcode.babycare.service.IDeviceConnectionService;

@RestController
@RequestMapping("/device")
public class DeviceConnectionRestControllerImpl implements IDeviceConnectionRestController{
    @Autowired
    private IDeviceConnectionService service;

    @Override
    @PostMapping("/connect")
    public ResponseEntity<DeviceConnectionResponse> connectDevice(DeviceConnetionRequest request) {
        return ResponseEntity.ok(service.connectDevice(request));
    }

    @Override
    @GetMapping("/disconnect")
    public ResponseEntity<DeviceConnectionResponse> disconnectDevice() {
       return ResponseEntity.ok(service.disconnectDevice());
    }
    
}
