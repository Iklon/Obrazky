package sample;

import java.io.*;

public class Output {

    private FileWriter writer;
    private FileReader reader;
    private BufferedWriter bufferedwriter;
    private BufferedReader bufferedreader;
    private File temp;

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
                if (bufferedwriter != null) bufferedwriter.close(); if (writer != null) writer.close();
            } catch (IOException exc) {exc.printStackTrace();}
        }
    }

    public void deleteImg(File image) {
        String line;
        try {
            temp = new File(image.getParentFile().toString() + "\\infotemp.txt");
            writer = new FileWriter(temp);
            bufferedwriter = new BufferedWriter(writer);
        }catch (IOException exc) {
            exc.printStackTrace();
        }
        try {
            reader = new FileReader(image.getParentFile().toString() + "\\info.txt");
            bufferedreader = new BufferedReader(reader);
            while ((line = bufferedreader.readLine()) != null) {
                if(!(line.contains(image.getName().toString()))) {
                    bufferedwriter.write(line);
                    bufferedwriter.newLine();
                }
            }
        }catch (IOException exc) {
            exc.printStackTrace();
        }
        try {
            if (bufferedwriter != null) bufferedwriter.close(); if (writer != null) writer.close();
            if (bufferedreader != null) bufferedreader.close(); if (reader != null) reader.close();
        } catch (IOException exc) {exc.printStackTrace();}
        File info = new File(image.getParentFile().toString() + "\\info.txt");
        info.delete();
        System.out.println("Odstranění záznamu:" + image.getName() + temp.renameTo(info));
    }
}
