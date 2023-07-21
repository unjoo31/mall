package shop.mtcoding.mall.model;

import lombok.*;


@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Integer id;
    private String name;
    private Integer price;
    private Integer qty;
    private String des; // 상품설명

    // @AllArgsConstructor : lombok에서 생성시 랜덤으로 순서가 지정될 수 있기 때문에 직접 만들어서 @Builder를 붙이는 것이 정확하다
    @Builder
    public ProductDTO(Integer id, String name, Integer price, Integer qty, String des) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.des = des;
    }
}
