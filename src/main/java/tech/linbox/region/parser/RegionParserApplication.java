package tech.linbox.region.parser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication
@ComponentScan("tech.linbox")
@EnableJpaRepositories("tech.linbox")
@EntityScan("tech.linbox")
public class RegionParserApplication {

  public static void main(String[] args) {
    log.info("server start");
    SpringApplication.run(RegionParserApplication.class, args);
  }

}
