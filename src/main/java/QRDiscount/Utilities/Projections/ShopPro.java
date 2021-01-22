/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Utilities.Projections;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Chahir Chalouati
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopPro {

    @NotBlank(message = "")
    private String name;

    @NotNull(message = "")
    private MultipartFile file;

    @NotBlank(message = "")
    private String city;

    @NotBlank(message = "")
    private String provaince;

    @NotBlank(message = "")
    private String country;

    @NotBlank(message = "")
    private String CAP;

    @NotBlank(message = "")
    private String avenue;

}
