package daos;

import models.NguoiYeu;
import models.ThongBao;
import utils.ConnectDatabase;

import java.sql.*;
import java.util.ArrayList;


public class NguoiYeuDAO implements DaoInterface<NguoiYeu> {

    private final String tableName = "nguoiyeu";
    private final String createQuery = "INSERT INTO `" + tableName + "`( `name`, `tuoi`, `sinhNhat`, `diaChi`, `gioiTinh`, `soDienThoai`) VALUES (?, ?, ?, ?, ?, ?)";
    private final String updateQuery = "UPDATE `" + tableName + "` SET `name` = ?, `tuoi` = ?, `sinhNhat` = ?, `diaChi` = ?, `gioiTinh` = ?, `soDienThoai` = ? WHERE `id` = ?";
    private final String deleteQuery = "DELETE FROM `" + tableName + "` WHERE `id` = ?";
    private final String findOneQuery = "SELECT * FROM `" + tableName + "` WHERE `id` = ?";
    private final String findAllQuery = "SELECT * FROM `" + tableName + "`";
    private final String searchQuery = "SELECT * FROM `" + tableName + "` WHERE `name` LIKE ?";
    private Connection connection = ConnectDatabase.getConnection();

    @Override
    public ThongBao<NguoiYeu> create(NguoiYeu data) throws Exception {
        try {
            PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, data.getName());
            statement.setInt(2, data.getTuoi());
            statement.setDate(3, data.getSinhNhat());
            statement.setString(4, data.getDiaChi());
            statement.setBoolean(5, data.getGioiTinh());
            statement.setString(6, data.getSoDienThoai());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            data.setId(resultSet.getInt(1));
            return new ThongBao<>("Thêm người yêu thành công", true, data);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ThongBao<>("Thêm người yêu thất bại", false, null);
        }
    }

    @Override
    public ThongBao<NguoiYeu> update(NguoiYeu data) throws Exception {
        return null;
    }

    @Override
    public ThongBao<NguoiYeu> delete(int id) throws Exception {
        return null;
    }

    @Override
    public NguoiYeu findOne(int id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<NguoiYeu> findAll() throws Exception {
        return null;
    }

    @Override
    public ArrayList<NguoiYeu> search(String key) throws Exception {
        return null;
    }

    @Override
    public ArrayList<NguoiYeu> findBy(String name, String value) throws Exception {
        return null;
    }

    @Override
    public NguoiYeu findOneBy(String name, String value) throws Exception {
        return null;
    }

    public static void main(String[] args) {
        NguoiYeuDAO dao = new NguoiYeuDAO();
        NguoiYeu nguoiYeu = new NguoiYeu();
        nguoiYeu.setName("Nguyễn Thị Vân Anh 2");
        nguoiYeu.setDiaChi("Đô Lương - Nghệ An");
        nguoiYeu.setGioiTinh(false);
        nguoiYeu.setSoDienThoai("0987654321");
        // sinh nhật is 2004 / 12/16
        nguoiYeu.setSinhNhat(new Date(new java.util.Date(2004,12,16).getTime()));
        nguoiYeu.setTuoi(20);
        try {
            System.out.println(dao.create(nguoiYeu));
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
