/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Controllers;

import QRDiscount.Entities.Shop;
import QRDiscount.Services.ShopService;
import QRDiscount.Utilities.Projections.ShopPro;
import java.io.IOException;
import java.security.Principal;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/shops")
@AllArgsConstructor
public class ShopRestController {

    private final ShopService shopService;

    /**
     *
     * @param principal
     * @return
     */
    @GetMapping()
    public ResponseEntity<?> list() {
        return shopService.findShops();
    }

    /**
     *
     * @param username
     * @param principal
     * @return
     */
    @GetMapping("/{username}")
    public ResponseEntity<?> get(@PathVariable String username, Principal principal) {
        return shopService.findShopByOwner(username, principal);
    }

    /**
     *
     * @param id
     * @param shop
     * @param principal
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Shop shop, Principal principal) {
        return shopService.editShop(shop, id, principal);
    }

    /**
     *
     * @param shop
     * @param principal
     * @return
     * @throws java.io.IOException
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> post(ShopPro shop, Principal principal) throws IOException {

        return shopService.createShop(shop, principal);
    }

    /**
     *
     * @param id
     * @param principal
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Principal principal) {
        return shopService.deleteShop(id, principal);
    }

}
