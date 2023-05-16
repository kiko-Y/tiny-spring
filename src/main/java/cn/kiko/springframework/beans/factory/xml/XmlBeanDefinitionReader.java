package cn.kiko.springframework.beans.factory.xml;

import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import cn.kiko.springframework.beans.BeansException;
import cn.kiko.springframework.beans.PropertyValue;
import cn.kiko.springframework.beans.factory.config.BeanDefinition;
import cn.kiko.springframework.beans.factory.config.BeanReference;
import cn.kiko.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import cn.kiko.springframework.beans.factory.support.BeanDefinitionRegistry;
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
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    /**
     * 解析 xml 文档，加载BeanDefinition
     * @param inputStream xml 的输入流
     * @throws ClassNotFoundException 无法加载Bean 对应的类不存在
     */
    private void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            // 判断元素 是否是blank
            if (!(childNodes.item(i) instanceof Element)) continue;
            // 判断对象 是否是bean
            if (!"bean".equals(childNodes.item(i).getNodeName())) continue;

            // 解析标签
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            String initMethod = bean.getAttribute("init-method");
            String destroyMethodName = bean.getAttribute("destroy-method");
            String scope = bean.getAttribute("scope");
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

            // 填充属性
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if (!(bean.getChildNodes().item(j) instanceof Element)) continue;
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;
                // 解析标签
                Element property = (Element) bean.getChildNodes().item(j);
                // 属性名
                String attrName = property.getAttribute("name");
                // 属性值
                String attrValue = property.getAttribute("value");
                // 引用
                String attrRef = property.getAttribute("ref");

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
