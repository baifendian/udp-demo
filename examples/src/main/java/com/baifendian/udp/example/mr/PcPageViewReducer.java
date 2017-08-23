package com.baifendian.udp.example.mr;

import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * <p>
 *
 * @author : shuanghu
 */
public class PcPageViewReducer extends
    Reducer<Text, Text, Text, NullWritable> {

  @Override
  public void reduce(Text key, Iterable<Text> values,
      Context context) throws IOException, InterruptedException {
    for (Text text: values) {
      context.write(text, NullWritable.get());
    }
  }
}
