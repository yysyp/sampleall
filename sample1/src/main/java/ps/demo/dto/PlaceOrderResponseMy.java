package ps.demo.dto;

import lombok.*;
import ps.demo.common.MyBaseResponse;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderResponseMy extends MyBaseResponse {

    private Data data;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    @Builder
    public static class Data {
        private Long orderId;
        private BigDecimal total;
        private String status;
    }

}
