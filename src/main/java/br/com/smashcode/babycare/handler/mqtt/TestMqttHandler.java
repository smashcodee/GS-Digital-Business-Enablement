package br.com.smashcode.babycare.handler.mqtt;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

public class TestMqttHandler implements MessageHandler {
    @Override
    public void handleMessage(Message<?> message) {
        System.out.println("message received: " + message.getPayload());
    }
}
