package com.example.Spring.service;

import com.example.Spring.entity.GiaoDichEntity;
import com.example.Spring.repository.GiaoDichRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class GiaoDichLogService {

    private final GiaoDichRepository giaoDichRepository;

    public GiaoDichLogService(GiaoDichRepository giaoDichRepository) {
        this.giaoDichRepository = giaoDichRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GiaoDichEntity ghiLogGiaoDich(String stkGui, String stkNhan, BigDecimal soTien, String loaiGiaoDich, String trangThai) {
        GiaoDichEntity gd = new GiaoDichEntity();
        gd.setRefNo("GD" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        gd.setStkGui(stkGui);
        gd.setStkNhan(stkNhan);
        gd.setSoTien(soTien != null ? soTien : BigDecimal.ZERO);
        gd.setLoaiGiaoDich(loaiGiaoDich);
        gd.setTrangThai(trangThai);
        gd.setThoiGian(LocalDateTime.now());
        return giaoDichRepository.save(gd);
    }
}