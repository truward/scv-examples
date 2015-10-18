package example.scv.aspect;

import java.util.Arrays;

/**
 * @author Alexander Shabanov
 */
public final class LoggingAspect {


  public void after(String methodName, Object[] args, Exception ex) {
    if (ex != null) {
      System.err.println("[ERROR] " + methodName + Arrays.asList(args));
      ex.printStackTrace(System.err);
      return;
    }
    System.out.println("[INFO] After " + methodName + Arrays.asList(args));
  }
}
