package cn.kiko.springframework.utils;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-05
 */
public class ClassUtils {
    public static void main(String[] args) {
        System.out.println(getDefaultClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(ClassUtils.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
    }

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }
}
