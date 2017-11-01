package com.baifendian.udp.example.storm;

import com.baifendian.udp.example.storm.bolt.TestBolt;
import com.baifendian.udp.example.storm.common.BundleUtils;
import com.baifendian.udp.example.storm.common.Params;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ResourceBundle;
import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestTopology {

  private static final Logger logger = LoggerFactory.getLogger(TestTopology.class);
  private static ResourceBundle bundle = null;

  public static void main(String[] args)
      throws InvalidTopologyException, AuthorizationException, AlreadyAliveException {
    System.out.println("USAGE: \n"
        + "storm jar <topo.jar> <com.package.TopologyMainClass> /xx/xx/storm_demo.properties\n");
    if (args.length == 1) {
      try {
        bundle = BundleUtils.resourceBundleInit(
            new BufferedInputStream(new FileInputStream(args[0])));
      } catch (FileNotFoundException e) {
        logger.error("Config file path error!");
      }
    } else {
      logger.error("Invalid command! args.length:{} args:{}", args.length, Arrays.toString(args));
      System.exit(1);
    }

    if (bundle != null) {
      final TopologyBuilder topologyBuilder = new TopologyBuilder();
      // Storm Topology Config
      Config stormConfig = new Config();
      stormConfig.setNumWorkers(Integer.parseInt(bundle.getString(Params.STORM_NUM_WORKERS)));

      // Add the Kafka Spout
      KafkaSpoutConfig<String, String> kafkaSpoutConfig = KafkaSpoutConfig.builder(
          bundle.getString(Params.BOOTSTRAP_SERVERS), bundle.getString(Params.KAFKA_TOPIC))
          .setGroupId(bundle.getString(Params.GROUP_ID))
          .build();
      KafkaSpout<String, String> kafkaSpout = new KafkaSpout<>(kafkaSpoutConfig);

      // Add the spout to the topology
      topologyBuilder
          .setSpout(bundle.getString(Params.STORM_KAFKA_SPOUT_NAME), kafkaSpout,
              Integer.parseInt(bundle.getString(Params.STORM_KAFKA_SPOUT_PARALLELISM)))
          .setNumTasks(Integer.parseInt(bundle.getString(Params.STORM_KAFKA_SPOUT_NUMBER_TASK)));

      // Add the bolt
      topologyBuilder
          .setBolt(bundle.getString(Params.TEST_BOLT_NAME), new TestBolt())
          .setNumTasks(Integer.parseInt(bundle.getString(Params.TEST_BOLT_NUM_TASKS)))
          .shuffleGrouping(bundle.getString(Params.STORM_KAFKA_SPOUT_NAME));

      // Submit the topology
      StormSubmitter.submitTopologyWithProgressBar(bundle.getString(Params.STORM_TOPOLOGY_NAME),
          stormConfig, topologyBuilder.createTopology());
    } else {
      logger.error("ERROR! Variable \"bundle\" is null!");
      System.exit(1);
    }
  }
}
