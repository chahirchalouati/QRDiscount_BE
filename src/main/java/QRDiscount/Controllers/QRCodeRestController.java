/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Controllers;

import QRDiscount.Services.QRCodeGenereatorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/qrcodes")
@AllArgsConstructor
public class QRCodeRestController {

    private final QRCodeGenereatorService codeGenereatorService;

//    @PostMapping
//    public ResponseEntity<?> post(@RequestBody Discount discount) {
//        return codeGenereatorService.discountGenerator(discount);
//    }
}
