package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 : " + prototypeBean1);
        System.out.println("prototypeBean2 : " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

//        prototypeBean1.destroy(); ->필요 경우 수동으로 호출할 수 있다.
        ac.close();
    }

    @Scope("prototype")
    //@Component가 없어도 AnnotationConfigApplicationContext에 파라미터로 해당 클래스를 넘겨주면서 컴포넌트 스캔처럼 동작하기 때문에 문제가 발생하지 않음
    static class PrototypeBean{
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
