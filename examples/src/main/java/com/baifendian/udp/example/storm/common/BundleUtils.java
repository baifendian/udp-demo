package com.baifendian.udp.example.storm.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BundleUtils {

  private static final Logger logger = LoggerFactory.getLogger(BundleUtils.class);

  public static ResourceBundle resourceBundleInit(InputStream inputStream) {
    ResourceBundle bundle = null;
    try {
      bundle = new PropertyResourceBundle(inputStream);
    } catch (IOException e) {
      logger.error("ResourceBundle init error!");
      e.printStackTrace();
    }
    return bundle;
  }

}
