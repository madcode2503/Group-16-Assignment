package Src;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class Room_list extends JFrame implements ActionListener {
    JButton search, cancel, refresh;
    JTable tb;
    Choice c_rnum;
    JMenuItem asc, des, b_list, c_list, free_1, free_all;
    JRadioButton rb;
    JLabel lb_cnt;

    Room_list() {
        getContentPane().setBackground(Color.white);
        setLayout(null);
        JMenuBar mb = new JMenuBar();
        JMenu list = new JMenu("List");
        list.setForeground(Color.blue);
        b_list = new JMenuItem("Client booking list");
        b_list.addActionListener(this);
        list.add(b_list);
        c_list = new JMenuItem("Client profile list");
        c_list.addActionListener(this);
        list.add(c_list);
        mb.add(list);
        JMenu sort = new JMenu("Sort");
        sort.setForeground(Color.red);
        asc = new JMenuItem("Ascending Order");
        asc.addActionListener(this);
        sort.add(asc);
        des = new JMenuItem("Descending Order");
        des.addActionListener(this);
        sort.add(des);
        mb.add(sort);
        JMenu free_room = new JMenu("Free Room");
        free_room.setForeground(Color.blue);
        free_1 = new JMenuItem("Release selected room");
        free_1.addActionListener(this);
        free_room.add(free_1);
        free_all = new JMenuItem("Release all rooms");
        free_all.addActionListener(this);
        free_room.add(free_all);
        mb.add(free_room);
        setJMenuBar(mb);
        JLabel heading = new JLabel("Room list");
        heading.setBounds(360, 5, 200, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        add(heading);
        JLabel lb_search = new JLabel("Choose room number");
        lb_search.setBounds(175, 45, 150, 50);
        add(lb_search);
        JLabel num_avlroom = new JLabel("Available rooms: "); // Number of available rooms
        num_avlroom.setBounds(500, 45, 150, 50);
        add(num_avlroom);
        lb_cnt = new JLabel();
        lb_cnt.setBounds(600, 45, 100, 50);
        add(lb_cnt);
        JLabel avl_only = new JLabel("Display available rooms only");
        avl_only.setBounds(520, 80, 170, 40);
        add(avl_only);
        try {
            DB db = new DB();
            ResultSet rs = db.s.executeQuery("Select COUNT(*) AS cnt from rooms where ROOM_STATUS='Available'");
            while (rs.next()) {
                int cnt = rs.getInt("cnt");
                lb_cnt.setText(String.valueOf(cnt));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        rb = new JRadioButton();
        rb.setBounds(495, 95, 20, 15);
        rb.setBackground(Color.white);
        rb.addActionListener(this);
        add(rb);
        search = new JButton("Search");
        search.setBackground(Color.pink);
        search.setBounds(50, 115, 80, 20);
        search.addActionListener(this);
        add(search);
        refresh = new JButton("Refresh");
        refresh.setBackground(Color.pink);
        refresh.setBounds(150, 115, 80, 20);
        refresh.addActionListener(this);
        add(refresh);
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.pink);
        cancel.setBounds(250, 115, 80, 20);
        cancel.addActionListener(this);
        add(cancel);
        c_rnum = new Choice();
        c_rnum.setBackground(Color.WHITE);
        c_rnum.setBounds(55, 60, 100, 50);
        add(c_rnum);
        try {
            DB db = new DB();
            ResultSet rs = db.s.executeQuery("Select ROOM_NUMBER from rooms");
            while (rs.next()) {
                c_rnum.add(rs.getString("ROOM_NUMBER"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tb = new JTable();
        try {
            DB db = new DB();
            ResultSet rs = db.s.executeQuery("Select * from rooms");
            tb.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        JScrollPane jsp = new JScrollPane(tb);
        jsp.setBounds(0, 150, 800, 340);
        add(jsp);
        setVisible(true);
        setSize(800, 500);
        setLocation(350, 100);
    }

    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        if (ae.getSource() == search) {
            try {
                DB db = new DB();
                ResultSet rs = db.s
                        .executeQuery("Select * from rooms where ROOM_NUMBER='" + c_rnum.getSelectedItem() + "'");
                tb.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == refresh) {
            try {
                DB db = new DB();
                ResultSet rs = db.s.executeQuery("Select * from rooms");
                tb.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {

            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
            new Homepage();
        } else if (msg.equals("Client booking list")) {
            setVisible(false);
            new Booking_list();
        } else if (msg.equals("Client profile list")) {
            setVisible(false);
            new Client_List();
        } else if (msg.equals("Ascending Order")) {
            try {
                DB db = new DB();
                ResultSet rs = db.s.executeQuery("select * from rooms order by ROOM_NUMBER ASC;");
                tb.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Descending Order")) {
            try {
                DB db = new DB();
                ResultSet rs = db.s.executeQuery("select * from rooms order by ROOM_NUMBER DESC;");
                tb.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Release selected room")) {
            String r_num = c_rnum.getSelectedItem();
            DB db = new DB();
            try {

                String query = "Update  rooms Set ROOM_STATUS='Available',MEETING_TIME='N/A',NUM_PROJs='0',NUM_PROJs='0',NUM_PROJs='0',NUM_ACs='0',NUM_SPKs='0',NUM_MICs='0' where ROOM_NUMBER='"
                        + r_num + "'";
                db.s.executeUpdate(query);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            try {

                ResultSet rs = db.s.executeQuery("Select COUNT(*) AS cnt from rooms where ROOM_STATUS='Available'");
                while (rs.next()) {
                    int cnt = rs.getInt("cnt");
                    lb_cnt.setText(String.valueOf(cnt));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Release all rooms")) {
            DB db = new DB();
            try {
                String query = "Update  rooms Set ROOM_STATUS='Available',MEETING_TIME='N/A',NUM_PROJs='0',NUM_PROJs='0',NUM_PROJs='0',NUM_ACs='0',NUM_SPKs='0',NUM_MICs='0'";
                db.s.executeUpdate(query);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            try {
                ResultSet rs = db.s.executeQuery("Select COUNT(*) AS cnt from rooms where ROOM_STATUS='Available'");
                while (rs.next()) {
                    int cnt = rs.getInt("cnt");
                    lb_cnt.setText(String.valueOf(cnt));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (rb.isSelected()) {
            try {
                DB db = new DB();
                ResultSet rs = db.s
                        .executeQuery("Select * from rooms Where ROOM_STATUS='Available'");
                tb.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (!rb.isSelected()) {
            try {
                DB db = new DB();
                ResultSet rs = db.s.executeQuery("Select * from rooms ");
                tb.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Room_list();
    }
}
