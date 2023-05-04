# TRIỂN KHAI MỘT ỨNG DỤNG QUẢN LÝ BẰNG RMI

### MỤC LỤC

#### [GIỚI THIỆU VỀ RMI](#gioithieu)

#### [TRIỂN KHAI MỘT VÍ DỤ CƠ BẢN VỀ RMI]()

#### [CẤU TRÚC THƯ MUC DỰ ÁN]()

#### [TRIỂN KHAI CODE]()

#### [GIẢI THÍCH MỘT SỐ HÀM, CLASS]()

<br></br>

#### RMI LÀ GÌ ?

RMI là viết tắt của "Remote Method Invocation" là một cơ chế trong Java cho phép các đối tượng Java gọi các phương thức
của các đối tượng khác ở máy chủ từ xa. Nó được sử dụng để triển khai các ứng dụng mạng phân tán, trong đó các thành
phần của ứng dụng chạy trên các máy khác nhau và giao tiếp với nhau thông qua mạng.

Các ứng dụng RMI bao gồm một máy chủ RMI, nơi các đối tượng được đăng ký và có thể được gọi từ xa bởi các máy khách RMI.
Máy khách RMI cũng sử dụng các đối tượng proxy để gọi các phương thức từ xa trên các đối tượng đó.

RMI được tích hợp sẵn trong JDK của Java và cung cấp một cách đơn giản để triển khai các ứng dụng phân tán bằng Java.

<br></br>

#### TRIỂN KHAI MỘT VÍ DỤ CƠ BẢN VỀ RMI

Đầu tiên, chúng ta sẽ tạo một interface `Calculator` để định nghĩa các phương thức cho máy tính:

```java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
    int add(int x, int y) throws RemoteException;
    int subtract(int x, int y) throws RemoteException;
    int multiply(int x, int y) throws RemoteException;
    int divide(int x, int y) throws RemoteException;
}
```

Sau đó, chúng ta sẽ triển khai interface này trên một class `CalculatorImpl`:

```java
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {
    public CalculatorImpl() throws RemoteException {
        super();
    }

    public int add(int x, int y) throws RemoteException {
        return x + y;
    }

    public int subtract(int x, int y) throws RemoteException {
        return x - y;
    }

    public int multiply(int x, int y) throws RemoteException {
        return x * y;
    }

    public int divide(int x, int y) throws RemoteException {
        return x / y;
    }
}
```
Ở đây, chúng ta triển khai các phương thức được định nghĩa trong `Calculator` và sử dụng **`UnicastRemoteObject`** để tạo một đối tượng RMI. `UnicastRemoteObject` là một lớp trừu tượng trong gói `java.rmi.server`, được sử dụng để tạo đối tượng RMI.

Tiếp theo, chúng ta sẽ tạo một máy chủ RMI để đăng ký `CalculatorImpl` và cung cấp dịch vụ tính toán cho các máy khách RMI:
```java
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorServer {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1099);
        Calculator calculator = new CalculatorImpl();
        Naming.rebind("//localhost/CalculatorService", calculator);
        System.out.println("CalculatorService is running...");
    }
}
```
Ở đây, chúng ta sử dụng `LocateRegistry` để tạo một `registry` RMI và `Naming.rebind()` để đăng ký `CalculatorImpl` với `registry`. Sau khi đăng ký thành công, các máy khách RMI có thể gọi các phương thức của `Calculator` thông qua đối tượng `calculator` đã đăng ký.

Cuối cùng, chúng ta sẽ tạo một máy khách RMI để gọi các phương thức từ xa trên CalculatorImpl:

```java
import java.rmi.Naming;

public class CalculatorClient {
    public static void main(String[] args) throws Exception {
        Calculator calculator = (Calculator) Naming.lookup("//localhost/CalculatorService");
        System.out.println("1 + 2 = " + calculator.add(1, 2));
        System.out.println("3 - 2 = " + calculator.subtract(3, 2));
        System.out.println("3 * 2 = " + calculator.multiply(3, 2));
        System.out.println("3 / 2 = " + calculator.divide(3, 2));
    }
}
```

#### CẤU TRÚC THƯ MỤC DỰ ÁN

```dtd
├───security  
├───src      
   └───main    
       └───java
           ├───client 
           │   ├───controlles
           │   ├───interfaces
           │   ├───models
           │   └───views
           ├───server
           │   ├───configs
           │   ├───constans
           │   ├───controlles
           │   ├───hashs
           │   ├───interfaces
           │   ├───managers
           │   ├───migrates
           │   ├───models
           │   ├───services
           │   │   └───database
           │   └───views
           └───shared
               ├───models
               └───utils
```
Dưới đây là giải thích cho cấu trúc thư mục của một dự án Java sử dụng Maven:

