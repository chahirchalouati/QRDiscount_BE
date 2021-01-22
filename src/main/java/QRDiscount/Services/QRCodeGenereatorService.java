package QRDiscount.Services;

import QRDiscount.Entities.UserDiscount;
import QRDiscount.Repositories.DiscountRepository;
import QRDiscount.Repositories.ShopRepository;
import QRDiscount.Repositories.UserRepository;
import QRDiscount.Utilities.Projections.DiscountPro;
import QRDiscount.Utilities.QRCodeGenereator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chahir Chalouati
 */
@Service
@AllArgsConstructor
@Slf4j
public class QRCodeGenereatorService {

    private final String baseDir = "./QrCodes";

    private final UserRepository userRepository;

    private final DiscountRepository discountRepository;

    private final ShopRepository shopRepository;

    /**
     * Generate discount for parent discount
     *
     * @param discount
     * @return
     */
    public ResponseEntity<?> discountGenerator(DiscountPro discount) {

        String createQRCodeImage = QRCodeGenereator.createQRCodeImage(baseDir, discount.toString(), 300, 300);
        // save to file
        // save to db

        return ResponseEntity.ok(createQRCodeImage);
    }

    /**
     * Generate discount for Child <Discount>
     *
     * @param userDiscount
     * @return
     */
    public ResponseEntity<?> userDiscountGenerator(UserDiscount userDiscount) {
        return null;
    }

}
