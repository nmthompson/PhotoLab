import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImagingOpException;

/**
 * Created by Nick on 10/2/15.
 */
public class PhotoApp extends JFrame {

    public PhotoApp(){

        Container contentPane = getContentPane(); //set contentPane for container
        contentPane.setPreferredSize(new Dimension(700, 700));
        PhotoHandler handler = new PhotoHandler();

        //panel to house images
        JLabel imageLabel = new JLabel("", SwingConstants.CENTER);
        JScrollPane scrollPane = new JScrollPane(imageLabel);
        scrollPane.setPreferredSize(new Dimension(500,500));

        ImageIcon icon = new ImageIcon("Images/starwars.jpg");
        imageLabel.setIcon(icon);


        //panel to house the image information
        JPanel imgDescPanel = new JPanel();

        JLabel description = new JLabel("Description:");
        JTextArea descArea = new JTextArea(4,20);

        imgDescPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        imgDescPanel.add(description);
        imgDescPanel.add(descArea);


        //Panel configuration to handle navigation buttons
        JPanel navPanel = new JPanel();

        JButton prevBtn = new JButton("Previous");
        prevBtn.addActionListener(new PhotoEventHandler());
        JButton nextBtn = new JButton("Next");
        prevBtn.addActionListener(new PhotoEventHandler());

        navPanel.add(prevBtn);
        navPanel.add(nextBtn);

        //Add panels to Container and set layout
        contentPane.add(scrollPane, BorderLayout.NORTH);
        contentPane.add(imgDescPanel, BorderLayout.CENTER);
        contentPane.add(navPanel, BorderLayout.SOUTH);


    }

    public static void main(String[] args){
        JFrame frame = new PhotoApp();
        frame.setVisible(true);
        frame.addWindowListener(new PhotoEventHandler());
        frame.pack();
    }


}
//Class to handle all PhotoApp button events
class PhotoEventHandler implements ActionListener, WindowListener {
    public PhotoEventHandler(){

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        System.exit(0);
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {}

    @Override
    public void windowClosed(WindowEvent windowEvent) {}

    @Override
    public void windowIconified(WindowEvent windowEvent) {}

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {}

    @Override
    public void windowActivated(WindowEvent windowEvent) {}

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {}
}