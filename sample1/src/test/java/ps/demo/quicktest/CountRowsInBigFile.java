package ps.demo.quicktest;

import ps.demo.common.MyFileUtil;

import java.io.*;

public class CountRowsInBigFile {

    public static void main(String[] args) {
        File file = MyFileUtil.getFileInHomeDir("2023-10-18_210654-BigFile.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            int lineCount = 0;
            String line = br.readLine();
            lineCount++;
            while (line != null) {
                line = br.readLine();
                if (line != null) {
                    lineCount++;
                }
            }
            System.out.println("File file:"+file+"\t total lines="+lineCount);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
