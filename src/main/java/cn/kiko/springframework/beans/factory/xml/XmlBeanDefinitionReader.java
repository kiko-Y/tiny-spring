package cn.kiko.springframework.beans.factory.xml;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.NodeList;

import cn.hutool.core.util.StrUtil;
import cn.kiko.springframework.beans.BeansException;
import cn.kiko.springframework.beans.PropertyValue;
import cn.kiko.springframework.beans.factory.config.BeanDefinition;
import cn.kiko.springframework.beans.factory.config.BeanReference;
import cn.kiko.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import cn.kiko.springframework.beans.factory.support.BeanDefinitionRegistry;
import cn.kiko.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import cn.kiko.springframework.core.io.Resource;
import cn.kiko.springframework.core.io.ResourceLoader;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-06
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {


    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }


    /**
     * 解析 resource 所指定的 xml 文档， 加载BeanDefinition
     * @param resource  包含 beanDefinition 的 xml 文档
     * @throws BeansException 解析异常
     */
    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try (InputStream inputStream = resource.getInputStream()) {
            doLoadBeanDefinitions(inputStream);
        } catch (IOException | ClassNotFoundException | DocumentException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    /**
     * 解析 xml 文档，加载BeanDefinition
     * @param inputStream xml 的输入流
     * @throws ClassNotFoundException 无法加载Bean 对应的类不存在
     */
    private void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException, DocumentException {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(inputStream);
        Element root = doc.getRootElement();


        Element componentScan = root.element("component-scan");

        if (Objects.nonNull(componentScan)) {
            String scanPath = componentScan.attributeValue("base-package");
            if (StrUtil.isEmpty(scanPath)) {
                throw new BeansException("The value of base-package attribute can not be empty or null");
            }
            scanPackage(scanPath);
        }

        List<Element> beanList = root.elements("bean");
        for (Element bean : beanList) {

            String id = bean.attributeValue("id");
            String name = bean.attributeValue("name");
            String className = bean.attributeValue("class");
            String initMethod = bean.attributeValue("init-method");
            String destroyMethodName = bean.attributeValue("destroy-method");
            String scope = bean.attributeValue("scope");
            // 获取 Class
            Class<?> clazz = Class.forName(className);

            // 优先级id > name 如果没有 id 或者 name 就用类名 作为beanName
            String beanName = StrUtil.isNotEmpty("id") ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // 定义 bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethodName);
            if (StrUtil.isNotEmpty(scope) && !(scope.equals(BeanDefinition.SCOPE_PROTOTYPE) || scope.equals(BeanDefinition.SCOPE_SINGLETON))) {
                throw new BeansException("bean scope error, scope [" + scope + "] not exists");
            }
            if (BeanDefinition.SCOPE_PROTOTYPE.equals(scope)) {
                beanDefinition.setScope(scope);
            }


            List<Element> propertyList = bean.elements("property");

            // 填充属性
            for (Element property : propertyList) {
                // 属性名
                String attrName = property.attributeValue("name");
                // 属性值
                String attrValue = property.attributeValue("value");
                // 引用
                String attrRef = property.attributeValue("ref");

                PropertyValue propertyValue = new PropertyValue(attrName,
                        StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue);

                // 创建属性信息
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }

            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }

            getRegistry().registerBeanDefinition(beanName, beanDefinition);

        }
    }

    /**
     * 扫描包
     * @param scanPath 包路径
     */
    private void scanPackage(String scanPath) {
        String[] basePackages = StrUtil.splitToArray(StrUtil.removeAll(scanPath, ' '), ',');
        ClassPathBeanDefinitionScanner scanner =
                new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }
}
