/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Validation;

import QRDiscount.Repositories.UserRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Chahir Chalouati
 */
public class EmailExistsValidator implements
        ConstraintValidator<EmailExists, String> {

    private final UserRepository repository;

    public EmailExistsValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(EmailExists constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        System.out.println(repository.findByEmail(email).toString());
        return repository.findByEmail(email) == null;
    }

}
