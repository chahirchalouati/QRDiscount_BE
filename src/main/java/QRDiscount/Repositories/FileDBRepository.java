/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Repositories;

import QRDiscount.Entities.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Chahir Chalouati
 */
public interface FileDBRepository extends JpaRepository<FileDB, Long> {

    public FileDB findByName(String filename);

}
