package migrations;

import utils.ConnectDatabase;

import java.sql.Connection;

public class NguoiYeu {

    Connection connection = ConnectDatabase.getConnection();
    String query = "CREATE TABLE `nguoiyeu` (" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT," +
            "  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL," +
            "  `tuoi` int(11) NOT NULL," +
            "  `sinhNhat` date NOT NULL," +
            "  `diaChi` tinytext COLLATE utf8mb4_unicode_ci NOT NULL," +
            "  `gioiTinh` bit(1) NOT NULL," +
            "  `soDienThoai` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL," +
            "  PRIMARY KEY (`id`)" +
            ")";

    public NguoiYeu() {

    }

    public void execute() {
        try {
            connection.createStatement().execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NguoiYeu nguoiYeu = new NguoiYeu();
        nguoiYeu.execute();
    }

}
