package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class Controller {
    private int picture=0;
    private File[] files;
    @FXML
    private Canvas canvas;
    @FXML
    private ImageView imageview;
    @FXML
    private Label label;
    @FXML
    private void loadDir() {
        DirectoryChooser chooser = new DirectoryChooser();
        File directory = chooser.showDialog(null);
        files = directory.listFiles();

        //zobrazit obrazek
        System.out.println(files[picture].getPath());
        label.setText(files[picture].getPath());
        Image image = new Image(files[picture].getPath());
        imageview.setImage(image);
    }
    @FXML
    private void nextImg() {
        picture++;
        //zobrazit obrázek
    }
}
