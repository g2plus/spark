package top.arhi.exception;



import lombok.Data;
import top.arhi.model.vo.ErrorResult;


@Data
public class BusinessException extends RuntimeException{

    private ErrorResult errorResult;

    public BusinessException(ErrorResult errorResult){
        super(errorResult.getMessage());
        this.errorResult = errorResult;
    }
}
