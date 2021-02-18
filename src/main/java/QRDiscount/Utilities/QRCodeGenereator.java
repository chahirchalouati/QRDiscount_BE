/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Utilities;

import QRDiscount.Entities.FileDB;
import QRDiscount.Exceptions.FileStorageException;
import QRDiscount.Repositories.FileDBRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Chahir Chalouati
 */
@Slf4j
@Component
public class QRCodeGenereator {

    @Autowired
    private FileDBRepository fileDBRepository;

    private final String baseDir = "./codes";

    public QRCodeGenereator() throws IOException {
        boolean dirExists = Files.exists(Paths.get(baseDir).toAbsolutePath().normalize());

        if (!dirExists) {
            Files.createDirectory(Paths.get(baseDir).toAbsolutePath().normalize());
        }

    }

    /**
     * create QR Code Image
     *
     * @param dir
     * @param content
     * @param width
     * @param height
     * @return path to file
     */
    public String createQRCodeImage(String content, int width, int height) {

        try {

            long epochSecond = Instant.now().getEpochSecond();

            String fileExtentions = "jpg";
            String fileName
                    = //new Instant token as String
                    String.valueOf(epochSecond)
                            //random UUID
                            .concat(UUID.randomUUID().toString())
                            //retreive extentions of file
                            .concat(".")
                            .concat(fileExtentions);

            String concat = baseDir
                    .concat("/")
                    .concat(fileName);

            HashMap<EncodeHintType, String> hints = new HashMap<>();

            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            BitMatrix bitMatrix = new MultiFormatWriter()
                    .encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            BufferedImage toBufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(toBufferedImage, fileExtentions, os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());

            //create path for new File to store
            Path path = Paths.get(concat).toAbsolutePath().normalize();

            // copy file in base directory
            long copy = Files.copy(is, path, StandardCopyOption.REPLACE_EXISTING);

            // prepare file data for database storage
            FileDB fileDB = new FileDB(null, path.toString(), fileName, copy, new Date(), new Date());

            if (copy > 0) {
                fileDBRepository.save(fileDB);
            } else {
                throw new FileStorageException("Unable to save File ");
            }

            return fileName;
        } catch (WriterException | IOException e) {

            log.error("Unable to create QR Code ", e);
            return null;
        }

    }
}
