import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import star.plan.demo.aop.service.MemberService;

/**
 * @Author: hecs
 * @Date: 2018/11/8 15:42
 * @Description:
 */
@ContextConfiguration(locations = "classpath*:spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MemberTest {
    @Autowired
    MemberService memberService;

    @Test
    public void modify(){
        memberService.modify(1L);
    }

    @Test
    public void remove() throws Exception {
        memberService.remove(1L);
    }
}
