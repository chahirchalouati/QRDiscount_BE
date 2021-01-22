/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Services;

import QRDiscount.Entities.Address;
import QRDiscount.Entities.AppUser;
import QRDiscount.Entities.Shop;
import QRDiscount.Exceptions.EntityExceptions.EntityNotFoundException;
import QRDiscount.Repositories.RoleRepository;
import QRDiscount.Repositories.ShopRepository;
import QRDiscount.Repositories.UserRepository;
import QRDiscount.Utilities.Projections.ShopPro;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Chahir Chalouati
 */
@Service
@AllArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final UserRepository userRepository;
    private final FileService fileService;
    private final RoleRepository roleRepository;

    /**
     * create Shop
     *
     * @param shopPro
     * @param principal
     * @return
     * @throws java.io.IOException
     */
    @Transactional
    public ResponseEntity<?> createShop(ShopPro shopPro, Principal principal) throws IOException {

        AppUser user = userRepository.findByEmailOrUsername(principal.getName(), principal.getName());

        user.getRoles().add(roleRepository.findByRole("NEGOZIANTE"));// he adding more than one role to user to fix later
        userRepository.save(user);

        String downloadFileEndPoint = "/files/";

        String url = downloadFileEndPoint.concat(fileService.storeFiles(shopPro.getFile()).getName());
        Address address = new Address(null, shopPro.getCity(), shopPro.getProvaince(), shopPro.getCountry(), shopPro.getCAP(), shopPro.getAvenue(), new Date(), new Date());

        Shop shop = new Shop();
        shop.setLogoUrl(url);
        shop.setName(shopPro.getName());
        shop.setAddress(address);
        shop.setUser(user);
        shop.setModifiedAt(new Date());
        shop.setCreatedAt(new Date());
        Shop save = shopRepository.save(shop);
        return new ResponseEntity<>(save, HttpStatus.CREATED);

    }

    /**
     * delete shop
     *
     * @param id
     * @param principal
     * @return
     */
    public ResponseEntity<?> deleteShop(Long id, Principal principal) {
        boolean existsById = shopRepository.existsById(id);
        if (!existsById) {
            throw new EntityNotFoundException("Unable to Found Shop with id : " + id);
        }
        shopRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    /**
     * modify Shop
     *
     * @param shop
     * @param id
     * @param principal
     * @return
     */
    public ResponseEntity<?> editShop(Shop shop, Long id, Principal principal) {
        boolean existsById = shopRepository.existsById(id);
        if (!existsById) {
            throw new EntityNotFoundException("Unable to Found Shop with id : " + id);
        }
        shop.setId(id);
        Shop save = shopRepository.save(shop);
        return new ResponseEntity<>(save, HttpStatus.ACCEPTED);

    }

    /**
     * findShop by Owner
     *
     * @param username
     * @param principal
     * @return
     */
    public ResponseEntity<?> findShopByOwner(String username, Principal principal) {
        List<Shop> shops = shopRepository.findByUser_email(username);
        return new ResponseEntity<>(shops, HttpStatus.OK);

    }

    /**
     * find All shops
     *
     *
     * @return
     */
    public ResponseEntity<?> findShops() {
        List<Shop> shops = shopRepository.findAll();
        return new ResponseEntity<>(shops, HttpStatus.OK);
    }

}
/**
 * *Add Fake Shop * this data are for test purpose only*
 */

//        List<Shop> shops = new ArrayList<>();
//
//        for (int i = 0; i < 1000; i++) {
//
//            Faker faker = new Faker();
//
//            Shop s = new Shop();
//            s.setId(null);
//
//            s.setAddress(
//                    new Address(null,
//                            faker.address().city(),
//                            faker.address().state(),
//                            faker.address().country(),
//                            faker.address().zipCode(),
//                            faker.address().streetName(),
//                            new Date(), new Date()));
//
//            s.setLogoUrl(faker.avatar().image());
//            s.setName(faker.name().name());
//            s.setUser(user);
//            s.setModifiedAt(new Date());
//            s.setCreatedAt(new Date());
//            shops.add(s);
//
//        }
//        List<Shop> saveAll = shopRepository.saveAll(shops);
//        return new ResponseEntity<>(saveAll, HttpStatus.CREATED);
