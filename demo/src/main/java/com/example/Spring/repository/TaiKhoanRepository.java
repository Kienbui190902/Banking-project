package com.example.Spring.repository;

import com.example.Spring.entity.TaiKhoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoanEntity, String> {

    Optional<TaiKhoanEntity> findBySoTaiKhoan(String soTaiKhoan);

    List<TaiKhoanEntity> findByKhachHang_MaKhachHang(String maKhachHang);
}
