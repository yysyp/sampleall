package ps.demo.common;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyBaseResponse implements Serializable {

    @Builder.Default
    protected String code = CodeEnum.SUCCESS.getCode();
    @Builder.Default
    protected String message = CodeEnum.SUCCESS.getDetailedMessage();
    protected String detail;
    protected String trace;
    protected String correlationId;
    protected String instance;
    @Builder.Default
    protected String timestamp = MyUtil.getNowDateStr();;
    protected String path;

    public static MyBaseResponse success() {
        return new MyBaseResponse();
    }

    public static MyBaseResponse of(CodeEnum codeEnum) {
        return MyBaseResponse.builder().code(codeEnum.getCode())
                .message(codeEnum.getDetailedMessage()).build();
    }

}
