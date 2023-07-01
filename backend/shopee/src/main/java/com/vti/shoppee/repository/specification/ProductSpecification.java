package com.vti.shoppee.repository.specification;

import com.vti.shoppee.entity.dto.SearchProductRequest;
import com.vti.shoppee.entity.entity.Product;
import com.vti.shoppee.entity.entity.ProductStatus;
import com.vti.shoppee.entity.entity.ShippingUnit;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> buildCondition(SearchProductRequest request) {
        return Specification.where(buildConditionName(request))
                .and(buildConditionProductType(request))
                .and(buildConditionPrice(request))
                .and(buildConditionStatus(request))
                .and(buildConditionShippingUnit(request));
    }

    public static Specification<Product> buildConditionName(SearchProductRequest request) {
        if (request.getName() != null && !"".equals(request.getName())) {
            // tạo điều kiện tìm kiếm với name
            return (root, query, cri) -> {
                // root: chọn cột, field để tìm kiếm (giá trị là thuộc tính trong java)
                // cri: khai báo loại so sánh dữ liệu (lớn hơn, nhỏ hơn, equal, like...)
                return cri.like(root.get("name"), "%" + request.getName() + "%");
            };
        } else {
            return null;
        }
    }
    public static Specification<Product> buildConditionProductType(SearchProductRequest request) {
        if (request.getProductType() != null && request.getProductType().size()>0) {
            // tạo điều kiện tìm kiếm với name
            return (root, query, cri) -> {
               return root.get("productType").in(request.getProductType());
            };
        } else {
            return null;
        }
    }

        public static Specification<Product> buildConditionPrice(SearchProductRequest request) {
            if (request.getMinPrice()>=0 && request.getMaxPrice()!=0){
                return (root, query, cri )-> {
                    return cri.between(root.get("price"), request.getMinPrice(),request.getMaxPrice());
                };
            } else {
                return null;
            }
        }
    public static Specification<Product> buildConditionStatus(SearchProductRequest request) {
        if (request.getStatus()!=null && request.getStatus().size()>0){
            return (root, query, criteriaBuilder) -> {
                return root.get("status").in(request.getStatus());
            };
        } else {
            return null;
        }
    }
    public static Specification<Product> buildConditionShippingUnit(SearchProductRequest request) {
        if (request.getShippingUnit() != null && request.getShippingUnit().size()>0){ // nếu không truyền phần tử nào thì lấy tất cả
            return (root, query, criteriaBuilder) -> {
                return root.get("shippingUnit").in(request.getShippingUnit());
            };
        } else {
            return null;
        }
    }
}
