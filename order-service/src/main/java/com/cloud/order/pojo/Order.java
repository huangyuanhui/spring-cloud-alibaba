package com.cloud.order.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cloud.feign.pojo.User;
import lombok.Data;

@Data
public class Order {
    private Long id;
    private Long price;
    private String name;
    private Integer num;
    private Long userId;

    @TableField(exist = false)
    private User user;
}
