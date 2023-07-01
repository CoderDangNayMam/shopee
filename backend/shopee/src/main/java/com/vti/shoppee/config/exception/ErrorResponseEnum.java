package com.vti.shoppee.config.exception;

public enum ErrorResponseEnum {
    NOT_FOUND_PRODUCT(404,"Không tìm thấy sản phẩm"),
    NOT_FOUND_ACCOUNT(404,"Không tìm thấy người dùng"),
    USERNAME_EXISTED(400,"Username đã tồn tại"),
    PHONENUMBER_EXISTED(400,"Số điện thoại đã được sử dụng"),
    EMAIL_EXISTED(400,"Email đã được sử dụng"),
    USERNAME_NOT_EXISTS(401,"Không tìm thấy username"),
    PASSWORD_INCORRECT(401,"Password không đúng"),
    ;
    public final int status;
    public final String message;
    ErrorResponseEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
