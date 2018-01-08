package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {

    private int picture=0;
    private List<FileRoad> roads;

    public Controller() {
        roads = new ArrayList<>();
    }

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
        if(directory != null) {
            roads = Arrays.stream(directory.listFiles()).map(FileRoad::new).collect(Collectors.toList());
            drawImage();
        }
    }
    @FXML
    private void nextImg() {
        picture = roads.size() > (picture + 1) ? picture + 1 : 0;
        drawImage();
    }

    private void drawImage() {
        GraphicsContext context = canvas.getGraphicsContext2D();
        roads.get(picture).draw(context);
        canvas.setOnMouseClicked(event -> roads.get(picture).onClick(event.getX(), event.getY()));
    }
}
