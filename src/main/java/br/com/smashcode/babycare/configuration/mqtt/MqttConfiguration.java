package br.com.smashcode.babycare.configuration.mqtt;

import br.com.smashcode.babycare.handler.mqtt.TestMqttHandler;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
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
        options.setServerURIs(new String[]{ MQTT_BROKER });
        /* Não coloquei senha e usuario no broker por enquanto... (recomendado)*/
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public IntegrationFlow mqttOutFlow() {
        return flow -> flow
                .handle(mqttOutbound());
    }

    @Bean
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(
                PUBLISHER_ID, mqttClientFactory());
        messageHandler.setDefaultTopic("message");
        return messageHandler;
    }

    @Bean
    public IntegrationFlow mqttInFlow() {
        // método depreciado, mas dps eu vejo isso não tenho tempo não.
        // dificil achar esse conteudo na net em portugues, peguei um artigo antigo
        return IntegrationFlows.from(mqttInbound())
                .handle(testMqttHandler())
                .get();
    }

    @Bean
    public MessageProducerSupport mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(CLIENT_ID,
                mqttClientFactory(), "message");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        return adapter;
    }

    @Bean
    public MessageHandler testMqttHandler() {
        return new TestMqttHandler();
    }
}
