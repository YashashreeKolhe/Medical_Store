/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medical_store;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author dell
 */
public class Manage_Stocks extends javax.swing.JFrame {

    /**
     * Creates new form Manage_Stocks
     */
    public Manage_Stocks() {
        initComponents();
        jTable_stock_table.getColumn("Medicine Name").setPreferredWidth(150);
        jTable_stock_table.getColumn("Supplier Name").setPreferredWidth(150);
        jTable_stock_table.getColumn("Quantity Purchase").setPreferredWidth(120);
        jTable_stock_table.getColumn("Cost of Purchase").setPreferredWidth(120);
        jTable_stock_table.getColumn("Date of Purchase").setPreferredWidth(120);
        jTable_stock_items.getColumn("Sr. No.").setPreferredWidth(60);
        jTable_stock_items.getColumn("Medicine Name").setPreferredWidth(150);
        jTable_stock_items.getColumn("Supplier Name").setPreferredWidth(120);
        jTable_stock_items.getColumn("Quantity Purchased").setPreferredWidth(120);
        jTable_stock_items.getColumn("Cost of Purchase").setPreferredWidth(120);
        jTable_stock_items.getColumn("Date of Purchase").setPreferredWidth(120);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        jXDatePicker1.setFormats(dateFormat);
        jXDatePicker2.setFormats(dateFormat);
        Date date = new Date();
        jLabel_date_of_purchase.setText(dateFormat.format(date));
        try(Connection conn = MySQL_Connector.getConnection()) {
            String query1 = "select supplier_name from supplier";
            ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
            while(rs1.next()) {
                jComboBox_supplier_name.addItem(rs1.getString("supplier_name"));
                jComboBox2_supplier_name.addItem(rs1.getString("supplier_name"));
                jComboBox3_supplier_name.addItem(rs1.getString("supplier_name"));
            }
            
            String query2 = "select medc_name from medicine";
            ResultSet rs2 = MySQL_Connector.runQuery(conn, query2);
            while(rs2.next()) {
                jComboBox_medicine_name.addItem(rs2.getString("medc_name"));
                jComboBox1_medicine_name.addItem(rs2.getString("medc_name"));
                jComboBox2_medicine_name.addItem(rs2.getString("medc_name"));
                jComboBox3_medicine_name.addItem(rs2.getString("medc_name"));

            }
            conn.close();
            rs1.close();
            rs2.close();
        }catch(SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e); 
        }
       // jComboBox_supplier_name.setSelectedIndex(-1);
       // jComboBox_medicine_name.setSelectedIndex(-1);
       // jComboBox3_supplier_name.setSelectedIndex(-1);
        DefaultTableModel tablemodel = (DefaultTableModel)this.jTable_stock_table.getModel();
        ListSelectionModel model = jTable_stock_table.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if(! model.isSelectionEmpty()) {
                    try {
                        int selectedRow = model.getMinSelectionIndex();
                        jTable_stock_info.setValueAt(jTable_stock_table.getValueAt(selectedRow, 0), 1, 1);
                        jTable_stock_info.setValueAt(jTable_stock_table.getValueAt(selectedRow, 1), 2, 1);
                        jTable_stock_info.setValueAt(jTable_stock_table.getValueAt(selectedRow, 4), 3, 1);
                        jTable_stock_info.setValueAt(jTable_stock_table.getValueAt(selectedRow, 2), 4, 1);
                        jTable_stock_info.setValueAt(jTable_stock_table.getValueAt(selectedRow, 3), 6, 1);
                        Connection conn = MySQL_Connector.getConnection();
                        
                        String query = "select medc_id, medc_quantity_in_tablets, medc_per_strip from medicine where medc_name = '"+jTable_stock_table.getValueAt(selectedRow, 0)+"'";
                        ResultSet rs = MySQL_Connector.runQuery(conn, query);
                        
                        if(rs.next()) {
                            jTable_stock_info.setValueAt(rs.getString("medc_id"), 0, 1);
                            jTable_stock_info.setValueAt(Integer.parseInt(rs.getString("medc_quantity_in_tablets"))/(int)Math.ceil(Float.parseFloat(rs.getString("medc_per_strip"))), 5, 1);
                            
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

        jPanel15 = new javax.swing.JPanel();
        generate_bill_button1 = new javax.swing.JButton();
        manage_medicine_button1 = new javax.swing.JButton();
        manage_stock_button1 = new javax.swing.JButton();
        manage_employee = new javax.swing.JButton();
        jButton_manage_defects = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox_supplier_name = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBox_medicine_name = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jXDatePicker = new org.jdesktop.swingx.JXDatePicker();
        jButton_search = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_stock_table = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jTextField_items_purchased = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField_cost_of_pur = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_stock_info = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2_medicine_name = new javax.swing.JComboBox<>();
        jComboBox2_supplier_name = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel12 = new javax.swing.JLabel();
        jTextField_quantity_purchased = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField_total_cost = new javax.swing.JTextField();
        jButton_update_stock = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jComboBox3_medicine_name = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jButton_delete_stock = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable_stock_info2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1_medicine_name = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel_date_of_purchase = new javax.swing.JLabel();
        jButton_add_item = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_stock_items = new javax.swing.JTable();
        jButton_add_to_database = new javax.swing.JButton();
        jLabel_quantity_purchased = new javax.swing.JTextField();
        jLabel_cost_of_purchase = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jTextField_supplier_name = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea_supplier_addr = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        jTextField_contact_number = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField_contact_email = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea_contact_person = new javax.swing.JTextArea();
        jButton_add_supplier = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea_supplier_addr1 = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        jTextField_contact_number1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField_contact_email1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea_contact_person1 = new javax.swing.JTextArea();
        jButton_remove_supplier = new javax.swing.JButton();
        jComboBox3_supplier_name = new javax.swing.JComboBox<>();
        jButton_update_supplier = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Navigation"));

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
        manage_stock_button1.setForeground(new java.awt.Color(191, 23, 23));
        manage_stock_button1.setText("Manage Stocks");
        manage_stock_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manage_stock_button1ActionPerformed(evt);
            }
        });

