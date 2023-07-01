package com.vti.shoppee.service;

import com.vti.shoppee.entity.dto.BaseRequest;
import com.vti.shoppee.entity.dto.ProductCreateRequestDto;
import com.vti.shoppee.entity.dto.ProductUpdateDto;
import com.vti.shoppee.entity.dto.SearchProductRequest;
import com.vti.shoppee.entity.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {
    List<Product> getAll();

    Product getById(int id);

    Product create(ProductCreateRequestDto dto);

    Product update(ProductUpdateDto dto);

    void deleteById(int id);

    Page<Product> search(SearchProductRequest request);
}
