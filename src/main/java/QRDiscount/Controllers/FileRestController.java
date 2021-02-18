/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Controllers;

import QRDiscount.Services.FileService;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileRestController {

    private final FileService fileService;

    @GetMapping("/{filename}")
    public ResponseEntity<?> get(@PathVariable(name = "filename") String filename, HttpServletRequest request) {

        System.out.println(" \n\n\n\n New File Send \n\n\n\n");

        return fileService.getFile(filename, request);
    }

}
