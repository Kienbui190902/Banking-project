package com.example.Spring.controller;

import com.example.Spring.dto.Api;
import com.example.Spring.dto.TaoKhachHangRequest;
import com.example.Spring.entity.KhachHangEntity;
import com.example.Spring.service.TaiKhoanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Khách Hàng", description = "Các API quản lý thông tin khách hàng")
@RestController
@RequestMapping("/api/v1/khach-hang")
public class KhachHangController {

    private final TaiKhoanService taiKhoanService;

    public KhachHangController(TaiKhoanService taiKhoanService) {
        this.taiKhoanService = taiKhoanService;
    }

    @PostMapping
    public ResponseEntity<Api<KhachHangEntity>> taoKhachHang(@Valid @RequestBody TaoKhachHangRequest request) {
        KhachHangEntity ketQua = taiKhoanService.taoKhachHang(request);
        return ResponseEntity.ok(new Api<>(true, "Tạo khách hàng thành công!", ketQua));
    }
}