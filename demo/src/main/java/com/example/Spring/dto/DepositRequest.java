package com.example.Spring.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepositRequest {

    @NotBlank(message = "Số tài khoản không được để trống!")
    private String soTaiKhoan;

    @NotNull(message = "Số tiền nạp không được để null!")
    @DecimalMin(value = "1000.0", message = "Số tiền nạp tối thiểu là 1,000 VND!")
    private BigDecimal soTien;
}