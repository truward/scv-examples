package example.scv.spec;

import com.truward.scv.contrib.plugin.delegation.api.DelegationSpecifierSupport;
import com.truward.scv.contrib.plugin.delegation.api.binding.DelegationTarget;
import com.truward.scv.contrib.plugin.delegation.api.binding.MultipleDelegationTargets;
import com.truward.scv.specification.Specification;
import com.truward.scv.specification.Target;
import example.scv.service.BarService;
import example.scv.service.BazService;
import example.scv.service.FooService;

import static com.truward.scv.specification.Parameters.any;

/**
 * @author Alexander Shabanov
 */
public class SampleDelegateSpec extends DelegationSpecifierSupport {

  @Specification
  public void fooDelegate() {
    // Create delegate for FooService
    final DelegationTarget<FooService> target = create(Target.fromClassName("example.scv.service.FooServiceDelegate"),
        FooService.class);

    // When any method invoked - throw UnsupportedOperationException
    target.throwException(UnsupportedOperationException.class).forAllMethods();

    // Override this behavior for particular methods
    target.returnConstant(1).forMethod().sum(any(int[].class));
  }

  @Specification
  public void barAndBazDelegates() {
    final MultipleDelegationTargets targets = create(Target.fromClassName("example.scv.service.BarAndBazDelegates"),
        BarService.class, BazService.class);

    // When any method invoked - throw an exception
    targets.throwException(UnsupportedOperationException.class).forAllMethods();
  }
}
