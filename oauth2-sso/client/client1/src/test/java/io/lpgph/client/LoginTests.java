/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.lpgph.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Slf4j
@AutoConfigureWebTestClient
@SpringBootTest
public class LoginTests {

  @Autowired private WebTestClient rest;

  @Autowired private ObjectMapper objectMapper;

  @Test
  public void login() throws Exception {
    EntityExchangeResult<String> rst =
        this.rest
            .get()
            .uri("http://localhost:8090/login")
            .headers(headers -> headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED))
            .attribute("username", "admin")
            .attribute("password", "admin")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(String.class)
            .returnResult();
    Map<String, ResponseCookie> cookieMultiValueMap = rst.getResponseCookies().toSingleValueMap();
    log.info("{} ", objectMapper.writeValueAsString(rst));
//    EntityExchangeResult<String> rst2 =
//        this.rest
//            .get()
//            .uri(
//                "http://localhost:8090/oauth/authorize?response_type=code&client_id=client1&scope=all&state=test&redirect_uri=http://localhost:8081/login/oauth2/code/c1")
//            .cookie("s0", cookieMultiValueMap.get("s0").getValue())
//            .exchange()
//            .expectStatus()
//            .isOk()
//            .expectBody(String.class)
//            .returnResult();
//    log.info("{} ", rst2.getResponseBody());
  }

//  @TestConfiguration
//  static class WebClientConfig {
//    @Bean
//    WebClient web() {
//      return WebClient.create("http://localhost:8090");
//    }
//  }
}
