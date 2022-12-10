package com.example.starter.EventBus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PointToPointExample extends AbstractVerticle {
  public static final Logger logger = LoggerFactory.getLogger(PointToPointExample.class);

  public static void main(String[] args) {
    var vertc = Vertx.vertx();
    vertc.deployVerticle(new Sender());
    vertc.deployVerticle(new Receiver());
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
  }

  static class Sender extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.setPeriodic(10, id ->
        vertx.eventBus().send(Sender.class.getName(), "Sending a message From Sender"));
    }
  }

  static class Receiver extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.eventBus().consumer(Sender.class.getName(), message ->
        logger.info("Received : {} ", message.body()));
    }
  }

}
