package br.com.smashcode.babycare.subscriber;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

public class MqttInboundSubscriber {

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleInputMessage(Message<String> message) {

        String payload = message.getPayload();
        
        // Processar a mensagem aqui
        System.out.println("Received message: " + payload);
        if(!payload.equals("start") || !payload.equals("stop")) {
            // devolver os dados em tempo real para o cliente...
        }
    }
}
