/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Repositories;

import QRDiscount.Entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Chahir Chalouati
 */
public interface RoleRepository extends JpaRepository<AppRole, Long> {

    AppRole findByRole(String role);
}
