import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class LinearSearchGUI2 extends JFrame {
    private static final int BOX_COUNT = 10;
    private JLabel[] boxes = new JLabel[BOX_COUNT];
    private int[] values = new int[BOX_COUNT];
    private JTextField inputField;
    private JButton searchButton;
    private JLabel resultLabel;
    private JPanel boxesPanel;

    public LinearSearchGUI2() {
        setTitle("Linear Search Visualization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel to hold the boxes
        boxesPanel = new JPanel();
        boxesPanel.setLayout(new GridLayout(1, BOX_COUNT, 5, 5));
        boxesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize boxes with random values and purple background
        Random rand = new Random();
        for (int i = 0; i < BOX_COUNT; i++) {
            values[i] = rand.nextInt(100); // Random value between 0 and 99
            boxes[i] = new JLabel(String.valueOf(values[i]), SwingConstants.CENTER);
            boxes[i].setOpaque(true);
            boxes[i].setBackground(Color.yellow); // Purple color
            boxes[i].setPreferredSize(new Dimension(50, 50));
            boxes[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            boxesPanel.add(boxes[i]);
        }

        // Panel for input and button
        JPanel controlPanel = new JPanel();
        inputField = new JTextField(5);
        searchButton = new JButton("Search");
        controlPanel.add(new JLabel("Enter value:"));
        controlPanel.add(inputField);
        controlPanel.add(searchButton);

        // Label to display result
        resultLabel = new JLabel(" ");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add panels to the frame
        add(boxesPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);

        // Add action listener to the button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLinearSearch();
            }
        });

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void performLinearSearch() {
        // Reset all boxes to default purple color
        for (JLabel box : boxes) {
            box.setBackground(new Color(249, 100, 18)); // Purple color
        }

        String inputText = inputField.getText().trim();
        if (inputText.isEmpty()) {
            resultLabel.setText("Please enter a value.");
            return;
        }

        int target;
        try {
            target = Integer.parseInt(inputText);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter an integer.");
            return;
        }

        // Perform linear search
        boolean found = false;
        for (int i = 0; i < BOX_COUNT; i++) {
            if (values[i] == target) {
                boxes[i].setBackground(Color.green);
                resultLabel.setText("Value found at index: " + i);
                found = true;
                break;
            }
        }

        if (!found) {
            // Change all boxes to yellow
            for (JLabel box : boxes) {
                box.setBackground(Color.pink);
            }
            resultLabel.setText("Value not found.");
        }
    }

    public static void main(String[] args) {
        // Run the GUI in the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LinearSearchGUI2();
            }
        });
    }
}