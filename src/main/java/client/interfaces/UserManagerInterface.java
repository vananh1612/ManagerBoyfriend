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
