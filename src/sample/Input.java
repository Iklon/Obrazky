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
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }

        try {
            reader = new FileReader(info);
            bufferedreader = new BufferedReader(reader);
        } catch (FileNotFoundException exc) {
            exc.printStackTrace();
        }
    }

    public int readImgInfo() {
        String newline=null, lastline;
        int count=0;
        try {
            while ((lastline = bufferedreader.readLine()) != null && lastline.length() != 0){
                newline = bufferedreader.readLine();
                if (newline == null) {
                    break;
                }
            }
            if (lastline == null) {
                if (newline == null) count = 0;
                else count = Integer.parseInt(newline.substring(3, 7));
            }
            else count = Integer.parseInt(lastline.substring(3, 7));
        } catch (IOException exc) {exc.printStackTrace();}
        try {
            if (bufferedreader != null) bufferedreader.close();
            if (reader != null) reader.close();
        } catch (IOException exc) {exc.printStackTrace();}
        return count;
    }

    public FileRoad readLine() {
        String line = null;
        try {
            line = bufferedreader.readLine();
        } catch (IOException exc) {exc.printStackTrace();}
        if (line != null) {
            File img = new File(info.getParentFile().getPath().toString() + "\\" + line.substring(0, 11));
            Line left = new Line();
            Line right = new Line();
            left.setTop(new Point(100,100));
            left.setBottom(new Point(100,200));
            right.setTop(new Point(200,100));
            right.setBottom(new Point(200,200));

            FileRoad fileroad = new FileRoad(img);
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
