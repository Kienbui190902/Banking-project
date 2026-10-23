package com.example.Spring.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ChuyenTienRequest {

    @NotBlank(message = "Số tài khoản gửi không được để trống!")
    private String stkGui;

    @NotBlank(message = "Số tài khoản nhận không được để trống!")
    private String stkNhan;

    @NotNull(message = "Số tiền không được để null!")
    @DecimalMin(value = "1000.0", message = "Số tiền chuyển tối thiểu là 1,000 VND!")
    private BigDecimal soTien;
}