package test;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {

  @Test
  public void test1() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    System.out.println(sdf.format(new Date()));
  }

  @Test
  public void test2() {
    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
    System.out.println(sdf.format(new Date()));
  }
}
