package pack.springjpa1;


//spring boot 기본 configuration
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringJpa1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringJpa1Application.class, args);
    }

}
