package ps.demo.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ps.demo.entity.Cart;
import ps.demo.repository.CartMapper;
import ps.demo.repository.UserMapper;

/**
 * TODO: to add more test cases. This is just a demo.
 * Quick test
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Ignore
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CartMapper cartMapper;

    @Test
    public void testSelect() {
        Cart cart = cartMapper.getCartAndItems(1L);
        cart.getItems().forEach(System.out::println);

    }


}
