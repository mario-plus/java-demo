package com.mario.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mario.metadata.Metadata;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zxz
 * @date 2023年03月22日 14:28
 */
public class JsonReadUtil {


    private static final ObjectMapper mapper = new ObjectMapper();


    /**
     * @param path resources目录下文件
     */
    public static <T> T readFile(String path, Class<T> clazz) throws IOException {
        try {
            return mapper.readValue(getFileAsStream(path), clazz);
        } catch (IOException e) {
            throw e;
        }

    }

    private static InputStream getFileAsStream(String path) throws IOException {
        return new ClassPathResource(path)
                .getInputStream();
    }


}
