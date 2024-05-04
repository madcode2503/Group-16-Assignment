package Src;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Update_Client extends JFrame implements ActionListener {
    JButton submit, cancel;
    JLabel lbid;
    JTextField tfphone, tfcom;
    Choice choice_id;

    Update_Client() {
        getContentPane().setBackground(Color.white);
        setLayout(null);
        choice_id = new Choice();
        choice_id.setBackground(Color.WHITE);
        choice_id.setBounds(40, 60, 100, 50);
        add(choice_id);
        JLabel lb_c = new JLabel("Choose client id");
        lb_c.setBounds(160, 45, 100, 50);
        add(lb_c);
        try {
            DB db = new DB();
            ResultSet rs = db.s.executeQuery("Select * from clients");
            while (rs.next()) {
                choice_id.add(rs.getString("C_ID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JLabel heading = new JLabel("Update Client ");
        heading.setBounds(235, 0, 200, 50);
        heading.setFont(new Font("serif", Font.BOLD, 20));
        add(heading);
        JLabel c_id = new JLabel("Client ID");
        c_id.setBounds(40, 35, 200, 180);
        c_id.setFont(new Font("serif", Font.BOLD, 17));
        add(c_id);
        lbid = new JLabel();
        lbid.setBounds(140, 113, 120, 25);
        lbid.setFont(new Font("serif", Font.BOLD, 17));
        add(lbid);
        JLabel name = new JLabel("Client name");
        name.setBounds(40, 75, 200, 180);
        name.setFont(new Font("serif", Font.BOLD, 17));
        add(name);
        JLabel phone = new JLabel("Phone");
        phone.setBounds(350, 35, 200, 180);
        phone.setFont(new Font("serif", Font.BOLD, 17));
        add(phone);
        JLabel com = new JLabel("Company");
        com.setBounds(350, 75, 200, 180);
        com.setFont(new Font("serif", Font.BOLD, 17));
        add(com);
        JLabel lbname = new JLabel();
        lbname.setBounds(140, 155, 120, 25);
        add(lbname);
        tfphone = new JTextField();
        tfphone.setBounds(425, 115, 120, 25);
        tfphone.addActionListener(this);
        add(tfphone);
        tfcom = new JTextField();
        tfcom.setBounds(425, 155, 120, 25);
        tfcom.addActionListener(this);
        add(tfcom);
        submit = new JButton("Submit");
        submit.setBounds(180, 230, 100, 30);
        submit.setBackground(Color.BLACK);
        submit.setFont(new Font("Tahoma", Font.BOLD, 12));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);
        cancel = new JButton("Cancel");
        cancel.setBounds(300, 230, 100, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.white);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 12));
        cancel.addActionListener(this);
        add(cancel);
        try {
            DB db = new DB();
            String query = "select * from clients where C_ID='" + choice_id.getSelectedItem() + "'";
            ResultSet rs = db.s.executeQuery(query);
            while (rs.next()) {
                lbid.setText(rs.getString("C_ID"));
                lbname.setText(rs.getString("C_NAME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        choice_id.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    DB db = new DB();
                    String query = "select * from clients where C_ID='" + choice_id.getSelectedItem() + "'";
                    ResultSet rs = db.s.executeQuery(query);
                    while (rs.next()) {
                        lbid.setText(rs.getString("C_ID"));
                        lbname.setText(rs.getString("C_NAME"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        setLocation(500, 200);
        setSize(600, 350);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String phone = tfphone.getText();
            String com = tfcom.getText();
            try {
                DB db = new DB();
                db.s.executeUpdate("Update clients Set C_PHONE='" + phone + "',C_COM='" + com + "'");
                JOptionPane.showMessageDialog(null, "Client information updated successfully");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == cancel) {
            new Client_List();
        }
    }

    public static void main(String[] args) {
        new Update_Client();
    }
}
