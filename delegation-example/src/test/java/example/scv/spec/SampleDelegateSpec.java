package example.scv.spec;

import com.truward.scv.contrib.plugin.delegation.api.DelegationSpecifierSupport;
import com.truward.scv.contrib.plugin.delegation.api.binding.ClassDelegationAction;
import com.truward.scv.contrib.plugin.delegation.api.binding.MultipleDelegationActions;
import com.truward.scv.specification.annotation.Specification;
import com.truward.scv.specification.annotation.TargetMapping;
import com.truward.scv.specification.annotation.TargetMappingEntry;
import example.scv.aspect.LoggingAspect;
import example.scv.service.BarService;
import example.scv.service.BazService;
import example.scv.service.FooService;

import static com.truward.scv.specification.Parameters.any;

/**
 * @author Alexander Shabanov
 */
@TargetMapping({
    @TargetMappingEntry(source = FooService.class, targetClassName = "example.scv.service.FooServiceDelegate"),
    @TargetMappingEntry(source = {BarService.class, BazService.class},
        targetClassName = "example.scv.service.BarAndBazDelegates")
})
public class SampleDelegateSpec extends DelegationSpecifierSupport {

  @Specification
  public void fooDelegate() {
    // Create delegate for FooService
    final ClassDelegationAction<FooService> target = forClass(FooService.class);

    // When any method invoked - throw UnsupportedOperationException
    target.throwException(UnsupportedOperationException.class).forAllMethods();

    // Override this behavior for particular methods
    target.returnConstant(1).forMethod().sum(any(int[].class));
  }

  @Specification
  public void barAndBazDelegates() {
    // Create multiple delegates for both BarService and BazService as static inner classes of BarAndBazDelegates
    final MultipleDelegationActions targets = forClasses(BarService.class, BazService.class);

    // When any method invoked - just delegate the call
    targets.delegateCall().forAllMethods();
    targets.bindAspect(LoggingAspect.class).forAllMethods();
  }
}
