package client.views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static client.views.AdminDashboard.*;

public class AdminDashboard {
    static final int FRAME_WIDTH = 1300;
    static final int FRAME_HEIGHT = 700;
    static final int SIDEBAR_WIDTH = 230;
    static final Color HEADER_BG_COLOR = new Color(252, 251, 255);
    static final Color FRAGMENT_BG_COLOR = new Color(252, 251, 255);
    static final Color MENUITEM_BG_COLOR = new Color(252, 133, 104);
    static final Color COLOR_BAR_BG_COLOR = new Color(237, 237, 237);
    static final Color PRIMARY_COLOR = new Color(141, 56, 242);
    static final Color SECONDARY_COLOR = new Color(141, 56, 242);
    static final int MENUITEM_WIDTH = 300;
    private static final int MENUITEM_HEIGHT = 30;
    private static final String[] MENUITEMS = {
            "Thêm sản phẩm mới",
            "Chỉnh sửa thông tin sản phẩm",
            "Xóa sản phẩm",
            "Xem danh sách sản phẩm",
            "Thêm người dùng mới",
            "Chỉnh sửa thông tin người dùng",
            "Xóa người dùng",
            "Xem danh sách người dùng",
            "Quản lý đơn hàng",
            "Xem danh sách đơn hàng",
            "Xem chi tiết đơn hàng",
            "Xác nhận đơn hàng",
            "Hủy đơn hàng",
            "Xem doanh thu",
            "Xem báo cáo tổng quan",
            "Thêm mã giảm giá",
            "Chỉnh sửa thông tin mã giảm giá",
            "Xóa mã giảm giá",
            "Xem danh sách mã giảm giá",
            "Đăng xuất"
    };

    private JFrame frame;
    private JPanel mainPanel, sideBarPanel, contentPanel, headerPanel, fragmentPanel, detailsPanel;
    private JList<ItemMenuSideBar> menuList;

    private void init() {
        // Create frame
        frame = new JFrame("Admin Dashboard");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Create main panel
        mainPanel = new JPanel(new BorderLayout());
        frame.setContentPane(mainPanel);

        // Create sidebar panel
        sideBarPanel = new JPanel(new BorderLayout());
        sideBarPanel.setPreferredSize(new Dimension(SIDEBAR_WIDTH, FRAME_HEIGHT));
        sideBarPanel.setBackground(PRIMARY_COLOR);

        // Create menu panel for sidebar
        DefaultListModel<ItemMenuSideBar> menuListModel = new DefaultListModel<>();
        for (String item : MENUITEMS) {
            menuListModel.addElement(new ItemMenuSideBar(item));
        }
        menuList = new JList<ItemMenuSideBar>(menuListModel);
        menuList.setBackground(SECONDARY_COLOR);
        menuList.setCellRenderer(new ItemMenuSideBarRender());
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuList.setLayoutOrientation(JList.VERTICAL);
        JScrollPane menuScrollPane = new JScrollPane(menuList);
        menuScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        menuScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        menuList.setSelectedIndex(0);
        // Tạo detail panel cho side bar
        detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBackground(SECONDARY_COLOR);
        JLabel detailsLabel = new JLabel("Xin chào Nguyễn Văn Nam");
        detailsPanel.add(detailsLabel, BorderLayout.CENTER);
        detailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        detailsLabel.setForeground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, COLOR_BAR_BG_COLOR));
        detailsPanel.setPreferredSize(new Dimension(FRAME_WIDTH - SIDEBAR_WIDTH, 150));
        sideBarPanel.add(menuScrollPane, BorderLayout.CENTER);
        sideBarPanel.add(detailsPanel, BorderLayout.NORTH);
        contentPanel = new JPanel(new BorderLayout());
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(HEADER_BG_COLOR);
        headerPanel.setPreferredSize(new Dimension(FRAME_WIDTH - SIDEBAR_WIDTH, 80));
        headerPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 0, 10, COLOR_BAR_BG_COLOR));
        JLabel titleLabel = new JLabel("Dashboard");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        menuScrollPane.setBorder(null);
        // Create fragment panel for content
        fragmentPanel = new JPanel(new BorderLayout());
        fragmentPanel.setBackground(COLOR_BAR_BG_COLOR);
        JLabel fragmentLabel = new JLabel(MENUITEMS[0]);
        fragmentLabel.setFont(new Font("Arial", Font.BOLD, 50));
        fragmentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel fragmentPanelContent = new JPanel(new BorderLayout());
        fragmentPanelContent.add(fragmentLabel, BorderLayout.CENTER);
        fragmentPanelContent.setPreferredSize(new Dimension(FRAME_WIDTH - SIDEBAR_WIDTH, 100));
        fragmentPanelContent.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, COLOR_BAR_BG_COLOR));
        fragmentPanelContent.setBackground(FRAGMENT_BG_COLOR);
        fragmentPanel.add(fragmentPanelContent, BorderLayout.CENTER);
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(fragmentPanel, BorderLayout.CENTER);
        mainPanel.add(sideBarPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        menuList.addListSelectionListener(e -> {
            int index = menuList.getSelectedIndex();
            fragmentLabel.setText(MENUITEMS[index]);
            frame.setTitle("Admin Dashboard - " + MENUITEMS[index]);
        });
    }

    public JPanel getFragmentPanel() {
        return fragmentPanel;
    }

    public static void main(String[] args) {
        new AdminDashboard().init();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ItemMenuSideBar {
    private String name;
}
class ItemMenuSideBarRender implements ListCellRenderer<ItemMenuSideBar> {
    private JPanel itemPanel, mainPanel;
    private JLabel text;
    private final Color FOREGROUND_COLOR = new Color(236, 236, 236);
    @Override
    public Component getListCellRendererComponent(JList<? extends ItemMenuSideBar> list, ItemMenuSideBar item, int index, boolean isSelected, boolean cellHasFocus) {
        itemPanel = new JPanel(new BorderLayout());
        mainPanel = new JPanel(new BorderLayout());
        text = new JLabel(item.getName());
        itemPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, COLOR_BAR_BG_COLOR));
        itemPanel.add(mainPanel, BorderLayout.CENTER);
        mainPanel.add(text, BorderLayout.CENTER);
        mainPanel.setBorder(new EmptyBorder(15, 10, 15, 0));
        if (isSelected) {
            mainPanel.setBackground(MENUITEM_BG_COLOR);
            text.setForeground(FOREGROUND_COLOR);
        } else {
            mainPanel.setBackground(SECONDARY_COLOR);
            text.setForeground(FOREGROUND_COLOR);
        }
        return itemPanel;
    }
}
