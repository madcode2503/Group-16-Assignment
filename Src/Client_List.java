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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class Client_List extends JFrame implements ActionListener {
    Choice c_id;
    JTable tb;
    JButton search,refresh,update,cancel;
    JMenuItem b_list,r_list,asc,des;
    
     Client_List(){
        getContentPane().setBackground(Color.white);
        setLayout(null);
        JMenuBar mb= new JMenuBar();
        JMenu list = new JMenu("List");
        list.setForeground(Color.blue);
        b_list = new JMenuItem("Client bookings");
        b_list.addActionListener(this);
        list.add(b_list);
        r_list = new JMenuItem("Room list");
        r_list.addActionListener(this);
        list.add(r_list);
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
        setJMenuBar(mb);
        JLabel heading = new JLabel("Client profile list");
        heading.setBounds(330, 5, 200, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        add(heading);
        JLabel lb_search = new JLabel("Choose client id");
        lb_search.setBounds(175, 45, 150, 50);
        add(lb_search);
        c_id = new Choice();
        c_id.setBackground(Color.WHITE);
        c_id.setBounds(55, 60, 100, 50);
        add(c_id);
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
        update = new JButton("Update");
        update.setBackground(Color.pink);
        update.setBounds(250, 115, 80, 20);
        update.addActionListener(this);
        add(update);
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.pink);
        cancel.setBounds(350, 115, 80, 20);
        cancel.addActionListener(this);
        add(cancel);
        try {
            DB db = new DB();
            ResultSet rs = db.s.executeQuery("Select C_ID from clients");
            while (rs.next()) {
                c_id.add(rs.getString("C_ID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tb=new JTable();
        try {
            DB db= new DB();
            ResultSet rs= db.s.executeQuery("Select * from clients");
            tb.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JScrollPane jsp = new JScrollPane(tb);
        jsp.setBounds(0, 150, 800, 340);
        add(jsp);
        setVisible(true);
        setSize(800, 500);
        setLocation(400, 100);
    }
    public void actionPerformed(ActionEvent ae) {
        String msg= ae.getActionCommand();
        if (msg.equals("Client booking list")) {
            setVisible(false);
            new Booking_list();
        } else if (msg.equals("Room list")) {
            setVisible(false);
            new Room_list();
        }
        else if (ae.getSource() == search) {
            try {
                DB db = new DB();
                ResultSet rs = db.s
                        .executeQuery("Select * from clients where C_ID='" + c_id.getSelectedItem() + "'");
                tb.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == refresh) {
            try {
                DB db = new DB();
                ResultSet rs = db.s.executeQuery("Select * from clients");
                tb.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {

            }
        } else if(ae.getSource()==update){
              new Update_Client();
        }
        else if (msg.equals("Ascending Order")) {
            try {
                DB db = new DB();
                ResultSet rs = db.s.executeQuery("select * from clients order by C_NAME ASC;");
                tb.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Descending Order")) {
            try {
                DB db = new DB();
                ResultSet rs = db.s.executeQuery("select * from clients order by C_NAME DESC;");
                tb.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (ae.getSource() == cancel) {
            setVisible(false);
            new Homepage();
        }
    }
    public static void main(String[] args) {
        new Client_List();
    }
}
