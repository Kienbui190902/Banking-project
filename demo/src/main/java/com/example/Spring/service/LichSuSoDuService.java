package com.example.Spring.service;

import com.example.Spring.entity.LichSuSoDuEntity;
import com.example.Spring.repository.LichSuSoDuRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LichSuSoDuService {

    private final LichSuSoDuRepository lichSuSoDuRepository;

    public LichSuSoDuService(LichSuSoDuRepository lichSuSoDuRepository) {
        this.lichSuSoDuRepository = lichSuSoDuRepository;
    }

    public void ghiBienDong(String soTaiKhoan, String refNo, String loaiBienDong,
                            BigDecimal soTien, BigDecimal soDuTruoc, BigDecimal soDuSau, String moTa) {
        LichSuSoDuEntity ls = new LichSuSoDuEntity(
                soTaiKhoan, refNo, loaiBienDong, soTien, soDuTruoc, soDuSau, moTa
        );
        lichSuSoDuRepository.save(ls);
    }

    public List<LichSuSoDuEntity> layLichSuTheoStk(String soTaiKhoan) {
        return lichSuSoDuRepository.findBySoTaiKhoanOrderByThoiGianDesc(soTaiKhoan);
    }
}
