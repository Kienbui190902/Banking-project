package com.example.Spring.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ChuyenTienRepository {
    private String refNo;
    private String trangThai;
    private String stkGui;
    private String stkNhan;
    private BigDecimal soTien;
    private BigDecimal soDuHienTai;
    private LocalDateTime thoiGian;
    private String trackingUrl;

    public ChuyenTienRepository(String refNo, String trangThai, String stkGui,
                                String stkNhan, BigDecimal soTien, BigDecimal soDuHienTai,
                                LocalDateTime thoiGian,String trackingUrl) {

        this.refNo = refNo;
        this.trangThai = trangThai;
        this.stkGui = stkGui;
        this.stkNhan = stkNhan;
        this.soTien = soTien;
        this.soDuHienTai = soDuHienTai;
        this.thoiGian = thoiGian;
        this.trackingUrl = trackingUrl;
    }

}
