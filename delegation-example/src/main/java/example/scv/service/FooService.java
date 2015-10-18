package example.scv.service;

import java.util.List;

/**
 * @author Alexander Shabanov
 */
public interface FooService {

  void bar();

  String baz(int intParam, double dblParam, Object objParam);

  int sum(int[] params);

  int hashCode(List<Integer> params);
}
