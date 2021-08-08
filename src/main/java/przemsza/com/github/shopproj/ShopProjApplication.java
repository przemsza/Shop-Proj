package przemsza.com.github.shopproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.criteria.Order;

@SpringBootApplication
public class ShopProjApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopProjApplication.class, args);

    }
}
