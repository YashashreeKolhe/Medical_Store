/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medical_store;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dell
 */
public class Manage_Defects extends javax.swing.JFrame {

    /**
     * Creates new form Manage_Defects
     */
    public Manage_Defects() {
        initComponents();
        jTable_defect_info.getColumn("Defect Number").setPreferredWidth(90);
        jTable_defect_info.getColumn("Description").setPreferredWidth(500);
        jComboBox_defect_no.setSelectedIndex(-1);
        try(Connection conn = MySQL_Connector.getConnection()) {
            String query = "select * from defect";
            ResultSet rs = MySQL_Connector.runQuery(conn, query);
     
            DefaultTableModel model = (DefaultTableModel) this.jTable_defect_info.getModel();
            while(rs.next()) {
                model.addRow(new Object[] {rs.getString("defect_id"),rs.getString("defect_description") });
                jComboBox_defect_no.addItem(rs.getString("defect_id"));
            }
            conn.close();
        }catch(SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e); 
        }
        jComboBox_supplier_name.setSelectedIndex(-1);
        jComboBox_medicine_name.setSelectedIndex(-1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        jXDatePicker_return.setFormats(dateFormat);
        jXDatePicker1_return.setFormats(dateFormat);
        try(Connection conn = MySQL_Connector.getConnection()) {
            String query1 = "select supplier_name from supplier";
            ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
            while(rs1.next()) {
                jComboBox_supplier_name.addItem(rs1.getString("supplier_name"));
            }
            String query2 = "select medc_name from medicine";
            ResultSet rs2 = MySQL_Connector.runQuery(conn, query2);
            while(rs2.next()) {
                jComboBox_medicine_name.addItem(rs2.getString("medc_name"));
                jComboBox1_medicine_name.addItem(rs2.getString("medc_name"));
            }
            
            String query3 = "select defect_id from defect";
            ResultSet rs3 = MySQL_Connector.runQuery(conn, query3);
            while(rs3.next()) {
                jComboBox_defect_num2.addItem(rs3.getString("defect_id"));
            }
            conn.close();
            rs1.close();
            rs2.close();
        }catch(SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e); 
        }
        
