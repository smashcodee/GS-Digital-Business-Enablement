package br.com.smashcode.babycare.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.smashcode.babycare.configuration.mqtt.MqttConfiguration.OutputGateway;
import br.com.smashcode.babycare.exception.BabyProjectNotExistsException;
import br.com.smashcode.babycare.repository.IBabyProjectRepository;
import br.com.smashcode.babycare.request.DeviceConnetionRequest;
import br.com.smashcode.babycare.response.DeviceConnectionResponse;
import br.com.smashcode.babycare.service.IDeviceConnectionService;
import br.com.smashcode.babycare.utils.BabyProjectUtils;

@Service
public class DeviceConnectionServiceImpl implements IDeviceConnectionService {

    @Autowired
    OutputGateway gateway;

    @Autowired
    private IBabyProjectRepository babyProjectRepository;

    @Override
    public DeviceConnectionResponse connectDevice(DeviceConnetionRequest request) {
        boolean projectExists = BabyProjectUtils.babyProjectExists(request.getProjectId());
        if(!projectExists) {
            throw new BabyProjectNotExistsException("Nenhum projeto foi encontrado com esse id.");
        }

        babyProjectRepository.findById(request.getProjectId()).get();
        

        String initalMessageAPI = "start";

        gateway.sendToMqtt(initalMessageAPI);

        // depois fazer uma lógica para criar uma rotina


        return new DeviceConnectionResponse(
            true,
            200,
            "Conectado com sucesso!"
        );
    }

    @Override
    public DeviceConnectionResponse disconnectDevice() {
     
        String initalMessageAPI = "stop";

        gateway.sendToMqtt(initalMessageAPI);
        
        return new DeviceConnectionResponse(
            true,
            200,
            "Desconectado com sucesso!"
        );
    }
    
}
