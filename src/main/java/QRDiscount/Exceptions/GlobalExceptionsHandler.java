/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Exceptions;

import QRDiscount.Exceptions.EntityExceptions.EntityNotFoundException;
import QRDiscount.Exceptions.EntityExceptions.PasswordInvalidException;
import QRDiscount.Exceptions.EntityExceptions.UserExistException;
import QRDiscount.Utilities.ApiResponse.ApiError;
import QRDiscount.Utilities.ApiResponse.ErrorEntityValidation;
import QRDiscount.Utilities.ApiResponse.ErrorsValidation;
import com.google.zxing.WriterException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Chahir Chalouati
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionsHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<?> entityNotFoundException(EntityNotFoundException enfe) {
        log.error(enfe.getLocalizedMessage(), enfe);
        return new ResponseEntity<>(new ApiError(enfe.getMessage(), HttpStatus.NOT_FOUND, new Date()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<?> httpMessageNotReadableException(HttpMessageNotReadableException hmnre) {
        log.error(hmnre.getLocalizedMessage(), hmnre);
        return new ResponseEntity<>(new ApiError(hmnre.getMessage(), HttpStatus.BAD_REQUEST, new Date()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    ResponseEntity<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException hrmnse) {
        log.error(hrmnse.getLocalizedMessage(), hrmnse);
        return new ResponseEntity<>(new ApiError(hrmnse.getMessage(), HttpStatus.BAD_REQUEST, new Date()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserExistException.class)
    ResponseEntity<?> UserExistException(UserExistException uee) {
        log.error(uee.getLocalizedMessage(), uee);
        return new ResponseEntity<>(new ApiError(uee.getMessage(), HttpStatus.BAD_REQUEST, new Date()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<?> DataIntegrityViolationException(DataIntegrityViolationException dive) {

        log.error(dive.getLocalizedMessage(), dive);
        return new ResponseEntity<>(new ApiError(dive.getMessage(), HttpStatus.BAD_REQUEST, new Date()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LockedException.class)
    ResponseEntity<?> LockedException(LockedException dive) {
        log.error(dive.getLocalizedMessage(), dive);
        return new ResponseEntity<>(new ApiError(dive.getMessage(), HttpStatus.BAD_REQUEST, new Date()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<?> BadCredentialsException(BadCredentialsException dive) {
        log.error(dive.getLocalizedMessage(), dive);
        return new ResponseEntity<>(new ApiError(dive.getMessage(), HttpStatus.BAD_REQUEST, new Date()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<?> ConstraintViolationException(ConstraintViolationException dive) {
        List<ErrorEntityValidation> collect = dive.getConstraintViolations().stream()
                .map(er -> new ErrorEntityValidation(er.getPropertyPath().toString(), er.getMessage(),
                er.getInvalidValue().toString()))
                .collect(Collectors.toList());

        log.error(dive.getLocalizedMessage(), dive);
        return new ResponseEntity<>(new ErrorsValidation("Invalid Fields", collect), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordInvalidException.class)
    ResponseEntity<?> PasswordInvalidException(PasswordInvalidException dive) {
        log.error(dive.getLocalizedMessage(), dive);
        return new ResponseEntity<>(new ApiError(dive.getMessage(), HttpStatus.BAD_REQUEST, new Date()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WriterException.class)
    ResponseEntity<?> WriterException(WriterException we) {
        log.error(we.getLocalizedMessage(), we);
        return new ResponseEntity<>(new ApiError(we.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, new Date()), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(FileStorageException.class)
    ResponseEntity<?> FileStorageException(FileStorageException fse) {
        log.error(fse.getLocalizedMessage(), fse);
        return new ResponseEntity<>(new ApiError(fse.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, new Date()), HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
