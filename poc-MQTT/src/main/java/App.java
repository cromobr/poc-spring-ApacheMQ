
import java.util.Date;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Random;

public class App {
	public static void main(String[] args) throws InterruptedException {

		//int nroEnvios = Integer.parseInt(args[0]);
		//int qtdSpleep = Integer.parseInt(args[1]);

		System.out.println("POC MQTT!");
		System.out.println("ESCRITA!");

		escreveMensagem();
		//escreveMensagem(nroEnvios, qtdSpleep);
		//Thread.sleep(qtdSpleep);

	}

	//public static void escreveMensagem(int nroEnvios, int qtdSpleep) throws InterruptedException {
	public static void escreveMensagem() throws InterruptedException {

		String topic = "topico";
		Date dataAtual = new Date();
		String content = dataAtual.toString();
		int qos = 2;
		String broker = "tcp://172.32.66.85:1883";
		String clientId = "None";
		MemoryPersistence persistence = new MemoryPersistence();
		try {
			MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			System.out.println("Connecting to broker: " + broker);
			sampleClient.connect(connOpts);
			System.out.println("Connected");

			MqttMessage message = new MqttMessage(content.getBytes());
			message.setQos(qos);
			Random gerador = new Random();
			while(true) {
				dataAtual = new Date();
				gerador = new Random();
				content = dataAtual.toString() +" rand:" + gerador.nextInt();
				message = new MqttMessage(content.getBytes());
				System.out.println("Publishing message: " + content);
				sampleClient.publish(topic, message);
				//Thread.sleep(qtdSpleep);
			}
			//System.out.println("Message published");
			//sampleClient.disconnect();
			//System.out.println("Disconnected");
		} catch (MqttException me) {
			System.out.println("trace " + me.getStackTrace());
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
	}

}
