package jp.tokuo.sand.line.handler;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import jp.tokuo.sand.line.property.RestTemplateProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@EnableConfigurationProperties(RestTemplateProperty.class)
@LineMessageHandler
// 普通にドキュメント見て普通にPostMappingで作成したほうが自由
// 楽するために"@LineMessageHandler"を利用
public class MessageHandler {

  private final RestTemplateProperty property;
  private final RestTemplate restTemplate;
  private final HttpEntity<String> httpEntity;

  public MessageHandler(RestTemplateProperty property, RestTemplate restTemplate) {
    this.property = property;
    this.restTemplate = restTemplate;
    final HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", String.format("Bearer %s", property.getBearerToken()));
    this.httpEntity = new HttpEntity<>(headers);
  }

//  @EventMapping
//  public void handleDefaultMessageEvent(Event event) {
//    System.out.println("event: " + event);
//  }

  @EventMapping
  public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws Exception {
    switch (event.getMessage().getText()) {
      case "Who am I?":
        final String API_PATH = String.format("https://%s%s/profile/%s",
          property.getDomain(), property.getBaseEndpoint(), event.getSource().getUserId());;
        final ResponseEntity<String> response =
          restTemplate.exchange(API_PATH, HttpMethod.GET, httpEntity, String.class);
        return new TextMessage(Objects.requireNonNull(response.getBody()));

      case "Tell me the port number":
        return new TextMessage("10945");

      default:
        return new TextMessage("ちょっと何言ってるか分からない");
    }
  }
}
