package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.inject.Provider;

public class SingtonWithPrototypeTest1 {

    // 프로토타입 테스트
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isSameAs(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isSameAs(1);
    }

    // 싱글톤 & 프로토 타입 테스트
    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(ClientBean.class, ClientBeanRealScope.class, ClientBeanRealScope2.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isSameAs(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isSameAs(2);

        // --------------------ObjectProvider를 이용한 객체 찾기 ------------------------ //
        ClientBeanRealScope clientBeanRealScope1 = ac.getBean(ClientBeanRealScope.class);
        int count11 = clientBeanRealScope1.logic();
        Assertions.assertThat(count11).isSameAs(1);

        ClientBeanRealScope clientBeanRealScope2 = ac.getBean(ClientBeanRealScope.class);
        int count22 = clientBeanRealScope2.logic();
        Assertions.assertThat(count22).isSameAs(1);

        // -----------------jakarta의 Provider를 이용한 객체 찾기 (gradle 추가 필요) -------------------- //
        ClientBeanRealScope2 clientBeanRealScope3 = ac.getBean(ClientBeanRealScope2.class);
        int count33 = clientBeanRealScope3.logic();
        Assertions.assertThat(count33).isSameAs(1);

        ClientBeanRealScope2 clientBeanRealScope4 = ac.getBean(ClientBeanRealScope2.class);
        int count44 = clientBeanRealScope4.logic();
        Assertions.assertThat(count44).isSameAs(1);
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean {
        private final PrototypeBean prototypeBean ; // 생성 시점에 주입
        public int logic () {
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBeanRealScope {
        private final ObjectProvider<PrototypeBean>  prototypeBeanProvider; // 애플리케이션 컨테이너 빈에서 찾기 기능
       // private final ObjectFactory<PrototypeBean> prototypeBeanFactory; -- ObjectProvider, ObjectFactory 동일 기능 사용
        public int logic () {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBeanRealScope2 {
        private final Provider<PrototypeBean> prototypeBeanProvider; // 애플리케이션 컨테이너 빈에서 찾기 기능
        public int logic () {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype") // 중첩 클래스?
    static class PrototypeBean {
        private int count = 0;
        public void addCount() {
            count++;
        }
        public int getCount() {
            return this.count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
