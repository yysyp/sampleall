package ps.demo.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ps.demo.entity.CartItem;

@Mapper
public interface CartItemMapper extends BaseMapper<CartItem> {

}
