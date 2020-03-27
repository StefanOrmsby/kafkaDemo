package kafkaDemo;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaProducerTest implements Runnable {

	private final KafkaProducer<String, String> producer;
	private final String topic;

	public KafkaProducerTest(String topicName) {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.33.92:9092,172.16.33.90:9092,172.16.33.91:9092,172.16.33.89:9092,172.16.33.174:9092,172.16.33.175:9092,172.16.33.176:9092,172.16.33.173:9092");
		props.put(ProducerConfig.ACKS_CONFIG, "1");
		props.put("retries", 0);
		props.put("batch.size", 100);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		// props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,ProducerInterceptorPrefix.class.getName());
		this.producer = new KafkaProducer<String, String>(props);
		this.topic = topicName;
	}

	@Override
	public void run() {
		int messageNo = 1;
		try {
			for (;;) {
				String messageStr = "11111111";
				producer.send(new ProducerRecord<String, String>(topic, null, messageStr));
				if (messageNo % 10000 == 0) {
					System.out.println("发送的信息:" + messageStr);
				}
				//if (messageNo % 30 == 0) {
				//	System.out.println("成功发送了" + messageNo + "条");
					//break;
				//}
				messageNo++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}

	public static void main(String args[]) {
		KafkaProducerTest test = new KafkaProducerTest("test");
		for (int i = 0; i < 1; i++) {
			Thread thread = new Thread(test);
			thread.start();
		}
	}
}