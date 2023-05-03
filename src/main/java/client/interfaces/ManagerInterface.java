package client.interfaces;

import shared.models.Notification;

import java.rmi.Remote;
import java.util.ArrayList;

public interface ManagerInterface<T> extends Remote {

    /**
     * Tạo một đối tượng mới dựa trên dữ liệu được cung cấp.
     *
     * @param data dữ liệu được sử dụng để tạo đối tượng mới.
     * @return trả về một đối tượng Notification để chứa thông tin về việc tạo đối
     * tượng mới.
     * @throws Exception nếu có vấn đề xảy ra trong khi giao tiếp với máy chủ
     *                   từ xa.
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
