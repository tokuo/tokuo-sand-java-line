package jp.tokuo.sand.line.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "tokuo.sand.rest-template")
public class RestTemplateProperty {

  private String myUserId;
  private String domain;
  private String baseEndpoint;
  private String bearerToken;
}
