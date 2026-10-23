package com.example.Spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tai_khoan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaiKhoanEntity {

    @Id
    @Column(name = "so_tai_khoan", length = 20, nullable = false)
    private String soTaiKhoan;

    @Column(name = "so_du", nullable = false)
    private BigDecimal soDu;

    @Column(name = "loai_tai_khoan", nullable = false)
    private String loaiTaiKhoan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_khach_hang", nullable = false)
    private KhachHangEntity khachHang;
}
