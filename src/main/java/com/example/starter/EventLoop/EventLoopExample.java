package com.example.starter.EventLoop;

import io.vertx.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class EventLoopExample extends AbstractVerticle {
  public static final Logger logger = LoggerFactory.getLogger(EventLoopExample.class);

  public static void main(String[] args) {
    var vertx = Vertx.vertx(
      new VertxOptions()
        .setMaxEventLoopExecuteTime(500)
        .setMaxEventLoopExecuteTimeUnit(TimeUnit.MILLISECONDS)
        .setBlockedThreadCheckInterval(1)
        .setBlockedThreadCheckIntervalUnit(TimeUnit.SECONDS)
        .setEventLoopPoolSize(4));
    vertx.deployVerticle(EventLoopExample.class.getName(),
      new DeploymentOptions().setInstances(4));

  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

  logger.info(" Start {}", getClass().getName());
  startPromise.complete();
  //things not to do in a verticle
    Thread.sleep(5000);

  }
}
