package com.example.Spring.controller;

import com.example.Spring.dto.Api;
import com.example.Spring.dto.CreateAccountRequest;
import com.example.Spring.dto.TaiKhoanResponse;
import com.example.Spring.entity.LichSuSoDuEntity;
import com.example.Spring.service.LichSuSoDuService;
import com.example.Spring.service.TaiKhoanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tài Khoản", description = "Các API thao tác mở tài khoản và tra cứu số dư")
@RestController
@RequestMapping("/api/v1/tai-khoan")
public class TaikhoanController {

    private final TaiKhoanService taiKhoanService;
    private final LichSuSoDuService lichSuSoDuService;

    public TaikhoanController(TaiKhoanService taiKhoanService,
                              LichSuSoDuService lichSuSoDuService) {

        this.taiKhoanService = taiKhoanService;
        this.lichSuSoDuService = lichSuSoDuService;
    }

    @PostMapping
    public ResponseEntity<Api<TaiKhoanResponse>> taoTaiKhoan(@Valid @RequestBody CreateAccountRequest request) {
        TaiKhoanResponse ketQua = taiKhoanService.taoTaiKhoan(request);
        return ResponseEntity.ok(new Api<>(true, "Tạo tài khoản thành công!", ketQua));
    }
    @GetMapping("/{soTaiKhoan}/bien-dong-so-du")
    public ResponseEntity<Api<List<LichSuSoDuEntity>>> layBienDongSoDu(@PathVariable String soTaiKhoan) {
        List<LichSuSoDuEntity> lichSu = lichSuSoDuService.layLichSuTheoStk(soTaiKhoan);
        return ResponseEntity.ok(new Api<>(true, "Lấy lịch sử biến động thành công!", lichSu));
    }
}