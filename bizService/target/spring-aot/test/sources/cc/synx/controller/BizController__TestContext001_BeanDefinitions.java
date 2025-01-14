package cc.synx.controller;

import cc.synx.api.UserClient;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link BizController}.
 */
@Generated
public class BizController__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'bizController'.
   */
  private static BeanInstanceSupplier<BizController> getBizControllerInstanceSupplier() {
    return BeanInstanceSupplier.<BizController>forConstructor(UserClient.class)
            .withGenerator((registeredBean, args) -> new BizController(args.get(0)));
  }

  /**
   * Get the bean definition for 'bizController'.
   */
  public static BeanDefinition getBizControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(BizController.class);
    beanDefinition.setInstanceSupplier(getBizControllerInstanceSupplier());
    return beanDefinition;
  }
}
