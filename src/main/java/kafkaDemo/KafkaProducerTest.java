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
		props.put("bootstrap.servers", "172.16.5.67:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, ProducerInterceptorPrefix.class.getName());
		this.producer = new KafkaProducer<String, String>(props);
		this.topic = topicName;
	}

	@Override
	public void run() {
		int messageNo = 1;
		try {
			for (;;) {
				String messageStr = "你好，这是第" + messageNo + "条数据";
				producer.send(new ProducerRecord<String, String>(topic, "Message", messageStr));
				// 生产了100条就打印
				if (messageNo % 10 == 0) {
					System.out.println("发送的信息:" + messageStr);
				}
				// 生产1000条就退出
				if (messageNo % 30 == 0) {
					System.out.println("成功发送了" + messageNo + "条");
					break;
				}
				messageNo++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}

	public static void main(String args[]) {
		KafkaProducerTest test = new KafkaProducerTest("genshuixue_topic_test");
		Thread thread = new Thread(test);
		thread.start();
	}
}