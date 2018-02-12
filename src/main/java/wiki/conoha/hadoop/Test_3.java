package wiki.conoha.hadoop;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
    private static BufferedInputStream bufferedInputStream;

    public Test_3(String fileName) {
        File file = new File(fileName);
        try {
            //创建文件输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            //创建缓冲出入流
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            //maven控制导入一个jxl包，使用Workbook来处理这个Excel表
            Workbook workbook = Workbook.getWorkbook(bufferedInputStream);
            //获取一下那个Sheet的第一页
            Sheet sheet = workbook.getSheet(0);
            stock = new String[sheet.getRows()][sheet.getColumns()];

            // j为行数，getCell("列号","行号")
            int j = sheet.getRows();
            int y = sheet.getColumns();
            for (int i = 0; i < j; i++) {
                for(int x=0; x<y; x++){
                    Cell c = sheet.getCell(x, i);
                    String s = c.getContents();
                    if (s.equals("")) {
                        s = "null";  //有些行可能回没有数据，但是要给他留个位置嘛
                    }
                    stock[i][x] = s;
                }
            }
            //各种关闭
            workbook.close();
            bufferedInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

    public static String[][] getStock() {
        return stock;
    }

    public static void setStock(String[][] stock) {
        Test_3.stock = stock;
    }

    public static void order() {
        System.out.println("一共"+stock.length+"个订单");
        int sum = 0;
        for (String[] s :stock
                ) {
            if (s[8]!="null") {
                sum++;
            }
        }
        System.out.println("其中"+sum+"个为有效订单");
        System.out.println();
    }
    public static void split() {
        Set<String> strings = new HashSet<String>();
        for (String[] s:stock
             ) {
            strings.add(s[9]);
        }
        //移除category，这个不是
        strings.remove("category");
        String[] split = new String[strings.size()];
        int sum = 0;
        System.out.println("类别有"+strings.size()+"种 分别是:");
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            split[sum] = iterator.next();
            sum++;
        }
        for (String s:split
             ) {
            System.out.print(s+" ");
        }
    }
    public static void count() {
        order();
        split();
    }


    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        System.out.println("开始时间:"+simpleDateFormat.format(new Date()));
        String fileName = "C:\\Users\\wizard\\IdeaProjects\\hadoopWordCount\\src\\main\\java\\wiki\\conoha\\hadoop\\tmall_goods_stock.xls";
        new Test_3(fileName);
        System.out.println("文件名:"+fileName);
        count();
        System.out.println("\n结束时间:"+simpleDateFormat.format(new Date()));

    }
}
