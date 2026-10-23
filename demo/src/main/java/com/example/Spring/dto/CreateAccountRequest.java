package com.example.Spring.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateAccountRequest {

    @NotBlank(message = "Số tài khoản không được để trống!")
    private String soTaiKhoan;

    @NotNull(message = "Số dư ban đầu không được để null!")
    @DecimalMin(value = "0.0", inclusive = true, message = "Số dư ban đầu phải lớn hơn hoặc bằng 0!")
    private BigDecimal soDuBanDau;

    @NotBlank(message = "Mã khách hàng sở hữu không được để trống!")
    private String maKhachHang;

    private String loaiTaiKhoan;
}   