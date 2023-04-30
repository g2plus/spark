package cn.tanhua.server.exception;


import cn.tanhua.model.vo.ErrorResult;
import lombok.Data;


@Data
public class BusinessException extends RuntimeException{

    private ErrorResult errorResult;

    public BusinessException(ErrorResult errorResult){
        super(errorResult.getMessage());
        this.errorResult = errorResult;
    }
}
