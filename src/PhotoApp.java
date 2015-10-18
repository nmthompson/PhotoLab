import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Nick on 10/2/15.
 */
public class PhotoApp extends JFrame implements ActionListener, Serializable, KeyListener {

    PhotoDBHandler handler = new PhotoDBHandler();
    ImageIcon image;

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu viewMenu;
    JMenuItem browse;
    JMenuItem maintain;

    JLabel imageLabel;

    JButton deleteBtn;
    JButton saveBtn;
    JButton addBtn;

    JLabel pictureCountLabel;
    JTextField pictureNumberTextField;

    JTextArea descriptionTextArea;
    JTextField dateTextField;

    JButton prevButton;
    JButton nextButton;

    boolean modify = false;

    int imgIndex = 0;
    Integer currTextNum = 1;
    public PhotoApp(){


        Container contentPane = getContentPane(); //set contentPane for container
        contentPane.setPreferredSize(new Dimension(1000, 700));


        //menu bar set up
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        viewMenu = new JMenu("View");
        browse = new JMenuItem("Browse...", KeyEvent.VK_B);
        maintain = new JMenuItem("Maintenance...", KeyEvent.VK_M);

        browse.addActionListener(this);
        viewMenu.add(browse);

        maintain.addActionListener(this);
        viewMenu.add(maintain);

        menuBar.add(fileMenu);
        menuBar.add(viewMenu);

        fileMenu.setMnemonic(KeyEvent.VK_F);
        viewMenu.setMnemonic(KeyEvent.VK_V);



        contentPane.add(menuBar, BorderLayout.NORTH);

        //image scroll pane set up
        imageLabel = new JLabel("", SwingConstants.CENTER);
        JScrollPane scrollPane = new JScrollPane(imageLabel);

        //set default image if list is empty initially
        if(handler.getNumPhotos() == 0){
            handler.addPhoto(new ImageIcon("Images/starwars.jpg"));
        }
        image = handler.getCurrPhoto(imgIndex).getImage();
        imageLabel.setIcon(image);

        contentPane.add(scrollPane, BorderLayout.CENTER);

        //control panel set up
        JPanel controlPane = new JPanel();
        controlPane.setLayout(new BoxLayout(controlPane, BoxLayout.PAGE_AXIS));

        //description panel set up
        JPanel descriptionPane = new JPanel();
        descriptionPane.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionTextArea = new JTextArea(4,20);
        descriptionTextArea.setText(handler.getCurrPhoto(imgIndex).getDescription());
        descriptionPane.add(descriptionLabel);
        descriptionPane.add(descriptionTextArea);

        //date panel set up
        JPanel datePane = new JPanel();

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setPreferredSize(new Dimension(descriptionLabel.getPreferredSize().width, dateLabel.getPreferredSize().height));
        dateTextField = new JTextField(handler.getCurrPhoto(imgIndex).getDate());
        datePane.add(dateLabel);
        datePane.add(dateTextField);

        //button panel setup for Maintenance mode
        JPanel buttonPane = new JPanel();

        buttonPane.add(deleteBtn = new JButton("Delete"));
        deleteBtn.addActionListener(this);
        buttonPane.add(saveBtn = new JButton("Save Changes"));
        saveBtn.addActionListener(this);
        buttonPane.add(addBtn = new JButton("Add Photo"));
        addBtn.addActionListener(this);
        deleteBtn.setVisible(false);
        saveBtn.setVisible(false);
        addBtn.setVisible(false);

        JPanel leftRightPane = new JPanel();
        leftRightPane.setLayout(new BorderLayout());
        leftRightPane.add(datePane,BorderLayout.WEST);
        leftRightPane.add(buttonPane,BorderLayout.EAST);

        //button panel setup for navigations
        JPanel southButtonPanel = new JPanel();
        pictureNumberTextField = new JTextField(currTextNum.toString(),4);
        pictureCountLabel = new JLabel(" of " + handler.getNumPhotos());
        prevButton = new JButton("< Prev");
        nextButton = new JButton("Next >");
        prevButton.addActionListener(this);
        nextButton.addActionListener(this);

        if(handler.getNumPhotos() == 1){
            prevButton.setEnabled(false);
            nextButton.setEnabled(false);
        }
        southButtonPanel.add(pictureNumberTextField);
        southButtonPanel.add(pictureCountLabel);
        southButtonPanel.add(prevButton);
        southButtonPanel.add(nextButton);

        FlowLayout flowLayout = (FlowLayout) southButtonPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);


