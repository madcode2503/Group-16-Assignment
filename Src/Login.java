package Src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.w3c.dom.events.Event;

import com.mysql.cj.protocol.Resultset;

@SuppressWarnings("unused")
public class Login extends JFrame implements ActionListener {
    JButton login;
    JButton cancel;
    JTextField tf_username;
    JPasswordField tf_password;

    Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        JLabel lb_username = new JLabel("Username");
        lb_username.setBounds(40, 40, 100, 20);
        add(lb_username);
        tf_username = new JTextField();
        tf_username.setBounds(150, 40, 150, 20);
        add(tf_username);

        JLabel lb_password = new JLabel("Password");
        lb_password.setBounds(40, 90, 100, 20);
        add(lb_password);
        tf_password = new JPasswordField();
        tf_password.setBounds(150, 90, 150, 20);
        add(tf_password);
        login = new JButton("Login");
        login.setBounds(40, 130, 100, 30);
        login.setBackground(Color.BLACK);
        login.setFont(new Font("Tahoma", Font.BOLD, 12));
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);
        cancel = new JButton("Cancel");
        cancel.setBounds(150, 130, 100, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.white);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 12));
        cancel.addActionListener(this);
        add(cancel);
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Icon\\manager_115893.png"));
        Image img1 = img.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon img2 = new ImageIcon(img1);
        JLabel img3 = new JLabel(img2);
        img3.setBounds(350, 20, 200, 200);
        add(img3);
        setSize(600, 300);
        setLocation(500, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String username = tf_username.getText();
            @SuppressWarnings("deprecation")
            String password = tf_password.getText();
            String query = "Select * from acc where username='" + username + "'and password='" + password + "'";
            try {
                DB db = new DB();
                ResultSet rs = db.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new Homepage();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password! Please check again! ");
                }
            } catch (Exception ex) {
                ex.getStackTrace();
            }
        } 
        else if (e.getSource() == cancel) {
            setVisible(false);
        }
    }

    
}
