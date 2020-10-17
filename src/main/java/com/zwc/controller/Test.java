package com.zwc.controller;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @author: zhangwch
 * @create: 2020-06-04 23:11
 **/
public class Test {

    /**
     * springboot启动类上面的注解获取配置类：
     * @SpringBootApplication
     * -
     * @EnableAutoConfiguration
     * -
     * @Import({AutoConfigurationImportSelector.class})
     * -
     * public String[] selectImports(AnnotationMetadata annotationMetadata) {
     *         if (!this.isEnabled(annotationMetadata)) {
     *             return NO_IMPORTS;
     *         } else {
     *             AutoConfigurationImportSelector.AutoConfigurationEntry autoConfigurationEntry = this
     *             .getAutoConfigurationEntry(annotationMetadata);
     *             return StringUtils.toStringArray(autoConfigurationEntry.getConfigurations());
     *         }
     *     }
     * -- getAutoConfigurationEntry(annotationMetadata)
     * -
     * protected AutoConfigurationImportSelector.AutoConfigurationEntry getAutoConfigurationEntry(AnnotationMetadata
     * annotationMetadata) {
     *         if (!this.isEnabled(annotationMetadata)) {
     *             return EMPTY_ENTRY;
     *         } else {
     *             AnnotationAttributes attributes = this.getAttributes(annotationMetadata);
     *             List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes);
     *             configurations = this.removeDuplicates(configurations);
     *             Set<String> exclusions = this.getExclusions(annotationMetadata, attributes);
     *             this.checkExcludedClasses(configurations, exclusions);
     *             configurations.removeAll(exclusions);
     *             configurations = this.getConfigurationClassFilter().filter(configurations);
     *             this.fireAutoConfigurationImportEvents(configurations, exclusions);
     *             return new AutoConfigurationImportSelector.AutoConfigurationEntry(configurations, exclusions);
     *         }
     *     }
     * -- this.getCandidateConfigurations(annotationMetadata, attributes);
     * -
     * protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
     *         List<String> configurations = SpringFactoriesLoader.loadFactoryNames(this
     *         .getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader());
     *         Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If
     *         you are using a custom packaging, make sure that file is correct.");
     *         return configurations;
     *     }
     * -- List<String> configurations = SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader());
     * 通过SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader())这个方法拿到jar包spring-boot-autoconfigure下META-INF/spring.factories中的配置（可联系Controller下的Test类中的方法进行理解），
     * 在META-INF/spring.factories文件中有个org.springframework.boot.autoconfigure.EnableAutoConfiguration配置，相当于是key，后面的都是value，这个EnableAutoConfiguration就是启动类上面的注解，大致意思就是启动使用@EnableAutoConfiguration注解的类时进行自动装配
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        InputStream in = Test.class.getClassLoader().getResourceAsStream("a.properties");
        props.load(in);

        Iterator var6 = props.entrySet().iterator();

        while(var6.hasNext()) {
            Map.Entry<?, ?> entry = (Map.Entry)var6.next();
            String factoryTypeName = ((String)entry.getKey()).trim();
            String[] var9 = StringUtils.commaDelimitedListToStringArray((String)entry.getValue());
            int var10 = var9.length;

            for(int var11 = 0; var11 < var10; ++var11) {
                String factoryImplementationName = var9[var11];
                System.out.println("factoryTypeName = [" + factoryTypeName + "] - factoryImplementationName = [" + factoryImplementationName.trim() + "]");
            }
        }

        System.out.println("args = [" + EnableAutoConfiguration.class.getName() + "]");
    }
}
