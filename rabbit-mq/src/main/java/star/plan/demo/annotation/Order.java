package star.plan.demo.annotation;

import lombok.Data;

/**
 * @Author: hecs
 * @Date: 2018/12/12 11:31
 * @Description:
 */
@Data
public class Order {
    private String messageId;
    private String id;
    private String orderNo;
    private Double saleAmount;
}
