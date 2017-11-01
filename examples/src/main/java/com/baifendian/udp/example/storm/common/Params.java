package com.baifendian.udp.example.storm.common;

/**
 * author: cow date: 2017/07/05 time: 上午10:51 desc:
 */
public class Params {

  // Kafka
  public static final String BOOTSTRAP_SERVERS = "bootstrap.servers";
  public static final String GROUP_ID = "group.id";
  public static final String KAFKA_TOPIC = "kafka.topic";
//  public static final String KAFKA_START_OFFSET = "kafka.start.offset";

  // Storm
  public static final String STORM_NUM_WORKERS = "storm.num.workers";
  public static final String STORM_TOPOLOGY_NAME = "storm.topology.name";

  // Storm Kafka Spout
  public static final String STORM_KAFKA_SPOUT_NAME = "storm.kafka.spout.name";
  public static final String STORM_KAFKA_SPOUT_PARALLELISM = "storm.kafka.spout.parallelism";
  public static final String STORM_KAFKA_SPOUT_NUMBER_TASK = "storm.kafka.spout.task.number";
//  public static final String STORM_KAFKA_SPOUT_SCHEME_CLASS = "storm.kafka.spout.scheme.class";

  // Test Bolt
  public static final String TEST_BOLT_NAME = "test.bolt.name";
  public static final String TEST_BOLT_NUM_TASKS = "test.bolt.num.tasks";
}
