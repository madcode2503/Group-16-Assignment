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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Update_booking extends JFrame implements ActionListener {
    Choice cr_num;
    @SuppressWarnings("rawtypes")
    JComboBox cb_ac,cb_prj,cb_spk,cb_mic;
    JButton update,cancel;
    Update_booking() {
        getContentPane().setBackground(Color.white);
        setLayout(null);
        JLabel heading = new JLabel("Update client booking");
        heading.setFont(new Font("serif", Font.BOLD, 25));
        heading.setBounds(245, 0, 250, 50);
        add(heading);
        JLabel c_id = new JLabel("Client ID:");
        c_id.setBounds(50, 100, 100, 50);
        c_id.setFont(new Font("serif", Font.PLAIN, 20));
        add(c_id);
        JLabel lb_id= new JLabel();
        lb_id.setBounds(150, 100, 100, 50);
        lb_id.setFont(new Font("serif", Font.BOLD, 20));
        add(lb_id);
        JLabel name= new JLabel("Client name:");
        name.setBounds(50, 150, 150, 50);
        name.setFont(new Font("serif", Font.PLAIN, 20));
        add(name);
        JLabel lbname=new JLabel();
        lbname.setBounds(160, 150, 150, 50);
        lbname.setFont(new Font("serif", Font.BOLD, 20));
        add(lbname);
        JLabel b_time= new JLabel("Booking time:");
        b_time.setBounds(350, 150, 150, 50);
        b_time.setFont(new Font("serif", Font.PLAIN, 20));
        add(b_time);
        JLabel lb_btime=new JLabel();
        lb_btime.setBounds(450, 150, 150, 50);
        lb_btime.setFont(new Font("serif", Font.BOLD, 20));
        add(lb_btime);
        JLabel m_time= new JLabel("Meeting time:");
        m_time.setBounds(350, 200, 150, 50);
        m_time.setFont(new Font("serif", Font.PLAIN, 20));
        add(m_time);
        JLabel lb_mtime=new JLabel();
        lb_mtime.setBounds(450, 200, 150, 50);
        lb_mtime.setFont(new Font("serif", Font.BOLD, 20));
        add(lb_mtime);
        JLabel phone= new JLabel("Phone:");
        phone.setBounds(50, 200, 100, 50);
        phone.setFont(new Font("serif", Font.PLAIN, 20));
        add(phone);
        JLabel lbphone=new JLabel();
        lbphone.setBounds(120,200, 150, 50);
        lbphone.setFont(new Font("serif", Font.BOLD, 20));
        add(lbphone);
        JLabel com= new JLabel("Company:");
        com.setBounds(350, 100, 100, 50);
        com.setFont(new Font("serif", Font.PLAIN, 20));
        add(com);
        JLabel lbcom= new JLabel();
        lbphone.setBounds(450,100, 150, 50);
        lbphone.setFont(new Font("serif", Font.BOLD, 20));
        add(lbcom);
        JLabel room= new JLabel("Room:");
        room.setBounds(50, 250, 100, 50);
        room.setFont(new Font("serif", Font.PLAIN, 20));
        add(room);
        JLabel lbroom=new JLabel();
        lbroom.setBounds(120,250, 150, 50);
        lbroom.setFont(new Font("serif", Font.BOLD, 20));
        add(lbroom);
        JLabel ac= new JLabel("A/C");
        ac.setBounds(350, 250, 150, 50);
        ac.setFont(new Font("serif", Font.PLAIN, 20));
        add(ac);
        cb_ac=new JComboBox<>(items(5));
        cb_ac.setBounds(470, 263, 100, 25);
        cb_ac.addActionListener(this);
        add(cb_ac);
        JLabel spk= new JLabel("Speaker");
        spk.setBounds(350, 300, 150, 50);
        spk.setFont(new Font("serif", Font.PLAIN, 20));
        add(spk);
        cb_spk=new JComboBox<>(items(5));
        cb_spk.setBounds(470, 313, 100, 25);
        cb_spk.addActionListener(this);
        add(cb_spk);
        JLabel prj= new JLabel("Projector");
        prj.setBounds(50, 300, 150, 50);
        prj.setFont(new Font("serif", Font.PLAIN, 20));
        add(prj);
        cb_prj=new JComboBox<>(choices);
        cb_prj.setBounds(170, 313, 100, 25);
        cb_prj.addActionListener(this);
        add(cb_prj);
        JLabel mic= new JLabel("Microphone");
        mic.setBounds(50, 350, 150, 50);
        mic.setFont(new Font("serif", Font.PLAIN, 20));
        add(mic);
        cb_mic=new JComboBox<>(items(8));
        cb_mic.setBounds(170, 363, 100, 25);
        cb_mic.addActionListener(this);
        add(cb_mic);
        update=new JButton("Update");
        update.setFont(new Font("serif", Font.BOLD, 20));
        update.setBackground(Color.pink);
        update.setBounds(180, 450, 100, 30);
        update.addActionListener(this);
        add(update);
        cancel=new JButton("Cancel");
        cancel.setFont(new Font("serif", Font.BOLD, 20));
        cancel.setBackground(Color.pink);
        cancel.setBounds(350, 450, 100, 30);
        cancel.addActionListener(this);
        add(cancel);
        cr_num = new Choice();
        cr_num.setBounds(50, 70, 100, 50);
        try {
            DB db = new DB();
            ResultSet rs = db.s.executeQuery(
                    "Select C_ID, C_NAME,C_PHONE,C_COM,BOOKING_DATE,MEETING_TIME from booking where ROOM_NUMBER='"
                            + cr_num.getSelectedItem() + "'");
            while (rs.next()) {
                lb_id.setText(rs.getString("C_ID"));
                lbname.setText(rs.getString("C_NAME"));
                lbphone.setText(rs.getString("C_PHONE"));
                lbcom.setText(rs.getString("C_COM"));
                lb_btime.setText(rs.getString("BOOKING DATE"));
                lb_mtime.setText(rs.getString("MEETING_TIME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cr_num.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    DB db = new DB();
                    ResultSet rs = db.s.executeQuery(
                            "Select C_ID, C_NAME,C_PHONE,C_COM,BOOKING_DATE,MEETING_TIME from booking where ROOM_NUMBER='"
                                    + cr_num.getSelectedItem() + "'");
                    while (rs.next()) {
                        lb_id.setText(rs.getString("C_ID"));
                        lbname.setText(rs.getString("C_NAME"));
                        lbphone.setText(rs.getString("C_PHONE"));
                        lbcom.setText(rs.getString("C_COM"));
                        lb_btime.setText(rs.getString("BOOKING DATE"));
                        lb_mtime.setText(rs.getString("MEETING_TIME"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        add(cr_num);
        JLabel text = new JLabel(" Room number");
        text.setBounds(160, 55, 150, 50);
        add(text);
        try {
            DB db = new DB();
            ResultSet rs = db.s.executeQuery("Select ROOM_NUMBER from booking");
            while (rs.next()) {
                cr_num.add(rs.getString("ROOM_NUMBER"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setVisible(true);
        setSize(700, 550);
        setLocation(500, 70);
    }
    public String[] choices = { "0", "1" };

    public String[] items(int n) {
        String[] iStrings = new String[n];
        for (int i = 0; i < iStrings.length; i++) {
            String temp = String.valueOf(i);
            iStrings[i] = temp;
        }
        return iStrings;
    }
    public void actionPerformed(ActionEvent ae) {
           if(ae.getSource()==update){
                String ac= (String)cb_ac.getSelectedItem();
                String prj= (String)cb_prj.getSelectedItem();
                String spk= (String)cb_spk.getSelectedItem();
                String mic= (String)cb_mic.getSelectedItem();
                try {
                    DB db=new DB();
                    String query="Update booking Set NUM_PROJs='"+prj+"', NUM_ACs='"+ac+"',NUM_SPKs='"+spk+"',NUM_MICs='"+mic+"' WHERE ROOM_NUMBER='"+cr_num.getSelectedItem()+"'";
                    String query2="Update rooms Set NUM_PROJs='"+prj+"', NUM_ACs='"+ac+"',NUM_SPKs='"+spk+"',NUM_MICs='"+mic+"' WHERE ROOM_NUMBER='"+cr_num.getSelectedItem()+"'";
                    db.s.executeUpdate(query);
                    db.s.executeUpdate(query2);
                    JOptionPane.showMessageDialog(null, "Data entered succesfully");
                    setVisible(false);
                    new Update_booking();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error"+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                
           }
           else{
            setVisible(false);
            new Booking_list();
           }
    }

    public static void main(String[] args) {
        new Update_booking();
    }
}