        manage_employee.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        manage_employee.setText("Manage Employees");
        manage_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manage_employeeActionPerformed(evt);
            }
        });

        jButton_manage_defects.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton_manage_defects.setText("Manage Defects");
        jButton_manage_defects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_manage_defectsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(manage_medicine_button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(generate_bill_button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manage_stock_button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manage_employee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_manage_defects, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(generate_bill_button1)
                .addGap(34, 34, 34)
                .addComponent(manage_medicine_button1)
                .addGap(35, 35, 35)
                .addComponent(manage_stock_button1)
                .addGap(39, 39, 39)
                .addComponent(jButton_manage_defects)
                .addGap(38, 38, 38)
                .addComponent(manage_employee)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("Supplier Name:");

        jComboBox_supplier_name.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None" }));
        jComboBox_supplier_name.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_supplier_nameItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel2.setText("Medicine Name:");

        jComboBox_medicine_name.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None" }));
        jComboBox_medicine_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_medicine_nameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel3.setText("Date of Purchase:");

        jXDatePicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXDatePickerActionPerformed(evt);
            }
        });

        jButton_search.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton_search.setText("Search");
        jButton_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(26, 26, 26))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_search, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_supplier_name, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_medicine_name, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox_medicine_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_supplier_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jXDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jButton_search)
                .addGap(25, 25, 25))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Matching Entries"));

        jTable_stock_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine Name", "Supplier Name", "Quantity Purchase", "Cost of Purchase", "Date of Purchase"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_stock_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(jTable_stock_table);

        jLabel26.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel26.setText("Total Items Purchased:");

        jTextField_items_purchased.setEditable(false);
        jTextField_items_purchased.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_items_purchasedActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel27.setText("Total cost of Purchase:");

        jTextField_cost_of_pur.setEditable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField_items_purchased)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField_cost_of_pur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_items_purchased, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_cost_of_pur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Stock Information"));

        jTable_stock_info.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Medicine ID", null},
                {"Medicine Name", null},
                {"Supplier Name", null},
                {"Date of Purchase", null},
                {"Quantity Purchased (in strips)", null},
                {"Current Available Quantity ", null},
                {"Cost of Purchase", null}
            },
            new String [] {
                "Term", "Details"
            }
        ));
        jScrollPane2.setViewportView(jTable_stock_info);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("View Older Stocks", jPanel1);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Update Information"));

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel9.setText("Medicine Name:");

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel10.setText("Supplier Name:");

        jComboBox2_medicine_name.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2_medicine_nameItemStateChanged(evt);
            }
        });
        jComboBox2_medicine_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2_medicine_nameActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel11.setText("Purchase Date:");

        jXDatePicker1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXDatePicker1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel12.setText("Quantity Purchased:");

        jTextField_quantity_purchased.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_quantity_purchasedActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel13.setText("Total Cost of Purchase:");

        jTextField_total_cost.setEditable(false);

        jButton_update_stock.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton_update_stock.setText("Update Stock");
        jButton_update_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_update_stockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox2_supplier_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(29, 29, 29)
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField_total_cost, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                            .addGap(116, 116, 116)
                                            .addComponent(jLabel12)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextField_quantity_purchased, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jComboBox2_medicine_name, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel7))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(393, 393, 393)
                        .addComponent(jButton_update_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2_medicine_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(61, 61, 61)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox2_supplier_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField_quantity_purchased, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField_total_cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(jButton_update_stock)
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Update Stock", jPanel3);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Delete Information"));

        jLabel14.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel14.setText("Medicine Name:");

        jLabel15.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel15.setText("Purchase Date:");

        jXDatePicker2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXDatePicker2ActionPerformed(evt);
            }
        });

        jButton_delete_stock.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton_delete_stock.setText("Delete Stock Entry");
        jButton_delete_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_delete_stockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox3_medicine_name, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_delete_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jComboBox3_medicine_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addComponent(jButton_delete_stock)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Stock Information"));

        jTable_stock_info2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Medicine ID", null},
                {"Medicine Name", null},
                {"Supplier Name", null},
                {"Date of Purchase", null},
                {"Quantity Purchased (in strips)", null},
                {"Current Available Quantity ", null},
                {"Cost of Purchase", null}
            },
            new String [] {
                "Term", "Details"
            }
        ));
        jScrollPane5.setViewportView(jTable_stock_info2);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(213, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Delete Stock", jPanel4);

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel4.setText("Medicine Name:");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel5.setText("Date of Purchase:");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel6.setText("Quantity of Strips Purchased:");

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel8.setText("Total Cost of Purchase:");

        jLabel_date_of_purchase.setEnabled(false);
        jLabel_date_of_purchase.setOpaque(true);

        jButton_add_item.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton_add_item.setLabel("Add item to Stock");
        jButton_add_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_add_itemActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Stock Items"));

        jTable_stock_items.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sr. No.", "Medicine Name", "Supplier Name", "Quantity Purchased", "Cost of Purchase", "Date of Purchase"
            }
        ));
        jScrollPane3.setViewportView(jTable_stock_items);

        jButton_add_to_database.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton_add_to_database.setText("Add Stock to Database");
        jButton_add_to_database.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_add_to_databaseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_add_to_database, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_add_to_database)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jLabel_quantity_purchased.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabel_quantity_purchasedActionPerformed(evt);
            }
        });

        jLabel_cost_of_purchase.setEditable(false);
        jLabel_cost_of_purchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabel_cost_of_purchaseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_date_of_purchase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLabel_cost_of_purchase))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel_quantity_purchased))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox1_medicine_name, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_add_item, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117)))
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox1_medicine_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel_date_of_purchase, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel_quantity_purchased, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel_cost_of_purchase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addComponent(jButton_add_item))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Insert New Stock", jPanel2);

        jLabel16.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel16.setText("Supplier Name:");

        jTextField_supplier_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_supplier_nameActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel17.setText("Address:");

        jTextArea_supplier_addr.setColumns(20);
        jTextArea_supplier_addr.setRows(5);
        jScrollPane4.setViewportView(jTextArea_supplier_addr);

        jLabel18.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel18.setText("Contact Number:");

        jLabel19.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel19.setText("Contact Email:");

        jLabel20.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel20.setText("Contact Person:");

        jTextArea_contact_person.setColumns(20);
        jTextArea_contact_person.setRows(5);
        jScrollPane6.setViewportView(jTextArea_contact_person);

        jButton_add_supplier.setText("Add Supplier");
        jButton_add_supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_add_supplierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_supplier_name, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_contact_number, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_contact_email))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_add_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(350, 350, 350))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField_supplier_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField_contact_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jTextField_contact_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jButton_add_supplier)
                .addGap(44, 44, 44))
        );

        jTabbedPane3.addTab("Add Supplier", jPanel13);

        jLabel21.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel21.setText("Supplier Name:");

        jLabel22.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel22.setText("Address:");

        jTextArea_supplier_addr1.setColumns(20);
        jTextArea_supplier_addr1.setRows(5);
        jScrollPane7.setViewportView(jTextArea_supplier_addr1);

        jLabel23.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel23.setText("Contact Number:");

        jLabel24.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel24.setText("Contact Email:");

        jTextField_contact_email1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_contact_email1ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel25.setText("Contact Person:");

        jTextArea_contact_person1.setColumns(20);
        jTextArea_contact_person1.setRows(5);
        jScrollPane8.setViewportView(jTextArea_contact_person1);

        jButton_remove_supplier.setText("Remove Supplier");
        jButton_remove_supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_remove_supplierActionPerformed(evt);
            }
        });

        jComboBox3_supplier_name.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3_supplier_nameItemStateChanged(evt);
            }
        });

        jButton_update_supplier.setText("Update Supplier");
        jButton_update_supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_update_supplierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_remove_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addComponent(jLabel21)
                            .addGap(18, 18, 18)
                            .addComponent(jComboBox3_supplier_name, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addComponent(jLabel22)
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane7))
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addComponent(jLabel23)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField_contact_number1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_contact_email1))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jButton_update_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel24)
                    .addComponent(jTextField_contact_email1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3_supplier_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jTextField_contact_number1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_remove_supplier)
                            .addComponent(jButton_update_supplier))
                        .addGap(46, 46, 46))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(219, Short.MAX_VALUE))))
        );

        jTabbedPane3.addTab("Remove/Update Supplier", jPanel14);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 927, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Manage Suppliers", jPanel11);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1019, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    private void jButton_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_searchActionPerformed
        String supplier_name = null;
        String medicine_name = null;
        String Date = null;
        DefaultTableModel dm = (DefaultTableModel) this.jTable_stock_table.getModel();
        int j = jTable_stock_table.getRowCount();
        for(int i = j-1;i >= 0 ; i--) {
            dm.removeRow(i);
        }
        if(jComboBox_supplier_name.getSelectedIndex() != 0) {
            supplier_name = jComboBox_supplier_name.getSelectedItem().toString();
        }
        
        if(jComboBox_medicine_name.getSelectedIndex() != 0) {
           medicine_name = jComboBox_medicine_name.getSelectedItem().toString();
        }
        if(jXDatePicker.getDate() != null) {
            SimpleDateFormat sysdate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date = sysdate.format(jXDatePicker.getDate()).toString();
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
                DefaultTableModel model = (DefaultTableModel)this.jTable_stock_table.getModel();
                String query3 = "select * from stock_details where supplier_id = '"+supplier_id+"' order by purchase_date_timestamp desc";
                ResultSet rs3 = MySQL_Connector.runQuery(conn, query3);
                while(rs3.next()) {
                    medicine_id = rs3.getString("medc_id");
                    String query4 = "select medc_name from medicine where medc_id = '"+medicine_id+"'";
                    ResultSet rs4 = MySQL_Connector.runQuery(conn, query4);
                    if(rs4.next())
                        medicine_name = rs4.getString("medc_name");
                    model.addRow(new Object[]{medicine_name, supplier_name, rs3.getString("quantity"), rs3.getString("total_cost_of_purchase"), rs3.getString("purchase_date_timestamp")});    
                }
                tableSort();
                
            }else if(supplier_name != null && Date != null && medicine_name == null) {
                String query1 = "select supplier_id from supplier where supplier_name = '"+supplier_name+"'";
                ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
                Integer supplier_id = 0;
                if(rs1.next()) {
                    supplier_id = Integer.parseInt(rs1.getString("supplier_id"));
                }
                DefaultTableModel model = (DefaultTableModel)this.jTable_stock_table.getModel();
                String query2 = "select * from stock_details where supplier_id = '"+supplier_id+"' && purchase_date_timestamp = '"+Date+"'";
                ResultSet rs2 = MySQL_Connector.runQuery(conn, query2);
                String medicine_id = null;
                while(rs2.next()) {
                    medicine_id = rs2.getString("medc_id");
                    String query4 = "select medc_name from medicine where medc_id = '"+medicine_id+"'";
                    ResultSet rs4 = MySQL_Connector.runQuery(conn, query4);
                    if(rs4.next())
                        medicine_name = rs4.getString("medc_name");
                    model.addRow(new Object[]{medicine_name, supplier_name, rs2.getString("quantity"), rs2.getString("total_cost_of_purchase"), rs2.getString("purchase_date_timestamp")});
                }
                tableSort();
                String query3 = "select sum(total_cost_of_purchase) cost, count(medc_id) items from stock_details where supplier_id = '"+supplier_id+"' && purchase_date_timestamp = '"+Date+"'";
                ResultSet rs3 = MySQL_Connector.runQuery(conn, query3);
                if(rs3.next()) {
                    jTextField_cost_of_pur.setText(rs3.getString("cost"));
                    jTextField_items_purchased.setText(rs3.getString("items"));
                }
            }else if((supplier_name == null && medicine_name != null && Date == null) || (supplier_name != null && medicine_name != null && Date == null)) {
                String query1 = "select medc_id from medicine where medc_name = '"+medicine_name+"'";
                ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
                DefaultTableModel model = (DefaultTableModel)this.jTable_stock_table.getModel();
                String medicine_id = null;
                if(rs1.next()) {
                    medicine_id = rs1.getString("medc_id");
                }
                String query2 = "select * from stock_details where medc_id = '"+medicine_id+"' order by purchase_date_timestamp";
                ResultSet rs2 = MySQL_Connector.runQuery(conn, query2);
                while(rs2.next()) {
                    String query3 = "select supplier_name from supplier where supplier_id = '"+Integer.parseInt(rs2.getString("supplier_id"))+"'";
                    ResultSet rs3 = MySQL_Connector.runQuery(conn, query3);
                    if(rs3.next())
                        supplier_name = rs3.getString("supplier_name");
                    model.addRow(new Object[]{medicine_name, supplier_name, rs2.getString("quantity"), rs2.getString("total_cost_of_purchase"), rs2.getString("purchase_date_timestamp")});
                }
            }else if(supplier_name != null && medicine_name != null && Date != null){
                DefaultTableModel model = (DefaultTableModel)this.jTable_stock_table.getModel();
                String query1 = "select * from stock_details where medc_id = (select medc_id from medicine where medc_name = '"+medicine_name+"') && purchase_date_timestamp = '"+Date+"' && supplier_id = (select supplier_id from supplier where supplier_name = '"+supplier_name+"')";
                ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
                if(rs1.next()) {
                    model.addRow(new Object[]{medicine_name, supplier_name, rs1.getString("quantity"), rs1.getString("total_cost_of_purchase"), rs1.getString("purchase_date_timestamp")});
                }else {
                    JOptionPane.showMessageDialog(null, "No entries found!");
                }
            }else if(supplier_name == null && medicine_name != null && Date != null) {
                DefaultTableModel model = (DefaultTableModel)this.jTable_stock_table.getModel();
                String query1 = "select * from stock_details where medc_id = (select medc_id from medicine where medc_name = '"+medicine_name+"') && purchase_date_timestamp = '"+Date+"'";
                ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
                if(rs1.next()) {
                    String query2 = "select supplier_name from supplier where supplier_id = "+rs1.getString("supplier_id")+"";
                    ResultSet rs2 = MySQL_Connector.runQuery(conn, query2);
                    if(rs2.next()) {
                        model.addRow(new Object[]{medicine_name, rs2.getString("supplier_name"), rs1.getString("quantity"), rs1.getString("total_cost_of_purchase"), rs1.getString("purchase_date_timestamp")});
                
                    }else {
                        JOptionPane.showMessageDialog(null, "No entries found!");
                    }
                }
            }else {
                JOptionPane.showMessageDialog(null, "Enter appropriate fields!");
            }
            conn.close();
        }catch(SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }//GEN-LAST:event_jButton_searchActionPerformed

    private static int serial_no = 1;
    
    private void jButton_add_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_add_itemActionPerformed
        String medicine_name = jComboBox1_medicine_name.getSelectedItem().toString();
        String supplier_name = "";
        try(Connection conn = MySQL_Connector.getConnection()) {
            String query1 = "select supplier_name from supplier where supplier_id = (select medc_supplier_id from medicine where medc_name = '"+medicine_name+"')";
            ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
            if(rs1.next())
             supplier_name = rs1.getString("supplier_name");
            conn.close();
        }catch(SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
        String Date = jLabel_date_of_purchase.getText();
        Integer cost = Integer.parseInt(jLabel_cost_of_purchase.getText());
        Integer quantity = Integer.parseInt(jLabel_quantity_purchased.getText());
        DefaultTableModel model = (DefaultTableModel)this.jTable_stock_items.getModel();
        model.addRow(new Object[] {serial_no, medicine_name, supplier_name, quantity, cost, Date});
        serial_no++;
    }//GEN-LAST:event_jButton_add_itemActionPerformed

    private void jLabel_cost_of_purchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLabel_cost_of_purchaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel_cost_of_purchaseActionPerformed

    private void jLabel_quantity_purchasedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLabel_quantity_purchasedActionPerformed
        String quantity = jLabel_quantity_purchased.getText();
        String medicine_name = jComboBox1_medicine_name.getSelectedItem().toString();
        float cost = 0; 
        Integer cost1;
        try(Connection conn = MySQL_Connector.getConnection()) {
            String query1 = "select medc_cost_per_strip from medicine where medc_name = '"+medicine_name+"'";
            ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
            if(rs1.next())
                cost = Float.parseFloat(rs1.getString("medc_cost_per_strip")) * Float.parseFloat(quantity);
            cost1 = (int)Math.ceil(cost);
            jLabel_cost_of_purchase.setText(cost1.toString());
            conn.close();
        }catch(SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_jLabel_quantity_purchasedActionPerformed

    private void jButton_add_to_databaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_add_to_databaseActionPerformed
        String medicine_id = null;
        Integer supplier_id = 0;
        Integer quantity = 0;
        Integer cost;
        Integer tablets_in_strips;
        String purchase_date = null;
        try(Connection conn = MySQL_Connector.getConnection()) {
            for(int i = 0; i<jTable_stock_items.getRowCount(); i++) {
                String query1 = "select medc_id, medc_per_strip, medc_quantity_in_tablets from medicine where medc_name = '"+jTable_stock_items.getValueAt(i, 1).toString()+"'";
                ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
                if(rs1.next())
                    medicine_id = rs1.getString("medc_id");
                tablets_in_strips = Integer.parseInt(rs1.getString("medc_quantity_in_tablets")) + (int)Math.ceil(Float.parseFloat(rs1.getString("medc_per_strip")) * (int)Math.ceil(Float.parseFloat(jTable_stock_items.getValueAt(i, 3).toString())));
                String query2 = "select supplier_id from supplier where supplier_name = '"+jTable_stock_items.getValueAt(i, 2).toString()+"'";
                ResultSet rs2 = MySQL_Connector.runQuery(conn, query2);
                if(rs2.next())
                    supplier_id = Integer.parseInt(rs2.getString("supplier_id"));
                purchase_date = jTable_stock_items.getValueAt(i, 5).toString();
                cost = Integer.parseInt(jTable_stock_items.getValueAt(i, 4).toString());
                quantity = Integer.parseInt(jTable_stock_items.getValueAt(i, 3).toString());
                
                String query6 = "select * from stock_details where medc_id = '"+medicine_id+"' && purchase_date_timestamp = '"+purchase_date+"'";
                ResultSet rs6 = MySQL_Connector.runQuery(conn, query6);
                if(rs6.next()) {
                    JOptionPane.showMessageDialog(null, "Entry already exists! Please update stock");
                }else {
                    String query4 = "insert into stock_details values('"+medicine_id+"', '"+supplier_id+"', '"+purchase_date+"', '"+quantity+"', '"+cost+"')";
                    MySQL_Connector.runUpdateQuery(conn, query4);
                    String query5 = "update medicine set medc_quantity_in_tablets = "+tablets_in_strips+" where medc_id = '"+medicine_id+"'";
                    MySQL_Connector.runUpdateQuery(conn, query5);
                }
            }
            JOptionPane.showMessageDialog(null, new String("Entry saved successfully in the Database!"));
            conn.close();
        }catch (SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
        this.setVisible(false);
        Manage_Stocks stocks = new Manage_Stocks();
        stocks.setVisible(true);
        stocks.jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jButton_add_to_databaseActionPerformed

    private void jButton_update_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_update_stockActionPerformed
        String medicine_id = null;
        Integer medicine_quantity = 0;
        Integer total_quantity = Integer.parseInt(jTextField_quantity_purchased.getText());
        System.out.println(total_quantity);
        float total_cost = Float.parseFloat(jTextField_total_cost.getText());
        Integer cost = (int)Math.ceil((double)total_cost);
        SimpleDateFormat sysdate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
        String date = sysdate.format(jXDatePicker1.getDate()).toString();
        try(Connection conn = MySQL_Connector.getConnection()) {
           String query1 = "select medc_id, medc_quantity_in_tablets, medc_per_strip from medicine where medc_name = '"+jComboBox2_medicine_name.getSelectedItem().toString()+"'";
           ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
           if(rs1.next()) {
               medicine_id = rs1.getString("medc_id");
               medicine_quantity = Integer.parseInt(rs1.getString("medc_quantity_in_tablets"));
           }
           String query2 = "update stock_details set quantity = '"+total_quantity+"', total_cost_of_purchase = '"+cost+"' where medc_id = '"+medicine_id+"' && purchase_date_timestamp = '"+date+"'";
           MySQL_Connector.runUpdateQuery(conn, query2);
            String query3 = "update medicine set medc_quantity_in_tablets = "+(Integer.parseInt(rs1.getString("medc_quantity_in_tablets"))+(int)Math.ceil(Float.parseFloat(rs1.getString("medc_per_strip")))*Integer.parseInt(jTextField_quantity_purchased.getText()))+" where medc_name = '"+jComboBox2_medicine_name.getSelectedItem().toString()+"'";
           MySQL_Connector.runUpdateQuery(conn, query3);
           JOptionPane.showMessageDialog(null, "Entry Updated successfully!");
        }catch(SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
       }
       this.setVisible(false);
       Manage_Stocks stock = new Manage_Stocks();
       stock.jTabbedPane1.setSelectedIndex(1);
       stock.setVisible(true);
    }//GEN-LAST:event_jButton_update_stockActionPerformed

    private void jComboBox2_medicine_nameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2_medicine_nameItemStateChanged

    }//GEN-LAST:event_jComboBox2_medicine_nameItemStateChanged

    private void jComboBox2_medicine_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2_medicine_nameActionPerformed
        try(Connection conn = MySQL_Connector.getConnection()) {
            String query1 = "select medc_id from medicine where medc_name = '"+jComboBox2_medicine_name.getSelectedItem().toString()+"'";
            ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
            if(rs1.next()) {
                String query2 = "select supplier_name from supplier where supplier_id = (select supplier_id from medicine where medc_id = '"+rs1.getString("medc_id")+"')";
                ResultSet rs2 = MySQL_Connector.runQuery(conn, query2);
                if(rs2.next()) {
                    jComboBox2_supplier_name.setSelectedItem(rs2.getString("supplier_name"));
                }
            }
            conn.close();
        }catch(SQLException e) {
             Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_jComboBox2_medicine_nameActionPerformed

    private void jXDatePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDatePicker1ActionPerformed
        SimpleDateFormat sysdate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
        String date = sysdate.format(jXDatePicker1.getDate()).toString();
        try(Connection conn = MySQL_Connector.getConnection()) {
            String query1 = "select medc_id from medicine where medc_name = '"+jComboBox2_medicine_name.getSelectedItem().toString()+"'";
            ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
            if(rs1.next()) {
                String query2 = "select * from stock_details where medc_id = '"+rs1.getString("medc_id")+"' && purchase_date_timestamp = '"+date+"'";
                ResultSet rs2 = MySQL_Connector.runQuery(conn, query2);
                if(rs2.next()) {
                    jTextField_total_cost.setText(rs2.getString("total_cost_of_purchase"));
                    jTextField_quantity_purchased.setText(rs2.getString("quantity"));
                }
            }
            conn.close();
        }catch(SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_jXDatePicker1ActionPerformed

    private void jTextField_quantity_purchasedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_quantity_purchasedActionPerformed
        try(Connection conn = MySQL_Connector.getConnection()) {
            String query1 = "select medc_cost_per_strip from medicine where medc_name = '"+jComboBox2_medicine_name.getSelectedItem().toString()+"'";
            Integer cost  = Integer.parseInt(jTextField_quantity_purchased.getText());
            ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
            if(rs1.next()) {
                cost = cost * (int)Math.ceil(Float.parseFloat(rs1.getString("medc_cost_per_strip")));
                jTextField_total_cost.setText(cost.toString());
            }
            conn.close();
        }catch(SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_jTextField_quantity_purchasedActionPerformed

    private void jXDatePickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDatePickerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jXDatePickerActionPerformed

    private void jComboBox_medicine_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_medicine_nameActionPerformed
       
    }//GEN-LAST:event_jComboBox_medicine_nameActionPerformed

    private void jComboBox_supplier_nameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_supplier_nameItemStateChanged
      /*  System.out.println(jComboBox_medicine_name.getSelectedItem().toString());
        try(Connection conn = MySQL_Connector.getConnection()) {
           String query = "select supplier_name from supplier where supplier_id = (select supplier_id from medicine where medc_name = '"+jComboBox_medicine_name.getSelectedItem().toString()+"')";
           ResultSet rs1 = MySQL_Connector.runQuery(conn, query);
           if(rs1.next()) {
               jComboBox_supplier_name.setSelectedItem(rs1.getString("supplier_name"));
           }
           conn.close();
        }catch(SQLException e) {
             Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }*/
    }//GEN-LAST:event_jComboBox_supplier_nameItemStateChanged

    private void jButton_delete_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_delete_stockActionPerformed
        SimpleDateFormat sysdate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
        String date = sysdate.format(jXDatePicker2.getDate());
        int result = JOptionPane.showConfirmDialog(rootPane, "Do you really want to delete this entry?");
        if(result == 1 || result == 2) {
            return;
        }
        try(Connection conn = MySQL_Connector.getConnection()) {
            String query = "delete from stock_details where purchase_date_timestamp = '"+date+"' && medc_id = (select medc_id from medicine where medc_name = '"+jComboBox3_medicine_name.getSelectedItem().toString()+"')";
            MySQL_Connector.runUpdateQuery(conn, query);
            JOptionPane.showMessageDialog(null, "Stock entry deleted successfully!");
            conn.close();
        }catch(SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
        this.setVisible(false);
        Manage_Stocks stocks = new Manage_Stocks();
        stocks.setVisible(true);
        stocks.jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jButton_delete_stockActionPerformed

    private void jXDatePicker2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDatePicker2ActionPerformed
        SimpleDateFormat sysdate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
        String date = sysdate.format(jXDatePicker2.getDate());
        
        try(Connection conn = MySQL_Connector.getConnection()) {
            String query = "select * from stock_details where purchase_date_timestamp = '"+date+"' && medc_id = (select medc_id from medicine where medc_name = '"+jComboBox3_medicine_name.getSelectedItem().toString()+"') ";
            ResultSet rs = MySQL_Connector.runQuery(conn, query);
            String query1 = "select supplier_name from supplier where supplier_id = (select supplier_id from stock_details where purchase_date_timestamp = '"+date+"' && medc_id = (select medc_id from medicine where medc_name = '"+jComboBox3_medicine_name.getSelectedItem().toString()+"') )";
            ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
            String query2 = "select medc_quantity_in_tablets, medc_per_strip from medicine where medc_name = '"+jComboBox3_medicine_name.getSelectedItem().toString()+"'";
            ResultSet rs2 = MySQL_Connector.runQuery(conn, query2);
            if(rs.next()) {
                if(rs1.next() && rs2.next()) {
                    jTable_stock_info2.setValueAt(rs.getString("medc_id"), 0, 1);
                    jTable_stock_info2.setValueAt(jComboBox3_medicine_name.getSelectedItem().toString(), 1, 1);
                    jTable_stock_info2.setValueAt(rs1.getString("supplier_name"), 2, 1);
                    jTable_stock_info2.setValueAt(date, 3, 1);
                    jTable_stock_info2.setValueAt(rs.getString("quantity"), 4, 1);
                    jTable_stock_info2.setValueAt(Integer.parseInt(rs2.getString("medc_quantity_in_tablets"))/(int)Math.ceil(Float.parseFloat(rs2.getString("medc_per_strip"))), 5,1);
                    jTable_stock_info2.setValueAt(rs.getString("total_cost_of_purchase"), 6, 1);
                }
            }
            conn.close();
        }catch(SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_jXDatePicker2ActionPerformed

    private void jTextField_supplier_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_supplier_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_supplier_nameActionPerformed

    private void jButton_add_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_add_supplierActionPerformed
       String supplier_name = jTextField_supplier_name.getText().toString();
       String supplier_addr = jTextArea_supplier_addr.getText().toString();
       String contact_number = jTextField_contact_number.getText().toString();
       String contact_email = jTextField_contact_email.getText().toString();
       String contact_person = jTextArea_contact_person.getText().toString();
       try(Connection conn = MySQL_Connector.getConnection()) {
         String query = "insert into supplier(supplier_name, supplier_addr, supplier_phone_num, supplier_email, supplier_contact_person) values ('"+supplier_name+"', '"+supplier_addr+"', '"+contact_number+"', '"+contact_email+"', '"+contact_person+"')";  
         MySQL_Connector.runUpdateQuery(conn, query);
         JOptionPane.showConfirmDialog(null, "Supplier Entry saved successfully!");
         conn.close();
       }catch(SQLException e) {
           Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
       }
       this.setVisible(false);
       Manage_Stocks stocks = new Manage_Stocks();
       stocks.setVisible(true);
       stocks.jTabbedPane1.setSelectedIndex(4);
       stocks.jTabbedPane3.setSelectedIndex(0);
    }//GEN-LAST:event_jButton_add_supplierActionPerformed

    private static long serial_no_supplier = 1;
    
    private void jButton_remove_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_remove_supplierActionPerformed
        int result = JOptionPane.showConfirmDialog(rootPane, "Do you really want to delete this entry?");
        if(result == 1 || result == 2) {
            return;
        }
        try(Connection conn = MySQL_Connector.getConnection()) {
            String query = "delete from supplier where supplier_name = '"+jComboBox3_supplier_name.getSelectedItem().toString()+"'";
            MySQL_Connector.runUpdateQuery(conn, query);
            conn.close();
            JOptionPane.showMessageDialog(null, "Entry removed successfully!");
        }catch(SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
        this.setVisible(false);
        Manage_Stocks stocks = new Manage_Stocks();
        stocks.setVisible(true);
        stocks.jTabbedPane1.setSelectedIndex(4);
        stocks.jTabbedPane3.setSelectedIndex(1);
    }//GEN-LAST:event_jButton_remove_supplierActionPerformed

    private void jComboBox3_supplier_nameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3_supplier_nameItemStateChanged
        try(Connection conn = MySQL_Connector.getConnection()) {
            String query = "select * from supplier where supplier_name = '"+jComboBox3_supplier_name.getSelectedItem().toString()+"'";
            ResultSet rs = MySQL_Connector.runQuery(conn, query);
            if(rs.next()) {
                jTextArea_supplier_addr1.setText(rs.getString("supplier_addr"));
                jTextField_contact_number1.setText(rs.getString("supplier_phone_num"));
                jTextField_contact_email1.setText(rs.getString("supplier_email"));
                jTextArea_contact_person1.setText(rs.getString("supplier_contact_person"));
            }
            conn.close();
        }catch(SQLException e) {
             Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_jComboBox3_supplier_nameItemStateChanged

    private void jButton_update_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_update_supplierActionPerformed
        try(Connection conn = MySQL_Connector.getConnection()) {
            String query = "update supplier set supplier_addr = '"+jTextArea_supplier_addr1.getText().toString()+"', supplier_phone_num = '"+jTextField_contact_number1.getText().toString()+"', supplier_email = '"+jTextField_contact_email1.getText().toString()+"', supplier_contact_person = '"+jTextArea_contact_person1.getText().toString()+"' where supplier_name = '"+jComboBox3_supplier_name.getSelectedItem().toString()+"'";
            MySQL_Connector.runUpdateQuery(conn, query);
            JOptionPane.showMessageDialog(null, "Entry updated successfully!");
            conn.close();
        }catch(SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);   
        }
        this.setVisible(false);
        Manage_Stocks stocks = new Manage_Stocks();
        stocks.setVisible(true);
        stocks.jTabbedPane1.setSelectedIndex(4);
        stocks.jTabbedPane3.setSelectedIndex(1);
    }//GEN-LAST:event_jButton_update_supplierActionPerformed

    private void jTextField_contact_email1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_contact_email1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_contact_email1ActionPerformed

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
       Manage_Defects defect = new Manage_Defects();
       defect.setVisible(true);
    }//GEN-LAST:event_jButton_manage_defectsActionPerformed

    private void manage_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manage_employeeActionPerformed
       this.setVisible(false);
       Owner_Manage_Employee window = new Owner_Manage_Employee();
       window.setVisible(true);
    }//GEN-LAST:event_manage_employeeActionPerformed

    private void jTextField_items_purchasedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_items_purchasedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_items_purchasedActionPerformed
    
    private void tableSort() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTable_stock_table.getModel());
        jTable_stock_table.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(4, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
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
            java.util.logging.Logger.getLogger(Manage_Stocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manage_Stocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manage_Stocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manage_Stocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manage_Stocks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton generate_bill_button1;
    private javax.swing.JButton jButton_add_item;
    private javax.swing.JButton jButton_add_supplier;
    private javax.swing.JButton jButton_add_to_database;
    private javax.swing.JButton jButton_delete_stock;
    private javax.swing.JButton jButton_manage_defects;
    private javax.swing.JButton jButton_remove_supplier;
    private javax.swing.JButton jButton_search;
    private javax.swing.JButton jButton_update_stock;
    private javax.swing.JButton jButton_update_supplier;
    private javax.swing.JComboBox<String> jComboBox1_medicine_name;
    private javax.swing.JComboBox<String> jComboBox2_medicine_name;
    private javax.swing.JComboBox<String> jComboBox2_supplier_name;
    private javax.swing.JComboBox<String> jComboBox3_medicine_name;
    private javax.swing.JComboBox<String> jComboBox3_supplier_name;
    private javax.swing.JComboBox<String> jComboBox_medicine_name;
    private javax.swing.JComboBox<String> jComboBox_supplier_name;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jLabel_cost_of_purchase;
    private javax.swing.JLabel jLabel_date_of_purchase;
    private javax.swing.JTextField jLabel_quantity_purchased;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
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
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable_stock_info;
    private javax.swing.JTable jTable_stock_info2;
    private javax.swing.JTable jTable_stock_items;
    private javax.swing.JTable jTable_stock_table;
    private javax.swing.JTextArea jTextArea_contact_person;
    private javax.swing.JTextArea jTextArea_contact_person1;
    private javax.swing.JTextArea jTextArea_supplier_addr;
    private javax.swing.JTextArea jTextArea_supplier_addr1;
    private javax.swing.JTextField jTextField_contact_email;
    private javax.swing.JTextField jTextField_contact_email1;
    private javax.swing.JTextField jTextField_contact_number;
    private javax.swing.JTextField jTextField_contact_number1;
    private javax.swing.JTextField jTextField_cost_of_pur;
    private javax.swing.JTextField jTextField_items_purchased;
    private javax.swing.JTextField jTextField_quantity_purchased;
    private javax.swing.JTextField jTextField_supplier_name;
    private javax.swing.JTextField jTextField_total_cost;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    private javax.swing.JButton manage_employee;
    private javax.swing.JButton manage_medicine_button1;
    private javax.swing.JButton manage_stock_button1;
    // End of variables declaration//GEN-END:variables
}
