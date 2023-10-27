package ps.demo.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderRequest implements Serializable {

    @Positive(message = "userId is invalid")
    private Long userId;

    @Positive(message = "cartId is invalid")
    private Long cartId;

    @NotNull(message = "cardNo is mandatory")
    private String cardNo;

    @NotNull(message = "expiryDate is mandatory")
    private Date expiryDate;

    @NotNull(message = "cvc is mandatory")
    private String cvc;

}
