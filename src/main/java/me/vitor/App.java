package me.vitor;

import static spark.Spark.get;
import static spark.Spark.init;

import spark.embeddedserver.EmbeddedServers;

public class App {

  public static void main(String[] args) {

    EmbeddedServers.add(
        EmbeddedServers.Identifiers.JETTY,
        new MyCustomEmbeddedServerFactory());

    get("/hello", TestController::getTest);
    init();
  }

}