package io.lpgph.res.order.rpc;

import io.lpgph.res.order.config.FeignClientConfig;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@LoadBalancerClient
@FeignClient(value = "goods", configuration = FeignClientConfig.class)
public interface IGoodsService {

  @GetMapping("/hello")
  String showMsg();
}
