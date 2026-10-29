package com.example.Spring.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GiaoDichResponse {
    private String refNo;
    private String stkNhan;
    private String stkGui;
    private BigDecimal soTien;
    private String loaiGiaoDich;
    private String trangThai;
    private LocalDateTime thoiGian;

    public GiaoDichResponse(String refNo, String stkNhan, String stkGui,
                            BigDecimal soTien, String loaiGiaoDich,
                            String trangThai, LocalDateTime thoiGian) {
        this.refNo = refNo;
        this.stkNhan = stkNhan;
        this.stkGui = stkGui;
        this.soTien = soTien;
        this.loaiGiaoDich = loaiGiaoDich;
        this.trangThai = trangThai;
        this.thoiGian = thoiGian;

    }
    public String getRefNo() {return refNo;}
    public String getStkNhan() {return stkNhan;}
    public String getStkGui() {return stkGui;}
    public BigDecimal getSoTien() {return soTien;}
    public String getLoaiGiaoDich() {return loaiGiaoDich;}
    public String getTrangThai() {return trangThai;}
    public LocalDateTime getThoiGian() {return thoiGian;}
}
