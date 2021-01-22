/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Utilities.Interfaces;

import QRDiscount.Utilities.Requests.SignInRequest;
import QRDiscount.Utilities.Requests.SignUpRequest;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Chahir Chalouati
 */
public interface AuthenticationService {

    ResponseEntity<?> signUp(@Valid SignUpRequest request);

    ResponseEntity<?> signIn(@Valid SignInRequest request);
}
