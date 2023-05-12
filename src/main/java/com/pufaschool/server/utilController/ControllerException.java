package com.pufaschool.server.utilController;

import com.pufaschool.conn.exception.*;
import com.pufaschool.conn.result.Result;
import com.pufaschool.conn.result.Status;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 统一异常处理
 */
@RestControllerAdvice
public class ControllerException {


    /**
     * 邮箱已存在异常
     *
     */
    @ExceptionHandler(EmailExistException.class)
    public Result emailExistException(EmailExistException e){

        return Result.error(Status.EMAIL_EXIST_ERR,e.getMessage());
    }
    /**
     * 用户未找到异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UsernameNotFoundExceptions.class)
    public Result usernameNotFoundExceptions(UsernameNotFoundExceptions e) {



        return Result.error(Status.USER_ERR, e.getMessage());
    }

    /**
     * 请求头的token为null异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(JwtIsNullException.class)
    public Result jwtIsNullException(JwtIsNullException e) {


        return Result.error(Status.TOKEN_NULL, e.getMessage());
    }

    /**
     * 用户被冻结异常
     */
    @ExceptionHandler(UsernameFreezeException.class)
    public Result usernameFreezeException(UsernameFreezeException e) {

        return Result.error(Status.USER_FROZEN, e.getMessage());
    }

    /**
     * 用户名重复注册异常
     */
    @ExceptionHandler(UsernameRepeatException.class)
    public Result usernameRepeatException(UsernameRepeatException e) {

        return Result.error(Status.USER_REPEAT, e.getMessage());
    }

    /**
     * 方法参数无效异常(数据校验)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuffer errStr = new StringBuffer();

        BindingResult bindingResult = e.getBindingResult();
        boolean hasErrors = bindingResult.hasErrors();
        if (hasErrors) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError allError : allErrors) {
                FieldError err = (FieldError) allError;
                String field = err.getField();
                String defaultMessage = allError.getDefaultMessage();
                errStr.append("[").append(field)
                        .append("]:")
                        .append(defaultMessage)
                        .append(";");
            }
        }
        String substring = errStr.toString().substring(errStr.toString().indexOf(":"), errStr.toString().indexOf(";"));

        return Result.error(Status.ERROR, substring);
    }

    @ExceptionHandler(PasswordErrorException.class)
    public Result passwordErrorException(PasswordErrorException e) {

        return Result.error(Status.TOKEN_NULL , e.getMessage());
    }

    /**
     * token异常(过期或者错误)
     */
    @ExceptionHandler(MalformedJwtException.class)
    public Result malformedJwtException(MalformedJwtException e) {

        return Result.error(Status.TOKEN_ERR, e.getMessage());
    }

    /**
     * 角色重复异常
     */
    @ExceptionHandler(RoleRepetitionException.class)
    public Result roleRepetition(RoleRepetitionException e) {

        return Result.error(Status.ROLE_REPETITION, e.getMessage());
    }

    /**
     * 验证码异常
     */
    @ExceptionHandler(YZMException.class)
    public Result yzmException(YZMException e) {

        return Result.error(Status.YZM_ERR, e.getMessage());
    }

    /**
     * 邮箱未找到异常
     */
    @ExceptionHandler(EmailNotFoundException.class)
    public Result emailNotFoundException(EmailNotFoundException e) {

        return Result.error(Status.EMAIL_NOT_FOUND, e.getMessage());
    }

    /**
     * 文件格式异常
     */
    @ExceptionHandler(FileFormatException.class)
    public Result fileFormatException(FileFormatException e) {

        return Result.error(Status.FILE_FORMAT_ERR, e.getMessage());
    }

    /**
     * 文件超出最大值异常
     */
    @ExceptionHandler(FileOverFlowMaxException.class)
    public Result fileOverFlowException(FileOverFlowMaxException e) {

        return Result.error(Status.FILE_OVERFLOW_ERR, e.getMessage());
    }

    /**
     * 非管理员异常
     */
    @ExceptionHandler(NonAdminException.class)
    public Result nonAdminException(NonAdminException e) {

        return Result.error(Status.NON_ADMIN, e.getMessage());
    }




}
