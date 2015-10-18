import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Nick on 10/2/15.
 */
public class Photo implements Serializable{

    private String date;
    private String description;
    private ImageIcon image;

    public Photo() {

    }

    public void setImage(String filename) {
        image = new ImageIcon(filename);
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setDescription(String desc) {
        description = desc;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(String dt) {
        date = dt;
    }

    public String getDate() {
        return date;
    }
}

class PhotoDBHandler implements Serializable{

    private static final long serialVersionUID = -2066891379292164622L;


    ArrayList<Photo> photoList = new ArrayList<Photo>();

    PhotoDBHandler(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("photos.ser"));
            oos.writeObject(photoList);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(("photos.ser")));
            photoList = (ArrayList<Photo>)ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Photo getCurrPhoto(int k){
        return photoList.get(k);

    }
    public int getNumPhotos(){

        return photoList.size();
    }
    public void addPhoto(ImageIcon newPhoto){
        Photo img = new Photo();
        img.setImage(newPhoto.toString());
        img.setDate("Enter Date...");
        img.setDescription("Enter Description...");
        photoList.add(img);
    }

    public void deletePhoto(int k){
        photoList.remove(k);
    }
    public void saveAll() throws IOException{
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("photos.ser"));
            oos.writeObject(photoList);
            oos.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }



}