        DefaultTableModel tablemodel = (DefaultTableModel)this.jTable_return_info.getModel();
        ListSelectionModel model = jTable_return_info.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if(! model.isSelectionEmpty()) {
                    try {
                        int selectedRow = model.getMinSelectionIndex();
                        
                        jTable_defect_table.setValueAt(jTable_return_info.getValueAt(selectedRow, 5), 4, 1);
                        jTable_defect_table.setValueAt(jTable_return_info.getValueAt(selectedRow, 4), 3, 1);
                        jTable_defect_table.setValueAt(jTable_return_info.getValueAt(selectedRow, 2), 2, 1);
                        jTable_defect_table.setValueAt(jTable_return_info.getValueAt(selectedRow, 3), 6, 1);
                       // jTable_defect_table.setValueAt(jTable_return_info.getValueAt(selectedRow, 1), 1, 1);
                        jTable_defect_table.setValueAt(jTable_return_info.getValueAt(selectedRow, 6), 5, 1);
                         jTable_defect_table.setValueAt(jTable_return_info.getValueAt(selectedRow, 7), 7, 1);
                        
                        Connection conn = MySQL_Connector.getConnection();
                        
                        String query = "select medc_id, medc_supplier_id from medicine where medc_name = '"+jTable_return_info.getValueAt(selectedRow, 1)+"'";
                        ResultSet rs = MySQL_Connector.runQuery(conn, query);
                        
                        if(rs.next()) {
                            jTable_defect_table.setValueAt(rs.getString("medc_id"), 0, 1);
                            String query1 = "select supplier_name from supplier where supplier_id = '"+Integer.parseInt(rs.getString("medc_supplier_id"))+"'";
                            ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
                            if(rs1.next()) {
                               jTable_defect_table.setValueAt(rs1.getString("supplier_name"), 1, 1);
                            }
                        }
                        
                    }catch(SQLException e) {
                        
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel13 = new javax.swing.JPanel();
        generate_bill_button1 = new javax.swing.JButton();
        manage_medicine_button1 = new javax.swing.JButton();
        manage_stock_button1 = new javax.swing.JButton();
        jButton_manage_employee = new javax.swing.JButton();
        jButton_manage_defects = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox_medicine_name = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBox_supplier_name = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jXDatePicker_return = new org.jdesktop.swingx.JXDatePicker();
        jButton_search2 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_defect_table = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_return_info = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1_medicine_name = new javax.swing.JComboBox<>();
        jXDatePicker1_return = new org.jdesktop.swingx.JXDatePicker();
        jLabel8 = new javax.swing.JLabel();
        jTextField_supplier_name = new javax.swing.JTextField();
        jTextField_quantity = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField_amount_received = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField_amount_pending = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBox_defect_num2 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea_desc = new javax.swing.JTextArea();
        jButton_add_entry = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox_defect_no = new javax.swing.JComboBox<>();
        jButton_search_defect = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_defect_info = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Navigation"));

        generate_bill_button1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        generate_bill_button1.setText("Generate Bill");
        generate_bill_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generate_bill_button1ActionPerformed(evt);
            }
        });

        manage_medicine_button1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        manage_medicine_button1.setText("Manage Medicines");
        manage_medicine_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manage_medicine_button1ActionPerformed(evt);
            }
        });

        manage_stock_button1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        manage_stock_button1.setText("Manage Stocks");
        manage_stock_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manage_stock_button1ActionPerformed(evt);
            }
        });

        jButton_manage_employee.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton_manage_employee.setText("Manage Employees");
        jButton_manage_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_manage_employeeActionPerformed(evt);
            }
        });

        jButton_manage_defects.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton_manage_defects.setForeground(new java.awt.Color(194, 12, 12));
        jButton_manage_defects.setText("Manage Defects");
        jButton_manage_defects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_manage_defectsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(manage_medicine_button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(generate_bill_button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manage_stock_button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_manage_employee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_manage_defects, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(generate_bill_button1)
                .addGap(34, 34, 34)
                .addComponent(manage_medicine_button1)
                .addGap(35, 35, 35)
                .addComponent(manage_stock_button1)
                .addGap(39, 39, 39)
                .addComponent(jButton_manage_defects)
                .addGap(38, 38, 38)
                .addComponent(jButton_manage_employee)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel2.setText("Medicine Name:");

        jComboBox_medicine_name.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None" }));
        jComboBox_medicine_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_medicine_nameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel3.setText("Supplier Name:");

        jComboBox_supplier_name.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None" }));

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel4.setText("Return Date:");

        jButton_search2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton_search2.setText("Search");
        jButton_search2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_search2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox_medicine_name, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox_supplier_name, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(46, 46, 46)
                        .addComponent(jXDatePicker_return, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jButton_search2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_medicine_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox_supplier_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jXDatePicker_return, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_search2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(1, 1, 1))); // NOI18N

        jTable_defect_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Medicine ID", null},
                {"Medicine Company", null},
                {"Return Date", ""},
                {"Quantity tobe Returned", null},
                {"Amount Received", null},
                {"Amount Pending", null},
                {"Defect ID", null},
                {"Defect Description", ""}
            },
            new String [] {
                "Term", "Value"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_defect_table.setRowHeight(29);
        jTable_defect_table.setRowMargin(2);
        jScrollPane2.setViewportView(jTable_defect_table);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Return Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(1, 1, 1))); // NOI18N

        jTable_return_info.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sr.No", "Medicine Name", "Return date", "Defect ID", "Quantity to return", "Amount Received", "Amount Pending", "Defect Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_return_info.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable_return_info.setColumnSelectionAllowed(true);
        jTable_return_info.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable_return_info);
        jTable_return_info.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(321, 321, 321))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 504, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("View Older Returns", jPanel5);

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel5.setText("Medicine Name:");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel6.setText("Supplier Name:");

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel7.setText("Return Date:");

        jComboBox1_medicine_name.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1_medicine_nameItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel8.setText("Quantity to be Returned:");

        jTextField_supplier_name.setEditable(false);

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel9.setText("Amount received:");

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel10.setText("Amount pending:");

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel11.setText("Defect Number:");

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel12.setText("Defect Description:");

        jTextArea_desc.setColumns(20);
        jTextArea_desc.setRows(5);
        jScrollPane4.setViewportView(jTextArea_desc);

        jButton_add_entry.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton_add_entry.setText("Add Entry");
        jButton_add_entry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_add_entryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox1_medicine_name, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jXDatePicker1_return, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                                    .addComponent(jTextField_supplier_name)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(82, 82, 82)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_amount_received)
                                    .addComponent(jTextField_amount_pending))))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox_defect_num2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel12)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(jButton_add_entry, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1_medicine_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox_defect_num2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField_supplier_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jXDatePicker1_return, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)))
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField_amount_received, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jButton_add_entry)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField_amount_pending, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );

        jTabbedPane2.addTab("Return Defective Items", jPanel6);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 984, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 611, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Defective Stock Management", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("Defect Number:");

        jButton_search_defect.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton_search_defect.setText("Search");
        jButton_search_defect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_search_defectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox_defect_no, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jButton_search_defect, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox_defect_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(jButton_search_defect)
                .addGap(55, 55, 55))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Defect Information"));

        jTable_defect_info.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Defect Number", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable_defect_info);
        if (jTable_defect_info.getColumnModel().getColumnCount() > 0) {
            jTable_defect_info.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("About Defects", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1035, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_search_defectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_search_defectActionPerformed
        DefaultTableModel model1 = (DefaultTableModel) this.jTable_defect_info.getModel();
        int j = jTable_defect_info.getRowCount();
        for (int i = j - 1; i >= 0; i--) {
            model1.removeRow(i);
        }
        try(Connection conn = MySQL_Connector.getConnection()) {
           String query = "select * from defect where defect_id = '"+Integer.parseInt(jComboBox_defect_no.getSelectedItem().toString())+"'";
           System.out.println(Integer.parseInt(jComboBox_defect_no.getSelectedItem().toString()));
           ResultSet rs = MySQL_Connector.runQuery(conn, query);
           if(rs.next()) {
               model1.addRow(new Object[] {rs.getString("defect_id"),rs.getString("defect_description") });
           }
        }catch(SQLException e) {
             Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_jButton_search_defectActionPerformed
    static int serial_no = 1;
    
    private void jButton_search2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_search2ActionPerformed
        String supplier_name = null;
        String medicine_name = null;
        String Date = null;
        DefaultTableModel dm = (DefaultTableModel) this.jTable_return_info.getModel();
        int j = jTable_return_info.getRowCount();
        for(int i = j-1;i >= 0 ; i--) {
            dm.removeRow(i);
        }
        if(jComboBox_supplier_name.getSelectedIndex() != 0) {
            supplier_name = jComboBox_supplier_name.getSelectedItem().toString();
        }
        
        if(jComboBox_medicine_name.getSelectedIndex() != 0) {
           medicine_name = jComboBox_medicine_name.getSelectedItem().toString();
        }
        if(jXDatePicker_return.getDate() != null) {
            SimpleDateFormat sysdate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date = sysdate.format(jXDatePicker_return.getDate()).toString();
        }
        
        try(Connection conn = MySQL_Connector.getConnection()) {
            if(supplier_name != null && medicine_name == null && Date == null) {
                String query1 = "select supplier_id from supplier where supplier_name = '"+supplier_name+"'";
                ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
                Integer supplier_id = 0;
                if(rs1.next()) {
                    supplier_id = Integer.parseInt(rs1.getString("supplier_id"));
                }
                String medicine_id = null;
                DefaultTableModel model = (DefaultTableModel)this.jTable_return_info.getModel();
                String query3 = "select * from stock_defect where supplier_id = '"+supplier_id+"' order by return_date desc";
                ResultSet rs3 = MySQL_Connector.runQuery(conn, query3);
                while(rs3.next()) {
                    medicine_id = rs3.getString("medc_id");
                    String query4 = "select medc_name from medicine where medc_id = '"+medicine_id+"'";
                    ResultSet rs4 = MySQL_Connector.runQuery(conn, query4);
                    if(rs4.next())
                        medicine_name = rs4.getString("medc_name");
                    model.addRow(new Object[]{serial_no, medicine_name, rs3.getString("return_date"), rs3.getString("defect_id"), rs3.getString("quantity"), rs3.getString("amount_received_back"), rs3.getString("amount_pending"), rs3.getString("defect_description_in_detail")});    
                    serial_no++;
                }
                
            }else if(supplier_name != null && Date != null && medicine_name == null) {
                String query1 = "select supplier_id from supplier where supplier_name = '"+supplier_name+"'";
                ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
                Integer supplier_id = 0;
                if(rs1.next()) {
                    supplier_id = Integer.parseInt(rs1.getString("supplier_id"));
                }
                DefaultTableModel model = (DefaultTableModel)this.jTable_return_info.getModel();
                String query2 = "select * from stock_defect where supplier_id = '"+supplier_id+"' && return_date = '"+Date+"'";
                ResultSet rs3 = MySQL_Connector.runQuery(conn, query2);
                String medicine_id = null;
                while(rs3.next()) {
                    medicine_id = rs3.getString("medc_id");
                    String query4 = "select medc_name from medicine where medc_id = '"+medicine_id+"'";
                    ResultSet rs4 = MySQL_Connector.runQuery(conn, query4);
                    if(rs4.next())
                        medicine_name = rs4.getString("medc_name");
                    model.addRow(new Object[]{serial_no, medicine_name, rs3.getString("return_date"), rs3.getString("defect_id"), rs3.getString("quantity"), rs3.getString("amount_received_back"), rs3.getString("amount_pending"), rs3.getString("defect_description_in_detail")});    
                }
                
            }else if(supplier_name == null && medicine_name != null && Date == null) {
                String query1 = "select medc_id from medicine where medc_name = '"+medicine_name+"'";
                ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
                DefaultTableModel model = (DefaultTableModel)this.jTable_return_info.getModel();
                String medicine_id = null;
                if(rs1.next()) {
                    medicine_id = rs1.getString("medc_id");
                }
                String query2 = "select * from stock_defect where medc_id = '"+medicine_id+"' order by return_date";
                ResultSet rs2 = MySQL_Connector.runQuery(conn, query2);
                while(rs2.next()) {
                    String query3 = "select supplier_name from supplier where supplier_id = '"+Integer.parseInt(rs2.getString("supplier_id"))+"'";
                    ResultSet rs3 = MySQL_Connector.runQuery(conn, query3);
                    if(rs3.next())
                        supplier_name = rs3.getString("supplier_name");
                    model.addRow(new Object[]{serial_no, medicine_name, rs2.getString("return_date"), rs2.getString("defect_id"), rs2.getString("quantity"), rs2.getString("amount_received_back"), rs2.getString("amount_pending"), rs2.getString("defect_description_in_detail")});    
                }
            }else if(supplier_name != null && medicine_name != null && Date != null){
                DefaultTableModel model = (DefaultTableModel)this.jTable_return_info.getModel();
                String query1 = "select * from stock_defect where medc_id = (select medc_id from medicine where medc_name = '"+medicine_name+"') && return_date = '"+Date+"' && supplier_id = (select supplier_id from supplier where supplier_name = '"+supplier_name+"')";
                ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
                if(rs1.next()) {
                    model.addRow(new Object[]{serial_no, medicine_name, rs1.getString("return_date"), rs1.getString("defect_id"), rs1.getString("quantity"), rs1.getString("amount_received_back"), rs1.getString("amount_pending"), rs1.getString("defect_description_in_detail")});    
                }else {
                    JOptionPane.showMessageDialog(null, "No entries found!");
                }
            }else if(supplier_name == null && medicine_name != null && Date != null) {
                DefaultTableModel model = (DefaultTableModel)this.jTable_return_info.getModel();
                String query1 = "select * from stock_defect where medc_id = (select medc_id from medicine where medc_name = '"+medicine_name+"') && return_date = '"+Date+"' && supplier_id = (select supplier_id from supplier where supplier_name = '"+supplier_name+"')";
                ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
                if(rs1.next()) {
                    model.addRow(new Object[]{serial_no, medicine_name, rs1.getString("return_date"), rs1.getString("defect_id"), rs1.getString("quantity"), rs1.getString("amount_received_back"), rs1.getString("amount_pending"), rs1.getString("defect_description_in_detail")});    
                }else {
                    JOptionPane.showMessageDialog(null, "No entries found!");
                }
            }
            conn.close();
        }catch(SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_jButton_search2ActionPerformed

    private void jButton_add_entryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_add_entryActionPerformed
        SimpleDateFormat sysdate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String Date = sysdate.format(jXDatePicker1_return.getDate());
        try(Connection conn = MySQL_Connector.getConnection()){ 
            String query = "select medc_id, medc_supplier_id, medc_per_strip, medc_quantity_in_tablets from medicine where medc_name = '"+jComboBox1_medicine_name.getSelectedItem().toString()+"'";
            ResultSet rs = MySQL_Connector.runQuery(conn, query);
            if(rs.next()) {
                String query1 = "insert into stock_defect values ('"+rs.getString("medc_id")+"', '"+Integer.parseInt(rs.getString("medc_supplier_id"))+"', '"+jTextField_quantity.getText().toString()+"', '"+jComboBox_defect_num2.getSelectedItem().toString()+"', '"+jTextArea_desc.getText().toString()+"', '"+Date+"', '"+jTextField_amount_received.getText().toString()+"', '"+jTextField_amount_pending.getText().toString()+"')";
                MySQL_Connector.runUpdateQuery(conn, query1);
                Integer quant = Integer.parseInt(rs.getString("medc_quantity_in_tablets")) - ((Integer.parseInt(jTextField_quantity.getText().toString()))*((int)Math.ceil(Float.parseFloat(rs.getString("medc_per_strip")))));
                String query2 = "update medicine set medc_quantity_in_tablets = "+quant+" where medc_id = '"+rs.getString("medc_id")+"'";
                MySQL_Connector.runUpdateQuery(conn, query2);
            }
            JOptionPane.showMessageDialog(null, "Entry saved succssfully!");
            conn.close();
        }catch(SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
        this.setVisible(false);
        Manage_Defects stocks = new Manage_Defects();
        stocks.setVisible(true);
        stocks.jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_jButton_add_entryActionPerformed
    
                                                 
    
    private void jComboBox1_medicine_nameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1_medicine_nameItemStateChanged
        try(Connection conn = MySQL_Connector.getConnection()) {
            String query = "select supplier_name from supplier where supplier_id = (select supplier_id from medicine where medc_name = '"+jComboBox1_medicine_name.getSelectedItem().toString()+"')";
            ResultSet rs = MySQL_Connector.runQuery(conn, query);
            if(rs.next()) {
                jTextField_supplier_name.setText(rs.getString("supplier_name"));
            }
            conn.close();
        }catch(SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_jComboBox1_medicine_nameItemStateChanged

    private void generate_bill_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generate_bill_button1ActionPerformed
        Owner_Generate_Bill window = new Owner_Generate_Bill();
        window.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_generate_bill_button1ActionPerformed

    private void manage_medicine_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manage_medicine_button1ActionPerformed
        this.setVisible(false);
        Owner_Manage_Medicine window = new Owner_Manage_Medicine();
        window.setVisible(true);
    }//GEN-LAST:event_manage_medicine_button1ActionPerformed

    private void manage_stock_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manage_stock_button1ActionPerformed
        this.setVisible(false);
        Manage_Stocks window = new Manage_Stocks();
        window.setVisible(true);
    }//GEN-LAST:event_manage_stock_button1ActionPerformed

    private void jButton_manage_defectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_manage_defectsActionPerformed
       this.setVisible(false);
       Manage_Defects window = new Manage_Defects();
       window.setVisible(true);
    }//GEN-LAST:event_jButton_manage_defectsActionPerformed

    private void jButton_manage_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_manage_employeeActionPerformed
       this.setVisible(false);
       Owner_Manage_Employee window = new Owner_Manage_Employee();
       window.setVisible(true);
    }//GEN-LAST:event_jButton_manage_employeeActionPerformed

    private void jComboBox_medicine_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_medicine_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_medicine_nameActionPerformed

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
            java.util.logging.Logger.getLogger(Manage_Defects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manage_Defects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manage_Defects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manage_Defects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manage_Defects().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton generate_bill_button1;
    private javax.swing.JButton jButton_add_entry;
    private javax.swing.JButton jButton_manage_defects;
    private javax.swing.JButton jButton_manage_employee;
    private javax.swing.JButton jButton_search2;
    private javax.swing.JButton jButton_search_defect;
    private javax.swing.JComboBox<String> jComboBox1_medicine_name;
    private javax.swing.JComboBox<String> jComboBox_defect_no;
    private javax.swing.JComboBox<String> jComboBox_defect_num2;
    private javax.swing.JComboBox<String> jComboBox_medicine_name;
    private javax.swing.JComboBox<String> jComboBox_supplier_name;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable_defect_info;
    private javax.swing.JTable jTable_defect_table;
    private javax.swing.JTable jTable_return_info;
    private javax.swing.JTextArea jTextArea_desc;
    private javax.swing.JTextField jTextField_amount_pending;
    private javax.swing.JTextField jTextField_amount_received;
    private javax.swing.JTextField jTextField_quantity;
    private javax.swing.JTextField jTextField_supplier_name;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1_return;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker_return;
    private javax.swing.JButton manage_medicine_button1;
    private javax.swing.JButton manage_stock_button1;
    // End of variables declaration//GEN-END:variables
}
