package com.mario.test;

import javax.crypto.spec.IvParameterSpec;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

/**
 * @author zxz
 * @date 2023年05月24日 10:47
 */
public class tyt {

    //863313061440692
    final static String msg1 = "{\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":1,\n" +
            "        \"deviceSN\":\"810375990532853760\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeBitSwitch\"\n" +
            "    },\n" +
            "    \"lampBitSwitchItem\":[\n" +
            "        {\n" +
            "            \"loopId\":\"1\",\n" +
            "            \"bitOn\":false\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    //775067503625
    final static String msg2 = "{\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":1,\n" +
            "        \"deviceSN\":\"829137789064138752\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeBitSwitch\"\n" +
            "    },\n" +
            "    \"lampBitSwitchItem\":[\n" +
            "        {\n" +
            "            \"loopId\":\"1\",\n" +
            "            \"bitOn\":false\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    //863313061261676
    final static String msg3 = "{\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":1,\n" +
            "        \"deviceSN\":\"810375805173977088\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeBitSwitch\"\n" +
            "    },\n" +
            "    \"lampBitSwitchItem\":[\n" +
            "        {\n" +
            "            \"loopId\":\"1\",\n" +
            "            \"dimmer\":50\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    //2c:6a:6f:00:ee:14
    final static String msg4 = "{\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":1,\n" +
            "        \"deviceSN\":\"798794619799293953\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeBitSwitch\"\n" +
            "    },\n" +
            "    \"lampBitSwitchItem\":[\n" +
            "        {\n" +
            "            \"loopId\":\"1\",\n" +
            "            \"bitOn\":false\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    //2c:6a:6f:00:ee:14
    final static String msg5 = "{\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":2,\n" +
            "        \"deviceSN\":\"798794619799293953\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeDataPollGet\"\n" +
            "    }\n" +
            "\n" +
            "    }";

    //775067503625
    final static String msg6 = "{\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":2,\n" +
            "        \"deviceSN\":\"829137789064138752\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeDataPollGet\"\n" +
            "    }\n" +
            "\n" +
            "    }";

    // 863313061440692
    final static String msg7 = "{\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":2,\n" +
            "        \"deviceSN\":\"810375990532853760\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeDataPollGet\"\n" +
            "    }\n" +
            "\n" +
            "    }";

    //863313061261676
    final static String msg8 = "{\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":2,\n" +
            "        \"deviceSN\":\"810375805173977088\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeDataPollGet\"\n" +
            "    }\n" +
            "\n" +
            "    }";


    //775067503625
    final static String msg9 = "\n" +
            "    {\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":0,\n" +
            "        \"deviceSN\":\"829137789064138752\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeHisDataPollGet\"\n" +
            "    },\n" +
            "    \"searchData\":{\n" +
            "        \"dataType\":3,\n" +
            "        \"beginTime\":1686103476000,\n" +
            "        \"endTime\":1688695476000\n" +
            "    }\n" +
            "    }";

    // 863313061440692
    final static String msg10 = "\n" +
            "    {\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":0,\n" +
            "        \"deviceSN\":\"810375990532853760\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeHisDataPollGet\"\n" +
            "    },\n" +
            "    \"searchData\":{\n" +
            "        \"dataType\":3,\n" +
            "        \"beginTime\":1686103476000,\n" +
            "        \"endTime\":1688695476000\n" +
            "    }\n" +
            "    }";


    //863313061261676
    final static String msg11 = "\n" +
            "    {\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":0,\n" +
            "        \"deviceSN\":\"810375805173977088\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeHisDataPollGet\"\n" +
            "    },\n" +
            "    \"searchData\":{\n" +
            "        \"dataType\":3,\n" +
            "        \"beginTime\":1686103476000,\n" +
            "        \"endTime\":1688695476000\n" +
            "    }\n" +
            "    }";
    //2c:6a:6f:00:ee:14
    final static String msg12 = "\n" +
            "    {\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":0,\n" +
            "        \"deviceSN\":\"798794619799293953\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeHisDataPollGet\"\n" +
            "    },\n" +
            "    \"searchData\":{\n" +
            "        \"dataType\":3,\n" +
            "        \"beginTime\":1686103476000,\n" +
            "        \"endTime\":1688695476000\n" +
            "    }\n" +
            "    }";

