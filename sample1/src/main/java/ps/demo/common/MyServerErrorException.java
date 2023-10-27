package ps.demo.common;

public class MyServerErrorException extends RuntimeException {
    private CodeEnum codeEnum;

    public CodeEnum getCodeEnum() {
        return this.codeEnum;
    }

    public MyServerErrorException(CodeEnum codeEnum) {
        super(codeEnum.getDetailedMessage());
        this.codeEnum = codeEnum;
    }
    public MyServerErrorException(CodeEnum codeEnum, Throwable cause) {
        super(codeEnum.getDetailedMessage(), cause);
        this.codeEnum = codeEnum;
    }

}
