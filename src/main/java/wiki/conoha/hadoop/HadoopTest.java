package wiki.conoha.hadoop;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: wzard
 * Date: 2018-02-09
 * Time: 14:16
 * ProjectName: hadoopWordCount
 * To change this template use File | Settings | File Templates.
 **/

public class HadoopTest {
    public static class HadoopTestMapper extends Mapper{
        public void map() {

        }
    }

    public static class HadoopTestReducer extends Reducer{
        public void reduce() {

        }
    }

    public static void main(String[] args) {
        Path inputPath = new Path(args[0]);
        Path outputPath = new Path(args[1]);
        try {
            Job job = new Job();
            job.setJobName("get Tmall goods comment");
            job.setMapperClass(HadoopTestMapper.class);
            job.setReducerClass(HadoopTestReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Object.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
