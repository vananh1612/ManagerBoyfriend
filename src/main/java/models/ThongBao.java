package models;

public class ThongBao<T> {
    // thongBao, kiemTra, Data
    private String thongBao;
    private Boolean kiemTra;
    private T data;

    public ThongBao() {
    }

    public ThongBao(String thongBao, Boolean kiemTra, T data) {
        this.thongBao = thongBao;
        this.kiemTra = kiemTra;
        this.data = data;
    }

    public String getThongBao() {
        return thongBao;
    }

    public void setThongBao(String thongBao) {
        this.thongBao = thongBao;
    }

    public Boolean getKiemTra() {
        return kiemTra;
    }

    public void setKiemTra(Boolean kiemTra) {
        this.kiemTra = kiemTra;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ThongBao{" +
                "thongBao='" + thongBao + '\'' +
                ", kiemTra=" + kiemTra +
                ", data=" + data +
                '}';
    }
}
