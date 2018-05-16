package run.star.plan.hecs.pay_route_model_002;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: hecs
 * @Date: 2018/5/15 20:20
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayContext {

    private Integer cents;

    private Integer code;

    private String  msg;
}
