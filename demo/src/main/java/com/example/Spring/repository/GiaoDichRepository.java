package com.example.Spring.repository;

import com.example.Spring.entity.GiaoDichEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface GiaoDichRepository extends JpaRepository<GiaoDichEntity, Long> {
    Optional<GiaoDichEntity> findByRefNo(String refNo);
    List<GiaoDichEntity> findByStkGuiOrStkNhan(String stkGui, String stkNhan);
    List<GiaoDichEntity> findByStkGuiAndTrangThai(String stkGui, String trangThai);

    @Query("SELECT COALESCE(SUM(g.soTien), 0) FROM GiaoDichEntity g WHERE g.trangThai = 'SUCCESS' AND g.loaiGiaoDich = 'CHUYEN_TIEN'")
    BigDecimal tinhTongTienGiaoDichThanhCong();

    Optional<GiaoDichEntity> findFirstByOrderBySoTienDesc();

    List<GiaoDichEntity> findAllByOrderBySoTienDesc();
}
