package sample;

import java.io.*;

public class Output {

    private File file;
    private FileWriter writer;
    private BufferedWriter bufferedwriter;
    private int lines;

    public Output(File file) {
        this.file = file;
        try {
            writer = new FileWriter(file.getParentFile().toString() + "\\info.txt", true);
            bufferedwriter = new BufferedWriter(writer);
        }catch (IOException exc) {
            exc.printStackTrace();
        }
        lines=0;
    }

    public void writeImg(Line left, Line right) {
        System.out.println(file.getParentFile().toString());
        System.out.println(file.getName());
        try {
            bufferedwriter.write(file.getName() + " [" + left.getTop().getX() + "," + left.getTop().getY()+ "]" + " [" + left.getBottom().getX() + "," + left.getBottom().getY()+ "]" +
                    " [" + right.getTop().getX() + "," + right.getTop().getY()+ "]" + " [" + right.getBottom().getX() + "," + right.getBottom().getY()+ "]",
                    lines*67, 67);
            bufferedwriter.newLine();
        }catch (IOException exc) {}
        finally {
            try {
                if (bufferedwriter != null)
                    bufferedwriter.close();
                if (writer != null)
                    writer.close();
            } catch (IOException ex) {ex.printStackTrace();}
        }
    }
}
