package daos;

import models.ThongBao;
import java.util.ArrayList;

public interface DaoInterface<T> {

    ThongBao<T> create(T data) throws Exception;

    ThongBao<T> update(T data) throws Exception;

    ThongBao<T> delete(int id) throws Exception;

    T findOne(int id) throws Exception;

    ArrayList<T> findAll() throws Exception;

    ArrayList<T> search(String key) throws Exception;

    ArrayList<T> findBy(String name, String value) throws Exception;

    T findOneBy(String name, String value) throws Exception;


}
