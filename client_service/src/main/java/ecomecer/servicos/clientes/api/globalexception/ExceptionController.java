package ecomecer.servicos.clientes.api.globalexception;

import ecomecer.servicos.clientes.domain.exception.ClientNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    private String logTime = LocalDateTime.now().toString();

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatusCode.valueOf(400));
        problem.setTitle("Error validating the fields entered");
        problem.setDetail("One or more fields are invalid. Fill in correctly and try again.");
        problem.setProperty("timestamp", Instant.now());

        Map<String, String> errors = getErrorFields(ex);
        errors.forEach((fieldName, message) -> {
            problem.setProperty(fieldName, message);
        });

        log.error("[{}] - [ExceptionController] - MethodArgumentNotValidException: Error validating the fields entered", logTime);
        errors.forEach((fieldName, message) -> {
            log.error("[{}] - [ExceptionController] - Invalid field: {} - Message: {}", logTime, fieldName, message);
        });

        return new ResponseEntity<Object>(problem, status);
    }

    private Map<String, String> getErrorFields(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ProblemDetail handlerClientNotFoundException(ClientNotFoundException exception){
        log.error("[{}] - [ExceptionController] - ClientNotFoundException: Client not found in database", logTime);

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,exception.getMessage());

        detail.setTitle("Client not found");
        detail.setProperty("timestamp", Instant.now());

        return detail;
    }
}