    //自研cat下发策略
    final static String msg21 = "{\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":0,\n" +
            "        \"deviceSN\":\"829137789064138752\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeSwitchPlanSet\"\n" +
            "    },\n" +
            "    \"lampNodeWorkSet\":[\n" +
            "        {\n" +
            "            \"loopId\":\"1\",\n" +
            "            \"segId\":\"1\",\n" +
            "            \"beginDate\":1687923235541,\n" +
            "            \"workModel\":{\n" +
            "                \"planOnTime\":\"16:00\",\n" +
            "                \"planOffTime\":\"21:00\",\n" +
            "                \"dimmer\":68\n" +
            "            }\n" +
            "            \n" +
            "        }\n" +
            "    ]\n" +
            "}";
    //自研cat查询策略
    final static String msg14 = "{\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":0,\n" +
            "        \"deviceSN\":\"829137789064138752\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeSwitchPlanGet\"\n" +
            "    },\n" +
            "    \"searchLampNodeWorkSet\":{\n" +
            "        \"beginDate\":1687923235123,\n" +
            "        \"days\":7\n" +
            "    }\n" +
            "}";
    //顺舟cat692下发策略
    final static String msg15 = "{\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":0,\n" +
            "        \"deviceSN\":\"810375990532853760\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeSwitchPlanSet\"\n" +
            "    },\n" +
            "    \"lampNodeWorkSet\":[\n" +
            "        {\n" +
            "            \"loopId\":\"1\",\n" +
            "            \"segId\":\"1\",\n" +
            "            \"beginDate\":1687923235541,\n" +
            "            \"workModel\":{\n" +
            "                \"planOnTime\":\"15:15\",\n" +
            "                \"planOffTime\":\"14:13\",\n" +
            "                \"dimmer\":88\n" +
            "            }\n" +
            "            \n" +
            "        }\n" +
            "    ]\n" +
            "}";

    //顺舟cat676下发策略
    final static String msg16 = "{\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":0,\n" +
            "        \"deviceSN\":\"810375805173977088\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeSwitchPlanSet\"\n" +
            "    },\n" +
            "    \"lampNodeWorkSet\":[\n" +
            "        {\n" +
            "            \"loopId\":\"1\",\n" +
            "            \"segId\":\"1\",\n" +
            "            \"beginDate\":1687923235541,\n" +
            "            \"workModel\":{\n" +
            "                \"planOnTime\":\"15:15\",\n" +
            "                \"planOffTime\":\"14:13\",\n" +
            "                \"dimmer\":88\n" +
            "            }\n" +
            "            \n" +
            "        }\n" +
            "    ]\n" +
            "}";
    //顺舟cat692查询策略
    final static String msg17 = "{\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":0,\n" +
            "        \"deviceSN\":\"810375990532853760\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeSwitchPlanGet\"\n" +
            "    },\n" +
            "    \"searchLampNodeWorkSet\":{\n" +
            "        \"beginDate\":1687923235123,\n" +
            "        \"days\":7\n" +
            "    }\n" +
            "}";

    //自研cat676查询策略
    final static String msg18 = "{\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":0,\n" +
            "        \"deviceSN\":\"810375805173977088\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeSwitchPlanGet\"\n" +
            "    },\n" +
            "    \"searchLampNodeWorkSet\":{\n" +
            "        \"beginDate\":1687923235123,\n" +
            "        \"days\":7\n" +
            "    }\n" +
            "}";


    final static String msg100 = "{\n" +
            "    \"lampNodeWorkSet\": [\n" +
            "        {\n" +
            "            \"workModel\": {\n" +
            "                \"planOnTime\": \"15:22\",\n" +
            "                \"planOffTime\": \"15:30\",\n" +
            "                \"dimmer\": 63\n" +
            "            },\n" +
            "            \"loopId\": 1,\n" +
            "            \"segId\": 1,\n" +
            "            \"beginDate\": 1688918400000\n" +
            "        },\n" +
            "        {\n" +
            "            \"workModel\": {\n" +
            "                \"planOnTime\": \"15:22\",\n" +
            "                \"planOffTime\": \"15:30\",\n" +
            "                \"dimmer\": 100\n" +
            "            },\n" +
            "            \"loopId\": 2,\n" +
            "            \"segId\": 1,\n" +
            "            \"beginDate\": 1688918400000\n" +
            "        }\n" +
            "    ],\n" +
            "    \"DeviceContext\": {\n" +
            "        \"deviceType\": 1,\n" +
            "        \"deviceSN\": \"829137789064138752\",\n" +
            "        \"hostSN\": \"\",\n" +
            "        \"commandID\": \"153814210074632\",\n" +
            "        \"isTranfs\": false,\n" +
            "        \"cmd\": \"lampNodeSwitchPlanSet\"\n" +
            "    }\n" +
            "}";

