package hello.core.autowired;

import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {
    
    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        //TDD # 테스트 주도 개발(test-driven development, TDD)
        DiscountService bean = ac.getBean(DiscountService.class);
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        int fixPrice = bean.discountPrice(memberA, 10000, "fixDiscountPolicy");

        assertThat(bean).isInstanceOf(DiscountService.class);
        assertThat(fixPrice).isEqualTo(1000);

        Member memberB = new Member(2L, "memberB", Grade.VIP);
        int ratePrice = bean.discountPrice(memberB, 20000, "rateDiscountPolicy");

        assertThat(ratePrice).isEqualTo(2000);

    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService (Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discountPrice(Member member, int price , String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            int discountPrice = discountPolicy.discount(member, price);
            System.out.println("discountPrice = " + discountPrice);
            return discountPrice; // discountPolicy.discount(member, price);
        }
    }
}
