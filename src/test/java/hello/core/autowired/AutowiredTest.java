package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autowiredOption() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(TestBean.class);
    }
    
    static class TestBean {

        @Autowired(required = false)
        public void setNoBean1(Member noBean) {
            System.out.println("noBean1 = " + noBean);
        }
        @Autowired
        public void setNoBean2(Optional<Member> noBean) {
            System.out.println("noBean2 = " + noBean);
        }
        @Autowired
        public void setNoBean3(@Nullable Member noBean) {
            System.out.println("noBean3 = " + noBean);
        }
        
    }

}


