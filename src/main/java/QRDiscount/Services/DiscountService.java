/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Services;

import QRDiscount.Entities.Discount;
import QRDiscount.Repositories.DiscountRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chahir Chalouati
 */
@Service
@AllArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;

    /**
     * find All discounts
     *
     * @return List Of Discount
     */
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(discountRepository.findAll(), HttpStatus.OK);
    }

    /**
     * find All discounts
     *
     * @param id
     * @return List Of Discount
     */
    public Discount findById(Long id) {
        return discountRepository.getOne(id);
    }

}
