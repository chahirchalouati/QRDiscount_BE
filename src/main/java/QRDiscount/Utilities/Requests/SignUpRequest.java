/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Utilities.Requests;

import QRDiscount.Validation.EmailExists;
import QRDiscount.Validation.PasswordConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Chahir Chalouati
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class SignUpRequest {

    @NotBlank(message = "First name can't be blank")
    private String username;
    @NotBlank(message = "Last name can't be blank")
    private String fullname;
    @Email(message = "Invalid e-mail address")
    @EmailExists
    private String email;
    @PasswordConstraint
    private String password;
    @NotBlank(message = "Role can't be blank")
    private String role;

}
