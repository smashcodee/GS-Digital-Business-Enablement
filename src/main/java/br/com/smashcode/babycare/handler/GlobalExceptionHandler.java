package br.com.smashcode.babycare.handler;

import br.com.smashcode.babycare.exception.*;
import br.com.smashcode.babycare.response.exception.CustomErrorResponse;
import br.com.smashcode.babycare.response.exception.FieldNotValidResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List<FieldNotValidResponse>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldNotValidResponse> list = new ArrayList<>();
        e.getFieldErrors().forEach(v -> {
            list.add(new FieldNotValidResponse(v.getField(), v.getDefaultMessage()));
        });
        log.error("[ MethodArgumentNotValidException ]: 400 - Bad request.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(list);
    }

    @ExceptionHandler({EmailAlreadyExistsException.class})
    public ResponseEntity<CustomErrorResponse> emailAlreadyExistsException(EmailAlreadyExistsException e) {
        var response = new CustomErrorResponse(e.getMessageError());
        log.error("[ EmailAlreadyExistsException ]: 400 - Bad request.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({AccountAlreadyExistsException.class})
    public ResponseEntity<CustomErrorResponse> accountAlreadyExistsException(AccountAlreadyExistsException e) {
        var response = new CustomErrorResponse(e.getMessageError());
        log.error("[ AccountAlreadyExistsException ]: 400 - Bad request.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({UsernameAlreadyExistsException.class})
    public ResponseEntity<CustomErrorResponse> usernameAlreadyExistsException(UsernameAlreadyExistsException e) {
        var response = new CustomErrorResponse(e.getMessageError());
        log.error("[ UsernameAlreadyExistsException ]: 400 - Bad request.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({AccountNotExistsException.class})
    public ResponseEntity<CustomErrorResponse> accountNotExistsException(AccountNotExistsException e) {
        var response = new CustomErrorResponse(e.getMessageError());
        log.error("[ AccountNotExistsException ]: 400 - Bad request.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({DateInvalidException.class})
    public ResponseEntity<CustomErrorResponse> dateInvalidException(DateInvalidException e) {
        var response = new CustomErrorResponse(e.getMessageError());
        log.error("[ DateInvalidException ]: 400 - Bad request.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<CustomErrorResponse> MissingServletRequestParameterException(MissingServletRequestParameterException e) {
        var message = "O parâmetro \"" + e.getParameterName() + "\" é obrigatório e deve ser do tipo \"" + e.getParameterType()+ "\".";
        var response = new CustomErrorResponse(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
