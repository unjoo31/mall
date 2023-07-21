package shop.mtcoding.mall.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
// 테이블 생성시 만들어지는 테이블 이름 설정한다.
@Table(name = "product_tb")
// JPA에서 사용되며, JPA Entity로 지정한다.
// JPA Entity는 데이터베이스 테이블과 매핑되는 클래스를 의미하며, 이를 통해 데이터베이스와 객체 지향 프로그래밍간의 매핑을 지원한다.
@Entity
public class Product {
    // JPA에서 사용되며, 해당 어노테이션이 붙을 필드를 테이블의 pk로 지정한다.
    @Id
    // JPA에서 사용되며, 해당 어노테이션이 붙을 필드의 값을 자동으로 생성해주는 방법을 지정한다.
    // strategy 속성을 통해 생성 전략을 설정할 수 있으며,
    // 위의 예시에서는 GenerationType.IDENTITY를 사용하여 데이터베이스의 자동 증가(primary key auto-increment) 기능을 이용하여 기본 키 값을 자동으로 생성합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer price;
    private Integer qty;

    @ManyToOne // 테이블 만들어질 때 fk가 설정된다
    // 오브젝트를 붙이면 된다. 변수로 하지 않고 오브젝트로 하는 이유 : 연관관계가 있기 때문 -> 판매자가 있어야 상품을 등록할 수 있기 때문
    private Seller seller;
}
