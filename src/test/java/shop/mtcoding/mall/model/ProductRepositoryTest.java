package shop.mtcoding.mall.model;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

// 특정 설정 클래스를 현재 테스트 클래스에 포함시킬 수 있습니다.
// ProductRepository.class, SellerRepository.class를 테스트 클래스에 포함시킨다
@Import({
        ProductRepository.class,
        SellerRepository.class
})
// JPA 관련 로직을 테스트를 하겠다
@DataJpaTest // T -> DS -> C -> ( R -> DB )
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    // 해당 메서드를 테스트 메서드로 지정하는 역할을 합니다.
    @Test
    public void findByIdDTO_test(){
        // given : 테스트를 하기 위해서 필요한 데이터 만들기
        productRepository.save("바나나", 5000, 50);

        // when : 테스트 진행
        ProductDTO productDTO = productRepository.findByIdDTO(1);
        System.out.println(productDTO.getId());
        System.out.println(productDTO.getName());
        System.out.println(productDTO.getPrice());
        System.out.println(productDTO.getQty());
        System.out.println(productDTO.getDes());
    }

    @Test
    public void findByIdJoinSeller_test(){
        // given (테스트를 하기 위해서 필요한 데이터 만들기)
        sellerRepository.save("홍길동", "ssar@nate.com");
        productRepository.saveWithFK("바나나", 5000, 50, 1);

        // when (테스트 진행)
        Product product = productRepository.findByIdJoinSeller(1);

        // then
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getQty());
        System.out.println("------- "+product.getSeller().getId());
        System.out.println("------- "+product.getSeller().getName());
        System.out.println("------- "+product.getSeller().getEmail());
    }

    @Test
    public void findById_test(){
        // given : 테스트를 하기 위해서 필요한 데이터 만들기
        productRepository.save("바나나", 5000, 50);

        // when : 테스트 진행
        Product product = productRepository.fingById(1);

        // then : 테스트 확인
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getQty());

    }

    @Test
    public void findAll_test(){
        // given : 테스트를 하기 위해서 필요한 데이터 만들기
        productRepository.save("바나나", 5000, 50);
        productRepository.save("딸기", 5000, 50);

        // when : 테스트 진행
        List<Product> productList = productRepository.findAll();

        // then : 테스트 확인
        for (Product product : productList) {
            System.out.println("-----------------");
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getQty());
        }
    }
}