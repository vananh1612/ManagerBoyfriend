package models;

import java.sql.Date;

public class NguoiYeu {
    private int id;
    private String name;
    private int tuoi;
    private Date sinhNhat;
    private String diaChi;
    private Boolean gioiTinh;
    private String soDienThoai;



    public NguoiYeu() {
    }

    public NguoiYeu(int id, String name, int tuoi, Date sinhNhat, String diaChi, Boolean gioiTinh, String soDienThoai) {
        this.id = id;
        this.name = name;
        this.tuoi = tuoi;
        this.sinhNhat = sinhNhat;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public Date getSinhNhat() {
        return sinhNhat;
    }



    public void setSinhNhat(Date sinhNhat) {
        this.sinhNhat = sinhNhat;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }



    @Override
    public String toString() {
        return "NguoiYeu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tuoi=" + tuoi +
                ", sinhNhat=" + sinhNhat +
                ", diaChi='" + diaChi + '\'' +
                ", gioiTinh=" + gioiTinh +
                ", soDienThoai='" + soDienThoai + '\'' +
                '}';
    }
}

