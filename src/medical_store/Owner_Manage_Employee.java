/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medical_store;

import com.mysql.jdbc.Connection;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import static medical_store.Employee_Window.serial_no;

/**
 *
 * @author abhishek
 */
public class Owner_Manage_Employee extends javax.swing.JFrame {
    
    public static int serial_no;
    /**
     * Creates new form Owner_Manage_Employee
     */
    public Owner_Manage_Employee() {
        serial_no = 1;
        initComponents();
        date_attendance.setText(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
        setSearchItem(role, "role", "role_name");
        setSearchItem(search_view, "employee", "emp_name");
        setSearchItem(role1, "role", "role_name");
        setSearchItem(search_update, "employee", "emp_name");
        setSearchItem(search_attendance, "employee", "emp_name");
        setSalary();
        DefaultTableModel tablemodel = (DefaultTableModel)this.attendance.getModel();
        ListSelectionModel model = attendance.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if(! model.isSelectionEmpty()) {
                    try {
                        int selectedRow = model.getMinSelectionIndex();
                        for(int i = selectedRow+1; i<attendance.getRowCount(); i++) {
                            attendance.setValueAt((Integer.parseInt(attendance.getValueAt(i, 0).toString())-1), i, 0);
                        }
                        tablemodel.removeRow(selectedRow);
                        serial_no = serial_no -1;
                    }catch(Exception e) {
                        
                    }
                }
            }
        });
        fillAttendance();
    }
    
    public Owner_Manage_Employee(int panel, String name) {
        serial_no = 1;
        initComponents();
        date_attendance.setText(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
        setSearchItem(role, "role", "role_name");
        setSearchItem(search_view, "employee", "emp_name");
        setSearchItem(role1, "role", "role_name");
        setSearchItem(search_update, "employee", "emp_name");
        setSearchItem(search_attendance, "employee", "emp_name");
        setSalary();
        DefaultTableModel tablemodel = (DefaultTableModel)this.attendance.getModel();
        ListSelectionModel model = attendance.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if(! model.isSelectionEmpty()) {
                    try {
                        int selectedRow = model.getMinSelectionIndex();
                        for(int i = selectedRow+1; i<attendance.getRowCount(); i++) {
                            attendance.setValueAt((Integer.parseInt(attendance.getValueAt(i, 0).toString())-1), i, 0);
                        }
                        tablemodel.removeRow(selectedRow);
                        serial_no = serial_no -1;
                    }catch(Exception e) {
                        
                    }
                }
            }
        });
        fillAttendance();
        jTabbedPane1.setSelectedIndex(panel);
        if(panel == 3) {
            search_update.setSelectedItem(name);
        }
        this.setVisible(true);
    }
    
    private void setSalary() {
        try {
            String date = date_attendance.getText();
            String[] arr = date.split("-");
            int count = 0, limit = Integer.parseInt(arr[2]);
            
            Connection conn = MySQL_Connector.getConnection();
            
            for(int i = 1; i <= limit; i++) {
                String num = "";
                if(i < 10) {
                    num = "0" + i;
                }
                else {
                    num += i;
                }
                String query = "select attendance from attendance_details where emp_id = " + (search_view.getSelectedIndex() + 1) + " and day = '" + arr[0] + "-" + arr[1] + "-" + num + "'";
                ResultSet rs = MySQL_Connector.runQuery(conn, query);
                if(rs.next()) {
                    if(rs.getString("attendance").equals("T")) {
                        count++;
                    }
                }
                rs.close();
            }
            
            float per = (float)count / limit * 100;
            String perc = "" + per;
            attendance_label.setText(perc + "%");
            
            String query = "select emp_salary from employee where emp_id = " + (search_view.getSelectedIndex() + 1);
            float salary = 0;
            ResultSet rs = MySQL_Connector.runQuery(conn, query);
            if(rs.next()) {
                salary = Float.parseFloat(rs.getString("emp_salary"));
            }
            
            float salary_till_now = per / 100 * salary / limit;
            String strSalary = "" + salary_till_now;
            
            salary_label.setText(strSalary);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Owner_Manage_Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void fillAttendance() {
        try {
            Connection conn = MySQL_Connector.getConnection();
            String query = "select emp_id from attendance_details where day = '" + date_attendance.getText() + "' and attendance = 'T'";
            ResultSet rs = MySQL_Connector.runQuery(conn, query);
            
            while(rs.next()) {
                query = "select emp_name from employee where emp_id = " + Integer.parseInt(rs.getString("emp_id"));
                ResultSet rs1 = MySQL_Connector.runQuery(conn, query);
                if(rs1.next()) {
                    addRowToAttendance(rs1.getString("emp_name"));
                }
            }
            
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Owner_Manage_Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void setSearchItem(JComboBox component, String table, String column) {
        try(Connection conn = MySQL_Connector.getConnection()) {
            String conn_query = "select " + column + " from " + table;
            ResultSet res = MySQL_Connector.runQuery(conn, conn_query);
            while(res.next()) {
                component.addItem(res.getString(column));
            }
            component.setVisible(true);
            conn.close();
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showInformationTable(JComboBox component, JTable table) {
        try(Connection conn = MySQL_Connector.getConnection()) {
            String employee_name = component.getSelectedItem().toString();
            String query = "select * from employee where emp_name = '" + employee_name + "'";
            ResultSet rs = MySQL_Connector.runQuery(conn, query);
            if(rs.next())
                table.setValueAt(rs.getString("emp_id"), 0, 1);
                table.setValueAt(rs.getString("emp_name"), 1, 1);
                String query2 = "select role_name from role where role_id = " + rs.getString("emp_role_id");
                ResultSet rs1 = MySQL_Connector.runQuery(conn, query2);
                if(rs1.next()) {
                    table.setValueAt(rs1.getString("role_name"), 2, 1);
                }
                table.setValueAt(rs.getString("emp_joining_date"), 3, 1);
                table.setValueAt(rs.getString("emp_addr"), 4, 1);
                table.setValueAt(rs.getString("emp_salary"), 5, 1);
                table.setValueAt(rs.getString("emp_mobile_num"), 6,1);
                table.setValueAt(rs.getString("emp_alternate_num"), 7, 1);
                table.setValueAt(rs.getString("emp_email"), 8, 1);
                table.setValueAt(rs.getString("emp_dob"), 9, 1);
                conn.close();
                rs.close();
                rs1.close();
        }catch(SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void showInformationToUpdate() {
        try {
            String old_name = search_update.getSelectedItem().toString();
            
            Connection conn = MySQL_Connector.getConnection();
            String query = "select * from employee where emp_name = '" + old_name + "'";
            ResultSet rs = MySQL_Connector.runQuery(conn, query);
            if(rs == null) {
                JOptionPane.showMessageDialog(null, "Error: Failed to connect to the database. Please try again after some time.");
            }
            if(rs.next()) {
                name1.setText(rs.getString("emp_name"));
                mob1.setText(rs.getString("emp_mobile_num"));
                mob_alt1.setText(rs.getString("emp_alternate_num"));
                salary1.setText(rs.getString("emp_salary"));
                addr1.setText(rs.getString("emp_addr"));
                email1.setText(rs.getString("emp_email"));
                role1.setSelectedItem(role.getItemAt(Integer.parseInt(rs.getString("emp_role_id")) - 1));
                dob1.setText(rs.getString("emp_dob"));
                doj1.setText(rs.getString("emp_joining_date"));
            }
            
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Owner_Manage_Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel12 = new javax.swing.JPanel();
        generate_bill_button = new javax.swing.JButton();
        manage_medicine_button = new javax.swing.JButton();
        manage_stock_button = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        date_attendance = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        search_attendance = new javax.swing.JComboBox<>();
        mark_attendance = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        attendance = new javax.swing.JTable();
        save_attendance = new javax.swing.JButton();
        cancel_attendance = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        search_view = new javax.swing.JComboBox<>();
        delete_view = new javax.swing.JButton();
        update_view = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        attendance_label = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        salary_label = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        role = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        dob = new org.jdesktop.swingx.JXDatePicker();
        jLabel7 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        mob = new javax.swing.JTextField();
        mob_alt = new javax.swing.JTextField();
        salary = new javax.swing.JTextField();
        doj = new org.jdesktop.swingx.JXDatePicker();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        addr = new javax.swing.JTextArea();
        submit_add = new javax.swing.JButton();
        cancel_add = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        search_update = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        name1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        mob1 = new javax.swing.JTextField();
        mob_alt1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        salary1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        addr1 = new javax.swing.JTextArea();
        jLabel20 = new javax.swing.JLabel();
        email1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        role1 = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        submit_update = new javax.swing.JButton();
        cancel_update = new javax.swing.JButton();
        dob1 = new javax.swing.JTextField();
        doj1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Navigation"));

        generate_bill_button.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        generate_bill_button.setText("Generate Bill");
        generate_bill_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generate_bill_buttonActionPerformed(evt);
            }
        });

        manage_medicine_button.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        manage_medicine_button.setText("Manage Medicines");
        manage_medicine_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manage_medicine_buttonActionPerformed(evt);
            }
        });

        manage_stock_button.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        manage_stock_button.setText("Manage Stocks");
        manage_stock_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manage_stock_buttonActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(201, 45, 45));
        jButton7.setText("Manage Employees");

        jButton1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton1.setText("Manage Defects");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton7)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(manage_medicine_button, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                        .addComponent(generate_bill_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(manage_stock_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(generate_bill_button)
                .addGap(34, 34, 34)
                .addComponent(manage_medicine_button)
                .addGap(35, 35, 35)
                .addComponent(manage_stock_button)
                .addGap(39, 39, 39)
                .addComponent(jButton1)
                .addGap(38, 38, 38)
                .addComponent(jButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setForeground(new java.awt.Color(166, 147, 147));
        jPanel1.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        jLabel1.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        jLabel1.setText("Date");

        date_attendance.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel3.setText("Name:");

        mark_attendance.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        mark_attendance.setText("Mark Attendance");
        mark_attendance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mark_attendanceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(29, 29, 29)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date_attendance, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_attendance, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mark_attendance, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(date_attendance, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(search_attendance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(mark_attendance)
                .addGap(22, 22, 22))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Present Employees"));

        attendance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sr. No.", "Employee Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(attendance);

        save_attendance.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        save_attendance.setText("Save Attendance");
        save_attendance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_attendanceActionPerformed(evt);
            }
        });

        cancel_attendance.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        cancel_attendance.setText("Cancel");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(save_attendance)
                        .addGap(32, 32, 32)
                        .addComponent(cancel_attendance))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save_attendance)
                    .addComponent(cancel_attendance))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Mark Attendance", jPanel1);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Employee"));

        jLabel13.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel13.setText("Employee Name:");

        search_view.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                search_viewItemStateChanged(evt);
            }
        });
        search_view.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                search_viewFocusGained(evt);
            }
        });
        search_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_viewActionPerformed(evt);
            }
        });

        delete_view.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        delete_view.setText("Delete");
        delete_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_viewActionPerformed(evt);
            }
        });

        update_view.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        update_view.setText("Update");
        update_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_viewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(62, 62, 62)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(update_view)
                        .addGap(46, 46, 46)
                        .addComponent(delete_view))
                    .addComponent(search_view, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(search_view, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update_view)
                    .addComponent(delete_view))
                .addGap(21, 21, 21))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Information"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"ID", null},
                {"Name", null},
                {"Role", null},
                {"Joining Date", null},
                {"Address", null},
                {"Salary", null},
                {"Mobile Number", null},
                {"Alternat Number", null},
                {"Email", null},
                {"Birth Date", null}
            },
            new String [] {
                "Field", "Value"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Salary Information"));

        jLabel2.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        jLabel2.setText("Attendance of this month(till now):");

        attendance_label.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        jLabel25.setText("Salary(till now):");

        salary_label.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        salary_label.setToolTipText("");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(attendance_label, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(salary_label)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(attendance_label))
                .addGap(40, 40, 40)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(salary_label))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View An Employee", jPanel3);

        jLabel4.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel4.setText("Name:");

        jLabel5.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel5.setText("Role:");

        role.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel6.setText("Date Of Birth");

        jLabel7.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel7.setText("Email");

        jLabel8.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel8.setText("Mobile Number:");

        jLabel9.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel9.setText("Alternate Number:");

        jLabel10.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel10.setText("Salary:");

        jLabel11.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel11.setText("Joining Date:");

        mob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel12.setText("Address:");

        addr.setColumns(20);
        addr.setRows(5);
        jScrollPane1.setViewportView(addr);

        submit_add.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        submit_add.setText("Submit");
        submit_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submit_addActionPerformed(evt);
            }
        });

        cancel_add.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        cancel_add.setText("Cancel");
        cancel_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_addActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(role, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7))
                            .addGap(29, 29, 29)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(email)))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(mob_alt)
                            .addComponent(salary)
                            .addComponent(mob)
                            .addComponent(doj, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(submit_add)
                .addGap(43, 43, 43)
                .addComponent(cancel_add)
                .addGap(31, 31, 31))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(mob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(mob_alt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(dob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(salary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(doj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submit_add)
                    .addComponent(cancel_add))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Add An Employee", jPanel2);

        search_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_updateActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel14.setText("Employee Name:");

        jLabel15.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel15.setText("Name:");

        jLabel16.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel16.setText("Mobile Number:");

        mob1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mob1ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel17.setText("Alternate Number:");

        jLabel18.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel18.setText("Salary:");

        jLabel19.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel19.setText("Joining Date:");

        addr1.setColumns(20);
        addr1.setRows(5);
        jScrollPane3.setViewportView(addr1);

        jLabel20.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel20.setText("Address:");

        jLabel21.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel21.setText("Email");

        jLabel22.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel22.setText("Date Of Birth");

        role1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                role1ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel23.setText("Role:");

        submit_update.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        submit_update.setText("Submit");
        submit_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submit_updateActionPerformed(evt);
            }
        });

        cancel_update.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        cancel_update.setText("Cancel");
        cancel_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_updateActionPerformed(evt);
            }
        });

        dob1.setEditable(false);

        doj1.setEditable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(62, 62, 62)
                        .addComponent(search_update, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(288, 288, 288))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel23))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(role1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(name1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel22)
                                        .addComponent(jLabel21))
                                    .addGap(29, 29, 29)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(email1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dob1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(241, 241, 241)
                                .addComponent(submit_update)
                                .addGap(43, 43, 43)
                                .addComponent(cancel_update))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(241, 241, 241)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel19))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(mob_alt1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                    .addComponent(salary1)
                                    .addComponent(mob1)
                                    .addComponent(doj1))))
                        .addGap(48, 48, 48))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(search_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(name1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(mob1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(role1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(mob_alt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel18)
                    .addComponent(salary1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dob1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(email1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(doj1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submit_update)
                    .addComponent(cancel_update))
                .addGap(54, 54, 54))
        );

        jTabbedPane1.addTab("Update An Employee", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 995, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generate_bill_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generate_bill_buttonActionPerformed
        Owner_Generate_Bill window = new Owner_Generate_Bill();
        window.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_generate_bill_buttonActionPerformed

    private void manage_medicine_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manage_medicine_buttonActionPerformed
        this.setVisible(false);
        Owner_Manage_Medicine window = new Owner_Manage_Medicine();
        window.setVisible(true);
    }//GEN-LAST:event_manage_medicine_buttonActionPerformed

    private void manage_stock_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manage_stock_buttonActionPerformed
        this.setVisible(false);
        Manage_Stocks window = new Manage_Stocks();
        window.setVisible(true);
    }//GEN-LAST:event_manage_stock_buttonActionPerformed

    private void mobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobActionPerformed

    private void roleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roleActionPerformed

    private void submit_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submit_addActionPerformed
        try {
            int result = JOptionPane.showConfirmDialog(rootPane, "Do you really want to add this entry?");
            if(result == 1 || result == 2) {
                return;
            }
            
            String strName = name.getText();
            String strAddr = addr.getText();
            String strEmail = email.getText();
            String strMob = mob.getText();
            String strMobAlt = mob_alt.getText();
            String strSalary = salary.getText();
            String strRole = role.getSelectedItem().toString(); 
            String birth = new SimpleDateFormat("yyyy-MM-dd").format(dob.getDate());
            String join = new SimpleDateFormat("yyyy-MM-dd").format(doj.getDate());
            String id = null;
            
            Connection conn = MySQL_Connector.getConnection();
            
            String query = "insert into employee(emp_name, emp_role_id, emp_addr, emp_dob, emp_email, emp_mobile_num, emp_alternate_num, emp_salary, emp_joining_date) values ('" + strName + "', " + (role.getSelectedIndex() + 1) + ", '" + strAddr + "', '" + birth + "', '" + strEmail + "', '" + strMob + "', '" + strMobAlt + "', " + Integer.parseInt(strSalary) + ", '" + join + "')";
            
            MySQL_Connector.runUpdateQuery(conn, query);
            JOptionPane.showMessageDialog(null, strName + " is added succesfully to the database.");
            
            this.setVisible(false);
            Owner_Manage_Employee window = new Owner_Manage_Employee(2, null);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Owner_Manage_Employee.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }//GEN-LAST:event_submit_addActionPerformed

    private void search_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_viewActionPerformed
        showInformationTable(search_view, jTable1);
        setSalary();
    }//GEN-LAST:event_search_viewActionPerformed

    private void cancel_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_addActionPerformed
        int result = JOptionPane.showConfirmDialog(rootPane, "Do you really want to cancel? All entered information will be lost.");
        if(result != 0) {
            return;
        }
        name.setText("");
        mob.setText("");
        mob_alt.setText("");
        salary.setText("");
        addr.setText("");
        email.setText("");
        role.setSelectedItem("Employee");
        dob.setDate(null);
        doj.setDate(null);
    }//GEN-LAST:event_cancel_addActionPerformed

    private void delete_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_viewActionPerformed
        try {
            int result = JOptionPane.showConfirmDialog(rootPane, "Do you really want to delete " + search_view.getSelectedItem().toString() + "? All data will be lost.");
            if(result != 0) {
                return;
            }
            
            String name = search_view.getSelectedItem().toString();
            String query = "delete from employee where emp_name = '" + search_view.getSelectedItem().toString() + "'";
            Connection conn = MySQL_Connector.getConnection();
            MySQL_Connector.runUpdateQuery(conn, query);
            JOptionPane.showMessageDialog(null, "Employee '" + name + "' is deleted succesfully!");
            
            this.setVisible(false);
            Owner_Manage_Employee window = new Owner_Manage_Employee(1, null);
        } catch (SQLException ex) {
            Logger.getLogger(Owner_Manage_Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_delete_viewActionPerformed

    private void search_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_updateActionPerformed
        showInformationToUpdate();
    }//GEN-LAST:event_search_updateActionPerformed

    private void mob1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mob1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mob1ActionPerformed

    private void role1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_role1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_role1ActionPerformed

    private void submit_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submit_updateActionPerformed
        try {
            int result = JOptionPane.showConfirmDialog(rootPane, "Do you really want to update " + search_update.getSelectedItem().toString() + "?");
            if(result != 0) {
                return;
            }
            
            Connection conn = MySQL_Connector.getConnection();
            String query = "update employee set emp_name = '" + name1.getText() +"', emp_mobile_num = '" + mob1.getText() + "', emp_role_id = " + (role1.getSelectedIndex() + 1) + ", emp_alternate_num = '" + mob_alt1.getText() + "', emp_salary = " + (int)Float.parseFloat(salary1.getText()) + ", emp_email = '" + email1.getText() + "', emp_addr = '" + addr1.getText() + "' where emp_name = '" + search_update.getSelectedItem().toString() + "'";
            MySQL_Connector.runUpdateQuery(conn, query);
            
            this.setVisible(false);
            Owner_Manage_Employee window = new Owner_Manage_Employee(3, name1.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Owner_Manage_Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_submit_updateActionPerformed

    private void cancel_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_updateActionPerformed
        int result = JOptionPane.showConfirmDialog(rootPane, "Do you really want to cancel? All your entered information will be lost.");
        if(result != 0) {
            return;
        }
        showInformationToUpdate();
    }//GEN-LAST:event_cancel_updateActionPerformed

    private void search_viewItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_search_viewItemStateChanged
        showInformationTable(search_view, jTable1);
        setSalary();
    }//GEN-LAST:event_search_viewItemStateChanged

    private void update_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_viewActionPerformed
        jTabbedPane1.setSelectedIndex(3);
        search_update.setSelectedItem(search_view.getSelectedItem().toString());
    }//GEN-LAST:event_update_viewActionPerformed

    
    private void mark_attendanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mark_attendanceActionPerformed
        for(int i = 0; i  < attendance.getRowCount(); i++) {
            if(search_attendance.getSelectedItem().toString().equals(attendance.getValueAt(i, 1).toString())) {
                return;
            }
        }
        addRowToAttendance(search_attendance.getSelectedItem().toString());
    }//GEN-LAST:event_mark_attendanceActionPerformed

    private void addRowToAttendance(String name) {
        DefaultTableModel model = (DefaultTableModel)attendance.getModel();
        model.addRow(new Object[] {null, null});
        attendance.setValueAt(serial_no, serial_no - 1, 0);
        attendance.setValueAt(name, serial_no - 1, 1);
        serial_no++;
    }
    private void save_attendanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_attendanceActionPerformed
        try {
            Connection conn = MySQL_Connector.getConnection();
            String query = "delete from attendance_details where day = '" + date_attendance.getText() + "'";
            MySQL_Connector.runUpdateQuery(conn, query);
            
            for(int i = 0; i < attendance.getRowCount(); i++) {
                query = "select emp_id from employee where emp_name = '" + attendance.getValueAt(i, 1).toString() + "'";
                ResultSet rs = MySQL_Connector.runQuery(conn, query);
                if(rs.next()) {
                    query = "insert into attendance_details values(" + Integer.parseInt(rs.getString("emp_id")) +", '" + date_attendance.getText() + "', 'T')";
                    MySQL_Connector.runUpdateQuery(conn, query);
                }
                rs.close();
            }
            
            query = "select emp_id from employee";
            ResultSet rs = MySQL_Connector.runQuery(conn, query);
            while(rs.next()) {
                query = "select * from attendance_details where emp_id = " + rs.getString("emp_id") + " and day = '" + date_attendance.getText() + "'";
                ResultSet rs1 = MySQL_Connector.runQuery(conn, query);
                if(rs1 == null || rs1.next() == false) {
                    query = "insert into attendance_details values(" + Integer.parseInt(rs.getString("emp_id")) + ", '" + date_attendance.getText() + "', 'F')";
                    MySQL_Connector.runUpdateQuery(conn, query);
                }
            }
            
            rs.close();
            conn.close();
            
            JOptionPane.showMessageDialog(null, "Attendance saved successfully!");
            
            this.setVisible(false);
            Owner_Manage_Employee window = new Owner_Manage_Employee(0, null);
        } catch (SQLException ex) {
            Logger.getLogger(Owner_Manage_Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_save_attendanceActionPerformed

    private void search_viewFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_search_viewFocusGained
        showInformationTable(search_view, jTable1);
        setSalary();
    }//GEN-LAST:event_search_viewFocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       this.setVisible(false);
       Manage_Defects window = new Manage_Defects();
       window.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "happened");
        DefaultTableModel model = (DefaultTableModel)attendance.getModel();
        if(attendance.getSelectedRow() != -1) {
            model.removeRow(attendance.getSelectedRow());
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Owner_Manage_Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Owner_Manage_Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Owner_Manage_Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Owner_Manage_Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Owner_Manage_Employee().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea addr;
    private javax.swing.JTextArea addr1;
    private javax.swing.JTable attendance;
    private javax.swing.JLabel attendance_label;
    private javax.swing.JButton cancel_add;
    private javax.swing.JButton cancel_attendance;
    private javax.swing.JButton cancel_update;
    private javax.swing.JLabel date_attendance;
    private javax.swing.JButton delete_view;
    private org.jdesktop.swingx.JXDatePicker dob;
    private javax.swing.JTextField dob1;
    private org.jdesktop.swingx.JXDatePicker doj;
    private javax.swing.JTextField doj1;
    private javax.swing.JTextField email;
    private javax.swing.JTextField email1;
    private javax.swing.JButton generate_bill_button;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton manage_medicine_button;
    private javax.swing.JButton manage_stock_button;
    private javax.swing.JButton mark_attendance;
    private javax.swing.JTextField mob;
    private javax.swing.JTextField mob1;
    private javax.swing.JTextField mob_alt;
    private javax.swing.JTextField mob_alt1;
    private javax.swing.JTextField name;
    private javax.swing.JTextField name1;
    private javax.swing.JComboBox<String> role;
    private javax.swing.JComboBox<String> role1;
    private javax.swing.JTextField salary;
    private javax.swing.JTextField salary1;
    private javax.swing.JLabel salary_label;
    private javax.swing.JButton save_attendance;
    private javax.swing.JComboBox<String> search_attendance;
    private javax.swing.JComboBox<String> search_update;
    private javax.swing.JComboBox<String> search_view;
    private javax.swing.JButton submit_add;
    private javax.swing.JButton submit_update;
    private javax.swing.JButton update_view;
    // End of variables declaration//GEN-END:variables
}
