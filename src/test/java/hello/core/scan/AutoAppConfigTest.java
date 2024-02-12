package hello.core.scan;

import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        MemberService memberServiceImpl = ac.getBean(MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
        Assertions.assertThat(memberServiceImpl).isInstanceOf(MemberServiceImpl.class);
    }

    /*
    * 나 홀로 테스트해본 싱글톤 테스트 
    * @Service나 @Component 어노테이션도 싱글톤으로 관리되는지 확인해보았음
    * 싱글톤으로 관리됨
    * */
    @Test
    void configurationTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberServiceImpl", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderServiceImpl", OrderServiceImpl.class);
        MemoryMemberRepository memberRepository = ac.getBean("memoryMemberRepository", MemoryMemberRepository.class);

        System.out.println("memberService.memberRepository = " + memberService.getMemberRepository());
        System.out.println("orderService.memberRepository = " + orderService.getMemberRepository());

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }
}
