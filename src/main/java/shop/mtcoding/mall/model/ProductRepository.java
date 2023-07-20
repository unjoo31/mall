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
    // EntityManager : JPA(Java Persistence API)에서 가장 중요한 인터페이스 중 하나로,
    // 영속성 컨텍스트(Persistence Context)와 데이터베이스 간의 상호작용을 관리하는 역할을 한다
    // JPA 엔티티와 데이터베이스 사이의 매핑을 처리하고 데이터베이스에 대한 CRUD(Create, Read, Update, Delete) 작업을 수행합니다.
    private EntityManager em;

    // Transactional :다 알아서 transaction 시작하고 종료함
    // insert, update, delete
    @Transactional
    public void save(String name, int price, int qty){
        // 이 메서드는 Native SQL 쿼리를 생성하기 위해 EntityManager를 사용합니다.
        // Native SQL 쿼리는 직접 SQL 문법을 사용하여 데이터베이스와 상호작용하는 방식입니다.
        Query query = em.createNativeQuery("insert into product_tb(name, price, qty) values(:name, :price, :qty)");
        // 이 메서드를 통해 Native SQL 쿼리에 파라미터를 바인딩합니다.
        // :name, :price, :qty와 같은 이름을 가진 파라미터를 쿼리에 설정하고, 실제 값은 메서드의 매개변수로부터 받아와 설정합니다.
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        // 이 메서드를 호출하여 Native SQL 쿼리를 실행하고 데이터베이스에 영향을 미치는 작업을 수행합니다.
        // 위의 코드에서는 데이터를 삽입(insert)하는 작업을 수행하고 있습니다.
        query.executeUpdate();
    }

    public List<Product> findAll() {
        Query query = em.createNativeQuery("select * from product_tb", Product.class);
        // getResultList() 메서드는 JPA(Java Persistence API)에서 사용되는 메서드 중 하나로, 쿼리를 실행하여 데이터베이스에서 가져온 결과를 리스트로 반환합니다.
        List<Product> productList = query.getResultList();
        return productList;
    }

    // 쉬운 방법
    public Product fingById(int id) {
        Query query = em.createNativeQuery("select * from product_tb where id = :id", Product.class);
        // 바인딩해준다 : 데이터를 화면에 준다?
        query.setParameter("id", id);
        // getSingleResult() 메서드는 JPA(Java Persistence API)에서 사용되는 메서드 중 하나로, 하나의 데이터를 반환할때 사용한다
        // 리턴타입이 오브젝트라서 다운캐스팅 해준다
        Product product = (Product) query.getSingleResult();
        return product;
    }

    // 귀찮은 방법
    public Product findById2(int id) {
        Query query = em.createNativeQuery("select * from product_tb where id = :id");
        query.setParameter("id", id);
        // row가 1건
        // 1, 바나나, 1000, 50
        Object[] object = (Object[]) query.getSingleResult();
        int id2 = (int) object[0];
        String name2 = (String) object[1];
        int price2 = (int) object[2];
        int qty2 = (int) object[3];

        Product product = new Product();
        product.setId(id2);
        product.setName(name2);
        product.setPrice(price2);
        product.setQty(qty2);
        return product;
    }

    @Transactional
    public void deleteById(int id){
        Query query = em.createNativeQuery("delete from product_tb where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public void update(String name, int price, int qty){
        Query query = em.createNativeQuery("update product_tb set price = :price, qty = :qty where name = :name");
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        query.executeUpdate();
    }
}