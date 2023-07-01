package com.vti.shoppee.entity.dto;

import com.vti.shoppee.entity.entity.ProductStatus;
import com.vti.shoppee.entity.entity.ProductType;
import com.vti.shoppee.entity.entity.ShippingUnit;
import lombok.Data;

import java.util.Set;

@Data
public class SearchProductRequest extends BaseRequest{
    private String name;
    private int minPrice;
    private int maxPrice;
    private Set<ProductStatus> status;
    private Set<ProductType> productType;
    private Set<ShippingUnit> shippingUnit; // set không lưu được các đối tượng giống nhau, list lưu được các đối tượng giống nhau
}
