package br.com.smashcode.babycare.service.impl;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Service
public class MqttCallbackService implements MqttCallback {

    @Override
    public void connectionLost(Throwable cause) {
        // Lida com a perda de conexão
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // Lida com mensagens recebidas
        System.out.println("Recebido mensagem no tópico: " + topic);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // Lida com a entrega de mensagens
    }
}
