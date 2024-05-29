package Src;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class Booking_list extends JFrame implements ActionListener {
    JTable tb;
    JButton search, refresh, cancel;
    Choice c_num;
    JMenuItem asc, des, r_list, c_list, free_1, free_all, add_c, update;

    Booking_list() {
        getContentPane().setBackground(Color.white);
        setLayout(null);
        JMenuBar mb = new JMenuBar();
        JMenu list = new JMenu("List");
        list.setForeground(Color.blue);
        r_list = new JMenuItem("Room list");
        r_list.addActionListener(this);
        list.add(r_list);
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
        JMenu remove_client = new JMenu("Remove client booking");
        remove_client.setForeground(Color.blue);
        free_1 = new JMenuItem("Remove selected client booking");
        free_1.addActionListener(this);
        remove_client.add(free_1);
        free_all = new JMenuItem("Remove all");
        free_all.addActionListener(this);
        remove_client.add(free_all);
        mb.add(remove_client);
        JMenu extra_opt = new JMenu("More options");
        extra_opt.setForeground(Color.red);
        add_c = new JMenuItem("New booking");
        add_c.addActionListener(this);
        extra_opt.add(add_c);
        update = new JMenuItem("Update client booking");
        update.addActionListener(this);
        extra_opt.add(update);
        mb.add(extra_opt);
        setJMenuBar(mb);
        JLabel heading = new JLabel("Client booking list");
        heading.setBounds(550, 5, 200, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        add(heading);
        JLabel lb_search = new JLabel("Choose room number");
        lb_search.setBounds(175, 45, 150, 50);
        add(lb_search);
        c_num = new Choice();
        c_num.setBackground(Color.WHITE);
        c_num.setBounds(55, 60, 100, 50);
        add(c_num);
        try {
            DB db = new DB();
            ResultSet rs = db.s.executeQuery("Select ROOM_NUMBER from booking");
            while (rs.next()) {
                c_num.add(rs.getString("ROOM_NUMBER"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        tb = new JTable();
        try {
            DB db = new DB();
            ResultSet rs = db.s.executeQuery("Select * from booking");
            tb.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        JScrollPane jsp = new JScrollPane(tb);
        jsp.setBounds(0, 150, 1300, 340);
        add(jsp);
        setVisible(true);
        setSize(1300, 500);
        setLocation(120, 100);
    }

    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        if (msg.equals("Room list")) {
            setVisible(false);
            new Room_list();
        } else if (msg.equals("Client profile list")) {
            setVisible(false);
            new Client_List();
        } else if (msg.equals("Ascending Order")) {
            try {
                DB db = new DB();
                ResultSet rs = db.s.executeQuery("select * from booking order by MEETING_TIME ASC;");
                tb.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Descending Order")) {
            try {
                DB db = new DB();
                ResultSet rs = db.s.executeQuery("select * from booking order by MEETING_TIME DESC;");
                tb.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Remove selected client booking")) {
            String r_num = c_num.getSelectedItem();
            DB db = new DB();
            try {
                String query = " Delete from booking where ROOM_NUMBER='"
                        + r_num + "'";
                String query2= "Update  rooms  Set ROOM_STATUS='Available',MEETING_TIME='N/A',PROJECTOR='0',A_Cs='0',SPEAKERS='0',MICROPHONES='0',C_ID='N/A' where ROOM_NUMBER='"
                + r_num + "'";
                String query3="Update id i Join booking b on i.ID=b.C_ID set i.STT='Available' where b.ROOM_NUMBER='"+r_num+"'";
                String query4= "Delete from clients where ROOM_NUMBER='"+r_num+"'";
                db.s.executeUpdate(query);
                db.s.executeUpdate(query2);
                db.s.executeUpdate(query3);
                db.s.executeUpdate(query4);
                JOptionPane.showMessageDialog(null, "Removed successfully");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (msg.equals("Remove all")) {
            DB db = new DB();
            try {
                String query = " Delete from booking ";
                String query2= "Update  rooms  Set ROOM_STATUS='Available',MEETING_TIME='N/A',PROJECTOR='0',A_Cs='0',SPEAKERS='0',MICROPHONES='0',C_ID='N/A'";
                String query3= "Update id set STT='Available'";
                String query4="Delete from clients";
                db.s.executeUpdate(query);
                db.s.executeUpdate(query2);
                db.s.executeUpdate(query3);
                db.s.executeUpdate(query4);
                JOptionPane.showMessageDialog(null, "Removed successfully");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(msg.equals("New booking")){
            setVisible(false);
            new New_booking2();
        }
        else if(msg.equals("Update client booking")){
                setVisible(false);
                new Update_booking();
        }
        else if (ae.getSource() == search) {
            try {
                DB db = new DB();
                ResultSet rs = db.s
                        .executeQuery("Select * from booking where ROOM_NUMBER='" + c_num.getSelectedItem() + "'");
                tb.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == refresh) {
            try {
                DB db = new DB();
                ResultSet rs = db.s.executeQuery("Select * from booking");
                tb.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {

            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
            new Homepage();
        }
    }

    public static void main(String[] args) {
        new Booking_list();
    }
}
