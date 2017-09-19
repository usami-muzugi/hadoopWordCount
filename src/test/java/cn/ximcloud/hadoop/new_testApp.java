package cn.ximcloud.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.net.URI;

public class new_testApp {


    public static final String HDFS_PATH = "hdfs://192.168.216.130:8020";
    public static final String  USER = "root";
    Configuration configuration = null;
    FileSystem fileSystem = null;


    /**
     * 上传文件到HDFS
     *
     * @throws Exception
     */
    @Test
    public void copyFromLocalFile() throws Exception {
        Path localPath = new Path("C:/Users/wizard/Desktop/test.txt");
        Path hdfsPath = new Path("/hdfs/test");
        fileSystem.copyFromLocalFile(localPath, hdfsPath);
    }

    /**
     * 查看某个目录下的所有文件
     */
    @Test
    public void listFiles() throws Exception {
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/hdfs/test"));

        for(FileStatus fileStatus : fileStatuses) {
            String isDir = fileStatus.isDirectory() ? "文件夹" : "文件";
            short replication = fileStatus.getReplication();
            long len = fileStatus.getLen();
            String path = fileStatus.getPath().toString();

            System.out.println(isDir + "\t" + replication + "\t" + len + "\t" + path);
        }

    }



    //按照指定分隔符进行拆分
    // words[0] = ID
    // words[1] = channel
    // words[2] = detailUrl
    // words[3] = commentUrl
    // words[4] = transactUrl
    // words[5] = get_time
    // words[6] = sellerID
    // words[7] = location
    // words[8] = sale
    // words[9] = category

    @Test
    public void MyshowStr() throws Exception {
        String string =
                "36666986788,tmall,https://detail.tmall.com/item.htm?id=36666986788&is_b=1&cat_id=2&q=&rn=51e91540ef76619253c4f518f6458d7e,https://rate.tmall.com/list_detail_rate.htm?itemId=36666986788&spuId=267388177&sellerId=101450072&order=3&currentPage=1,缺省值,9/1/2016 19:21:59,101450072,四川 成都 ,1";
        String[] words = string.split(",");
        int sum = 1;
            for(String word : words){
                System.out.println(sum);
                System.out.println(word);
                sum++;
            }


    }


    @Before
    public void setUp() throws Exception{
        System.out.println("new_testApp.setUp");
        Configuration configuration = new Configuration();
        fileSystem = FileSystem.get(new URI(HDFS_PATH),configuration,USER);
    }

    @After
    public void tearDown() throws Exception{
        System.out.println("new_testApp.tearDown");
        configuration = null;
        fileSystem = null;
    }
}