    final static String msg101 = "{\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":0,\n" +
            "        \"deviceSN\":\"829137789064138752\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeSwitchPlanGet\"\n" +
            "    },\n" +
            "    \"searchLampNodeWorkSet\":{\n" +
            "        \"beginDate\":1687923235123,\n" +
            "        \"days\":7\n" +
            "    }\n" +
            "}";
    final static String msg102 = "\n" +
            "    {\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":0,\n" +
            "        \"deviceSN\":\"829137789064138752\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeHisDataPollGet\"\n" +
            "    },\n" +
            "    \"searchData\":{\n" +
            "        \"dataType\":2,\n" +
            "        \"beginTime\":1686485935000,\n" +
            "        \"endTime\":1689163713737\n" +
            "    }\n" +
            "    }";
    final static String msg103 = "\n" +
            "    {\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":0,\n" +
            "        \"deviceSN\":\"810375990532853760\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeHisDataPollGet\"\n" +
            "    },\n" +
            "    \"searchData\":{\n" +
            "        \"dataType\":2,\n" +
            "        \"beginTime\":1686485935000,\n" +
            "        \"endTime\":1689163713737\n" +
            "    }\n" +
            "    }";

    final static String msg104 = "\n" +
            "    {\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":0,\n" +
            "        \"deviceSN\":\"810375805173977088\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeHisDataPollGet\"\n" +
            "    },\n" +
            "    \"searchData\":{\n" +
            "        \"dataType\":2,\n" +
            "        \"beginTime\":1686485935000,\n" +
            "        \"endTime\":1689163713737\n" +
            "    }\n" +
            "    }";

    final static String msg105 = "\n" +
            "    {\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":0,\n" +
            "        \"deviceSN\":\"798794619799293953\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeParamsSet\"\n" +
            "    },\n" +
            "    \"lampNodeParams\":{\n" +
            "        \"hostdomain\":\"115.236.153.174\",\n" +
            "        \"hostPort\":\"56936\",\n" +
            "        \"deviceSN\":\"798794619799293953\",\n" +
            "        \"APN\":\"\",\n" +
            "        \"pulseInterval\":150,\n" +
            "        \"autoUploadPeriod\":150,\n" +
            "        \"index\":1,\n" +
            "        \"controled\":1,\n" +
            "        \"loopNum\":1,\n" +
            "        \"version\":\"\",\n" +
            "        \"versionTime\":\"\",\n" +
            "        \"lampLoopParams\":[\n" +
            "            {\n" +
            "                \"loopid\":1,\n" +
            "                \"controled\":1,\n" +
            "                \"phase\":\"\",\n" +
            "                \"groupid\":1,\n" +
            "                \"planOnTime\":\"21:00\",\n" +
            "                \"planOffTime\":\"06:00\",\n" +
            "                \"dimmer\":12,\n" +
            "                \"lowerUAlarmLimit\":260,\n" +
            "                \"overUAlarmLimit\":170,\n" +
            "                \"lowerIAlarmLimit\":0.8,\n" +
            "                \"overIAlarmLimit\":1.0,\n" +
            "                \"leakageIProtectLimit\":0,\n" +
            "                \"leakageIAlarmLimit\":0,\n" +
            "                \"overProtectLimit\":0\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "\n" +
            "    }";

    //历史告警
    final static String msg112 = "\n" +
            "    {\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":0,\n" +
            "        \"deviceSN\":\"798794619799293953\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeHisDataPollGet\"\n" +
            "    },\n" +
            "    \"searchData\":{\n" +
            "        \"dataType\":2,\n" +
            "        \"beginTime\":1686103476000,\n" +
            "        \"endTime\":1690337710000\n" +
            "    }\n" +
            "    }";


    //历史工况
    final static String msg113 = "\n" +
            "    {\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":0,\n" +
            "        \"deviceSN\":\"829137789064138752\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeHisDataPollGet\"\n" +
            "    },\n" +
            "    \"searchData\":{\n" +
            "        \"dataType\":3,\n" +
            "        \"beginTime\":1686103476000,\n" +
            "        \"endTime\":1689737888000\n" +
            "    }\n" +
            "    }";

    //历史事件
    final static String msg114 = "\n" +
            "    {\n" +
            "    \"deviceContext\":{\n" +
            "        \"deviceType\":0,\n" +
            "        \"deviceSN\":\"829137789064138752\",\n" +
            "        \"hostSN\":\"\",\n" +
            "        \"isTranfs\":false,\n" +
            "        \"commandID\":342453,\n" +
            "        \"cmd\":\"lampNodeHisDataPollGet\"\n" +
            "    },\n" +
            "    \"searchData\":{\n" +
            "        \"dataType\":1,\n" +
            "        \"beginTime\":1689133088000,\n" +
            "        \"endTime\":1689737888000\n" +
            "    }\n" +
            "    }";

