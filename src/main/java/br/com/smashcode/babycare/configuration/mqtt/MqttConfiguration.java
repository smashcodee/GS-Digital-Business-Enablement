package br.com.smashcode.babycare.configuration.mqtt;

import br.com.smashcode.babycare.subscriber.MqttInboundSubscriber;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttConfiguration {

    @Value("${mqtt.broker.url}")
    private String MQTT_BROKER;
    private static final String CLIENT_ID = "spring-client";
    private static final String PUBLISHER_ID = "node-publisher";

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{MQTT_BROKER});
        options.setAutomaticReconnect(true);
        options.setCleanSession(false);
        /* Não coloquei senha e usuário no broker por enquanto... (recomendado)*/
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(
                PUBLISHER_ID, mqttClientFactory());

        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("message-deviceUID-123");
        return messageHandler;
    }

    @Bean
    public IntegrationFlow mqttInFlow() {
        return IntegrationFlows.from("mqttInputChannel")
                .handle(mqttInboundSubscriber(), "handleInputMessage")
                .get();
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(CLIENT_ID,
                mqttClientFactory(), "message-deviceUID-123");
        adapter.setOutputChannelName("mqttInputChannel");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        return adapter;
    }

    @Bean
    public MqttInboundSubscriber mqttInboundSubscriber() {
        return new MqttInboundSubscriber();
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    
    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    public interface OutputGateway {

        void sendToMqtt(String data);

    }
}
