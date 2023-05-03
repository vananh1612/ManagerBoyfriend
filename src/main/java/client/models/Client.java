package client.models;

import server.interfaces.UserManagerInterface;
import shared.models.Notification;
import shared.models.User;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Client implements UserManagerInterface {
    private UserManagerInterface userManager;

    public Client() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8080);
            userManager = (UserManagerInterface) registry.lookup("UserManager");
            System.out.println(userManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        ArrayList<User> users = client.userManager.getAll();
        for (User user : users) {
            System.out.println(user);
        }
        User user = new User();
        user.setUsername("test3");
        user.setPassword("test");
        user.setRole("client");
        System.out.println(client.userManager.create(user));
    }


    @Override
    public Notification<User> create(User data) throws Exception {
        return this.userManager.create(data);
    }

    @Override
    public Notification<User> update(int id, User data) throws Exception {
        return this.userManager.update(id, data);
    }

    @Override
    public Notification<User> delete(int id) throws Exception {
        return this.userManager.delete(id);
    }

    @Override
    public ArrayList<User> getAll() throws Exception {
        return this.userManager.getAll();
    }

    @Override
    public User getById(int id) throws Exception {
        return this.userManager.getById(id);
    }

    @Override
    public ArrayList<User> getAllBy(String field, String value) throws Exception {
        return this.userManager.getAllBy(field, value);
    }

    @Override
    public User getBy(String field, String value) throws Exception {
        return this.userManager.getBy(field, value);
    }

    @Override
    public ArrayList<User> search(String field, String value, boolean findAll) throws Exception {
        return this.userManager.search(field, value, findAll);
    }

    @Override
    public Notification<User> login(String username, String password) throws Exception {
        return this.userManager.login(username, password);
    }

    @Override
    public Notification<User> register(String username, String password) throws Exception {
        return this.userManager.register(username, password);
    }
}