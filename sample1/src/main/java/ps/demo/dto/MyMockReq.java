

package ps.demo.dto;

import lombok.*;

import javax.validation.constraints.NotNull;


@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MyMockReq {

    @NotNull(message = "uri is mandatory")
    private String uri;
    private Boolean regexMatch;
    @NotNull(message = "method is mandatory")
    private String method;
    private Integer status;
    private String headers;
    private String body;

}





