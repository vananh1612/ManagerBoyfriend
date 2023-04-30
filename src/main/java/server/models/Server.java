package server.models;

import server.configs.Configs;
import server.interfaces.UserManagerInterface;
import server.managers.UserManager;
import server.services.database.UserService;
import shared.models.User;

import java.lang.reflect.Array;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Server {
    public UserManagerInterface userManager;
    public Server() {
        try {
            userManager = new UserManager(new UserService());
            LocateRegistry.createRegistry(Configs.SERVER_PORT);
            LocateRegistry.getRegistry(8080).rebind("UserManager", userManager);
            System.out.println("Server created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}

