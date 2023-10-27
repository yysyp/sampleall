package ps.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ps.demo.common.MyBaseController;
import ps.demo.dto.PlaceOrderRequest;
import ps.demo.dto.PlaceOrderResponseMy;
import ps.demo.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController extends MyBaseController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Order to place order for current user's cart")
    @PostMapping("/place-order")
    public PlaceOrderResponseMy placeOrder(@RequestBody @Validated PlaceOrderRequest request) {
        PlaceOrderResponseMy response = orderService.placeOrder(request);
        return response;

    }

}
