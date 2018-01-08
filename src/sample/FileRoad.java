package sample;

import javafx.scene.canvas.GraphicsContext;
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

    public FileRoad(File img) {
        this.img = img;
        counter = 0;
        left = new Line();
        right = new Line();
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
            counter++;
            switch (counter){
                case 1:
                    left.setTop(p);
                    break;
                case 2:
                    left.setBottom(p);
                    break;
                case 3:
                    right.setTop(p);
                    break;
                case 4:
                    right.setBottom(p);
                    break;
            }
        }
    }

    public void draw(GraphicsContext context) {
        try {
            context.drawImage(new Image(new FileInputStream(img)), 0, 0, 200, 200);
            if(left.getTop() != null)
                context.fillOval(left.getTop().getX(), left.getTop().getY(), 5, 5);
            if(left.getBottom() != null)
                context.fillOval(left.getBottom().getX(), left.getBottom().getY(), 5, 5);
            if(right.getTop() != null)
                context.fillOval(right.getTop().getX(), right.getTop().getY(), 5, 5);
            if(right.getBottom() != null)
                context.fillOval(right.getBottom().getX(), right.getBottom().getY(), 5, 5);
            if(left.getBottom() != null && left.getTop() != null)
                context.strokeLine(left.getTop().getX(), left.getTop().getY(), left.getBottom().getX(), left.getBottom().getY());
            if(right.getBottom() != null && right.getTop() != null)
                context.strokeLine(right.getTop().getX(), right.getTop().getY(), right.getBottom().getX(), right.getBottom().getY());
        }catch (FileNotFoundException exc) {
            exc.printStackTrace();
        }
    }

    public void onClick(double x, double y) {
        if(counter < 4){
            addPoint(new Point(x, y));
        }else {
            //TODO zkontrolovat kolizi
        }
    }
}
