/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Utilities;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import io.micrometer.core.instrument.util.StringUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Chahir Chalouati
 */
@Slf4j
public class QRCodeGenereator {

    /**
     * create QR Code Image
     *
     * @param dir
     * @param content
     * @param width
     * @param height
     * @return path to file
     */
    public static String createQRCodeImage(String dir, String content, int width, int height) {
        // id qr
        // link validity
        //

        try {
            File dirFile = null;
            if (StringUtils.isNotBlank(dir)) {
                dirFile = new File(dir);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
            }
            String qrcodeFormat = "png";
            HashMap<EncodeHintType, String> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            File file = new File(Paths.get(dir).toAbsolutePath().toString(), UUID.randomUUID().toString() + "." + qrcodeFormat);
            MatrixToImageWriter.writeToPath(bitMatrix, qrcodeFormat, file.toPath());
            return file.getAbsolutePath();
        } catch (WriterException | IOException e) {
            log.error("Unable to create QR Code ", e);
        }
        return "";
    }
}