    final static String request2 = "CiAgICB7CiAgICAiZGV2aWNlQ29udGV4dCI6ewogICAgICAgICJkZXZpY2VUeXBlIjowLAogICAgICAgICJkZXZpY2VTTiI6Ijc5ODc5NDYxOTc5OTI5Mzk1MyIsCiAgICAgICAgImhvc3RTTiI6IiIsCiAgICAgICAgImlzVHJhbmZzIjpmYWxzZSwKICAgICAgICAiY29tbWFuZElEIjozNDI0NTMsCiAgICAgICAgImNtZCI6ImxhbXBOb2RlSGlzRGF0YVBvbGxHZXQiCiAgICB9LAogICAgInNlYXJjaERhdGEiOnsKICAgICAgICAiZGF0YVR5cGUiOjIsCiAgICAgICAgImJlZ2luVGltZSI6MTY4NjEwMzQ3NjAwMCwKICAgICAgICAiZW5kVGltZSI6MTY5MDMzNzcxMDAwMAogICAgfQogICAgfQ,CiAgICB7CiAgICAiZGV2aWNlQ29udGV4dCI6ewogICAgICAgICJkZXZpY2VUeXBlIjowLAogICAgICAgICJkZXZpY2VTTiI6Ijc5ODc5NDYxOTc5OTI5Mzk1MyIsCiAgICAgICAgImhvc3RTTiI6IiIsCiAgICAgICAgImlzVHJhbmZzIjpmYWxzZSwKICAgICAgICAiY29tbWFuZElEIjozNDI0NTMsCiAgICAgICAgImNtZCI6ImxhbXBOb2RlSGlzRGF0YVBvbGxHZXQiCiAgICB9LAogICAgInNlYXJjaERhdGEiOnsKICAgICAgICAiZGF0YVR5cGUiOjIsCiAgICAgICAgImJlZ2luVGltZSI6MTY4NjEwMzQ3NjAwMCwKICAgICAgICAiZW5kVGltZSI6MTY5MDMzNzcxMDAwMAogICAgfQogICAgfQ,CiAgICB7CiAgICAiZGV2aWNlQ29udGV4dCI6ewogICAgICAgICJkZXZpY2VUeXBlIjowLAogICAgICAgICJkZXZpY2VTTiI6Ijc5ODc5NDYxOTc5OTI5Mzk1MyIsCiAgICAgICAgImhvc3RTTiI6IiIsCiAgICAgICAgImlzVHJhbmZzIjpmYWxzZSwKICAgICAgICAiY29tbWFuZElEIjozNDI0NTMsCiAgICAgICAgImNtZCI6ImxhbXBOb2RlSGlzRGF0YVBvbGxHZXQiCiAgICB9LAogICAgInNlYXJjaERhdGEiOnsKICAgICAgICAiZGF0YVR5cGUiOjIsCiAgICAgICAgImJlZ2luVGltZSI6MTY4NjEwMzQ3NjAwMCwKICAgICAgICAiZW5kVGltZSI6MTY5MDMzNzcxMDAwMAogICAgfQogICAgfQ";

    public static void main(String[] args) throws InterruptedException {

        System.out.println(Base64.getEncoder().encodeToString(msg105.getBytes()));
    }

    public static Map<LocalDateTime/*开始时间*/, LocalDateTime/*结束时间*/> splitTime(Long startTime, Long endTime) {
        Map<LocalDateTime, LocalDateTime> map = new HashMap<>();
        LocalDateTime startDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), ZoneOffset.ofHours(8));
        LocalDateTime endDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(endTime), ZoneOffset.ofHours(8));
        while (endDateTime.isAfter(startDateTime)) {
            if (endDateTime.plusDays(-7).isAfter(startDateTime)) {//7天
                map.put(endDateTime.plusDays(-7), endDateTime);
            } else {
                map.put(startDateTime, endDateTime);
            }
            endDateTime = endDateTime.plusDays(-7);
        }
        return map;
    }

    public static String getMMddFromTimestamp(Long timestamp) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(8));
        return localDateTime.toLocalDate().format(DateTimeFormatter.ofPattern("MM-dd"));
    }

    public static double getLightPower(double voltage, double current) {
        BigDecimal b1 = new BigDecimal(Double.toString(voltage));
        BigDecimal b2 = new BigDecimal(Double.toString(current));
        return b1.multiply(b2, MathContext.DECIMAL64).doubleValue();
    }


    final static Map<Long, ArrayList<Long>> map = new HashMap<>();

    public static synchronized Long getRid(Long deviceId) {
        List<Long> longs = map.get(deviceId);
        if (longs == null || longs.isEmpty()) {
            ArrayList<Long> list = new ArrayList<>();
            list.add(1L);
            map.put(deviceId, list);
            return Long.parseLong(String.valueOf(list.size()));
        } else {
            if (longs.size() >= 65535) {
                longs.clear();
                longs.add(1L);
            } else {
                Long i = longs.size() + 1L;
                longs.add(i);
            }
            return Long.parseLong(String.valueOf(longs.size()));
        }
    }
}
