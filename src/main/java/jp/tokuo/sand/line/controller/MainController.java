package jp.tokuo.sand.line.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class MainController {

  @GetMapping(value = "/demo")
  public ResponseEntity<String> getDemo(){
    return ResponseEntity.ok().body("hoge");
  }
}
