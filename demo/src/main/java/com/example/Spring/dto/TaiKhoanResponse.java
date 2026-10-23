package com.example.Spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaiKhoanResponse {
    private String soTaiKhoan;
    private BigDecimal soDu;
    private String loaiTaiKhoan;
    private String tenChuTaiKhoan;
}