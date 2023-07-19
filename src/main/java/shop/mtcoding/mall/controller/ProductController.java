package shop.mtcoding.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.mall.model.Product;
import shop.mtcoding.mall.model.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // 메인 페이지를 get해서 읽어줘
    @GetMapping("/")
    public String home(HttpServletRequest request){
        List<Product> productList = productRepository.findAll();
        // 데이터는 먼저 request에 담는다.
        // request.setAttribute(key, value);
        request.setAttribute("productlist", productList);
        return "home";
    }

    // write 페이지를 get해서 읽어줘
    @GetMapping("/write")
    public String writePage(){
        return "write";
    }

    // product를 post해서 줄게
    @PostMapping("/product")
    public String write(String name, int price, int qty){
        System.out.println("name : " + name);
        System.out.println("price : " + price);
        System.out.println("qty : " + qty);
        //return "write";

        productRepository.save(name,price,qty);
        // 데이터 저장하면 상품목록 페이지로 리다이렉트 시켜준다
        return "redirect:/";
    }
}
