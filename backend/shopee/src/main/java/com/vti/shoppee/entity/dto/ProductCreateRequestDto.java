package com.vti.shoppee.entity.dto;

import com.vti.shoppee.entity.entity.ProductStatus;
import com.vti.shoppee.entity.entity.ProductType;
import com.vti.shoppee.entity.entity.ShippingUnit;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data

public class ProductCreateRequestDto {
    @NotBlank(message = "{product.create.name.notBlank}")
    @Length(max = 255, message = "Tên không được quá 255 ký tự")
    private String name;

    @NotBlank(message = "Phải có ảnh")
    private String image;

    @NotNull(message = "Không được để trống giá tiền")
    @Positive(message = "Giá phải lớn hơn 0đ")
    private Integer price;

    @NotNull(message = "Không được để trống tình trạng")
    private ProductStatus status;

    @NotNull(message = "Không được để trống đơn vị vận chuyển")
    private ShippingUnit shippingUnit;

    @NotNull(message = "{product.create.*.notBlank}")
    private ProductType productType;
}
