import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Nick on 10/2/15.
 */
public class PhotoApp extends JFrame {

    public PhotoApp(){

        Container contentPane = getContentPane(); //set contentPane for container
        contentPane.setPreferredSize(new Dimension(500, 700));

        JPanel imgPanel = new JPanel(); //panel to house images


        //panel to house the image information
        JPanel imgDescPanel = new JPanel();

        JLabel description = new JLabel("Description:");

        imgDescPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        imgDescPanel.add(description);


        //Panel configuration to handle navigation buttons
        JPanel navPanel = new JPanel();

        JButton prevBtn = new JButton("Previous");
        prevBtn.addActionListener(new PhotoEventHandler());
        JButton nextBtn = new JButton("Next");
        prevBtn.addActionListener(new PhotoEventHandler());

        navPanel.add(prevBtn);
        navPanel.add(nextBtn);

        //Add panels to Container and set layout
        contentPane.add(imgPanel, BorderLayout.NORTH);
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