package QRDiscount.Controllers;

import QRDiscount.Services.AuthenticationServiceImpl;
import QRDiscount.Utilities.Requests.SignInRequest;
import QRDiscount.Utilities.Requests.SignUpRequest;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInRequest request) {
        if (request.getEmail() == null || request.getPassword() == null) {
            throw new BadCredentialsException("Bad credentials");
        }
        return authenticationServiceImpl.signIn(request);
    }

    /**
     * User Sign-Up
     *
     * @param request
     * @return new registered User
     */
    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationServiceImpl.signUp(request);
    }
}
