package io.lpgph.ddd.common.bean;

import lombok.Value;

/** 商品由 产品以及SKU组成 */
@Value
public class Goods {
  long productId;
  long productSkuId;
}
