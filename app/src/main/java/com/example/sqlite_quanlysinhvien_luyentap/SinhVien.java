package com.example.sqlite_quanlysinhvien_luyentap;

public class SinhVien {
    private int IdSV;
    private String TenSV;

    public SinhVien(int idSV, String tenSV) {
        IdSV = idSV;
        TenSV = tenSV;
    }

    public int getIdSV() {
        return IdSV;
    }

    public void setIdSV(int idSV) {
        IdSV = idSV;
    }

    public String getTenSV() {
        return TenSV;
    }

    public void setTenSV(String tenSV) {
        TenSV = tenSV;
    }
}
