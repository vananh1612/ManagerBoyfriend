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
