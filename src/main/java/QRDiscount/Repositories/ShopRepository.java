/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Repositories;

import QRDiscount.Entities.Shop;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Chahir Chalouati
 */
public interface ShopRepository extends JpaRepository<Shop, Long> {

    //trovare tutti negozi di un utente
    List<Shop> findByUser_email(String email);

}
