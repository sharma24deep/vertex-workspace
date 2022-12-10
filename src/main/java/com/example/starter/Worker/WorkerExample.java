package com.example.starter.Worker;

import com.example.starter.WorkerVerticle.WorkerVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerExample extends AbstractVerticle {

  public static final Logger logger = LoggerFactory.getLogger(AbstractVerticle.class);

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new WorkerVerticle(),
      new DeploymentOptions()
        .setWorker(true)
        .setWorkerPoolSize(1)
        .setWorkerPoolName("My-Worker_verticle"));
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    startPromise.complete();
    vertx.executeBlocking(event -> {
      logger.info("Executing Blocking code");
      try {
        Thread.sleep(5000);
        event.fail("FOrced Fail!!!");
      } catch (InterruptedException e) {
        logger.error(" Failed with exception : ",e);
        event.fail(" Failed with exception : "+e);
      }
    }, result ->{
      if(result.succeeded()){
        logger.info("Blocking call done successfully");
      } else {
        logger.info("Message Blocking Failed due to :",result.cause());
      }
    });

  }
}
