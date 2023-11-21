package br.com.smashcode.babycare.service;

import br.com.smashcode.babycare.request.DeviceConnetionRequest;
import br.com.smashcode.babycare.response.DeviceConnectionResponse;

public interface IDeviceConnectionService {
    DeviceConnectionResponse connectDevice(DeviceConnetionRequest request);
    DeviceConnectionResponse disconnectDevice();
}