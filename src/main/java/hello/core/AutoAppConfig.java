package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan (
        basePackages = "hello.core",
        // 컴포넌트 스캔 스프링빈으로 자동 주입 시, Configuration 어노테이션이 붙은 클랙스 파일은 스캔을 통한 빈주입 시 제외 해라
        // 지금 AppConfig 파일 들어가면 @Configuration로 지정되어있고 @Bean 등록을 수동으로 하고 있자나 ,
        // 지금까지 만들어 왔던 예제코드를 살리기 위해서 AppConfig에 붙어 있는 @Configuration어노테이션을 지우지 않고 ComponentScan 할 때, excludeFilters를 적용해 @Configuration 어노테이션이 적용된 클래스는 Scan 하지 않도록 설정!
        // 실무에서는  @Configuration 어노테이션을 따로 빼지 않고 모두 component-Scan으로 관리한다고 함
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
