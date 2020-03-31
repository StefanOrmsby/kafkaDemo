package kafkaDemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class FastJsonDemo {

  public static void main(String[] args) {
    String a = "{\"@timestamp\":\"2019-09-16T09:41:40.845Z\",\"@metadata\":{\"beat\":\"filebeat\",\"type\":\"_doc\",\"version\":\"7.3.0\",\"topic\":\"filebeat_gaotu_api_nginx_access\"},\"message\":\"117.136.99.2\\u0001 -\\u0001 [16/Sep/2019:17:05:28 +0800]\\u0001 POST /user/v2/send_passcode HTTP/1.1\\u0001 403\\u0001 162\\u0001 -\\u0001 -\\u0001 0.000\\u0001 Apache-HttpClient/UNAVAILABLE (java 1.4)\\u0001 -\\u0001 api.gaotu100.com\\u0001 -\\u0001 -\\u0001 -\",\"severity\":\"high\",\"type\":\"filebeat_gaotu_api_nginx_access\",\"ecs\":{\"version\":\"1.0.1\"},\"host\":{\"name\":\"al-bj-gaotu-nginx04\"},\"agent\":{\"ephemeral_id\":\"c2a62d49-4abf-411c-b7f4-c22461f743f8\",\"hostname\":\"al-bj-gaotu-nginx04\",\"id\":\"dd415947-f4d6-4f1f-bf96-7b61234e9b7c\",\"version\":\"7.3.0\",\"type\":\"filebeat\"},\"log\":{\"offset\":1093986,\"file\":{\"path\":\"/apps/log/nginx/api.gaotu100.com.access.log\"}},\"store\":\"persistent\",\"business\":\"gaotu100\",\"environment\":\"prod\",\"project\":\"api.gaotu100.com\",\"gear\":\"nginx\",\"input\":{\"type\":\"log\"}}";

    JSONObject object = JSON.parseObject(a);
    // 利用键值对的方式获取到值
    String line = (String) object.get("message");

    System.out.println(line);
  }

}
