package me.vitor;

import spark.Request;
import spark.Response;

public class TestController {

//  @Inject
//  TestService testService;

  public static String getTest(Request request, Response response) {
    String value = new TestService().test();
    return value;
  }

}
