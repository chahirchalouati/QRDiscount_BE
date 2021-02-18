package QRDiscount.Services;

import QRDiscount.Entities.Discount;
import QRDiscount.Entities.Shop;
import QRDiscount.Entities.UserDiscount;
import QRDiscount.Exceptions.EntityExceptions.EntityNotFoundException;
import QRDiscount.Repositories.DiscountRepository;
import QRDiscount.Repositories.ShopRepository;
import QRDiscount.Repositories.UserRepository;
import QRDiscount.Utilities.Projections.DiscountPro;
import QRDiscount.Utilities.QRCodeGenereator;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final QRCodeGenereator codeGenereator;

    /**
     * Generate discount for parent discount
     *
     * @param discount
     * @return
     */
    @Transactional
    public ResponseEntity<?> discountGenerator(DiscountPro discount) {
        Shop one = shopRepository.getOne(discount.getIdShop());

        if (one == null) {
            throw new EntityNotFoundException("Unable to Found Shop with id " + discount.getIdShop());

        }
        String filename = codeGenereator.createQRCodeImage(discount.toString(), 300, 300);

        if (filename != null) {

            Discount ds = new Discount();
            ds.setShop(one);
            ds.setUrlDiscount("/files/".concat(filename));
            ds.setValidityFrom(discount.getValidFrom());
            ds.setValidityTo(discount.getValidTo());
            ds.setCreatedAt(new Date());
            ds.setModifiedAt(new Date());
            ds.setDescriptionDiscount(discount.getDescription());
            Discount save = discountRepository.save(ds);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Generate discount for Child
     *
     * @param userDiscount
     * @return
     */
    public ResponseEntity<?> userDiscountGenerator(UserDiscount userDiscount) {
        return null;
    }

}
