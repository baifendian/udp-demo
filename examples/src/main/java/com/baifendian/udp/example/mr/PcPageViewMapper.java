package com.baifendian.udp.example.mr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * <p>
 *
 * @author : shuanghu
 */
public class PcPageViewMapper extends Mapper<Object, Text, Text, Text> {
  @Override
  public void map(Object key, Text val, Context context)
      throws IOException, InterruptedException {
    JSONObject obj = null;

    try{
      obj = JSON.parseObject(val.toString());
    }catch (Throwable e){
      e.printStackTrace();
      return;
    }

    if (!obj.containsKey("sid")|| !obj.containsKey("appkey")){
      return;
    }

    context.write(new Text(obj.getString("sid")), val);
  }
}
