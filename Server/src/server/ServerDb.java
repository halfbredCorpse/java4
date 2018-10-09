package server;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServerDb extends javax.swing.JFrame {
    static boolean accepting;
    static Connection connect;
    static PreparedStatement prepStatement;
    static Statement statement;
    static ResultSet result;
    
    static PrintWriter printer;
    static File file;
    static byte[] bytes;
    static OutputStream outStream;
    
    static ServerSocket server;
    static Socket socket;
    static BufferedReader reader;
    static BufferedWriter writer;
    static InputStreamReader input;
    static OutputStreamWriter output;
    
    static String DB_URL = "jdbc:mysql://localhost:3306/server_db",
                  DB_UN = "root",
                  DB_PW = "",
                  DB_SQL_UPDATE = "INSERT INTO remote_records (User_Id, Date_Time) VALUES (?,?)",
                  DB_SQL_RETRIEVE_CONTACTS = "SELECT * FROM remote_contacts",
                  DB_SQL_RETRIEVE_USERS = "SELECT * FROM remote_users",
                  DB_SQL_RETRIEVE_RECORDS = "SECLECT * FROM remote_records",
                  DB_SQL_SEARCH = "SELET * FROM remote_records WHERE User_Id = ?";
    static final String FILENAME = "report.csv";
    static final int PORT = 5000;
    
    /**
     * Creates new form ServerDb
     */
    public ServerDb() {
        initComponents();
        
        accepting = false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_yes = new javax.swing.JButton();
        btn_no = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Emergency Event Database");

        jLabel2.setText("Accept Updates:");

        btn_yes.setText("Yes");
        btn_yes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_yesActionPerformed(evt);
            }
        });

        btn_no.setText("No");
        btn_no.setEnabled(false);
        btn_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_noActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_yes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_no))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btn_yes)
                    .addComponent(btn_no))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Opens application's ServerSocket and accepts updates
    private void btn_yesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_yesActionPerformed
        accepting = true;
        btn_yes.setEnabled(false);
        btn_no.setEnabled(true);
    }//GEN-LAST:event_btn_yesActionPerformed

    private void btn_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_noActionPerformed
        accepting = false;
        btn_yes.setEnabled(true);
        btn_no.setEnabled(false);
    }//GEN-LAST:event_btn_noActionPerformed

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
            java.util.logging.Logger.getLogger(ServerDb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerDb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerDb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerDb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerDb().setVisible(true);
            }
        });
        
        receiveMessage();
    }
    
    /*
        Check if server is receiving clients
    */
    private static void receiveMessage() {
        String key;
        
        try {
            while (true) {
                server = new ServerSocket(PORT);
                socket = server.accept();
                
                // Accepts key from app to determine action to exeute
                input = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(input);
                key = reader.readLine();
                
                if (key.equals("1"))
                    updateServerDb();
                else if (key.equals("2"))
                    updateClientDb();
                else if (key.equals("3"))
                    exportToFile();
                else if (key.equals("4"))
                    viewEmergencyHistory(); 
                
                input.close();
                reader.close();
                server.close();
                socket.close();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
            
    }
    
    private static void updateServerDb() {
        String message = "",
               entry[];
        
        try {
            do {
                message = reader.readLine();
                entry = message.split(",");
                
                if (message.equals("0"))
                    break;
                else {
                    Class.forName("com.mysql.jdbc.Driver");
                    connect = DriverManager.getConnection(DB_URL, DB_URL, DB_PW);
                    prepStatement = connect.prepareStatement(DB_SQL_UPDATE);
                    prepStatement.setString(1, entry[0]);
                    prepStatement.setString(2, entry[1]);
                    prepStatement.executeUpdate();
                }
            } while (message.equals("0"));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace(); 
        }
    }
    
    private static void updateClientDb() {
        String message,
               entry[];
        
        try {
            do {
                message = reader.readLine();
                entry = message.split(",");
                
                if (message.equals("0"))
                    break;
                else {
                    Class.forName("com.mysql.jdbc.Driver");
                    connect = DriverManager.getConnection(DB_URL, DB_URL, DB_PW);
                    prepStatement = connect.prepareStatement(DB_SQL_UPDATE);
                    prepStatement.setString(1, entry[0]);
                    prepStatement.setString(2, entry[1]);
                    prepStatement.executeUpdate();
                }
            } while (message.equals("0"));
                
            output = new OutputStreamWriter(socket.getOutputStream());
            writer = new BufferedWriter(output);
            
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(DB_URL, DB_URL, DB_PW);
            statement = connect.createStatement();
            result = statement.executeQuery(DB_SQL_RETRIEVE_CONTACTS);
            
            while (result.next()) {
                message = result.getString("User_Id") + "," + result.getString("Emergency_Contact") + "\n";
                writer.write(message);
                writer.flush();
            }
            
            writer.write("0");
            writer.flush();
            
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(DB_URL, DB_URL, DB_PW);
            statement = connect.createStatement();
            result = statement.executeQuery(DB_SQL_RETRIEVE_USERS);
            
            while (result.next()) {
                message = result.getString("User_Id") + "," + result.getString("First_Name") + "," + result.getString("Last_Name") + "\n";
                writer.write(message);
                writer.flush();
            }
            
            writer.write("0");
            writer.flush();
            
            output.close();
            writer.close();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void exportToFile() {
        String message;
        
        try {
            output = new OutputStreamWriter(socket.getOutputStream());
            writer = new BufferedWriter(output);
            printer = new PrintWriter(FILENAME, "UTF-8");
            
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(DB_URL, DB_URL, DB_PW);
            statement = connect.createStatement();
            result = statement.executeQuery(DB_SQL_RETRIEVE_RECORDS);

            while (result.next()) {
                message = result.getString("User_Id") + "," + result.getDate("Date_Time").toString() + "\n";
                printer.print(message);
            }

            printer.close();

            outStream = socket.getOutputStream();
            file = new File(FILENAME);
            
            bytes = new byte[(int) file.length()];
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            
            outStream.close();
            output.close();
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void viewEmergencyHistory() {
        String user, message;
        
        try {
            user = reader.readLine();
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(DB_URL, DB_URL, DB_PW);
            prepStatement = connect.prepareStatement(DB_SQL_SEARCH);
            prepStatement.setString(1, user);
            result = prepStatement.executeQuery();
            
            output = new OutputStreamWriter(socket.getOutputStream());
            writer = new BufferedWriter(output);
            
            while (result.next()) {
                message = result.getString("User_Id") + "," + result.getDate("Date_Time").toString() + "\n";
                writer.write(message);
                writer.flush();
            }
            
            writer.write("0");
            writer.flush();
            
            output.close();
            writer.close();
        } catch (Exception e) {
            
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_no;
    private javax.swing.JButton btn_yes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
