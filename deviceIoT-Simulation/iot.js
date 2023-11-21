/**
 * @author VitorRT
 * full development by: VitorRT (Github)
 * 
 * Isso é apenas uma simulação do nosso dispositivo iot.
 * No caso para implementar o iot, basta apenas transcrever essa lógica 
 * para o node-red ou para uma outra ferramenta.
 */

import mqtt from 'mqtt';

const brokerUrl = process.env.DOCKER_MQTT_BROKER || 'mqtt://localhost:1883';
const deviceId = "deviceUID-123";
const incomingTopic = `message-${deviceId}`;
const outgoingTopic = `message-${deviceId}`;

function generateClientId() {
  return `node-publisher-${Math.random().toString(16).substr(2, 8)}`;
}

const client = mqtt.connect(brokerUrl, {
  clientId: generateClientId()
});

client.on('connect', () => {
  console.log('Conectado ao broker MQTT');

  client.subscribe(incomingTopic, (err) => {
    if (!err) {
      console.log(`Inscrição no tópico "${incomingTopic}" realizada com sucesso`);
    } else {
      console.error('Erro ao se inscrever no tópico:', err);
    }
  });
});


function simulateHeartRate() {
  const baselineHeartRate = 120;
  const randomVariation = Math.random() * 20 - 10;
  const simulatedHeartRate = baselineHeartRate + randomVariation;
  const finalHeartRate = Math.max(80, Math.min(160, simulatedHeartRate));

  return Math.round(finalHeartRate).toString();
}

let intervalId;

const startLoop = () => {
  intervalId = setInterval(() => {
    client.publish(outgoingTopic, simulateHeartRate());
  }, 2000);
};

const stopLoop = () => {
  clearInterval(intervalId);
  console.log("Device disconnected.");
};

client.on('message', (topic, message) => {
  if (message.toString() === 'start' && topic === incomingTopic) {
    startLoop();
  } else if (message.toString() === 'stop' && topic === incomingTopic) {
    stopLoop();
  }
});

client.on('error', (error) => {
  console.error('Erro de conexão:', error);
});

client.on('close', () => {
  console.log('Conexão fechada, tentando reconectar...');
  client.connect({
    clientId: generateClientId()
  });
});

client.on('reconnect', () => {
  console.log('Tentando reconectar...');
});

client.on('offline', () => {
  console.log('Cliente MQTT offline');
});
