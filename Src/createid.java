package Src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class createid extends JFrame implements ActionListener {
    JButton sub;

    createid() {
        setLayout(null);
        setVisible(true);
        setSize(500, 400);
        setLocation(400, 200);
        sub = new JButton("Sub");
        sub.setBounds(200, 100, 100, 50);
        sub.addActionListener(this);
        add(sub);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == sub) {
            try {
                   DB db=new DB();
                   for(int i=2001;i<4000;i++)
                   {db.s.executeUpdate("Insert into id values('"+i+"','Available')");
                }
            } catch (Exception e) {
JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);          }
        }
    }

    public static void main(String[] args) {
        new createid();
    }
}
