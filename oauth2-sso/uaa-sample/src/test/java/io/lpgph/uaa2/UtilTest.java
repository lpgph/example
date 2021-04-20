package io.lpgph.uaa2;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class UtilTest {

  @Test
  public void runTest() {
    String url1 = "http://www.xx.com";
    String url2 = "w.xx.com";
    String url3 = "http://w.xx.com";
    String url4 = "ssss";
    String url5 = "https://www.xx.cn";
    Pattern pattern =
        Pattern.compile(
            "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~/])+$");
    System.out.println(pattern.matcher(url1).matches());
    System.out.println(pattern.matcher(url2).matches());
    System.out.println(pattern.matcher(url3).matches());
    System.out.println(pattern.matcher(url4).matches());
    System.out.println(pattern.matcher(url5).matches());
  }
}
