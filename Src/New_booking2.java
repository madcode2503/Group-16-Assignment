package Src;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import com.mysql.cj.protocol.Resultset;

import net.proteanit.sql.DbUtils;

@SuppressWarnings("unused")
public class New_booking2 extends JFrame implements ActionListener {
    JTable table;
    JTextField tfname, tfphone, tfcom;
    JLabel lbtime;
    JLabel lbid;
    @SuppressWarnings("rawtypes")
    JComboBox cb_ac, cbrnum, cbtime, cbtime2, cbprj, cbspk, cbmic;
    JButton submit;
    JButton cancel;
    JRadioButton rb;

    @SuppressWarnings("unchecked")
    New_booking2() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("Booking form");
        heading.setBounds(400, 10, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);
        rb = new JRadioButton();
        rb.setBounds(900, 55, 20, 15);
        rb.setBackground(Color.white);
        rb.addActionListener(this);
        add(rb);
        JLabel av_only = new JLabel("Display available only");
        av_only.setBounds(930, 55, 150, 15);
        add(av_only);
        JLabel c_id = new JLabel("Client ID");
        c_id.setBounds(20, 15, 200, 180);
        c_id.setFont(new Font("serif", Font.BOLD, 17));
        add(c_id);
        lbid = new JLabel();
        lbid.setBounds(120, 95, 120, 25);
        lbid.setFont(new Font("serif", Font.BOLD, 17));
        add(lbid);
        try {
            DB db = new DB();
            ResultSet rs = db.s.executeQuery("Select ID from id where STT='Available'");
            if (rs.next()) {

                lbid.setText(rs.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JLabel name = new JLabel("Client name");
        name.setBounds(20, 55, 200, 180);
        name.setFont(new Font("serif", Font.BOLD, 17));
        add(name);
        JLabel phone = new JLabel("Phone");
        phone.setBounds(20, 95, 200, 180);
        phone.setFont(new Font("serif", Font.BOLD, 17));
        add(phone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        String text = date.format(formatter);
        JLabel b_time = new JLabel("Booking time");
        b_time.setBounds(280, 55, 200, 180);
        b_time.setFont(new Font("serif", Font.BOLD, 17));
        add(b_time);
        lbtime = new JLabel("" + text);
        lbtime.setBounds(400, 57, 200, 180);
        add(lbtime);
        JLabel com = new JLabel("Company");
        com.setBounds(280, 15, 200, 180);
        com.setFont(new Font("serif", Font.BOLD, 17));
        add(com);
        JLabel select_room = new JLabel("Room");
        select_room.setBounds(20, 135, 200, 180);
        select_room.setFont(new Font("serif", Font.BOLD, 17));
        add(select_room);
        JLabel mtime = new JLabel("Meeting time");
        mtime.setBounds(280, 95, 200, 180);
        mtime.setFont(new Font("serif", Font.BOLD, 17));
        add(mtime);
        JLabel prj = new JLabel("Projector");
        prj.setBounds(20, 175, 200, 180);
        prj.setFont(new Font("serif", Font.BOLD, 17));
        add(prj);
        JLabel ac = new JLabel("A/C");
        ac.setBounds(280, 135, 200, 180);
        ac.setFont(new Font("serif", Font.BOLD, 17));
        add(ac);
        JLabel mic = new JLabel("Microphone");
        mic.setBounds(20, 215, 200, 180);
        mic.setFont(new Font("serif", Font.BOLD, 17));
        add(mic);
        JLabel spk = new JLabel("Speaker");
        spk.setBounds(280, 175, 200, 180);
        spk.setFont(new Font("serif", Font.BOLD, 17));
        add(spk);
        tfname = new JTextField();
        tfname.setBounds(120, 135, 120, 25);
        tfname.addActionListener(this);
        add(tfname);
        tfphone = new JTextField();
        tfphone.setBounds(120, 175, 120, 25);
        tfphone.addActionListener(this);
        add(tfphone);
        tfcom = new JTextField();
        tfcom.setBounds(400, 95, 120, 25);
        tfcom.addActionListener(this);
        add(tfcom);
        cbrnum = new JComboBox<>();
        cbrnum.setBounds(120, 215, 120, 25);
        cbrnum.addActionListener(this);
        add(cbrnum);
        cbtime = new JComboBox<>(timestr(8));
        cbtime.setBounds(400, 175, 120, 25);
        cbtime.addActionListener(this);
        add(cbtime);
        JLabel sym = new JLabel("-");
        sym.setFont(new Font("serif", Font.BOLD, 45));
        sym.setBounds(530, 170, 80, 20);
        add(sym);
        cbtime2 = new JComboBox<>(timestr(9));
        cbtime2.setBounds(555, 175, 120, 25);
        cbtime2.addActionListener(this);
        add(cbtime2);
        cbprj = new JComboBox<>(choices);
        cbprj.setBounds(120, 255, 120, 25);
        cbprj.addActionListener(this);
        add(cbprj);
        cb_ac = new JComboBox<>(items(5));
        cb_ac.setBounds(400, 215, 120, 25);
        cb_ac.addActionListener(this);
        add(cb_ac);
        cbmic = new JComboBox<>(items(8));
        cbmic.setBounds(120, 295, 120, 25);
        cbmic.addActionListener(this);
        add(cbmic);
        cbspk = new JComboBox<>(items(5));
        cbspk.setBounds(400, 255, 120, 25);
        cbspk.addActionListener(this);
        add(cbspk);
        
        submit = new JButton("Submit");
        submit.setFont(new Font("serif", Font.BOLD, 20));
        submit.setBackground(Color.pink);
        submit.setBounds(180, 350, 100, 30);
        submit.addActionListener(this);
        add(submit);
        cancel = new JButton("Cancel");
        cancel.setFont(new Font("serif", Font.BOLD, 20));
        cancel.setBackground(Color.pink);
        cancel.setBounds(340, 350, 100, 30);
        cancel.addActionListener(this);
        add(cancel);
        try {
            DB db = new DB();
            ResultSet rs = db.s.executeQuery("Select * from rooms where ROOM_STATUS='Available'");
            while (rs.next()) {
                cbrnum.addItem(rs.getString("ROOM_NUMBER"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        table = new JTable();
        try {
            DB db = new DB();
            ResultSet rs = db.s.executeQuery("Select ROOM_NUMBER, ROOM_STATUS from rooms ");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(740, 97, 350, 305);
        add(jsp);
        setSize(1100, 440);
        setLocation(250, 150);
        setVisible(true);
    }

    public String[] timestr(int n) {
        String[] harray = new String[32];
        int index = 0;
        for (int hour = n; hour < 24; hour++) {
            for (int mnt = 0; mnt < 60; mnt += 30) {
                harray[index++] = String.format("%02d:%02d", hour, mnt);
            }
        }
        return harray;
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
         if (ae.getSource() == submit) {
            String id = lbid.getText();
            String name = tfname.getText();
            String phone = tfphone.getText();
            String com = tfcom.getText();
            String b_date = lbtime.getText();
            String m_time = (String) cbtime.getSelectedItem();
            String m_time2 = (String) cbtime2.getSelectedItem();
            String rnum = (String) cbrnum.getSelectedItem();
            String ac = (String) cb_ac.getSelectedItem();
            String prj = (String) cbprj.getSelectedItem();
            String spk = (String) cbspk.getSelectedItem();
            String mic = (String) cbmic.getSelectedItem();

            try {
                DB db = new DB();
                String query = "Insert into booking values('" + id + "','" + name + "','" + phone + "','" + com + "','"
                        + b_date
                        + "','" + rnum + "','" + m_time + "-" + m_time2 + "','" + prj + "','" + ac + "','" + spk
                        + "','" + mic + "')";
                String query1 = "Insert into clients values('" + id + "','" + name + "','" + phone + "','" + com + ",'"+rnum+"'')";
                String query2 = "UPDATE rooms SET ROOM_STATUS='Booked', MEETING_TIME='" + m_time + "-" + m_time2
                        + "', PROJECTOR='" + prj + "', A_Cs='" + ac + "', SPEAKERS='" + spk +
                        "', MICROPHONES='" + mic + "',C_ID='"+id+"' WHERE ROOM_NUMBER='" + rnum + "'";
                String query3 = "Update id SET STT='Unavailable' where ID='" + id + "'";
               
                db.s.executeUpdate(query);
                db.s.executeUpdate(query1);
                db.s.executeUpdate(query2);
                db.s.executeUpdate(query3);
                JOptionPane.showMessageDialog(null, "Data entered succesfully");
                setVisible(false);
                new New_booking();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
            new Booking_list();
        } else if (rb.isSelected()) {
            try {
                DB db = new DB();
                ResultSet rs = db.s
                        .executeQuery("Select ROOM_NUMBER,ROOM_STATUS from rooms Where ROOM_STATUS='Available'");
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (!rb.isSelected()) {
            try {
                DB db = new DB();
                ResultSet rs = db.s.executeQuery("Select ROOM_NUMBER,ROOM_STATUS from rooms ");
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // public static void main(String[] args) {
    // new New_booking2();
    // }

}

