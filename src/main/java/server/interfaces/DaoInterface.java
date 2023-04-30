package server.interfaces;

import shared.models.Notification;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DaoInterface<T> {
    /**
     * Tạo một đối tượng mới dựa trên dữ liệu được cung cấp.
     *
     * @param data dữ liệu được sử dụng để tạo đối tượng mới.
     * @return trả về một đối tượng Notification để chứa thông tin về việc tạo đối
     * tượng mới.
     * @throws SQLException nếu có vấn đề xảy ra trong khi truy vấn dữ liệu
     */
    Notification<T> create(T data) throws SQLException;

    /**
     * Cập nhật dữ liệu liên quan đến ID được chỉ định.
     *
     * @param id   ID của dữ liệu cần cập nhật.
     * @param data Dữ liệu mới để liên kết với ID được chỉ định.
     * @return Một đối tượng Notification chứa thông tin về việc cập nhật.
     * @throws SQLException nếu có vấn đề khi cập nhật dữ liệu.
     */
    Notification<T> update(int id, T data) throws SQLException;

    /**
     * Xóa dữ liệu liên quan đến ID được chỉ định.
     *
     * @param id ID của dữ liệu cần được xóa.
     * @return Một đối tượng Notification chứa thông tin về việc xóa.
     * @throws SQLException nếu có vấn đề khi xóa dữ liệu.
     */
    Notification<T> delete(int id) throws SQLException;

    /**
     * Lấy tất cả dữ liệu
     *
     * @return Tất cả dữ liệu
     * @throws SQLException nếu có vấn đề khi lấy tất cả dữ liệu.
     */
    ArrayList<T> getAll() throws SQLException;

    /**
     * Lấy dữ liệu theo ID
     *
     * @param id ID của dữ liệu
     * @return dữ liệu theo ID
     * @throws SQLException nếu có vấn đề khi lấy dữ liệu theo ID.
     */
    T getById(int id) throws SQLException;

    /**
     * Lấy dữ liệu theo field
     *
     * @param field Field của dữ liệu
     * @param value Value của dữ liệu
     * @return Mảng dữ liệu theo field
     * @throws SQLException nếu có vấn đề khi lấy dữ liệu theo field.
     */
    ArrayList<T> getAllBy(String field, String value) throws SQLException;

    /**
     * Lấy dữ liệu theo field
     *
     * @param field Field của dữ liệu
     * @param value Value của dữ liệu
     * @return Một đối tượng dữ liệu theo field
     * @throws SQLException nếu có vấn đề khi lấy dữ liệu theo field.
     */
    T getBy(String field, String value) throws SQLException;

    /**
     * Tìm kiếm dữ liệu theo field
     *
     * @param field Field của dữ liệu
     * @param value Value của dữ liệu
     * @return Một đối tượng dữ liệu theo field
     * @throws SQLException nếu có vấn đề khi tìm kiếm dữ liệu theo field.
     */
    ArrayList<T> search(String field, String value, boolean findAll) throws SQLException;
}
