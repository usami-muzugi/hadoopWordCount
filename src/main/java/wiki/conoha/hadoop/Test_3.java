package wiki.conoha.hadoop;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: wzard
 * Date: 2018-02-09
 * Time: 15:08
 * ProjectName: hadoopWordCount
 * To change this template use File | Settings | File Templates.
 **/

public class Test_3 {
    private static String[][] stock;
    private static BufferedInputStream bufferedReader;

    public Test_3(String fileName) {
        File file = new File(fileName);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            Test_3.bufferedReader = bufferedInputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static String[][] getStock() {
        return stock;
    }

    public static void setStock(String[][] stock) {
        Test_3.stock = stock;
    }

    public static String[][] count() {

    }
    public static void main(String[] args) {
        Test_3 test_3 = new Test_3("");
    }
}
