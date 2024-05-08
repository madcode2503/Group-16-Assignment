package Src;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Homepage extends JFrame implements ActionListener {
    JMenuItem b_list,c_list,r_list,logout,exit_app;
    Homepage() {
        setSize(1200, 750);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icon\\1484_06.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1200, 750, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        
        JMenuBar mb = new JMenuBar();
        JMenu newInformation = new JMenu("Booking");
        newInformation.setForeground(Color.BLUE);
        JMenuItem new_booking = new JMenuItem("New Booking");
        new_booking.addActionListener(this);
        newInformation.add(new_booking);
        mb.add(newInformation);
        JMenu admin = new JMenu("View details");
        b_list=new JMenuItem("Client booking list");
        b_list.addActionListener(this);
        admin.add(b_list);
        c_list=new JMenuItem("Client profile list");
        c_list.addActionListener(this);
        admin.add(c_list);
        r_list=new JMenuItem("Room list");
        r_list.addActionListener(this);
        admin.add(r_list);
        mb.add(admin);
        JMenu exit = new JMenu("Exit");
        exit.setForeground(Color.RED);
        logout = new JMenuItem("Log out");
        logout.addActionListener(this);
        exit.add(logout);
        exit_app = new JMenuItem("Exit");
        exit_app.addActionListener(this);
        exit.add(exit_app);
        mb.add(exit);
        setJMenuBar(mb);
        setLocation(200, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String msg = e.getActionCommand();
        if (msg.equals("New Booking")) {
            setVisible(false);
            new New_booking();
        } 
        else if(msg.equals("Client booking list")){
             setVisible(false);
             new Booking_list();
        }
        else if(msg.equals("Client profile list")){
            setVisible(false);
            new Client_List();
        }
        else if(msg.equals("Room list")){
            setVisible(false);
            new Room_list();
        }
        else if (msg.equals("Log out")) {
            setVisible(false);
            new Login();
        } else if (msg.equals("Exit")) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Homepage();
    }

}
