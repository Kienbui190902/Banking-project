package com.example.Spring.controller;

import com.example.Spring.dto.*;
import com.example.Spring.entity.GiaoDichEntity;
import com.example.Spring.service.ChuyenTienService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Tag(name = "Giao Dịch", description = "Các API thực hiện giao dịch chuyển tiền, nạp tiền và tra cứu lịch sử")
@RestController
@RequestMapping("/api/v1/giao-dich")
public class GiaoDichController {

    private final ChuyenTienService chuyenTienService;

    public GiaoDichController(ChuyenTienService chuyenTienService) {
        this.chuyenTienService = chuyenTienService;
    }

    @PostMapping("/chuyen-tien")
    public ResponseEntity<Api<TaiKhoanResponse>> chuyenTien(@Valid @RequestBody ChuyenTienRequest request) {
        TaiKhoanResponse ketQua = chuyenTienService.thucHienChuyenTien(request);
        return ResponseEntity.ok(new Api<>(true, "Chuyển tiền thành công!", ketQua));
    }

    @PostMapping("/nap-tien")
    public ResponseEntity<Api<TaiKhoanResponse>> napTien(@Valid @RequestBody DepositRequest request) {
        TaiKhoanResponse ketQua = chuyenTienService.thucHienNapTien(request);
        return ResponseEntity.ok(new Api<>(true, "Nạp tiền thành công!", ketQua));
    }

    @GetMapping("/lich-su/{soTaiKhoan}")
    public ResponseEntity<Api<List<GiaoDichResponse>>> xemLichSu(@PathVariable String soTaiKhoan) {
        List<GiaoDichResponse> danhSach = chuyenTienService.layLichSuGiaoDich(soTaiKhoan);
        return ResponseEntity.ok(new Api<>(true, "Lấy lịch sử thành công!", danhSach));
    }

    @GetMapping("/tim -kiem/{refNo}")
    public ResponseEntity<Api<GiaoDichDetailResponse>> timTheoRefNo(
            @PathVariable String refNo,
            @RequestParam String soTaiKhoan) {
        GiaoDichDetailResponse ketQua = chuyenTienService.timChiTietGiaoDich(refNo,soTaiKhoan);
        return ResponseEntity.ok(new Api<>(true, "Tìm thấy giao dịch!", ketQua));
    }

    @GetMapping("/trang-thai")
    public ResponseEntity<Api<List<GiaoDichEntity>>> layTheoTrangThai(
            @RequestParam String stk,
            @RequestParam String status) {
        List<GiaoDichEntity> danhSach = chuyenTienService.layTheoTrangThai(stk, status);
        return ResponseEntity.ok(new Api<>(true, "Lọc giao dịch thành công!", danhSach));
    }

    @GetMapping("/thong-ke/tong-tien")
    public ResponseEntity<Api<BigDecimal>> tinhTongTien() {
        BigDecimal tong = chuyenTienService.tinhTongTienThanhCong();
        return ResponseEntity.ok(new Api<>(true, "Tính tổng tiền thành công!", tong));
    }

    @GetMapping("/thong-ke/nhom-theo-tai-khoan")
    public ResponseEntity<Api<Map<String, List<GiaoDichEntity>>>> nhomTheoTaiKhoan() {
        Map<String, List<GiaoDichEntity>> ketQua = chuyenTienService.groupTheoTaiKhoanNguon();
        return ResponseEntity.ok(new Api<>(true, "Nhóm giao dịch thành công!", ketQua));
    }

    @GetMapping("/thong-ke/lon-nhat")
    public ResponseEntity<Api<GiaoDichEntity>> timGiaoDichLonNhat() {
        GiaoDichEntity gd = chuyenTienService.timGiaoDichLonNhat();
        return ResponseEntity.ok(new Api<>(true, "Giao dịch lớn nhất:", gd));
    }

    @GetMapping("/thong-ke/sap-xep-giam-dan")
    public ResponseEntity<Api<List<GiaoDichEntity>>> sapXepGiamDan() {
        List<GiaoDichEntity> danhSach = chuyenTienService.sapXepGiamDanTheoSoTien();
        return ResponseEntity.ok(new Api<>(true, "Danh sách sắp xếp giảm dần:", danhSach));
    }
}