*   Thư mục gốc của dự án có thể có tên bất kỳ, tuy nhiên thường được đặt là tên của dự án. Trong cây thư mục này, thường sẽ có một số thư mục quan trọng, như sau:

    *   `security`: chứa các file liên quan đến bảo mật, ví dụ như file cấu hình cho SSL/TLS.
    *   `src`: chứa mã nguồn của dự án.
        *   `main`: chứa mã nguồn chính của dự án.
            *   `java`: chứa các package chứa mã nguồn Java.
                *   `client`: chứa mã nguồn liên quan đến phía client.
                    *   `controllers`: chứa các lớp điều khiển (controller) cho phía client.
                    *   `interfaces`: chứa các interface cho phía client.
                    *   `models`: chứa các lớp mô hình (model) cho phía client.
                    *   `views`: chứa các lớp giao diện (view) cho phía client.
                *   `server`: chứa mã nguồn liên quan đến phía server.
                    *   `configs`: chứa các file cấu hình cho server.
                    *   `constants`: chứa các hằng số cho server.
                    *   `controllers`: chứa các lớp điều khiển (controller) cho server.
                    *   `hashs`: chứa các lớp liên quan đến việc băm (hash) dữ liệu.
                    *   `interfaces`: chứa các interface cho server.
                    *   `managers`: chứa các lớp quản lý (manager) cho server.
                    *   `migrates`: chứa các file liên quan đến việc di chuyển dữ liệu giữa các phiên bản của server.
                    *   `models`: chứa các lớp mô hình (model) cho server.
                    *   `services`: chứa các lớp dịch vụ (service) cho server.
                        *   `database`: chứa các lớp liên quan đến cơ sở dữ liệu cho server.
                    *   `views`: chứa các lớp giao diện (view) cho server.
                *   `shared`: chứa các package chung cho cả client và server.
                    *   `models`: chứa các lớp mô hình (model) chung cho cả client và server.
                    *   `utils`: chứa các tiện ích chung cho cả client và server.



#### TRIỂN KHAI CODE

Tương tư như những lần trước mà có người đã chỉ cho em
Sau khi đã tạo `DAOInterface` và các service cần thiết để làm việc với `Database`

```java
package server.interfaces;

import shared.models.Notification;

import java.util.ArrayList;

public interface DaoInterface<T> {
    /**
     * Tạo một đối tượng mới dựa trên dữ liệu được cung cấp.
     *
     * @param data dữ liệu được sử dụng để tạo đối tượng mới.
     * @return trả về một đối tượng Notification để chứa thông tin về việc tạo đối
     * tượng mới.
     *  nếu có vấn đề xảy ra trong khi truy vấn dữ liệu
     */
    Notification<T> create(T data);

    /**
     * Cập nhật dữ liệu liên quan đến ID được chỉ định.
     *
     * @param id   ID của dữ liệu cần cập nhật.
     * @param data Dữ liệu mới để liên kết với ID được chỉ định.
     * @return Một đối tượng Notification chứa thông tin về việc cập nhật.
     *  nếu có vấn đề khi cập nhật dữ liệu.
     */
    Notification<T> update(int id, T data);

    /**
     * Xóa dữ liệu liên quan đến ID được chỉ định.
     *
     * @param id ID của dữ liệu cần được xóa.
     * @return Một đối tượng Notification chứa thông tin về việc xóa.
     *  nếu có vấn đề khi xóa dữ liệu.
     */
    Notification<T> delete(int id);

    /**
     * Lấy tất cả dữ liệu
     *
     * @return Tất cả dữ liệu
     *  nếu có vấn đề khi lấy tất cả dữ liệu.
     */
    ArrayList<T> getAll();

    /**
     * Lấy dữ liệu theo ID
     *
     * @param id ID của dữ liệu
     * @return dữ liệu theo ID
     *  nếu có vấn đề khi lấy dữ liệu theo ID.
     */
    T getById(int id);

    /**
     * Lấy dữ liệu theo field
     *
     * @param field Field của dữ liệu
     * @param value Value của dữ liệu
     * @return Mảng dữ liệu theo field
     *  nếu có vấn đề khi lấy dữ liệu theo field.
     */
    ArrayList<T> getAllBy(String field, String value);

    /**
     * Lấy dữ liệu theo field
     *
     * @param field Field của dữ liệu
     * @param value Value của dữ liệu
     * @return Một đối tượng dữ liệu theo field
     *  nếu có vấn đề khi lấy dữ liệu theo field.
     */
    T getBy(String field, String value);

    /**
     * Tìm kiếm dữ liệu theo field
     *
     * @param field Field của dữ liệu
     * @param value Value của dữ liệu
     * @return Một đối tượng dữ liệu theo field
     *  nếu có vấn đề khi tìm kiếm dữ liệu theo field.
     */
    ArrayList<T> search(String field, String value, boolean findAll);
}
```


