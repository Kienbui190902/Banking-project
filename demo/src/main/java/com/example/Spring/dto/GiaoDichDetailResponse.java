package com.example.Spring.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GiaoDichDetailResponse {
    private String refNo;
    private String stkGui;
    private String stkNhan;
    private BigDecimal soTien;
    private String loaiGiaoDich;
    private String trangThai;
    private LocalDateTime thoiGian;

    public GiaoDichDetailResponse(String refNo,String stkGui,String stkNhan,
                                  BigDecimal soTien, String loaiGiaoDich,
                                  String trangThai,LocalDateTime thoiGian){
        this.refNo = refNo;
        this.stkGui = stkGui;
        this.stkNhan = stkNhan;
        this.soTien = soTien;
        this.loaiGiaoDich = loaiGiaoDich;
        this.trangThai = trangThai;
        this.thoiGian = thoiGian;

    }

    public String getRefNo() {
        return refNo;
    }

    public String getStkGui(){
        return stkGui;
    }

    public String getStkNhan(){
        return stkNhan;
    }

    public BigDecimal getSoTien() {
        return soTien;
    }

    public String getLoaiGiaoDich() {
        return loaiGiaoDich;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public LocalDateTime getThoiGian() {
        return thoiGian;
    }

}
