package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    private File directory;

    public Controller() {
        roads = new ArrayList<>();
    }

    @FXML
    private Canvas canvas;

    @FXML
    private Label label;

    @FXML
    private void loadDir() {
        picture= 0;
        DirectoryChooser chooser = new DirectoryChooser();
        directory = chooser.showDialog(null);
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
        try {
            canvas.setHeight(new Image(new FileInputStream(roads.get(picture).getImg())).getHeight());
            canvas.setWidth(new Image(new FileInputStream(roads.get(picture).getImg())).getWidth());
        }catch (FileNotFoundException exc) {
            exc.printStackTrace();
        }
        roads.get(picture).draw(context);
        canvas.setOnMouseClicked(event -> roads.get(picture).onClick(event.getX(), event.getY()));
        label.setText(roads.get(picture).getImg().toString().replace(directory.toString()+"\\", ""));

    }
}
