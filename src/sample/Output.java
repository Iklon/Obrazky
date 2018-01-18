package sample;

import java.io.*;

public class Output {

    private FileWriter writer;
    private BufferedWriter bufferedwriter;

    public Output() {
    }

    public void writeImgInfo(Line left, Line right, File file) {
        try {
            writer = new FileWriter(file.getParentFile().toString() + "\\info.txt", true);
            bufferedwriter = new BufferedWriter(writer);
        }catch (IOException exc) {
            exc.printStackTrace();
        }
        System.out.println("zapis - " + file.getName());
        try {
            bufferedwriter.write(file.getName() + " [" + left.getTop().getX() + "," + left.getTop().getY()+ "]" + " [" + left.getBottom().getX() + "," + left.getBottom().getY()+ "]" +
                    " [" + right.getTop().getX() + "," + right.getTop().getY()+ "]" + " [" + right.getBottom().getX() + "," + right.getBottom().getY()+ "]");
            bufferedwriter.newLine();
        } catch (IOException exc){exc.printStackTrace(); }
        finally {
            try {
                if (bufferedwriter != null) bufferedwriter.close();
                if (writer != null) writer.close();
            } catch (IOException exc) {exc.printStackTrace();}
        }
    }
}
