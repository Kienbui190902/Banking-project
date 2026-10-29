package com.example.Spring.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ChuyenTienResponse {
    private String refNo;
    private String trangThai;
    private String stkGui;
    private String stkNhan;
    private BigDecimal soTien;
    private BigDecimal soDuHienTai;
    private LocalDateTime thoiGian;
    private String trackingUrl;

    public ChuyenTienResponse(String refNo, String trangThai, String stkGui,
                              String stkNhan, BigDecimal soTien, BigDecimal soDuHienTai,
                              LocalDateTime thoiGian, String trackingUrl) {
        this.refNo = refNo;
        this.trangThai = trangThai;
        this.stkGui = stkGui;
        this.stkNhan = stkNhan;
        this.soTien = soTien;
        this.soDuHienTai = soDuHienTai;
        this.thoiGian = thoiGian;
        this.trackingUrl = trackingUrl;
    }

    public String getRefNo() { return refNo; }
    public String getTrangThai() { return trangThai; }
    public String getStkGui() { return stkGui; }
    public String getStkNhan() { return stkNhan; }
    public BigDecimal getSoTien() { return soTien; }
    public BigDecimal getSoDuHienTai() { return soDuHienTai; }
    public LocalDateTime getThoiGian() { return thoiGian; }
    public String getTrackingUrl() { return trackingUrl; }

}
