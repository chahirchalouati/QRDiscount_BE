/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Controllers;

import QRDiscount.Services.DiscountService;
import QRDiscount.Services.QRCodeGenereatorService;
import QRDiscount.Utilities.Projections.DiscountPro;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/discounts")
@AllArgsConstructor
public class DiscountRestController {

    private final QRCodeGenereatorService codeGenereatorService;

    private final DiscountService discountService;

    @GetMapping
    public ResponseEntity<?> getDiscounts() {
        return discountService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody DiscountPro discount) {
        return codeGenereatorService.discountGenerator(discount);
    }

    //genera offerta
    //trovare l'offerta
    //modificare l'offerta
    //cancellare l'offerta
}
