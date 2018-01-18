package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
    private boolean last=false;
    private Output output;

    public Controller() {
        roads = new ArrayList<>();
        output = new Output();
    }

    @FXML
    private Canvas canvas;

    @FXML
    private Label label;

    @FXML
    private void loadDir() {
        DirectoryChooser chooser = new DirectoryChooser();
        directory = chooser.showDialog(null);
        if(directory != null) {
            Input input = new Input(directory);
            picture = input.readImgInfo();
            roads = Arrays.stream(directory.listFiles()).map(FileRoad::new).collect(Collectors.toList());
            drawImage();
        }
    }

    @FXML
    private void nextImg() {
        if (!roads.get(picture).getWritten() && roads.get(picture).getRight().getBottom() != null) {
            output.writeImgInfo(roads.get(picture).getLeft(), roads.get(picture).getRight(), roads.get(picture).getImg());
            roads.get(picture).setWritten();
        }
        picture = roads.size() > (picture + 1) ? picture + 1 : 0;
        drawImage();
        last=false;
    }

    @FXML
    private void previousImg() {
        picture --;
        if (picture == -1) picture = 0;
        last=true;
        drawImage();
    }

    @FXML
    private void canvasClicked(MouseEvent mouse) {
        roads.get(picture).onClick(mouse.getX(), mouse.getY());
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
        roads.get(picture).draw(context, last);
        label.setText(roads.get(picture).getImg().toString().replace(directory.toString()+"\\", ""));
    }
}
