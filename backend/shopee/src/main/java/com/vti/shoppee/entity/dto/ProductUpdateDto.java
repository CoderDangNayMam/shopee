package com.vti.shoppee.entity.dto;

import com.vti.shoppee.entity.entity.ProductStatus;
import com.vti.shoppee.entity.entity.ProductType;
import com.vti.shoppee.entity.entity.ShippingUnit;
import lombok.Data;

@Data
public class ProductUpdateDto {
    private int id;

    private String name;

    private String  image;

    private int price;

    private ProductStatus status;

    private ShippingUnit shippingUnit;

    private ProductType productType;
}
