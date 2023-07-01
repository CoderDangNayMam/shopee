package com.vti.shoppee.entity.entity;

import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@Data
@MappedSuperclass // để đánh dấu class này cũng là 1 phần trong các entity khác
public class BaseEntity {
    @Column(name = "created_date")
    protected Date createdDate;
    @Column(name = "updated_date")
    protected Date updatedDate;
    @Column(name = "created_by")
    protected String createdBy;
    @Column(name = "updated_by")
    protected String updatedBy;

    /**
     * Hàm này gọi khi entity được thêm mới
     */
    @PrePersist
    public void onPrepersist(){
        this.createdDate = new Date();
        this.createdBy = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

    /**
     * Hàm này gọi tới khi entity được update
     */
    @PreUpdate
    public void onPreUpdate(){
        this.updatedDate = new Date();
        this.updatedBy = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

    }
}