        controlPane.add(descriptionPane);
        controlPane.add(leftRightPane);
        controlPane.add(southButtonPanel);


        contentPane.add(controlPane, BorderLayout.SOUTH); // Or PAGE_END

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == maintain){ //handle maintenance view action
            deleteBtn.setVisible(true);
            saveBtn.setVisible(true);
            addBtn.setVisible(true);
            modify = true;
        } else if(actionEvent.getSource() == browse){//handle browse view action
            deleteBtn.setVisible(false);
            saveBtn.setVisible(false);
            addBtn.setVisible(false);
            modify = false;
        } else if(actionEvent.getSource() == addBtn){//handle add image button actions
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(getParent());

            if(returnVal == JFileChooser.APPROVE_OPTION){

                ImageIcon addedImage = new ImageIcon(chooser.getSelectedFile().getName());
                handler.addPhoto(addedImage);
                pictureCountLabel.setText("of " + handler.getNumPhotos());

                image = handler.getCurrPhoto(imgIndex).getImage();
                imageLabel.setIcon(image);

                currTextNum = imgIndex + 1;

                pictureNumberTextField.setText(currTextNum.toString());
                dateTextField.setText(handler.getCurrPhoto(imgIndex).getDate());
                descriptionTextArea.setText(handler.getCurrPhoto(imgIndex).getDescription());
            }
        } else if(actionEvent.getSource() == prevButton){
            if(imgIndex > 0){
                imgIndex--;
                image = handler.getCurrPhoto(imgIndex).getImage();
                imageLabel.setIcon(image);
                currTextNum = imgIndex + 1;
                pictureNumberTextField.setText(currTextNum.toString());
                dateTextField.setText(handler.getCurrPhoto(imgIndex).getDate());
                descriptionTextArea.setText(handler.getCurrPhoto(imgIndex).getDescription());
            }
        } else if(actionEvent.getSource() == nextButton){
            if(imgIndex < handler.getNumPhotos() - 1){
                imgIndex++;
                image = handler.getCurrPhoto(imgIndex).getImage();
                imageLabel.setIcon(image);
                currTextNum = imgIndex+1;
                dateTextField.setText(handler.getCurrPhoto(imgIndex).getDate());
                descriptionTextArea.setText(handler.getCurrPhoto(imgIndex).getDate());
                nextButton.setEnabled(true);
                if(imgIndex == handler.getNumPhotos()){
                    nextButton.setEnabled(false);
                }
            }
        } else if(actionEvent.getSource() == deleteBtn && modify == true){
            if (imgIndex > 0 && handler.getNumPhotos() > 1) {
                handler.deletePhoto(imgIndex);
                imgIndex--;
                image = handler.getCurrPhoto(imgIndex).getImage();
                imageLabel.setIcon(image);
                currTextNum = imgIndex + 1;
                pictureNumberTextField.setText(currTextNum.toString());
                dateTextField.setText(handler.getCurrPhoto(imgIndex).getDate());
                descriptionTextArea.setText(handler.getCurrPhoto(imgIndex).getDescription());
                pictureCountLabel.setText("of " + handler.getNumPhotos());

            } else if (imgIndex == 0 && handler.getNumPhotos() > 1) {

                handler.deletePhoto(imgIndex);
                image = handler.getCurrPhoto(imgIndex).getImage();
                imageLabel.setIcon(image);
                currTextNum = imgIndex + 1;
                pictureNumberTextField.setText(currTextNum.toString());
                dateTextField.setText(handler.getCurrPhoto(imgIndex).getDate());
                descriptionTextArea.setText(handler.getCurrPhoto(imgIndex).getDescription());
                pictureCountLabel.setText("of " + handler.getNumPhotos());
            }

            else if (handler.getNumPhotos() == 1) {

                handler.deletePhoto(imgIndex);
                image = new ImageIcon();
                imageLabel.setIcon(image);
                currTextNum = imgIndex + 1;
                pictureNumberTextField.setText("0");
                dateTextField.setText("");
                descriptionTextArea.setText("");
                pictureCountLabel.setText("of 0");
            }

        } else if(actionEvent.getSource() == dateTextField && modify == true){
            handler.getCurrPhoto(imgIndex).setDate(dateTextField.getText());
        } else if(actionEvent.getSource() == saveBtn && modify == true){
            try {
                handler.saveAll();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args){
        JFrame frame = new PhotoApp();
        frame.setVisible(true);
        frame.pack();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
