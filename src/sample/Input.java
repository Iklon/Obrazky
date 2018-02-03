package sample;

import javafx.scene.control.Alert;

import java.io.*;

public class Input {

    private FileReader reader;
    private BufferedReader bufferedreader;
    private  File info;


    public Input(File dir) {
        info = new File(dir.getPath().toString() + "\\info.txt");
        if(info.exists()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info soubor");
            alert.setContentText("Nalezen info soubor, naváže se na jeho poslední zápis");
            alert.show();
        }
        else {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Info soubor");
            alert2.setContentText("Nenalezen info soubor, bude vytvořen");
            alert2.show();
            try {
                info.createNewFile();
            } catch (IOException exc) {exc.printStackTrace();}
        }

        try {
            reader = new FileReader(info);
            bufferedreader = new BufferedReader(reader);
        } catch (FileNotFoundException exc) {exc.printStackTrace();}
    }

    public FileRoad readImg() {
        String line = null;
        Line left = new Line();
        Line right = new Line();
        try {
            line = bufferedreader.readLine();
        } catch (IOException exc) {exc.printStackTrace();}
        if (line != null) {
            String points [] = line.split("\\[");
            left.setTop(new Point((double)Float.valueOf(points[1].substring(0, points[1].indexOf(","))), (double)Float.valueOf(points[1].substring(points[1].indexOf(",")+1, points[1].indexOf("]")))));
            left.setBottom(new Point((double)Float.valueOf(points[2].substring(0, points[2].indexOf(","))), (double)Float.valueOf(points[2].substring(points[2].indexOf(",")+1, points[2].indexOf("]")))));
            right.setTop(new Point((double)Float.valueOf(points[3].substring(0, points[3].indexOf(","))), (double)Float.valueOf(points[3].substring(points[3].indexOf(",")+1, points[3].indexOf("]")))));
            right.setBottom(new Point((double)Float.valueOf(points[4].substring(0, points[4].indexOf(","))), (double)Float.valueOf(points[4].substring(points[4].indexOf(",")+1, points[4].indexOf("]")))));

            FileRoad fileroad = new FileRoad(new File(info.getParentFile().getPath().toString() + "\\" + line.substring(0, 11)));
            fileroad.setCounter(4);
            fileroad.setLeft(left);
            fileroad.setRight(right);
            fileroad.setWritten(true);
            return fileroad;
        }
        else return null;
    }

    public void close() {
        try {
            if (bufferedreader != null) bufferedreader.close();
            if (reader != null) reader.close();
        } catch (IOException exc) {exc.printStackTrace();}
    }
}
