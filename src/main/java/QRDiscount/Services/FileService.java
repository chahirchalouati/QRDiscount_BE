/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Services;

import QRDiscount.Entities.FileDB;
import QRDiscount.Exceptions.FileStorageException;
import QRDiscount.Repositories.FileDBRepository;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Chahir Chalouati
 */
@Service
@Slf4j
public class FileService {

    @Autowired
    private FileDBRepository fileDBRepository;

    private final String baseDir = "./assets";

    public FileService() throws IOException {
        boolean dirExists = Files.exists(Paths.get(baseDir).toAbsolutePath().normalize());

        if (!dirExists) {
            Files.createDirectory(Paths.get(baseDir).toAbsolutePath().normalize());
        }

    }

    /**
     * store assets file like image for shop and other
     *
     * @param file
     * @return
     * @throws IOException
     */
    @Transactional
    public FileDB storeFiles(MultipartFile file) throws IOException {

        String fileExtentions = file.getOriginalFilename().split("\\.")[1];
        // to make file unique i use  combination of instant as String an UUID api to generate unique name

        long epochSecond = Instant.now().getEpochSecond();

        String newFileName
                = //new Instant token as String
                String.valueOf(epochSecond)
                        //random UUID
                        .concat(UUID.randomUUID().toString())
                        //retreive extentions of file
                        .concat(".")
                        .concat(fileExtentions);

        String concat = baseDir
                .concat("/")
                .concat(newFileName);

        //create path for new File to store
        Path path = Paths.get(concat).toAbsolutePath().normalize();
        // copy file in base directory
        long copy = Files
                .copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        // prepare file data for database storage
        FileDB fileDB = new FileDB(null, path.toString(), newFileName, copy, new Date(), new Date());
        if (copy > 0) {
            fileDBRepository.save(fileDB);
        } else {
            throw new FileStorageException("Unable to save File ");
        }
        return fileDB;
    }

    /**
     * download File
     *
     * @param filename
     * @param request
     * @return
     */
    public ResponseEntity<?> getFile(String filename, HttpServletRequest request) {

        try {

            FileDB file = fileDBRepository.findByName(filename);

            Path path = Paths.get(file.getPath()).toAbsolutePath().normalize();

            Resource resource = new UrlResource(path.toUri());

            if (resource.exists()) {

                String contentType = request
                        .getServletContext()
                        .getMimeType(
                                resource.getFile()
                                        .getAbsolutePath());

                if (contentType == null) {
                    contentType = "application/octet-stream";
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS).cachePrivate().proxyRevalidate())
                        .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.contentLength()))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);

            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseEntity.notFound().build();
    }
}
