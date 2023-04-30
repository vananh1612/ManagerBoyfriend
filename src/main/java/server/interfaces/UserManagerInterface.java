package server.interfaces;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import shared.models.Notification;
import shared.models.User;

public interface UserManagerInterface extends ManagerInterface<User> {

    @Override
    Notification<User> create(User data) throws RemoteException, SQLException;

    @Override
    Notification<User> update(int id, User data) throws RemoteException, SQLException;

    @Override
    Notification<User> delete(int id) throws RemoteException, SQLException;

    @Override
    ArrayList<User> getAll() throws RemoteException, SQLException;

    @Override
    User getById(int id) throws RemoteException, SQLException;

    @Override
    ArrayList<User> getAllBy(String field, String value) throws RemoteException, SQLException;

    @Override
    User getBy(String field, String value) throws RemoteException, SQLException;

    @Override
    ArrayList<User> search(String field, String value, boolean findAll) throws RemoteException, SQLException;
}
