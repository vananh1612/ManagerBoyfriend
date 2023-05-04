package client.views;

import javax.swing.*;

public class CollapsiblePanelExample extends JFrame {

    public CollapsiblePanelExample() {
        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create a menu
        JMenu menu = new JMenu("File");

        // Create menu items
        JMenuItem menuItem1 = new JMenuItem("Open");
        JMenuItem menuItem2 = new JMenuItem("Exit");

        // Add the menu items to the menu
        menu.add(menuItem1);
        menu.addSeparator(); // adds a separator line between items
        menu.add(menuItem2);

        // Add the menu to the menu bar
        menuBar.add(menu);

        // Set the menu bar as the window's menu bar
        setJMenuBar(menuBar);

        // Set window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Menu Example");
        setSize(400, 300);
        setLocationRelativeTo(null); // centers the window on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        new CollapsiblePanelExample();
    }
}
