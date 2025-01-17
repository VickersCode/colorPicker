import javax.swing.*;
import java.awt.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

    private Rainbow currentColor;
    private JTextField redField, greenField, blueField;
    private JPanel colorSample;
    private boolean isModified = false;

    public GUI(Rainbow[] colorList) {
        setTitle("Color Picker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new BorderLayout());

        // Color list in EAST
        DefaultListModel<Rainbow> options = new DefaultListModel<>();
        for (Rainbow color : colorList) {
            options.addElement(color);
        }
        JList<Rainbow> colorJList = new JList<>(options);
        colorJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(colorJList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(150, 0)); // Width fixed, height 0 means grow to fill available space
        add(scrollPane, BorderLayout.EAST);

        colorJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    updateColor(colorJList.getSelectedValue());
                }
            }
        });

        // Color sample in NORTH
        colorSample = new JPanel();
        colorSample.setPreferredSize(new Dimension(300, 200)); 
        colorSample.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(colorSample, BorderLayout.NORTH);

        // RGB components in CENTER
        JPanel rgbPanel = new JPanel(new GridLayout(3, 1));
        rgbPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        redField = addLabelAndFieldAndButtons(rgbPanel, "Red:", 0);
        greenField = addLabelAndFieldAndButtons(rgbPanel, "Green:", 1);
        blueField = addLabelAndFieldAndButtons(rgbPanel, "Blue:", 2);
        add(rgbPanel, BorderLayout.CENTER);

        // Buttons in SOUTH
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveColor());
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetColor());
        buttonPanel.add(saveButton);
        buttonPanel.add(resetButton);
        
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JTextField addLabelAndFieldAndButtons(JPanel panel, String labelText, int index) {
        panel.add(new JLabel(labelText));
        JTextField textField = new JTextField(5);
        textField.setEditable(false);
    
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    
        JButton minus = new JButton("-");
        minus.addActionListener(e -> adjustColor(textField, index, -5));
        JButton plus = new JButton("+");
        plus.addActionListener(e -> adjustColor(textField, index, +5));
    
        controlPanel.add(minus);
        controlPanel.add(textField);
        controlPanel.add(plus);
        panel.add(controlPanel);
        return textField;
    }

    private void updateColor(Rainbow color) {
        if (color != null) {
            currentColor = color;
            isModified = false;
            updateFieldsAndSample();
            updateTitle();
        }
    }

    private void updateFieldsAndSample() {
        int[] rgb = currentColor.getRGB();
        redField.setText(String.valueOf(rgb[0]));
        greenField.setText(String.valueOf(rgb[1]));
        blueField.setText(String.valueOf(rgb[2]));
        colorSample.setBackground(new Color(rgb[0], rgb[1], rgb[2]));
    }

    private void adjustColor(JTextField field, int index, int delta) {
        int value = Integer.parseInt(field.getText());
        value = Math.max(0, Math.min(255, value + delta)); 
        field.setText(String.valueOf(value));
        currentColor.adjustRGB(index, delta);
        isModified = true;
        updateTitle();
        updateFieldsAndSample();
    }

    private void saveColor() {
        if (currentColor != null) {
            currentColor.saveRGB(); 
            isModified = false;
            updateTitle();
        }
    }

    private void resetColor() {
        if (currentColor != null) {
            currentColor.resetRGB(); 
            updateFieldsAndSample(); 
            isModified = false;
            updateTitle();
        }
    }
    

    private void updateTitle() {
        setTitle(isModified ? "Color Picker*" : "Color Picker");
    }
}