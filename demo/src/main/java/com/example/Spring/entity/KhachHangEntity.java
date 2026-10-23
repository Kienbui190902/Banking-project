package com.example.Spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "khach_hang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KhachHangEntity {

    @Id
    @Column(name = "ma_khach_hang", length = 50, nullable = false)
    private String maKhachHang;

    @Column(name = "ho_ten", nullable = false)
    private String hoTen;

    @Column(name = "so_dien_thoai", length = 15)
    private String soDienThoai;


    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaiKhoanEntity> danhSachTaiKhoan = new ArrayList<>();
}