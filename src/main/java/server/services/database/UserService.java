package server.services.database;

import server.hashs.RSAService;
import server.interfaces.DaoInterface;
import server.services.ConnectDatabase;
import shared.models.Notification;
import shared.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserService implements DaoInterface<User> {

    Connection conn = ConnectDatabase.getConnection();
    private final String tableName = "users";
    private final String primaryKey = "id";
    private final String[] fields = {"id", "username", "password", "email", "role"};
    private final String queryGetAll = "SELECT * FROM " + tableName;
    private final String queryGetById = "SELECT * FROM " + tableName + " WHERE " + primaryKey + " = ?";
    private final String queryGetBy = "SELECT * FROM " + tableName + " WHERE ? = ?";
    private final String queryInsert = "INSERT INTO " + tableName + " (username, password, email, role) VALUES (?, ?, ?, ?)";
    private String queryUpdate = "UPDATE " + tableName + " SET username = ?, password = ?, email = ?, role = ? WHERE " + primaryKey + " = ?";
    private final String queryDelete = "DELETE FROM " + tableName + " WHERE " + primaryKey + " = ?";
    private final String queryFindByEmail = "SELECT * FROM " + tableName + " WHERE email = ?";
    private final String queryFindByUsername = "SELECT * FROM " + tableName + " WHERE username = ?";
    private User user;


    @Override
    public Notification<User> create(User data) {
        try {
            if (checkEmailExist(data.getEmail())) {
                return new Notification<>("Email đã được sử dụng", false, null);
            }
            if (checkUsernameExist(data.getUsername())) {
                return new Notification<>("Tên đăng nhập đã được sử dụng", false, null);
            }
            String hashPassword = RSAService.encrypt(data.getPassword());
            PreparedStatement preparedStatement = conn.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, data.getUsername());
            preparedStatement.setString(2, hashPassword);
            preparedStatement.setString(3, data.getEmail());
            preparedStatement.setString(4, data.getRole());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                data.setId(resultSet.getInt(1));
            } else {
                return new Notification<>("Không thể tạo tài khoản", false, null);
            }
            return new Notification<>("Tạo tài khoản thành công", true, data);
        } catch (Exception e) {
            return new Notification<>("Không thể tạo tài khoản", false, null);
        }
    }

    public User getUserByEmail(String email) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(queryFindByEmail);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(primaryKey));
                user.setUsername(resultSet.getString(fields[1]));
                user.setPassword(resultSet.getString(fields[2]));
                user.setEmail(resultSet.getString(fields[3]));
                user.setRole(resultSet.getString(fields[4]));

                return user;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public User getUserByUsername(String username) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(queryFindByUsername);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(primaryKey));
                user.setUsername(resultSet.getString(fields[1]));
                user.setPassword(resultSet.getString(fields[2]));
                user.setEmail(resultSet.getString(fields[3]));
                user.setRole(resultSet.getString(fields[4]));
                return user;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean checkEmailExist(String email) {
        User user = getUserByEmail(email);
        return user != null;
    }

    public boolean checkUsernameExist(String username) {
        User user = getUserByUsername(username);
        return user != null;
    }

    @Override
    public Notification<User> update(int id, User data) {
        try {
            User user = getById(id);
            if (user == null) {
                return new Notification<>("Không tìm thấy tài khoản", false, null);
            }
            user = this.getUserByEmail(data.getEmail());
            if (user != null && user.getId() != id) {
                return new Notification<>("Email đã được sử dụng", false, null);

            }
            user = this.getUserByUsername(data.getUsername());
            if (user != null && user.getId() != id) {
                return new Notification<>("Tên đăng nhập đã được sử dụng", false, null);
            }
            if (data.getPassword().isEmpty()) {
                this.queryUpdate = "UPDATE " + tableName + " SET username = ?, email = ?, role = ? WHERE " + primaryKey + " = ?";
            }
            PreparedStatement preparedStatement = conn.prepareStatement(queryUpdate);
            preparedStatement.setString(1, data.getUsername());
            preparedStatement.setString(2, data.getEmail());
            if (!data.getPassword().isEmpty()) {
                String hashPassword = RSAService.encrypt(data.getPassword());
                preparedStatement.setString(3, hashPassword);
            } else {
                preparedStatement.setString(3, user.getPassword());

            }
            preparedStatement.executeUpdate();
            return new Notification<>("Cập nhật tài khoản thành công", true, data);
        } catch (Exception e) {
            return new Notification<>("Không thể cập nhật tài khoản", false, null);
        }
    }

    @Override
    public Notification<User> delete(int id) {
        try {
            User user = getById(id);
            if (user == null) {
                return new Notification<>("Không tìm thấy tài khoản", false, null);
            }
            PreparedStatement preparedStatement = conn.prepareStatement(queryDelete);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return new Notification<>("Xóa tài khoản thành công", true, user);
        } catch (Exception e) {
            return new Notification<>("Không thể xóa tài khoản", false, null);
        }
    }

    public ArrayList<User> pushData(ResultSet resultSet) {
        ArrayList<User> users = new ArrayList<>();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(primaryKey));
                user.setUsername(resultSet.getString(fields[1]));
                user.setPassword(resultSet.getString(fields[2]));
                user.setEmail(resultSet.getString(fields[3]));
                user.setRole(resultSet.getString(fields[4]));
                users.add(user);
            }
        } catch (Exception ignored) {

        }
        return users;
    }

    @Override
    public ArrayList<User> getAll() {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(queryGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            return pushData(resultSet);
        } catch (Exception e) {
            return new ArrayList<User>();
        }
    }

    @Override
    public User getById(int id) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(queryGetById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                user.setId(resultSet.getInt(primaryKey));
                user.setUsername(resultSet.getString(fields[1]));
                user.setPassword(resultSet.getString(fields[2]));
                user.setEmail(resultSet.getString(fields[3]));
                user.setRole(resultSet.getString(fields[4]));
            }
            return user;
        } catch (Exception e) {
            return user;
        }
    }

    @Override
    public ArrayList<User> getAllBy(String field, String value) {
        try {
            String queryGetAllBy = "SELECT * FROM user WHERE " + field + " = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(queryGetAllBy);
            preparedStatement.setString(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            return pushData(resultSet);
        } catch (Exception e) {
            return new ArrayList<User>();
        }
    }


    @Override
    public User getBy(String field, String value) {
        User user = new User();
        try {
            String queryGetBy = "SELECT * FROM user WHERE " + field + " = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(queryGetBy);
            preparedStatement.setString(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(primaryKey));
                user.setUsername(resultSet.getString(fields[1]));
                user.setPassword(resultSet.getString(fields[2]));
                user.setEmail(resultSet.getString(fields[3]));
                user.setRole(resultSet.getString(fields[4]));
                return user;
            }
            return null;
        } catch (Exception e) {
            return user;
        }
    }

    @Override
    public ArrayList<User> search(String field, String value, boolean findAll) {
        String querySearch;
        if (findAll) {
            querySearch = "SELECT * FROM user WHERE ";
            for (int i = 0; i < fields.length; i++) {
                if (i == 0) {
                    querySearch += fields[i] + " LIKE ?";
                }
                if (i < fields.length - 1) {
                    querySearch += " OR " + fields[i] + " LIKE ?";
                } else {
                    querySearch += fields[i] + " LIKE ?";
                }
            }
        } else {
            querySearch = "SELECT * FROM user WHERE " + field + " LIKE ?";
        }
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(querySearch);
            preparedStatement.setString(1, "%" + value + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            return pushData(resultSet);
        } catch (Exception e) {
            return new ArrayList<User>();
        }
    }

    public Notification<User> login(String username, String password) {
        try {
            System.out.println(username + " " + password);
            User user = this.getUserByUsername(username);
            if (user == null) {
                return new Notification<>("Tên đăng nhập hoặc mật khẩu không đúng", false, null);
            }
            System.out.println(user);
            String decryptPassword = RSAService.decrypt(user.getPassword());
            System.out.println(decryptPassword);
            if (!decryptPassword.equals(password)) {
                return new Notification<>("Tên đăng nhập hoặc mật khẩu không đúng", false, null);
            }
            return new Notification<>("Đăng nhập thành công", true, user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Notification<>("Không thể đăng nhập", false, null);
        }

    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        ArrayList<User> users = userService.getAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    public Notification<User> register(String username, String password) {
        return null;
    }

}
