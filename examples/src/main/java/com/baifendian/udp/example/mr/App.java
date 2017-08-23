package com.baifendian.udp.example.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * <p>
 *
 * @author : shuanghu
 */
public class App extends Configured implements Tool {

  public static void main(String[] args) throws Exception {
    if (args.length < 2){
      System.out.println("Usage [input, output]");
      System.exit(1);
    }
    int res = ToolRunner.run(new App(), args);
    System.exit(res);
  }

  public int run(String[] args) throws Exception {
    String input = args[0];
    String output = args[1];

    Configuration conf = new Configuration();

    Job job = Job.getInstance(conf, "Pc page view.");

    job.setJarByClass(App.class);
    job.setMapperClass(PcPageViewMapper.class);
    job.setReducerClass(PcPageViewReducer.class);

    FileInputFormat.addInputPath(job, new Path(input));

    FileOutputFormat.setOutputPath(job, new Path(output));

    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(Text.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(NullWritable.class);

    return job.waitForCompletion(true) ? 0:1;
  }
}
