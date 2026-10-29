package com.example.Spring.exception;

import java.math.BigDecimal;

public class InsufficientBalanceException extends RuntimeException {
    private final String soTaiKhoan;
    private final BigDecimal soDuHienTai;
    private final BigDecimal soTienYeuCau;
    private final BigDecimal soTienConThieu;

    public InsufficientBalanceException(String soTaiKhoan, BigDecimal soDuHienTai, BigDecimal soTienYeuCau) {
        super(String.format("Tài khoản %s không đủ số dư để thực hiện giao dịch!", soTaiKhoan));
        this.soTaiKhoan = soTaiKhoan;
        this.soDuHienTai = soDuHienTai;
        this.soTienYeuCau = soTienYeuCau;
        this.soTienConThieu = (soTienYeuCau != null && soDuHienTai != null)
                ? soTienYeuCau.subtract(soDuHienTai)
                : BigDecimal.ZERO;
    }

    public InsufficientBalanceException(String message) {
        super(message);
        this.soTaiKhoan = null;
        this.soDuHienTai = null;
        this.soTienYeuCau = null;
        this.soTienConThieu = null;
    }

    public String getSoTaiKhoan() {
        return soTaiKhoan;
    }

    public BigDecimal getSoDuHienTai() {
        return soDuHienTai;
    }

    public BigDecimal getSoTienYeuCau() {
        return soTienYeuCau;
    }

    public BigDecimal getSoTienConThieu() {
        return soTienConThieu;
    }
}

