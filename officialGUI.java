import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class officialGUI extends JFrame {
    private DefaultTableModel tableModel;
    private competitorList competitorList;
    private JTable competitorTable;

    public officialGUI(competitorList competitorList) {
        this.competitorList = competitorList;

        setTitle("Competitor Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set dark mode look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.put("control", new ColorUIResource(255, 255, 255));
            // Other UI settings...
        } catch (Exception e) {
            e.printStackTrace();
        }

        createLayout();
    }

    private void createLayout() {
        JTabbedPane tabbedPane = new JTabbedPane();

        // Panel 1: View Short/Full details
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        tabbedPane.addTab("View Short/Full details", panel1);

        // Add components for Panel 1
        createViewDetailsPanel(panel1);

        // Panel 2: View table of competitors
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        tabbedPane.addTab("View Competitors Table", panel2);

        // Add components for Panel 2
        createCompetitorsTable(panel2);

        // Panel 3: Amend and view competitors
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        tabbedPane.addTab("Amend and view competitors", panel3);

        // Add components for Panel 3
        createAmendAndViewPanel(panel3);

        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save the competitor report to a text file
                String reportPath = "FinalReport.txt";
                // Close the program
                System.exit(0);
            }
        });

        // Add components to the main frame
        add(tabbedPane, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
    }

    //FULL DETAILS AND SHORT DETAILS------------------------------------------------------------------------------
    private void createViewDetailsPanel(JPanel panel) {
        JPanel searchPanel = new JPanel();
        JLabel searchLabel = new JLabel("Enter Competitor Number:");
        JTextField searchField = new JTextField(10);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());

        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(detailsArea);

        JPanel buttonPanel = new JPanel();
        JButton shortDetailsButton = new JButton("View Short Details");
        JButton fullDetailsButton = new JButton("View Full Details");
        buttonPanel.add(shortDetailsButton);
        buttonPanel.add(fullDetailsButton);

        // Add a separate panel for buttons
        JPanel separateButtonPanel = new JPanel();
        separateButtonPanel.add(shortDetailsButton);
        separateButtonPanel.add(fullDetailsButton);

        // Adjust layout
        detailsPanel.add(searchPanel, BorderLayout.NORTH);
        detailsPanel.add(separateButtonPanel, BorderLayout.WEST);
        detailsPanel.add(detailsScrollPane, BorderLayout.CENTER);

        // Action listeners...
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int competitorNumber = Integer.parseInt(searchField.getText());
                RMDCompetitor competitor = competitorList.getCompetitorByNumber(competitorNumber);

                if (competitor != null) {
                    // Display short details initially
                    detailsArea.setText(competitor.getShortDetails());

                    shortDetailsButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Display short details
                            detailsArea.setText(competitor.getShortDetails());
                        }
                    });

                    fullDetailsButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Display full details
                            detailsArea.setText(competitor.getFullDetails());
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(panel, "Competitor not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(detailsPanel, BorderLayout.CENTER);
    }
    //FULL DETAILS AND SHORT DETAILS-----------------------------------------------------------------


    private void updateCompetitorTable() {

        List<List<Object>> data = new ArrayList<>();
        for (RMDCompetitor details : competitorList.getAllCompetitors()) {
            String[] detailsArray = details.getFullDetails().split("\n");
            List<Object> rowData = Arrays.asList((Object[]) detailsArray);
            data.add(rowData);
        }


        List<String> columnIdentifiers = new ArrayList<>();
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            columnIdentifiers.add(tableModel.getColumnName(i));
        }

        // Clear existing rows
        tableModel.setRowCount(0);


        for (List<Object> rowData : data) {
            tableModel.addRow(rowData.toArray());
        }


        tableModel.fireTableDataChanged();
    }





    //CATEGORY TABLE -----------------------------------------------------------------------------------------------
    private void createCompetitorsTable(JPanel panel) {
        List<String> headers = List.of("Competitor", "Name", "Age", "Email", "Date of Birth", "Category", "Level", "Scores");
        List<List<Object>> data = new ArrayList<>();
        this.competitorTable = new JTable(tableModel);




        manager.readDataFromFile("RunSwimComp.csv", competitorList);

        for (RMDCompetitor competitor : competitorList.getAllCompetitors()) {
            List<Object> rowData = new ArrayList<>(Arrays.asList(
                    competitor.getUniqueID(),
                    competitor.getName(),
                    competitor.getAge(),
                    competitor.getEmail(),
                    competitor.getDOB(),
                    competitor.getCategory(),
                    competitor.getLevel(),
                    Arrays.toString(competitor.getScoreArray())
            ));
            data.add(rowData);
        }

        // Initialize tableModel
        tableModel = new DefaultTableModel(data.stream().map(List::toArray).toArray(Object[][]::new), headers.toArray());

        JTable competitorTable = new JTable(tableModel);
        competitorTable.setBackground(new Color(40, 40, 40));
        competitorTable.setForeground(new Color(230, 230, 230));

        JScrollPane scrollPane = new JScrollPane(competitorTable);
        scrollPane.getViewport().setBackground(new Color(40, 40, 40));

        JScrollPane scrollP = new JScrollPane(this.competitorTable);
        scrollPane.getViewport().setBackground(new Color(40, 40, 40));


        panel.add(scrollPane, BorderLayout.CENTER);


        JButton filterButton = new JButton("Filter by Category");
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String category = JOptionPane.showInputDialog(panel, "Enter Category (Amateur or Professional):");

                if (category != null && (category.equalsIgnoreCase("Amateur") || category.equalsIgnoreCase("Professional"))) {

                    filterTableByCategory(category);
                } else {
                    JOptionPane.showMessageDialog(panel, "Invalid Category", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton showAllButton = new JButton("Show All Competitors (REFRESH BUTTON)");
        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display all competitors
                showAllCompetitors();
            }
        });


        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(filterButton);
        buttonPanel.add(showAllButton);


        panel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void filterTableByCategory(String category) {
        // Clear existing rows
        tableModel.setRowCount(0);


        for (RMDCompetitor competitor : competitorList.getAllCompetitors()) {
            if (category.isEmpty() || competitor.getCategory().equalsIgnoreCase(category)
                    || (category.equalsIgnoreCase("Professional") && competitor.getLevel().equalsIgnoreCase("Professional"))
                    || (category.equalsIgnoreCase("Amateur") && competitor.getLevel().equalsIgnoreCase("Amateur"))) {
                List<Object> rowData = new ArrayList<>(Arrays.asList(
                        competitor.getUniqueID(),
                        competitor.getName(),
                        competitor.getAge(),
                        competitor.getEmail(),
                        competitor.getDOB(),
                        competitor.getCategory(),
                        competitor.getLevel(),
                        Arrays.toString(competitor.getScoreArray())
                ));
                tableModel.addRow(rowData.toArray());
            }
        }
    }

    private void showAllCompetitors() {
        // Clear existing rows
        tableModel.setRowCount(0);

        // Add rows for all competitors
        for (RMDCompetitor competitor : competitorList.getAllCompetitors()) {
            List<Object> rowData = new ArrayList<>(Arrays.asList(
                    competitor.getUniqueID(),
                    competitor.getName(),
                    competitor.getAge(),
                    competitor.getEmail(),
                    competitor.getDOB(),
                    competitor.getCategory(),
                    competitor.getLevel(),
                    Arrays.toString(competitor.getScoreArray())
            ));
            tableModel.addRow(rowData.toArray());
        }
    }
    //CATEGORY END---------------------------------------------------------------------------------


    private void createAmendAndViewPanel(JPanel panel3) {
        JPanel searchPanel = new JPanel();
        JLabel searchLabel = new JLabel("Enter Competitor Number:");
        JTextField searchField = new JTextField(10);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(3, 1));

        JButton alterScoresButton = new JButton("Alter Scores");
        JButton editDetailsButton = new JButton("Edit Competitor Details");
        JButton removeCompetitorButton = new JButton("Remove Competitor");

        actionPanel.add(alterScoresButton);
        actionPanel.add(editDetailsButton);
        actionPanel.add(removeCompetitorButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);

        // Add components to the panel
        panel3.add(searchPanel, BorderLayout.NORTH);
        panel3.add(actionPanel, BorderLayout.WEST);
        panel3.add(resultScrollPane, BorderLayout.CENTER);

        // Action listeners...
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int competitorNumber = Integer.parseInt(searchField.getText());
                RMDCompetitor competitor = competitorList.getCompetitorByNumber(competitorNumber);

                if (competitor != null) {
                    resultArea.setText(competitor.getFullDetails());

                    alterScoresButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Call a method to handle Alter Scores
                            handleAlterScores(competitor, resultArea);
                        }
                    });

                    editDetailsButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Call a method to handle Edit Competitor Details
                            handleEditDetails(competitor, resultArea);
                        }
                    });

                    removeCompetitorButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Call a method to handle Remove Competitor
                            handleRemoveCompetitor(competitor, resultArea);
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(panel3, "Competitor not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


    private void handleAlterScores(RMDCompetitor competitor, JTextArea resultArea) {

        String newScoresString = JOptionPane.showInputDialog(this, "Enter new scores (comma-separated):");

        if (newScoresString != null) {
            try {
                // Parse the new scores
                String[] newScoresArray = newScoresString.split(",");
                int[] newScores = new int[newScoresArray.length];
                for (int i = 0; i < newScoresArray.length; i++) {
                    newScores[i] = Integer.parseInt(newScoresArray[i].trim());
                }

                // Update the competitor's scores
                competitor.setScoreArray(newScores);

                // Display a message in resultArea to inform the user
                resultArea.setText("Scores altered successfully.");

                // Refresh the table
                updateCompetitorTable(); // Refresh the table
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid scores.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void handleEditDetails(RMDCompetitor competitor, JTextArea resultArea) {
        // Open a dialog window for editing details
        openEditDetailsWindow(competitor, resultArea);

    }


    private void openEditDetailsWindow(RMDCompetitor competitor, JTextArea resultArea) {
        // Create a dialog window
        JDialog editDetailsDialog = new JDialog(this, "Edit Competitor Details", true);
        editDetailsDialog.setSize(400, 300);
        editDetailsDialog.setLayout(new BorderLayout());

        // Create components for the edit details window
        JPanel editDetailsPanel = new JPanel();
        editDetailsPanel.setLayout(new GridLayout(8, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(competitor.getName());

        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField(String.valueOf(competitor.getAge()));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(competitor.getEmail());

        JLabel dobLabel = new JLabel("Date of Birth:");
        JTextField dobField = new JTextField(competitor.getDOB());

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField(competitor.getCategory());

        JLabel levelLabel = new JLabel("Level:");
        JTextField levelField = new JTextField(competitor.getLevel());

        JLabel scoresLabel = new JLabel("Scores:");
        JTextField scoresField = new JTextField(Arrays.toString(competitor.getScoreArray()));

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        editDetailsPanel.add(nameLabel);
        editDetailsPanel.add(nameField);
        editDetailsPanel.add(ageLabel);
        editDetailsPanel.add(ageField);
        editDetailsPanel.add(emailLabel);
        editDetailsPanel.add(emailField);
        editDetailsPanel.add(dobLabel);
        editDetailsPanel.add(dobField);
        editDetailsPanel.add(categoryLabel);
        editDetailsPanel.add(categoryField);
        editDetailsPanel.add(levelLabel);
        editDetailsPanel.add(levelField);
        editDetailsPanel.add(scoresLabel);
        editDetailsPanel.add(scoresField);
        editDetailsPanel.add(saveButton);
        editDetailsPanel.add(cancelButton);

        // Add the edit details panel to the dialog
        editDetailsDialog.add(editDetailsPanel, BorderLayout.CENTER);

        // Action listener for the Save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the competitor object with new details
                competitor.setName(nameField.getText());
                competitor.setAge(Integer.parseInt(ageField.getText()));
                competitor.setEmail(emailField.getText());
                competitor.setDOB(dobField.getText());
                competitor.setCategory(categoryField.getText());
                competitor.setLevel(levelField.getText());


                // Close the dialog
                editDetailsDialog.dispose();

                // Display a message in resultArea to inform the user
                resultArea.setText("Details edited successfully.");
                updateCompetitorTable(); // Refresh the table
            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the dialog without saving changes
                editDetailsDialog.dispose();
            }
        });


        editDetailsDialog.setLocationRelativeTo(this);

        // Make the dialog visible
        editDetailsDialog.setVisible(true);
    }

    private void handleRemoveCompetitor(RMDCompetitor competitor, JTextArea resultArea) {
        // Ask for confirmation before removing the competitor
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this competitor?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {

            competitorList.removeCompetitor(competitor);


            resultArea.setText("Competitor removed successfully.");


            updateCompetitorTable();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                competitorList competitorList = new competitorList();
                manager.readDataFromFile("RunSwimComp.csv", competitorList);
                officialGUI gui = new officialGUI(competitorList);
                gui.setVisible(true);
            }
        });
    }
}

