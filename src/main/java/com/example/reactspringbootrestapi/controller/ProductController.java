package com.example.reactspringbootrestapi.controller;

import com.example.reactspringbootrestapi.domain.Product;
import com.example.reactspringbootrestapi.dto.ProductConverter;
import com.example.reactspringbootrestapi.dto.ProductDto;
import com.example.reactspringbootrestapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    @ResponseBody
    public List<ProductDto> showProducts(@RequestParam Map<String, String> params){
        List<ProductDto> products = null;
        if (params.containsKey("id")) {
            products = List.of(ProductConverter.toProductDto(productService.getProductById(Long.parseLong(params.get("id"))).get()));
        }
        else if (params.containsKey("name")) {
            products = List.of(ProductConverter.toProductDto(productService.getProductByName(params.get("name")).get()));
        }
        else if (params.containsKey("category")){
            products = productService.getProductByCategory(params.get("category")).stream().map(ProductConverter::toProductDto).toList();
        }
        else {
            products = productService.getAllProducts().stream().map(ProductConverter::toProductDto).toList();
        }
        return products;
    }

    @PostMapping("")
    @ResponseBody
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = productService.createProduct(productDto.getName(),productDto.getCategory(), productDto.getPrice(), productDto.getDescription());
        return ProductConverter.toProductDto(product);
    }

    @PutMapping("")
    @ResponseBody
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product product = productService.updateProduct(productDto.getName(),productDto.getCategory(), productDto.getPrice(), productDto.getDescription());
        return ProductConverter.toProductDto(product);
    }

    @DeleteMapping("")
    @ResponseBody
    public void deleteProducts() {
        productService.deleteAll();
    }
}
