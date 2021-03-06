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
        picture = 0;
        DirectoryChooser chooser = new DirectoryChooser();
        directory = chooser.showDialog(null);
        if(directory != null) {
            Input input = new Input(directory);
            roads = Arrays.stream(directory.listFiles()).map(FileRoad::new).collect(Collectors.toList());
            FileRoad fileroad;
            while((fileroad = input.readImg()) != null) {
                roads.set(Integer.parseInt(fileroad.getImg().getName().toString().substring(3,7))-1, fileroad);
                picture = Integer.parseInt(fileroad.getImg().getName().toString().substring(3,7))-1;
                System.out.println(picture);
            }
            input.close();

            drawImage();
        }
    }

    @FXML
    private void nextImg() {
        if (!roads.get(picture).getWritten() && roads.get(picture).getRight().getBottom() != null) {
            output.writeImg(roads.get(picture).getLeft(), roads.get(picture).getRight(), roads.get(picture).getImg());
            roads.get(picture).setWritten(true);
        }
        if (roads.get(picture).getLeft().getTop() != null) {
            if (roads.get(picture).getRight().getBottom() == null ) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Uložení obrázku");
                alert.setContentText("Nebyly vybrány 4 body - záznam nebyl uložen");
                alert.show();
            }
        }
        picture = roads.size() > (picture + 1) ? picture + 1 : 0;
        drawImage();
    }

    @FXML
    private void previousImg() {
        picture --;
        if (picture == -1) picture = 0;
        drawImage();
    }

    @FXML
    private void deletePoints() {
        roads.get(picture).delete();
        output.deleteImg(roads.get(picture).getImg());
        roads.get(picture).setWritten(false);
        drawImage();
    }

    @FXML
    private void canvasClicked(MouseEvent mouse) {
        roads.get(picture).click(mouse.getX(), mouse.getY());
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
        label.setText(roads.get(picture).getImg().toString().replace(directory.toString()+"\\", ""));
    }
}
