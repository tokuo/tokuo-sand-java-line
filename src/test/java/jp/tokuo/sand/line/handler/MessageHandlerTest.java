package jp.tokuo.sand.line.handler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MessageHandlerTest {

  private final MessageHandler messageHandler;

  @Autowired
  public MessageHandlerTest(MessageHandler messageHandler){
    this.messageHandler = messageHandler;
  }

//  @Test
//  void restTemplateTest(){
//    messageHandler.handleDefaultMessageEvent(null);
//  }
}
