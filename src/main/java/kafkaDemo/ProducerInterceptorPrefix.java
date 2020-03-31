package kafkaDemo;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class ProducerInterceptorPrefix implements ProducerInterceptor<String, String> {

  private volatile long sendSuccess = 0;
  private volatile long sendFailure = 0;

  @Override
  public void configure(Map<String, ?> configs) {
    // TODO Auto-generated method stub

  }

  @Override
  public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
    // TODO Auto-generated method stub
    String modifiedValue = "profix1-" + record.value();
    return new ProducerRecord<String, String>(record.topic(), record.partition(),
        record.timestamp(), record.key(), modifiedValue, record.headers());
  }

  @Override
  public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
    // TODO Auto-generated method stub
    if (exception == null) {
      sendSuccess++;
    } else {
      sendFailure++;
    }
  }

  @Override
  public void close() {
    // TODO Auto-generated method stub
    double successRatio = (double) sendSuccess / (sendFailure + sendSuccess);
    System.out.println("成功率:" + successRatio * 100 + "%");
  }

}
