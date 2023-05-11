package cn.kiko.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author shijiayue <shijiayue@kuaishou.com>
 * Created on 2023-05-05
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
