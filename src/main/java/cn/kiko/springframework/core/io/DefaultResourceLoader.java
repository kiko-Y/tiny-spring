package cn.kiko.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import cn.hutool.core.lang.Assert;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-05
 */
public class DefaultResourceLoader implements ResourceLoader {
    public static void main(String[] args) throws IOException {
        URL resource = ClassLoader.getSystemClassLoader().getResource("logback.xml");
        InputStream is = resource.openConnection().getInputStream();
        byte[] bytes = is.readAllBytes();
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
    }

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }

}
