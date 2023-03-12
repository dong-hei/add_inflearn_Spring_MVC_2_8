package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice //RestController + ControllerAdvice,모든 컨트롤러에 적용된다.
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST) //에러넘버 바꾸기
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[ExceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }//실무에서 많이쓰이는 Handler

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("[ExceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-ex", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    } //실무에서 많이쓰이는 Handler

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //에러넘버 바꾸기
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[ExceptionHandler] ex", e);
        return new ErrorResult("EX", "내부 오류");

    } //최상위 Exception (UserException,IllegalArgumentException가 처리못한 예외를 처리해준다.)

}
