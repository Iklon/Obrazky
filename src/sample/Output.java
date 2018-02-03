package sample;

import java.io.*;

public class Output {

    private FileWriter writer;
    private FileReader reader;
    private BufferedWriter bufferedwriter;
    private BufferedReader bufferedreader;
    private File temp;
    private File info;

    public void writeImgInfo(Line left, Line right, File file) {
        try {
            writer = new FileWriter(file.getParentFile().toString() + "\\info.txt", true);
            bufferedwriter = new BufferedWriter(writer);
        }catch (IOException exc) {exc.printStackTrace();}
        System.out.println("zapis - " + file.getName());
        try {
            bufferedwriter.write(file.getName() + " [" + left.getTop().getX() + "," + left.getTop().getY()+ "]" + " [" + left.getBottom().getX() + "," + left.getBottom().getY()+ "]" +
                    " [" + right.getTop().getX() + "," + right.getTop().getY()+ "]" + " [" + right.getBottom().getX() + "," + right.getBottom().getY()+ "]");
            bufferedwriter.newLine();
        } catch (IOException exc){exc.printStackTrace(); }
        try {
            if (bufferedwriter != null) bufferedwriter.close(); if (writer != null) writer.close();
        } catch (IOException exc) {exc.printStackTrace();}
}

    public void writeImg(Line left, Line right, File file) {
        int next_img_position;
        boolean flag = false;
        String line;
        int img_position = Integer.parseInt(file.getName().toString().substring(3, 7));
        try {
            temp = new File(file.getParentFile().toString() + "\\infotemp.txt");
            writer = new FileWriter(temp);
            bufferedwriter = new BufferedWriter(writer);
        }catch (IOException exc) {exc.printStackTrace();}
        try {
            reader = new FileReader(file.getParentFile().toString() + "\\info.txt");
            bufferedreader = new BufferedReader(reader);
            if((line = bufferedreader.readLine()) == null) {
                System.out.println("Noline if");
                bufferedwriter.write(file.getName() + " [" + left.getTop().getX() + "," + left.getTop().getY() + "]" + " [" + left.getBottom().getX() + "," + left.getBottom().getY() + "]" +
                        " [" + right.getTop().getX() + "," + right.getTop().getY() + "]" + " [" + right.getBottom().getX() + "," + right.getBottom().getY() + "]");
                bufferedwriter.newLine();
            }else {
                do {
                    next_img_position = Integer.parseInt(line.substring(3, 7));
                    System.out.println("Další obrazek: " + next_img_position);
                    if (img_position < next_img_position) {
                        flag = true;
                        System.out.println("While if");
                        bufferedwriter.write(file.getName() + " [" + left.getTop().getX() + "," + left.getTop().getY() + "]" + " [" + left.getBottom().getX() + "," + left.getBottom().getY() + "]" +
                                " [" + right.getTop().getX() + "," + right.getTop().getY() + "]" + " [" + right.getBottom().getX() + "," + right.getBottom().getY() + "]");
                        bufferedwriter.newLine();
                        bufferedwriter.write(line);
                        bufferedwriter.newLine();
                        break;
                    } else {
                        System.out.println("While else");
                        bufferedwriter.write(line);
                        bufferedwriter.newLine();
                    }
                } while ((line = bufferedreader.readLine()) != null);
                if (!flag) {
                    bufferedwriter.write(file.getName() + " [" + left.getTop().getX() + "," + left.getTop().getY() + "]" + " [" + left.getBottom().getX() + "," + left.getBottom().getY() + "]" +
                            " [" + right.getTop().getX() + "," + right.getTop().getY() + "]" + " [" + right.getBottom().getX() + "," + right.getBottom().getY() + "]");
                    bufferedwriter.newLine();
                }
            }
        } catch (IOException exc) {exc.printStackTrace();}
        System.out.println("Zapsan obrazek: " + file.getName());
        try {
            if (bufferedwriter != null) bufferedwriter.close(); if (writer != null) writer.close();
            if (bufferedreader != null) bufferedreader.close(); if (reader != null) reader.close();
        } catch (IOException exc) {exc.printStackTrace();}
        info = new File(file.getParentFile().toString() + "\\info.txt");
        info.delete();
        System.out.println("Odstranění temp souboru: " + temp.renameTo(info));
    }

    public void deleteImg(File image) {
        String line;
        try {
            temp = new File(image.getParentFile().toString() + "\\infotemp.txt");
            writer = new FileWriter(temp);
            bufferedwriter = new BufferedWriter(writer);
        }catch (IOException exc) {exc.printStackTrace();}
        try {
            reader = new FileReader(image.getParentFile().toString() + "\\info.txt");
            bufferedreader = new BufferedReader(reader);
            while ((line = bufferedreader.readLine()) != null) {
                if(!(line.contains(image.getName().toString()))) {
                    bufferedwriter.write(line);
                    bufferedwriter.newLine();
                }
            }
        }catch (IOException exc) {exc.printStackTrace();}
        try {
            if (bufferedwriter != null) bufferedwriter.close(); if (writer != null) writer.close();
            if (bufferedreader != null) bufferedreader.close(); if (reader != null) reader.close();
        } catch (IOException exc) {exc.printStackTrace();}
        info = new File(image.getParentFile().toString() + "\\info.txt");
        info.delete();
        System.out.println("Odstraněn obrázek: " + image.getName() + temp.renameTo(info));
    }
}
