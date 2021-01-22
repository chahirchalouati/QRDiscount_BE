/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Repositories;

import QRDiscount.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Chahir Chalouati
 */
public interface UserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByEmailOrUsername(String email, String username);

    AppUser findByEmail(String email);

    public boolean existsAppUserByEmail(String email);

    public boolean existsAppUserByUsername(String username);
}
