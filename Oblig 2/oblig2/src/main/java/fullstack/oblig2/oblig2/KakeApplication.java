package fullstack.oblig2.oblig2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("fullstack.oblig2.oblig2")
@SpringBootApplication
public class KakeApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(KakeApplication.class, args);
    }

}
