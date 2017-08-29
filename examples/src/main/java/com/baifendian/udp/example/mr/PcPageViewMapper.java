package com.baifendian.udp.example.mr;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * <p>
 *
 * @author : shuanghu
 */
public class PcPageViewMapper extends Mapper<Object, Text, Text, NullWritable> {
  @Override
  public void map(Object key, Text val, Context context)
      throws IOException, InterruptedException {
    String valStr = val.toString();
    if (valStr.isEmpty()){
      return;
    }

    String arr[] = valStr.split("\t");
    if (arr.length < 6){
      return;
    }


    context.write(val, NullWritable.get());
  }
}
