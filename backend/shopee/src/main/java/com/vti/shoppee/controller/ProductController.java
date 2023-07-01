package com.vti.shoppee.controller;

import com.vti.shoppee.config.Annotation.ProductIDExists;
import com.vti.shoppee.entity.dto.BaseRequest;
import com.vti.shoppee.entity.dto.ProductCreateRequestDto;
import com.vti.shoppee.entity.dto.ProductUpdateDto;
import com.vti.shoppee.entity.dto.SearchProductRequest;
import com.vti.shoppee.entity.entity.Order;
import com.vti.shoppee.entity.entity.Product;
import com.vti.shoppee.service.IProductService;
import com.vti.shoppee.service.iml.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin("*")
@Validated
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/get-all")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public Product getById(@PathVariable @ProductIDExists int id) {
        return productService.getById(id);
    }

    @PostMapping("/create")
    public Product create(@RequestBody @Valid ProductCreateRequestDto dto) {
        return productService.create(dto);
    }

    @PutMapping("/update")
    public Product update(@RequestBody @Valid ProductUpdateDto dto) {
        return productService.update(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public String deleteById(@PathVariable int id) {
        productService.deleteById(id);
        return "Đã xoá thành công";
    }
    @PostMapping("/search")
    public Page<Product> search(@RequestBody SearchProductRequest request){
        return productService.search(request);
    }
}
