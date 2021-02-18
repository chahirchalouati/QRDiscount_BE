/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Controllers;

import QRDiscount.Services.DiscountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Chahir Chalouati
 */
@Controller
@AllArgsConstructor
public class PageController {

    private final DiscountService discountService;

    @RequestMapping("/qrdiscount")
    public String page(Model model, @RequestParam(name = "id", required = true) Long id) {
        System.out.println(id);
        model.addAttribute("discount", discountService.findById(id));
        return "index";
    }

}
