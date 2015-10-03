import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Nick on 10/2/15.
 */
public class Photo {
    String description;
    String date;
    Image img;

    public Photo(){

    }

    public Photo(String description, String date, Image img){
        this.description = description;
        this.date = date;
        this.img = img;
    }
}
class PhotoHandler{

    ArrayList<Photo> photoList = new ArrayList<Photo>();

    public void getPhoto(){
    }
    public int getNumPhotos(){

        return photoList.size();
    }
    public void addPhoto(Photo newPhoto){
        photoList.add(newPhoto);
    }
    public void deletePhoto(Photo photo){
        photoList.remove(photo);
    }

    //getPhoto
    //getNumPhotos
    //addPhoto
    //deletePhoto
    //saveAll?


}