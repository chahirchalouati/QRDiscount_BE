package QRDiscount.Controllers;

import QRDiscount.Services.AuthenticationServiceImpl;
import QRDiscount.Utilities.Requests.SignInRequest;
import QRDiscount.Utilities.Requests.SignUpRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/auths")
@Slf4j
@AllArgsConstructor
public class AuthRestController {

    private final AuthenticationServiceImpl authenticationServiceImpl;

    /**
     * User Sign-In
     *
     * @param request
     * @return JWTResponse with generated Token
     */
    @PostMapping(value = "/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest request) {

        System.out.println(request.toString());
        return authenticationServiceImpl.signIn(request);
    }

    /**
     * User Sign-Up
     *
     * @param request
     * @return new registered User
     */
    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) {

        System.out.println(request.toString());

        return authenticationServiceImpl.signUp(request);
    }
}
