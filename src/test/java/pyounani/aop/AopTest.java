package pyounani.aop;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import pyounani.aop.order.OrderRepository;
import pyounani.aop.order.OrderService;
import pyounani.aop.order.aop.AspectV1;
import pyounani.aop.order.aop.AspectV2;
import pyounani.aop.order.aop.AspectV3;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
//@Import(AspectV1.class)
//@Import(AspectV2.class)
@Import(AspectV3.class)
@SpringBootTest
public class AopTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo() {
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success() {
        orderService.orderItem("itemA");
    }

    @Test
    void exception() {
        assertThatThrownBy(() -> orderService.orderItem("ex"))
                .isInstanceOf(IllegalStateException.class);
    }
}
