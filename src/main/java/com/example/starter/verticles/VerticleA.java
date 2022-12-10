package com.example.starter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerticleA extends AbstractVerticle {
  private static final Logger logger = LoggerFactory.getLogger(VerticleA.class);

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    logger.info(" Start {} ", getClass().getName());
    vertx.deployVerticle( new VerticleAA());
    vertx.deployVerticle( new VerticleAB());
    startPromise.complete();
  }
}
