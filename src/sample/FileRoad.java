package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by marekseda on 08.01.18.
 */
public class FileRoad {
    private Line left;
    private Line right;
    private File img;
    private int counter;
    private Output output;

    public FileRoad(File img) {
        this.img = img;
        counter = 0;
        left = new Line();
        right = new Line();
        output = new Output(img);
    }

    public Line getLeft() {
        return left;
    }

    public void setLeft(Line left) {
        this.left = left;
    }

    public Line getRight() {
        return right;
    }

    public void setRight(Line right) {
        this.right = right;
    }

    public File getImg() {
        return img;
    }

    public void setImg(File img) {
        this.img = img;
    }

    public void addPoint(Point p) {
        if(counter < 4) {
            switch (counter) {
                case 0:
                    left.setTop(p);
                    break;
                case 1:
                    left.setBottom(p);
                    break;
                case 2:
                    right.setTop(p);
                    break;
                case 3:
                    right.setBottom(p);
                    break;
            }
        }
    }

    public void draw(GraphicsContext context) {
        try {
            Image image = new Image(new FileInputStream(img));
            context.drawImage(image, 0, 0, image.getWidth(), image.getHeight());
            if(left.getTop() != null)
                context.fillOval(left.getTop().getX(), left.getTop().getY(), 8, 8);
            if(left.getBottom() != null)
                context.fillOval(left.getBottom().getX(), left.getBottom().getY(), 8, 8);
            if(right.getTop() != null)
                context.fillOval(right.getTop().getX(), right.getTop().getY(), 8, 8);
            if(right.getBottom() != null)
                context.fillOval(right.getBottom().getX(), right.getBottom().getY(), 8, 8);
            if(left.getBottom() != null && left.getTop() != null)
                context.strokeLine(left.getTop().getX()+4, left.getTop().getY()+4, left.getBottom().getX()+4, left.getBottom().getY()+4);
            if(right.getBottom() != null && right.getTop() != null) {
                context.strokeLine(right.getTop().getX() + 4, right.getTop().getY() + 4, right.getBottom().getX() + 4, right.getBottom().getY() + 4);
                output.writeImg(left, right);
            }
        }catch (FileNotFoundException exc) {
            exc.printStackTrace();
        }
    }

    public void onClick(double x, double y) {
        if(counter < 4){
            addPoint(new Point(x, y));
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Chyba výběru bodů");
            alert.setContentText("Vybrali jste více než 4 body");
            alert.show();
        }
        counter++;
    }
}
