package cc.synx;

import cc.synx.api.UserClient;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;

/**
 * Bean definitions for {@link BizServiceApplication}.
 */
@Generated
public class BizServiceApplication__TestContext001_BeanDefinitions {
  /**
   * Get the bean definition for 'bizServiceApplication'.
   */
  public static BeanDefinition getBizServiceApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(BizServiceApplication.class);
    beanDefinition.setTargetType(BizServiceApplication.class);
    ConfigurationClassUtils.initializeConfigurationClass(BizServiceApplication.class);
    beanDefinition.setInstanceSupplier(BizServiceApplication$$SpringCGLIB$$0::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'initWebClient'.
   */
  private static BeanInstanceSupplier<UserClient> getInitWebClientInstanceSupplier() {
    return BeanInstanceSupplier.<UserClient>forFactoryMethod(BizServiceApplication.class, "initWebClient")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(BizServiceApplication.class).initWebClient());
  }

  /**
   * Get the bean definition for 'initWebClient'.
   */
  public static BeanDefinition getInitWebClientBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(UserClient.class);
    beanDefinition.setInstanceSupplier(getInitWebClientInstanceSupplier());
    return beanDefinition;
  }
}
