package ps.demo.common;

public class MyClientErrorException extends RuntimeException {
    private CodeEnum codeEnum;

    public CodeEnum getCodeEnum() {
        return this.codeEnum;
    }

    public MyClientErrorException(CodeEnum codeEnum) {
        super(codeEnum.getDetailedMessage());
        this.codeEnum = codeEnum;
    }

    public MyClientErrorException(CodeEnum codeEnum, Throwable cause) {
        super(codeEnum.getDetailedMessage(), cause);
        this.codeEnum = codeEnum;
    }
}
