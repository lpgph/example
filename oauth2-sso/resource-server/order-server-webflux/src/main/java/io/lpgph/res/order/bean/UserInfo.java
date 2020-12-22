package io.lpgph.res.order.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

    private String user_name;
    private Long userId;
}
