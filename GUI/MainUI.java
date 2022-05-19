package com.company.GUI;

import com.company.services.PairEmployees;
import com.company.services.ReadFromFile;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.ParseException;

public class MainUI implements ActionListener {
    // Panel
    private JPanel rootPanel;
    // Table
    private JTable infoTable;
    // Button
    private JButton importFileButton;

    // Constructor
    public MainUI(){
        //waiting to import file
        this.importFileButton.addActionListener(this);
    }

    // Getter
    public JPanel getRootPanel(){
        return rootPanel;
    }

    // Creating table and fill with some data
    private void createTable(Object[][] data){

        // Setting name of columns
        infoTable.setModel(new DefaultTableModel(
                data,
                new String[]{"Employee ID #1","Employee ID #2", "Project ID", "Days worked"}
        ));
        // Setting align to center of a column's value
        TableColumnModel columns = infoTable.getColumnModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        columns.getColumn(0).setCellRenderer(centerRenderer);
        columns.getColumn(1).setCellRenderer(centerRenderer);
        columns.getColumn(2).setCellRenderer(centerRenderer);
        columns.getColumn(3).setCellRenderer(centerRenderer);
    }

    // Action for importing file
    public void actionPerformed(ActionEvent e) {
        // After clicking on the button
        // The program try to read info from the file
        if (e.getSource() == this.importFileButton) {
            JFileChooser fileChooser = new JFileChooser();

            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                ReadFromFile read = new ReadFromFile();

                try {
                    read.ReadFile(filePath);
                } catch (FileNotFoundException | ParseException ex) {
                    ex.printStackTrace();
                }

                // if reading is successful program start to calculate which pair of employees has the longest working together period
                // Then finds if they have other common projects
                // Create table and display the information
                if (read.isTemp()) {
                    PairEmployees time = new PairEmployees(read.getEmployees());
                    Object[][] data = time.pairEmployees();
                    createTable(data);
                }
            }
        }
    }

}
