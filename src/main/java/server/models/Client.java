package server.models;

import server.configs.Configs;
import server.interfaces.UserManagerInterface;
import shared.models.Notification;
import shared.models.User;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;

public class Client {
    private UserManagerInterface userManager;

    public Client() {
        try {
            Registry registry = LocateRegistry.getRegistry(Configs.SERVER_ADDRESS, Configs.SERVER_PORT);
            userManager = (UserManagerInterface) registry.lookup("UserManager");
            System.out.println(userManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, RemoteException {
        Client client = new Client();
        User user = new User();
        user.setUsername("test3");
        user.setPassword("test3");
        user.setEmail("test3");
        user.setRole("test3");
        System.out.println(client.createUser(user));
    }


    public Notification<User> createUser(User data) throws RemoteException, SQLException {
        return this.userManager.create(data);
    }

}