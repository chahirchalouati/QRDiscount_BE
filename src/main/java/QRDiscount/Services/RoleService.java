/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Services;

import QRDiscount.Entities.AppRole;
import QRDiscount.Repositories.RoleRepository;
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
public class RoleService {

    private final RoleRepository roleRepository;

    public ResponseEntity createRole(AppRole role) {
        AppRole save = roleRepository.save(role);
        return new ResponseEntity(save, HttpStatus.CREATED);
    }
}
