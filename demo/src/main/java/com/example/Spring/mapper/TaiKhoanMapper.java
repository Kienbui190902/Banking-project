package com.example.Spring.mapper;

import com.example.Spring.dto.TaiKhoanResponse;
import com.example.Spring.entity.TaiKhoanEntity;
import org.springframework.stereotype.Component;

@Component
public class TaiKhoanMapper {

    public TaiKhoanResponse toResponse(TaiKhoanEntity entity) {
        if (entity == null) {
            return null;
        }

        String tenChu = (entity.getKhachHang() != null)
                ? entity.getKhachHang().getHoTen()
                : "Chưa xác định";

        return new TaiKhoanResponse(
                entity.getSoTaiKhoan(),
                entity.getSoDu(),
                entity.getLoaiTaiKhoan(),
                tenChu
        );
    }
}