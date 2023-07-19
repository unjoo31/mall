package shop.mtcoding.mall.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository // 컴퍼넌트 스캔 (IoC 컨테이너에 알아서 new 해준다)
public class ProductRepository {

    @Autowired // IoC 컨테이너에서 new되어 있는걸 꺼내온다 (디펜던씨 인젝션)
    private EntityManager em;

    @Transactional // Transactional :다 알아서 transaction 시작하고 종료함
    public void save(String name, int price, int qty){
        Query query = em.createNativeQuery("insert into product_tb(name, price, qty) values(:name, :price, :qty)");
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        query.executeUpdate();
    }

    public List<Product> findAll() {
        Query query = em.createNativeQuery("select * from product_tb", Product.class);
        // 여러건일때 : getResultList, 한건일때 : getsingle
        List<Product> productList = query.getResultList();
        return productList;
    }
}