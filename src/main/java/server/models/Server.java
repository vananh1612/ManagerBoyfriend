package server.models;

import server.configs.Configs;
import server.interfaces.UserManagerInterface;
import server.managers.UserManager;
import server.services.database.UserService;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteServer;

public class Server {
    public UserManagerInterface userManager;

    public Server() {
        try {
            userManager = new UserManager(new UserService());
            LocateRegistry.createRegistry(8080);
            LocateRegistry.getRegistry(8080).rebind("UserManager", userManager);
            //print ip address server
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}

