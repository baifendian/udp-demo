package com.baifendian.udp.example.storm.bolt;

import java.util.Map;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBolt extends BaseRichBolt {

  private static final Logger logger = LoggerFactory.getLogger(TestBolt.class);

  private OutputCollector collector;

  @Override
  public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
    this.collector = collector;
  }

  @Override
  public void execute(Tuple input) {
    String message = input.toString().trim();
    if (message.length() != 0) {
      logger.info("Execute tuple of message id: {}, sourceComponent: {}, message: {}",
          input.getMessageId(), input.getSourceComponent(), input.getString(input.size() - 1));
      collector.emit(input, new Values(message));
      collector.ack(input);
    }
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    declarer.declare(new Fields("message"));
  }
}
