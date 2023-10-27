package ps.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ps.demo.entity.Product;
import ps.demo.repository.ProductMapper;

/**
 * //@Cacheable将在执行方法之前( #result还拿不到返回值)判断condition，如果返回true，则查缓存；
 * @Cacheable(value = "user", key = "#id", condition = "#id lt 10")
 * public User conditionFindById(final Long id)
 *
 * //@CachePut将在执行完方法后（#result就能拿到返回值了）判断condition，如果返回true，则放入缓存；
 * @CachePut(value = "user", key = "#id", condition = "#result.username ne 'zhang'")
 * public User conditionSave(final User user)
 *
 * //@CachePut将在执行完方法后（#result就能拿到返回值了）判断unless，如果返回false，则放入缓存；（即跟condition相反）
 * @CachePut(value = "user", key = "#user.id", unless = "#result.username eq 'zhang'")
 * public User conditionSave2(final User user)
 *
 * //@CacheEvict， beforeInvocation=false表示在方法执行之后调用（#result能拿到返回值了）；且判断condition，如果返回true，则移除缓存；
 * @CacheEvict(value = "user", key = "#user.id", beforeInvocation = false, condition = "#result.username ne 'zhang'")
 * public User conditionDelete(final User user)
 *
 * condition 不指定相当于 true，unless 不指定相当于 false
 * 　　　　当 condition = false，一定不会缓存；
 * 　　　　当 condition = true，且 unless = true，不缓存；
 * 　　　　当 condition = true，且 unless = false，缓存；
 *
 * id—>user; username—>id; email—>id;
 * @Cacheable(value="cacheName", key="#user.username", cacheValue="#caches[0].get(#caches[0].get(#username).get())")
 * public User findByUsername(String username)
 */
@Slf4j
@Service
@CacheConfig(cacheNames = {"myCache"})
public class CacheService {

    @Autowired
    private ProductMapper productMapper;

    /*
    If the return product is null or product.id is null or product.id is 0 or product.name is null or product.name length is 0
    then Not to cache the result.
     */
    @Cacheable(key = "targetClass+'-Prod-'+#id",
            unless = "#result?.id == null or #result?.id == 0 or #result?.name == null or #result?.name?.length == 0")
    public Product findProductById(Long id) {
        log.info("Actually find product by id, id={}", id);
        Product product = productMapper.selectById(id);
        return product;
    }

    @CacheEvict(key = "targetClass+'-Prod-'+#p0")
    public void deleteProductFromCache(Long id) {
        log.info("Delete product id={} from cache", id);
        //productMapper.deleteById(id);
    }


}
