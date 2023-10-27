package ps.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ps.demo.common.MyClientErrorException;
import ps.demo.common.CodeEnum;
import ps.demo.common.MyUtil;
import ps.demo.common.MyServerErrorException;
import ps.demo.dto.PlaceOrderRequest;
import ps.demo.dto.PlaceOrderResponseMy;
import ps.demo.entity.*;
import ps.demo.repository.*;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private PaymentMapper paymentMapper;

    @Transactional
    public PlaceOrderResponseMy placeOrder(PlaceOrderRequest request) {

        // Validate cart
        Cart cart = cartMapper.getCartAndItems(request.getCartId());

        if(cart == null || !cart.getUserId().equals(request.getUserId())) {
            throw new MyClientErrorException(CodeEnum.INVALID_ID);
        }

        // Create order from cart
        Order order = Order.builder().userId(cart.getUserId())
                .totalPrice(cart.getTotalPrice())
                .paymentMethod(MyUtil.PAYMENT_METHOD)
                .transactionId(UUID.randomUUID().toString())
                .createdAt(MyUtil.getNowDate()).status(MyUtil.PENDING).build();
        orderMapper.insert(order);

        // Validate & update stock & create order items
        // TODO: To use batch update.
        for(CartItem item : cart.getItems()) {
            Stock stock = stockMapper.selectById(item.getProductId());
            if(stock.getQuantity() < item.getQuantity()) {
                throw new MyServerErrorException(CodeEnum.NO_ENOUGH_STOCK);
            }
            Integer originStockQuantity = stock.getQuantity();
            stock.setQuantity(originStockQuantity - item.getQuantity());
            int updated = stockMapper.update(stock, new QueryWrapper<Stock>()
                    .eq("product_id", item.getProductId())
                    .eq("quantity", originStockQuantity));
            //Make sure the record updating is not conflicting.
            if (updated != 1) {
                throw new MyServerErrorException(CodeEnum.CONCURRENT_OPERATION);
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setProductId(item.getProductId());
            orderItem.setTotalPrice(item.getTotalPrice());
            orderItemMapper.insert(orderItem);
        }

        //Store payment information
        Payment payment = Payment.builder().orderId(order.getId())
                .createdAt(MyUtil.getNowDate())
                .amount(cart.getTotalPrice())
                .cardNo(request.getCardNo())
                .cvcNo(request.getCvc())
                .expiryDate(request.getExpiryDate()).build();
        paymentMapper.insert(payment);

        //Clean Cart and cartItems
        cartItemMapper.deleteBatchIds(cart.getItems().stream().map(e -> e.getId())
                .collect(Collectors.toList()));
        cartMapper.deleteById(cart.getId());

        // Build response
        PlaceOrderResponseMy.Data data = PlaceOrderResponseMy.Data.builder()
                .orderId(order.getId()).total(order.getTotalPrice())
                .status(MyUtil.PENDING).build();
        return new PlaceOrderResponseMy(data);

    }

}
