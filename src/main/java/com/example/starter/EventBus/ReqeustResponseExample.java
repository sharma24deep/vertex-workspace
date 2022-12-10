package com.example.starter.EventBus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReqeustResponseExample extends AbstractVerticle {
  public static final Logger logger = LoggerFactory.getLogger(ReqeustResponseExample.class);

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new RequestVerticle());
    vertx.deployVerticle(new ResponseVerticle());
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    logger.info(" Deployed Verticle : {}",getClass().getName());
  }


  static class RequestVerticle extends AbstractVerticle{
    public static final Logger log = LoggerFactory.getLogger(RequestVerticle.class);
    public static final String ADDRESS = "my.request.address";

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      var eventBus =vertx.eventBus();
      final String message = "Hello inside RequestVerticle EventBus";
      log.info(" Sending : {}", message);
      eventBus.<String>request(ADDRESS,message, reply ->{
        log.info(" Response : {}",reply.result().body());
      });
    }
  }

  static class ResponseVerticle extends  AbstractVerticle{
    public static final Logger log = LoggerFactory.getLogger(ResponseVerticle.class);


    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();

      vertx.eventBus().<String>consumer(RequestVerticle.ADDRESS, message ->{
      logger.info("Received Message: {} ",message.body());
      message.reply("Received your messabge , Thanks !!");
      });
    }
  }

}
