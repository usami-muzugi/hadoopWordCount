package wiki.conoha.hadoop;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: wzard
 * Date: 2018-02-09
 * Time: 13:15
 * ProjectName: hadoopWordCount
 * To change this template use File | Settings | File Templates.
 **/

public class Test {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        System.out.println("开始时间:"+simpleDateFormat.format(new Date()));

        //获取文件
        File fileTmallGoodsComment = new File("C:\\Users\\wizard\\IdeaProjects\\hadoopWordCount\\src\\main\\java\\wiki\\conoha\\hadoop\\tmall_goods_comment.xls");
        if (!fileTmallGoodsComment.exists()) {
            System.out.println("file is not exists!");
        }
        try {
            //创建文件输入流
            FileInputStream fileInputStream = new FileInputStream(fileTmallGoodsComment);
            //创建缓冲出入流
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            //maven控制导入一个jxl包，使用Workbook来处理这个Excel表
            Workbook workbook = Workbook.getWorkbook(bufferedInputStream);
            //获取一下那个Sheet的第一页
            Sheet sheet = workbook.getSheet(0);
            File outputFile = new File("C:\\Users\\wizard\\IdeaProjects\\hadoopWordCount\\src\\main\\java\\wiki\\conoha\\hadoop\\tmall_goods_comment.txt");
            FileWriter fileWriter = new FileWriter(outputFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            // j为行数，getCell("列号","行号")
            int j = sheet.getRows();
            int y = sheet.getColumns();
            for (int i = 0; i < j; i++) {
                for(int x=0; x<y; x++){
                    Cell c = sheet.getCell(x, i);
                    String s = c.getContents();
                    bufferedWriter.write(s);
                    //这里就加个 | 好啦
                    bufferedWriter.write("|");
                    bufferedWriter.flush();
                }
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            //各种关闭
            bufferedWriter.close();
            fileWriter.close();
            workbook.close();
            bufferedInputStream.close();
            fileInputStream.close();
            System.out.println("结束时间:"+simpleDateFormat.format(new Date()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
}
