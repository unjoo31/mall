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

// @Controller 어노테이션은 Spring Framework에서 컨트롤러 클래스를 지정하는 어노테이션입니다.
// 이 클래스가 컨트롤러 역할을 수행하며, 웹 요청에 대한 처리를 담당합니다.
@Controller
public class ProductController {

    // @Autowired 어노테이션은 Spring Framework에서 의존성 주입(Dependency Injection)을 수행할 때 사용됩니다.
    @Autowired
    private ProductRepository productRepository;

    // 메인 페이지를 get해서 읽어줘
    // @GetMapping 어노테이션은 HTTP GET 요청을 처리하는 메서드를 지정하는 어노테이션입니다.
    @GetMapping("/")
    // HttpServletRequest 객체를 매개변수로 받고, ProductRepository를 사용하여 모든 상품 정보를 조회한 후,
    // 조회한 결과를 "productlist"라는 이름으로 request의 attribute에 저장하고, "home"이라는 뷰를 반환합니다.
    public String home(HttpServletRequest request){
        List<Product> productList = productRepository.findAll();
        // 데이터는 먼저 request에 담는다.
        // request.setAttribute(key, value);
        request.setAttribute("productlist", productList);
        return "home";
    }

    // write 페이지를 get해서 읽어줘
    //  "/write" 경로로 들어오는 GET 요청을 처리합니다. writePage() 메서드는 단순히 "write"라는 뷰를 반환합니다.
    @GetMapping("/write")
    public String writePage(){
        return "write";
    }

    // product를 post해서 줄게
    // "/product" 경로로 들어오는 POST 요청을 처리합니다.
    @PostMapping("/product")
    // write() 메서드는 name, price, qty라는 세 개의 매개변수를 받고,
    // 이를 출력한 후 ProductRepository를 사용하여 데이터베이스에 새로운 상품 정보를 저장합니다.
    // 그리고 "/" 경로로 리다이렉트하여 상품 리스트를 다시 조회하도록 합니다.
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
