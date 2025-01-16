import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {


    public GUI(Rainbow[] colorList) {
        setTitle("Color Picker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new BorderLayout());        

        // add color objects to window
        DefaultListModel<Rainbow> options = new DefaultListModel<>();
        for (Rainbow color : colorList) {
            options.addElement(color);
        }
        JList<Rainbow> colorJList = new JList<>(options);
        colorJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(colorJList), BorderLayout.EAST);

        setVisible(true);
    }
}