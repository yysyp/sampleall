package ps.demo.repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ps.demo.entity.OrderItem;

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}
