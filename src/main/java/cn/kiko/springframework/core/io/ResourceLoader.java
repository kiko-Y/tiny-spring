package cn.kiko.springframework.core.io;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-05
 */
public interface ResourceLoader {
    /**
     * Pseudo URL prefix for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