Tạo một lớp `Interface` kế thừa cho lớp Remote trong RMI
```java
package server.interfaces;

import java.rmi.Remote;
import java.lang.Exception;
import java.util.ArrayList;

import shared.models.Notification;

public interface ManagerInterface<T> extends Remote {

    /**
     * Tạo một đối tượng mới dựa trên dữ liệu được cung cấp.
     *
     * @param data dữ liệu được sử dụng để tạo đối tượng mới.
     * @return trả về một đối tượng Notification để chứa thông tin về việc tạo đối
     * tượng mới.
     * @throws Exception nếu có vấn đề xảy ra trong khi giao tiếp với máy chủ
     *                         từ xa.
     */
    Notification<T> create(T data) throws Exception;

    /**
     * Cập nhật dữ liệu liên quan đến ID được chỉ định.
     *
     * @param id   ID của dữ liệu cần cập nhật.
     * @param data Dữ liệu mới để liên kết với ID được chỉ định.
     * @return Một đối tượng Notification chứa thông tin về việc cập nhật.
     * @throws Exception nếu có vấn đề khi cập nhật dữ liệu.
     */
    Notification<T> update(int id, T data) throws Exception;

    /**
     * Xóa dữ liệu liên quan đến ID được chỉ định.
     *
     * @param id ID của dữ liệu cần được xóa.
     * @return Một đối tượng Notification chứa thông tin về việc xóa.
     * @throws Exception nếu có vấn đề khi xóa dữ liệu.
     */
    Notification<T> delete(int id) throws Exception;

    /**
     * Lấy tất cả dữ liệu
     *
     * @return Tất cả dữ liệu
     * @throws Exception nếu có vấn đề khi lấy tất cả dữ liệu.
     */
    ArrayList<T> getAll() throws Exception;

    /**
     * Lấy dữ liệu theo ID
     *
     * @param id ID của dữ liệu
     * @return dữ liệu theo ID
     * @throws Exception nếu có vấn đề khi lấy dữ liệu theo ID.
     */
    T getById(int id) throws Exception;

    /**
     * Lấy dữ liệu theo field
     *
     * @param field Field của dữ liệu
     * @param value Value của dữ liệu
     * @return Mảng dữ liệu theo field
     * @throws Exception nếu có vấn đề khi lấy dữ liệu theo field.
     */
    ArrayList<T> getAllBy(String field, String value) throws Exception;

    /**
     * Lấy dữ liệu theo field
     *
     * @param field Field của dữ liệu
     * @param value Value của dữ liệu
     * @return Một đối tượng dữ liệu theo field
     * @throws Exception nếu có vấn đề khi lấy dữ liệu theo field.
     */
    T getBy(String field, String value) throws Exception;

    /**
     * Tìm kiếm dữ liệu theo field
     *
     * @param field Field của dữ liệu
     * @param value Value của dữ liệu
     * @return Một đối tượng dữ liệu theo field
     * @throws Exception nếu có vấn đề khi tìm kiếm dữ liệu theo field.
     */
    ArrayList<T> search(String field, String value, boolean findAll) throws Exception;
}

```
Đối tượng này tương tự có các chức năng như lớp `DAO` nhưng đã kế thừa lớp `Remote`

Ở đây khi xây dựng các `Class` để đặc tả cho một đối tượng đã sử dụng thư viện lombook để viết code ngắn hơn 
nhưng vẫn đảm bảo về mặt code.

```java
package shared.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Lớp User đại diện cho một người dùng trong hệ thống
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable { 

    /** ID của người dùng. */
    private int id;

    /** Tên đăng nhập của người dùng. */
    private String username;

    /** Mật khẩu của người dùng. */
    private String password;

    /** Địa chỉ email của người dùng. */
    private String email;

    /** Vai trò của người dùng. */
    private String role;

}

```


Ở đây chương trình đã demo chức năng quản lý Người dùng
Đầu tiên ta sẽ tạo một `Interface` kế thừa lại lớp `MangerInterface`

```java
package client.interfaces;

import shared.models.Notification;
import shared.models.User;

import java.util.ArrayList;

public interface UserManagerInterface extends ManagerInterface<User> {

    @Override
    Notification<User> create(User data) throws Exception;

    @Override
    Notification<User> update(int id, User data) throws Exception;

    @Override
    Notification<User> delete(int id) throws Exception;

    @Override
    ArrayList<User> getAll() throws Exception;

    @Override
    User getById(int id) throws Exception;

    @Override
    ArrayList<User> getAllBy(String field, String value) throws Exception;

    @Override
    User getBy(String field, String value) throws Exception;

    @Override
    ArrayList<User> search(String field, String value, boolean findAll) throws Exception;

    Notification<User> login(String username, String password) throws Exception;

    Notification<User> register(String username, String password) throws Exception;

}

```

