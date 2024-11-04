package com.jungle.tt.exception
import com.jungle.tt.status.EnumStatus
import com.jungle.tt.dto.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class CustomExceptionHandler {
    // 요청 파라미터의 유효성 검증에 실패했을 때 발생하는 예외를 처리.
    // 에러 필드와 메시지를 추출하여 어떤 필드에서 오류가 발생했는지 상세한 정보를 제공.
    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun methodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<BaseResponse<Map<String, String>>>{
        val errors = mutableMapOf<String, String>()
        ex.bindingResult.allErrors.forEach{ error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.defaultMessage
            errors[fieldName] = errorMessage ?: "Not Exception Message"
        }

        return ResponseEntity(BaseResponse(EnumStatus.ERROR.name, errors, EnumStatus.ERROR.msg), HttpStatus.BAD_REQUEST)
    }


    // 사용자 정의 예외로, 잘못된 입력이 있을 때 발생
    @ExceptionHandler(InvalidInputException::class)
    protected fun invalidInputException(ex: InvalidInputException): ResponseEntity<BaseResponse<Map<String, String>>>{
        val errors = mapOf(ex.fieldName to (ex.message ?: "Not Exception Message"))
        return ResponseEntity(BaseResponse(EnumStatus.ERROR.name, errors, EnumStatus.ERROR.msg), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(BadCredentialsException::class)
    protected fun badCredentialsException(ex: BadCredentialsException):
            ResponseEntity<BaseResponse<Map<String, String>>> {
        val errors = mapOf("로그인 실패" to "아이디 혹은 비밀번호를 다시 확인하세요.")
        return ResponseEntity(BaseResponse(
            EnumStatus.ERROR.name,
            errors,
            EnumStatus.ERROR.msg
        ), HttpStatus.BAD_REQUEST)
    }

    // 위에서 처리되지 않은 모든 예외를 처리
    @ExceptionHandler(Exception::class)
    protected fun defaultException(ex: Exception): ResponseEntity<BaseResponse<Map<String, String>>>{
        val errors = mapOf("미처리 에러" to (ex.message ?: "Not Exception Message"))
        return ResponseEntity(BaseResponse(EnumStatus.ERROR.name, errors, EnumStatus.ERROR.msg), HttpStatus.BAD_REQUEST)
    }

}