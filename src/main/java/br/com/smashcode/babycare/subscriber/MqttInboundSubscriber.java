package br.com.smashcode.babycare.subscriber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class MqttInboundSubscriber {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleInputMessage(Message<String> message) {

        String payload = message.getPayload();
        
        // Processar a mensagem aqui
        System.out.println("Received message: " + payload);
        if(!payload.equals("start") && !payload.equals("stop")) {
           messagingTemplate.convertAndSend("/topic/mqtt-heartRate", payload);
        }
    }
}
