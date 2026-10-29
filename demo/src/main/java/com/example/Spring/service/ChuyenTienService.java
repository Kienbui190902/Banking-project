package com.example.Spring.service;

import com.example.Spring.dto.*;
import com.example.Spring.entity.GiaoDichEntity;
import com.example.Spring.entity.TaiKhoanEntity;
import com.example.Spring.exception.InsufficientBalanceException;
import com.example.Spring.exception.ResourceNotFoundException;
import com.example.Spring.mapper.TaiKhoanMapper;
import com.example.Spring.repository.GiaoDichRepository;
import com.example.Spring.repository.TaiKhoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChuyenTienService {

    private final GiaoDichRepository giaoDichRepository;
    private final TaiKhoanRepository taiKhoanRepository;
    private final TaiKhoanMapper taiKhoanMapper;
    private final GiaoDichLogService giaoDichLogService;
    private final LichSuSoDuService lichSuSoDuService;

    public ChuyenTienService(GiaoDichRepository giaoDichRepository,
                             TaiKhoanRepository taiKhoanRepository,
                             TaiKhoanMapper taiKhoanMapper,
                             GiaoDichLogService giaoDichLogService,
                             LichSuSoDuService lichSuSoDuService) {
        this.giaoDichRepository = giaoDichRepository;
        this.taiKhoanRepository = taiKhoanRepository;
        this.taiKhoanMapper = taiKhoanMapper;
        this.giaoDichLogService = giaoDichLogService;
        this.lichSuSoDuService = lichSuSoDuService;
    }

    public GiaoDichDetailResponse timChiTietGiaoDich(String refNo,String soTaiKhoan){
        GiaoDichEntity gd = giaoDichRepository.findByRefNo(refNo)
                .orElseThrow(()-> new ResourceNotFoundException("GiaoDich","refNo",refNo));

        boolean laNguoiGui = gd.getStkGui() != null && gd.getStkGui().equalsIgnoreCase(soTaiKhoan);
        boolean laNguoiNhan = gd.getStkNhan() != null && gd.getStkNhan().equalsIgnoreCase(soTaiKhoan);

        if (!laNguoiGui && !laNguoiNhan) {
            throw new IllegalArgumentException("Bạn không có quyền xem thông tin!");
        }

        return  new GiaoDichDetailResponse(
                gd.getRefNo(),
                gd.getStkGui(),
                gd.getStkNhan(),
                gd.getSoTien(),
                gd.getLoaiGiaoDich(),
                gd.getTrangThai(),
                gd.getThoiGian()
        );

    }

    @Transactional
    public TaiKhoanResponse thucHienChuyenTien(ChuyenTienRequest request) {
        if (request.getSoTien() == null || request.getSoTien().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Số tiền chuyển phải lớn hơn 0!");
        }

        if (request.getStkGui().equalsIgnoreCase(request.getStkNhan())) {
            throw new IllegalArgumentException("Tài khoản gửi và nhận không được trùng nhau!");
        }

        try {
            TaiKhoanEntity tkGui = taiKhoanRepository.findById(request.getStkGui())
                    .orElseThrow(() -> new ResourceNotFoundException("TaiKhoan", "soTaiKhoan", request.getStkGui()));

            TaiKhoanEntity tkNhan = taiKhoanRepository.findById(request.getStkNhan())
                    .orElseThrow(() -> new ResourceNotFoundException("TaiKhoan", "soTaiKhoan", request.getStkNhan()));

            if (tkGui.getSoDu().compareTo(request.getSoTien()) < 0) {
                throw new InsufficientBalanceException(tkGui.getSoTaiKhoan(),tkGui.getSoDu(),request.getSoTien());
            }
            BigDecimal soDuTruocGui = tkGui.getSoDu();
            BigDecimal soDuTruocNhan = tkNhan.getSoDu();

            BigDecimal soDuSauGui = soDuTruocGui.subtract(request.getSoTien());
            BigDecimal soDuSauNhan = soDuTruocNhan.add(request.getSoTien());

            tkGui.setSoDu(tkGui.getSoDu().subtract(request.getSoTien()));
            tkNhan.setSoDu(tkNhan.getSoDu().add(request.getSoTien()));

            taiKhoanRepository.save(tkGui);
            taiKhoanRepository.save(tkNhan);

            GiaoDichEntity logGd = giaoDichLogService.ghiLogGiaoDich(
                    request.getStkGui(), request.getStkNhan(), request.getSoTien(), "CHUYEN_TIEN", "SUCCESS"
            );
            lichSuSoDuService.ghiBienDong(
                    tkGui.getSoTaiKhoan(),
                    logGd.getRefNo(),
                    "TRU_TIEN",
                    request.getSoTien(),
                    soDuTruocGui,
                    soDuSauGui,
                    "Chuyển tiền đến tài khoản " + tkNhan.getSoTaiKhoan()
            );
            lichSuSoDuService.ghiBienDong(
                    tkNhan.getSoTaiKhoan(),
                    logGd.getRefNo(),
                    "CONG_TIEN",
                    request.getSoTien(),
                    soDuTruocNhan,
                    soDuSauNhan,
                    "Nhận tiền từ tài khoản " + tkGui.getSoTaiKhoan()
            );

            return taiKhoanMapper.toResponse(tkGui);

        } catch (Exception ex) {
            giaoDichLogService.ghiLogGiaoDich(request.getStkGui(), request.getStkNhan(), request.getSoTien(), "CHUYEN_TIEN", "FAILED");
            throw ex;
        }
    }

    @Transactional
    public TaiKhoanResponse thucHienNapTien(DepositRequest request) {
        if (request.getSoTien() == null || request.getSoTien().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Số tiền nạp phải lớn hơn 0!");
        }

        try {
            TaiKhoanEntity taiKhoan = taiKhoanRepository.findById(request.getSoTaiKhoan())
                    .orElseThrow(() -> new ResourceNotFoundException("Tài khoản không tồn tại!"));

            taiKhoan.setSoDu(taiKhoan.getSoDu().add(request.getSoTien()));
            taiKhoanRepository.save(taiKhoan);

            giaoDichLogService.ghiLogGiaoDich(null, request.getSoTaiKhoan(), request.getSoTien(), "NAP_TIEN", "SUCCESS");

            return taiKhoanMapper.toResponse(taiKhoan);

        } catch (Exception ex) {
            giaoDichLogService.ghiLogGiaoDich(null, request.getSoTaiKhoan(), request.getSoTien(), "NAP_TIEN", "FAILED");
            throw ex;
        }
    }

    public List<GiaoDichResponse> layLichSuGiaoDich(String soTaiKhoan) {
        if (!taiKhoanRepository.existsById(soTaiKhoan)) {
            throw new ResourceNotFoundException("TaiKhoan", "soTaiKhoan", soTaiKhoan);
        }

        return giaoDichRepository.findByStkGuiOrStkNhan(soTaiKhoan, soTaiKhoan)
                .stream()
                .map(gd -> new GiaoDichResponse(
                        gd.getRefNo(),
                        gd.getStkGui(),
                        gd.getStkNhan(),
                        gd.getSoTien(),
                        gd.getLoaiGiaoDich(),
                        gd.getTrangThai(),
                        gd.getThoiGian()
                ))
                .toList();
    }

    public BigDecimal tinhTongTienThanhCong() {
        return giaoDichRepository.tinhTongTienGiaoDichThanhCong();
    }

    public Map<String, List<GiaoDichEntity>> groupTheoTaiKhoanNguon() {
        return giaoDichRepository.findAll().stream()
                .filter(gd -> gd.getStkGui() != null)
                .collect(Collectors.groupingBy(GiaoDichEntity::getStkGui));
    }

    public List<GiaoDichEntity> sapXepGiamDanTheoSoTien() {
        return giaoDichRepository.findAllByOrderBySoTienDesc();
    }

    public GiaoDichEntity timGiaoDichLonNhat() {
        return giaoDichRepository.findFirstByOrderBySoTienDesc().orElse(null);
    }

    public List<GiaoDichEntity> layTheoTrangThai(String stk, String status) {
        return giaoDichRepository.findByStkGuiOrStkNhan(stk, stk).stream()
                .filter(gd -> status.equalsIgnoreCase(gd.getTrangThai()))
                .collect(Collectors.toList());
    }
}