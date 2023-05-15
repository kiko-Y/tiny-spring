package cn.kiko.springframework.beans.factory;

/**
 * 实现此接口，既能感知到所属的 BeanName
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-15
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String name);
}
