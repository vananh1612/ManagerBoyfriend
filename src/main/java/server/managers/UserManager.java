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

    private DaoInterface<User> userService;

    public UserManager(DaoInterface userService) throws RemoteException {
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


}
