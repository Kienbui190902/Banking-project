package com.example.Spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "giao_dich")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiaoDichEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ref_no", unique = true, length = 50)
    private String refNo;

    @Column(name = "stk_gui", length = 50)
    private String stkGui;

    @Column(name = "stk_nhan", length = 50)
    private String stkNhan;

    @Column(name = "so_tien", precision = 19, scale = 2, nullable = false)
    private BigDecimal soTien;

    @Column(name = "loai_giao_dich", length = 30)
    private String loaiGiaoDich;

    @Column(name = "trang_thai", length = 20)
    private String trangThai;

    @Column(name = "thoi_gian")
    private LocalDateTime thoiGian;

    @PrePersist
    public void prePersist() {
        if (this.thoiGian == null) {
            this.thoiGian = LocalDateTime.now();
        }
    }
}