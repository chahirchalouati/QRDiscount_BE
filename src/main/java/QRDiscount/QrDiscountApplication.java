package QRDiscount;

import QRDiscount.Entities.AppRole;
import QRDiscount.Repositories.RoleRepository;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QrDiscountApplication {

    private final RoleRepository roleRepository;

    public QrDiscountApplication(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    String[] roles = {"USER", "ADMIN", "NEGOZIANTE"};//Roles

    public static void main(String[] args) {
        SpringApplication.run(QrDiscountApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return (String[] args) -> {
            try {                
                if (roleRepository.findAll().size() <= 0) {
                    List<AppRole> collectRoles = Arrays.asList(roles)
                            .stream()
                            .map(r -> new AppRole(null, r, new Date()))
                            .collect(Collectors.toList());
                    roleRepository.saveAll(collectRoles);
                }

            } catch (Exception e) {
            }
        };
    }

}
