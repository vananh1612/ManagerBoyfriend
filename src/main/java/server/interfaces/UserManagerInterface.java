package server.interfaces;

import java.rmi.RemoteException;
import java.util.ArrayList;

import shared.models.Notification;
import shared.models.User;

public interface UserManagerInterface extends ManagerInterface<User> {

    @Override
    Notification<User> create(User data) throws RemoteException;

    @Override
    Notification<User> update(int id, User data) throws RemoteException;

    @Override
    Notification<User> delete(int id) throws RemoteException;

    @Override
    ArrayList<User> getAll() throws RemoteException;

    @Override
    User getById(int id) throws RemoteException;

    @Override
    ArrayList<User> getAllBy(String field, String value) throws RemoteException;

    @Override
    User getBy(String field, String value) throws RemoteException;

    @Override
    ArrayList<User> search(String field, String value) throws RemoteException;
}
