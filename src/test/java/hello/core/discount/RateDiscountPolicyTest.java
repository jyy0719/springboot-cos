package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// static으로 사용
import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다. ")
    void vip_o () {
        Member member = new Member(1L, "memberA", Grade.VIP);
        int discountPrice = rateDiscountPolicy.discount(member, 10000);
        // assertThat 을 static으로 임포트하여 사용하는 것이 좋음 
        assertThat(discountPrice).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 10% 할인이 적용되지 말아야 한다. ")
    void vip_x () {
        Member member = new Member(1L, "memberA", Grade.BASIC);
        int discountPrice = rateDiscountPolicy.discount(member, 10000);

        assertThat(discountPrice).isEqualTo(0);
    }
}