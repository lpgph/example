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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Slf4j
@SpringBootTest
public class LoginTest {

  private final WebClient webClient =
      WebClient.builder()
          .baseUrl("http://localhost:8090/")
          .exchangeStrategies(
              ExchangeStrategies.builder()
                  .codecs(
                      configurer -> {
                        configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024);
                      })
                  .build())
          .build();

  @Autowired private ObjectMapper objectMapper;

  @Test
  public void login2() throws Exception {
    MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
    formData.add("username", "admin");
    formData.add("password", "admin");
    //    http://localhost:8081/oauth2/authorization/c1
    formData.add(
        "return_url",
        "/oauth/authorize?response_type=code&client_id=client1&scope=all&state=test&redirect_uri=http://localhost:8081/login/oauth2/code/c1");
    Mono<ClientResponse> mono =
        this.webClient
            .post()
            .uri("http://localhost:8090/login")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .bodyValue(formData)
            .exchange();
    ClientResponse rps = mono.block();
    log.info("\n\nheader\n{}\n\n", rps.headers().asHttpHeaders());
    log.info("\n\ncookies {}\n\n", rps.cookies());

    //
    // http://localhost:8090/oauth/authorize?response_type=code&client_id=client1&scope=all&state=test&redirect_uri=http://localhost:8081/login/oauth2/code/c1

  }

  @Test
  public void login() throws Exception {
    MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
    formData.add("username", "admin");
    formData.add("password", "admin");
    Mono<ClientResponse> mono =
        this.webClient
            .post()
            .uri("http://localhost:8090/login")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .bodyValue(formData)
            .exchange();
    ClientResponse rps = mono.block();
    log.info("\n\nheader\n{}\n\n", rps.headers().asHttpHeaders());
    log.info("\n\ncookies {}\n\n", rps.cookies());

    //    rps.getHeaders().get("Set-Cookie").get(0);
    ResponseCookie cookie = rps.cookies().get("s0").get(0);

    ResponseEntity<String> rps2 =
        this.webClient
            .get()
            .uri(
                "http://localhost:8090/oauth/authorize?response_type=code&client_id=client1&scope=all&state=test&redirect_uri=http://localhost:8081/login/oauth2/code/c1")
            .cookie(cookie.getName(), cookie.getValue())
            .retrieve()
            .toEntity(String.class)
            .block();

    log.info("\n\n{}\n\n", objectMapper.writeValueAsString(rps2));

    ResponseEntity<String> rps3 =
        this.webClient
            .get()
            .uri(rps2.getHeaders().getLocation())
            .cookie(cookie.getName(), cookie.getValue())
            .retrieve()
            .toEntity(String.class)
            .block();

    log.info("\n\n{}\n\n", objectMapper.writeValueAsString(rps3));
  }
}
