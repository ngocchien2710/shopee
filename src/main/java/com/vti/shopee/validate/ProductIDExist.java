package com.vti.shopee.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})// Khai báo vị trí sử dụng
@Retention(RetentionPolicy.RUNTIME) //khai báo thời điểm sử dụng
@Documented
@Constraint(validatedBy = ProductIDExistValidator.class) // khai báo class xử lí logic
public @interface ProductIDExist {
    //trường message là bắt buộc khai báo nội dung sẽ trả về khi field k hợp lệ
    String message() default "Sản phẩm k tồn tại";

    //2 cái này bắt buộc phải có để hibernate validator có thể hoạt động
    Class<?>[] groups() default {};
    Class<?extends Payload>[] payload() default{};


}
