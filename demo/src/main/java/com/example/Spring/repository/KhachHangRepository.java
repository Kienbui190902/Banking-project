package com.example.Spring.repository;

import com.example.Spring.entity.KhachHangEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHangEntity, String> {
}