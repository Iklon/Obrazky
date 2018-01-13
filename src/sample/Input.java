package sample;

import javafx.scene.control.Alert;

import java.io.*;

public class Input {

    private FileReader reader;
    private BufferedReader bufferedreader;


    public Input(File dir) {
        File info = new File(dir.getPath().toString() + "\\info.txt");
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
        String newline, lastline;
        int count=0;
        try {
            while ((lastline = bufferedreader.readLine()) != null){
                if ((newline = bufferedreader.readLine()) == null) {
                    break;
                }
            }
            if (lastline == null) count = 0;
            else count = Integer.parseInt(lastline.substring(3, 7));
        } catch (IOException exc) {exc.printStackTrace(); count = 0; System.out.println("nacteni exc1");}
        finally {
            try {
                if (bufferedreader != null) bufferedreader.close();
                if (reader != null) reader.close();
            } catch (IOException exc) {exc.printStackTrace(); System.out.println("nacteni exc2");}
        }
        return count;
    }
}
