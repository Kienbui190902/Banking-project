package com.example.Spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaoKhachHangRequest {

    @NotBlank(message = "Mã khách hàng không được để trống!")
    @Size(min = 3, max = 20, message = "Mã khách hàng phải từ 3 đến 20 ký tự!")
    private String maKhachHang;

    @NotBlank(message = "Họ tên không được để trống!")
    private String hoTen;

    @NotBlank(message = "Số điện thoại không được để trống!")
    @Pattern(regexp = "^(0|\\+84)[0-9]{9}$", message = "Số điện thoại không đúng định dạng Việt Nam (10 số)!")
    private String soDienThoai;
}