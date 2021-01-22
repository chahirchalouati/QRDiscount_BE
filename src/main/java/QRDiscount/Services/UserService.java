/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Services;

import QRDiscount.Entities.AppUser;
import QRDiscount.Repositories.RoleRepository;
import QRDiscount.Repositories.UserRepository;
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
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public ResponseEntity create(AppUser user, String role) {
        user.getRoles().add(roleRepository.findByRole(role));
        AppUser save = userRepository.save(user);
        return new ResponseEntity(save, HttpStatus.CREATED);
    }

    public ResponseEntity delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResponseEntity update(AppUser user, Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);

    }

}
