package server.managers;

import server.interfaces.UserManagerInterface;
import shared.models.Notification;
import shared.models.User;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class UserManager implements UserManagerInterface {
    @Override
    public Notification<User> create(User data) throws RemoteException {
        return null;
    }

    @Override
    public Notification<User> update(int id, User data) throws RemoteException {
        return null;
    }

    @Override
    public Notification<User> delete(int id) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<User> getAll() throws RemoteException {
        return null;
    }

    @Override
    public User getById(int id) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<User> getAllBy(String field, String value) throws RemoteException {
        return null;
    }

    @Override
    public User getBy(String field, String value) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<User> search(String field, String value) throws RemoteException {
        return null;
    }
}
