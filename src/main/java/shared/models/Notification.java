package shared.models;

import lombok.Data;

import java.io.Serializable;

/**
 * Lớp này thể hiện một thông báo với dữ liệu liên quan và trạng thái thành công.
 *
 * @param <T> Kiểu dữ liệu liên kết với thông báo
 */
@Data
public class Notification<T> implements Serializable {

    /** Chuỗi thông báo */
    private String message;

    /** Dữ liệu liên quan đến thông báo */
    private T data;

    /** Trạng thái thành công của thông báo */
    private boolean success;
}
