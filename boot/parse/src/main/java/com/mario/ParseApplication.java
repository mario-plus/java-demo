package com.mario;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.mario.abr.AbrParseToBytes;
import com.mario.abr.down.IDownConvert;
import com.mario.metadata.CmdInfo;
import com.mario.metadata.DownLinkMapping;
import com.mario.metadata.Metadata;
import com.mario.parse.ParseFactory;
import com.mario.push.PushInfo;
import com.mario.util.ByteUtil;
import com.mario.util.JsonReadUtil;
import com.mario.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author zxz
 * @date 2023年03月22日 14:19
 */
@SpringBootApplication
@Slf4j
public class ParseApplication implements ApplicationRunner, ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(ParseApplication.class, args);
    }

    @Autowired
    ReflectUtil reflectUtil;

    @Autowired
    ParseFactory parseFactory;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //PushInfo pushInfo = getPushInfo_string();
        //PushInfo pushInfo = getPushInfo_string2();
        //PushInfo pushInfo = getPushInfo_json();
        PushInfo pushInfo = getPushInfo_hex();


        //TODO 数据只在项目启动是加载，每个线程应该copy downMapping，防止数据格式被篡改
        Metadata metadata = JsonReadUtil.readFile("converts/parse.json", Metadata.class);
        DownLinkMapping downMapping = metadata.getDownMappingByServiceName(pushInfo.getServiceName());
        downMapping(pushInfo, downMapping);

    }

    private void downMapping(PushInfo pushInfo, DownLinkMapping downMapping) throws Exception {
        if (downMapping.getServiceConverter() != null && !"".equals(downMapping.getServiceConverter())) {//1.自定义服务转换器
            IDownConvert convert = (IDownConvert) applicationContext.getBean(downMapping.getServiceConverter());
            byte[] bytes = (byte[]) convert.convert(pushInfo, downMapping);
            log.info("自定义转换器下行数据：" + new String(bytes, StandardCharsets.UTF_8));
        } else if (downMapping.getData().size() > 0) {
            CmdInfo cmdInfoByCmd = downMapping.getCmdInfoByCmd(pushInfo.getFunctionKey());
            if (cmdInfoByCmd == null) {
                throw new Exception("暂未配置物模型转换数据");
            } else {
                String cmdConvert = cmdInfoByCmd.getCmdConvert();
                if (cmdConvert != null && !"".equals(cmdConvert)) {//2.自定义单个物模型转换器
                    IDownConvert convert = (IDownConvert) applicationContext.getBean(cmdConvert);
                    byte[] bytes = (byte[]) convert.convert(pushInfo, cmdInfoByCmd);
                    log.info("自定义物模型下行数据：" + new String(bytes, StandardCharsets.UTF_8));
                } else {//3.按模板进行数据解析
                    AbrParseToBytes parse = parseFactory.getParse(downMapping.getMsgType());
                    byte[] convert = parse.doCmdDownConvert(pushInfo, cmdInfoByCmd);
                    log.info("根据模板解析下行数据：{}", ByteUtil.byte2Hex(convert));
                }
            }
        }
    }


    private PushInfo getPushInfo_json() {
        PushInfo pushInfo = new PushInfo();//下行数据
        pushInfo.setFunctionKey("f1");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 123L);
        jsonObject.put("serialNum", Arrays.asList("123", "2342", "6435"));
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("light", Arrays.asList(1, 2, 3, 4));
        jsonObject.put("device", jsonObject1);
        pushInfo.setContext(jsonObject);
        pushInfo.setServiceName("service2");
        return pushInfo;
    }

    public PushInfo getPushInfo_string2() {
        PushInfo pushInfo = new PushInfo();//下行数据
        pushInfo.setFunctionKey("getSceneName");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ugid", 64);
        pushInfo.setContext(jsonObject);
        pushInfo.setServiceName("service3");
        return pushInfo;
    }

    public PushInfo getPushInfo_hex() {
        PushInfo pushInfo = new PushInfo();//下行数据
        pushInfo.setFunctionKey("f1");
        pushInfo.setServiceName("service4");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("devNum", 12);
        jsonObject.put("devOrder", 2);
        jsonObject.put("param1", 1);
        jsonObject.put("param2", 2);
        jsonObject.put("param3", 3);
        pushInfo.setContext(jsonObject);
        return pushInfo;
    }


    private PushInfo getPushInfo_string() {
        PushInfo pushInfo = new PushInfo();//下行数据
        pushInfo.setFunctionKey("f1");
        JSONObject jsonObject = new JSONObject();
        JSONObject config = new JSONObject();
        config.put("ip", "10.3.50.224");
        jsonObject.put("config", config);
        jsonObject.put("name", "测试设备");
        jsonObject.put("devNum", 0);
        jsonObject.put("id", 123L);
        jsonObject.put("bright", 123);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("123");
        jsonArray.add("345");
        jsonArray.add("789");
        jsonObject.put("list", jsonArray);
        pushInfo.setContext(jsonObject);
        pushInfo.setServiceName("service1");
        return pushInfo;
    }



}
