package com.mario;

import ch.qos.logback.core.util.FileUtil;
import com.mario.input.ColorLightSDK;
import com.mario.input.utils.NativeLoader;
import com.sun.jna.Native;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.*;

/**
 * @author zxz
 * @date 2023年03月06日 14:35
 */
@SpringBootApplication
public class SdkApplication {
//    @Bean
//    public void loadLib(){
//        String path = "C:\\Users\\zhouxiaoze\\Desktop\\colorT\\SDKPlatform_2022.12.05\\SDKPlatform\\CLTDeviceSDK\\Linux\\x64";
//        String systemType = System.getProperty("os.name");
//        String ext = (systemType.toLowerCase().contains("win")) ? ".dll" : ".so";
//        if (ext.equals(".so")) {
//            try {
//                NativeLoader.loader(path);
//            } catch (Exception e) {
//                System.out.println("加载so库失败");
//            }
//            System.out.println("loaded");
//        }
//    }

    @Bean
    public void init(){
        try {
            String path = System.getProperty("java.io.tmpdir");
            String name = "libCLTDevice.so";
            // 获取sources下的资源
            ClassPathResource classPathResource = new ClassPathResource("linux-x86-64/" + name);
            InputStream input = classPathResource.getInputStream();
            String fileName = path + "/lib/" + name;
            File file=new File(fileName);
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedInputStream in=null;
            BufferedOutputStream out=null;
            in=new BufferedInputStream(input);
            out=new BufferedOutputStream(new FileOutputStream(fileName));
            int len=-1;
            byte[] b=new byte[1024];
            while((len=in.read(b))!=-1){
                out.write(b,0,len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            //
        }

    }
    public static void main(String[] args) {
        SpringApplication.run(SdkApplication.class, args);
    }

    @PostConstruct
    public void start(){
        String path = System.getProperty("java.io.tmpdir");
        ColorLightSDK load = Native.load(path + "/lib/" + "libCLTDevice.so", ColorLightSDK.class);
        System.out.println(load.CLTSearchAllDevice());
    }
}
