package com.example.Spring.repository;

import com.example.Spring.entity.LichSuSoDuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LichSuSoDuRepository extends JpaRepository<LichSuSoDuEntity, Long> {
    List<LichSuSoDuEntity> findBySoTaiKhoanOrderByThoiGianDesc(String soTaiKhoan);
}
