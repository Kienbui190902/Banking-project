package com.example.Spring.service;

import com.example.Spring.dto.CreateAccountRequest;
import com.example.Spring.dto.TaiKhoanResponse;
import com.example.Spring.dto.TaoKhachHangRequest;
import com.example.Spring.entity.KhachHangEntity;
import com.example.Spring.entity.TaiKhoanEntity;
import com.example.Spring.exception.ResourceNotFoundException;
import com.example.Spring.mapper.TaiKhoanMapper;
import com.example.Spring.repository.KhachHangRepository;
import com.example.Spring.repository.TaiKhoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class TaiKhoanService {

    private final KhachHangRepository khachHangRepository;
    private final TaiKhoanRepository taiKhoanRepository;
    private final TaiKhoanMapper taiKhoanMapper;

    public TaiKhoanService(KhachHangRepository khachHangRepository,
                           TaiKhoanRepository taiKhoanRepository,
                           TaiKhoanMapper taiKhoanMapper) {
        this.khachHangRepository = khachHangRepository;
        this.taiKhoanRepository = taiKhoanRepository;
        this.taiKhoanMapper = taiKhoanMapper;
    }

    @Transactional
    public KhachHangEntity taoKhachHang(TaoKhachHangRequest request) {
        if (khachHangRepository.existsById(request.getMaKhachHang())) {
            throw new IllegalArgumentException("Mã khách hàng đã tồn tại: " + request.getMaKhachHang());
        }

        KhachHangEntity kh = new KhachHangEntity(
                request.getMaKhachHang(),
                request.getHoTen(),
                request.getSoDienThoai(),
                new ArrayList<>()
        );
        return khachHangRepository.save(kh);
    }

    @Transactional
    public TaiKhoanResponse taoTaiKhoan(CreateAccountRequest request) {
        if (taiKhoanRepository.existsById(request.getSoTaiKhoan())) {
            throw new IllegalArgumentException("Số tài khoản đã tồn tại: " + request.getSoTaiKhoan());
        }

        if (request.getSoDuBanDau().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Số dư ban đầu không được âm!");
        }

        KhachHangEntity kh = khachHangRepository.findById(request.getMaKhachHang())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng: " + request.getMaKhachHang()));

        TaiKhoanEntity tk = new TaiKhoanEntity(
                request.getSoTaiKhoan(),
                request.getSoDuBanDau(),
                request.getLoaiTaiKhoan(),
                kh
        );

        TaiKhoanEntity saved = taiKhoanRepository.save(tk);
        return taiKhoanMapper.toResponse(saved);
    }
}