Sau đó trong pakage `manager` mình sẽ tạo một lớp `UserManager` thực thi lớp `UserManagerInterface`

```java
package server.managers;

import server.interfaces.DaoInterface;
import server.interfaces.UserManagerInterface;
import server.services.database.UserService;
import shared.models.Notification;
import shared.models.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager extends UnicastRemoteObject implements UserManagerInterface {

    private final UserService userService;

    public UserManager(UserService userService) throws RemoteException {
        this.userService = userService;
    }

    @Override
    public Notification<User> create(User data) throws RemoteException, SQLException {
        return this.userService.create(data);
    }

    @Override
    public Notification<User> update(int id, User data) throws RemoteException, SQLException {
        return this.userService.update(id, data);
    }

    @Override
    public Notification<User> delete(int id) throws RemoteException, SQLException {
        return this.userService.delete(id);
    }

    @Override
    public ArrayList<User> getAll() throws RemoteException, SQLException {
        return this.userService.getAll();
    }

    @Override
    public User getById(int id) throws RemoteException, SQLException {
        return this.userService.getById(id);
    }

    @Override
    public ArrayList<User> getAllBy(String field, String value) throws RemoteException, SQLException {
        return this.userService.getAllBy(field, value);
    }

    @Override
    public User getBy(String field, String value) throws RemoteException, SQLException {
        return this.userService.getBy(field, value);
    }


    @Override
    public ArrayList<User> search(String field, String value, boolean findAll) throws RemoteException, SQLException {
        return this.userService.search(field, value, findAll);
    }

    @Override
    public Notification<User> login(String username, String password) throws Exception {
        return this.userService.login(username, password);
    }

    @Override
    public Notification<User> register(String username, String password) throws Exception {
        return this.userService.register(username, password);
    }
}

```

Lớp UserService có các chức năng để làm việc với `Database`

```java
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

```
Sau khi đã triển khai xong ở class `Server` trong pakage `model` của `server`
Mình sẽ đăng ký như sau

```java
package server.models;

import server.configs.Configs;
import server.interfaces.UserManagerInterface;
import server.managers.UserManager;
import server.services.database.UserService;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteServer;

public class Server {
    public UserManagerInterface userManager;

    public Server() {
        try {
            userManager = new UserManager(new UserService());
            LocateRegistry.createRegistry(8080);
            LocateRegistry.getRegistry(8080).rebind("UserManager", userManager);
            //print ip address server
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}


```

Ở client mình sẽ sử dụng lại `ip` của `Server` và chính `port` mình đã đăng ký trong `server` để kết nối


```java
package client.models;

import server.interfaces.UserManagerInterface;
import shared.models.Notification;
import shared.models.User;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Client implements UserManagerInterface {
    private UserManagerInterface userManager;

    public Client() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8080);
            userManager = (UserManagerInterface) registry.lookup("UserManager");
            System.out.println(userManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        ArrayList<User> users = client.userManager.getAll();
        for (User user : users) {
            System.out.println(user);
        }
        User user = new User();
        user.setUsername("test3");
        user.setPassword("test");
        user.setRole("client");
        System.out.println(client.userManager.create(user));
    }


    @Override
    public Notification<User> create(User data) throws Exception {
        return this.userManager.create(data);
    }

    @Override
    public Notification<User> update(int id, User data) throws Exception {
        return this.userManager.update(id, data);
    }

    @Override
    public Notification<User> delete(int id) throws Exception {
        return this.userManager.delete(id);
    }

    @Override
    public ArrayList<User> getAll() throws Exception {
        return this.userManager.getAll();
    }

    @Override
    public User getById(int id) throws Exception {
        return this.userManager.getById(id);
    }

    @Override
    public ArrayList<User> getAllBy(String field, String value) throws Exception {
        return this.userManager.getAllBy(field, value);
    }

    @Override
    public User getBy(String field, String value) throws Exception {
        return this.userManager.getBy(field, value);
    }

    @Override
    public ArrayList<User> search(String field, String value, boolean findAll) throws Exception {
        return this.userManager.search(field, value, findAll);
    }

    @Override
    public Notification<User> login(String username, String password) throws Exception {
        return this.userManager.login(username, password);
    }

    @Override
    public Notification<User> register(String username, String password) throws Exception {
        return this.userManager.register(username, password);
    }
}
```


```
    Registry registry = LocateRegistry.getRegistry("localhost", 8080);
    userManager = (UserManagerInterface) registry.lookup("UserManager");
```

Ở đây ip mặc định của máy tính là `localhost` và `port` là `8080`

Còn một số phần trong `view` và `controller` nữa nhưng mà em tự tìm hiểu đi
Anh không phải rảnh để ngồi làm như thế này cho em. Mà là vì lời nói của anh, chắc như thế này cũng dữ lời với em rồi
Sau này chúc em học thật tốt. 2023