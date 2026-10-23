package com.example.Spring.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "lich_su_so_du")
public class LichSuSoDuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String soTaiKhoan;

    @Column(nullable = false)
    private String refNo;

    @Column(nullable = false)
    private String loaiBienDong;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal soTienBienDong;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal soDuTruoc;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal soDuSau;

    private String moTa;

    @Column(nullable = false)
    private LocalDateTime thoiGian;

    public LichSuSoDuEntity() {}

    public LichSuSoDuEntity(String soTaiKhoan, String refNo, String loaiBienDong,
                            BigDecimal soTienBienDong, BigDecimal soDuTruoc,
                            BigDecimal soDuSau, String moTa) {
        this.soTaiKhoan = soTaiKhoan;
        this.refNo = refNo;
        this.loaiBienDong = loaiBienDong;
        this.soTienBienDong = soTienBienDong;
        this.soDuTruoc = soDuTruoc;
        this.soDuSau = soDuSau;
        this.moTa = moTa;
        this.thoiGian = LocalDateTime.now();
    }

    // Getters and Setters ...
    public Long getId() { return id; }
    public String getSoTaiKhoan() { return soTaiKhoan; }
    public String getRefNo() { return refNo; }
    public String getLoaiBienDong() { return loaiBienDong; }
    public BigDecimal getSoTienBienDong() { return soTienBienDong; }
    public BigDecimal getSoDuTruoc() { return soDuTruoc; }
    public BigDecimal getSoDuSau() { return soDuSau; }
    public String getMoTa() { return moTa; }
    public LocalDateTime getThoiGian() { return thoiGian; }
}