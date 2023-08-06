package com.mario.abr.up;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zxz
 * @date 2023年07月17日 18:11
 */

@Data
public class xxx {


    @JSONField(name="isOrg")
    private boolean isAlarm;

    public static void main(String[] args) {
        xxx xxx = new xxx();
        xxx.setAlarm(false);
        System.out.println(JSON.toJSONString(xxx));
        double v = new BigDecimal(149.0 / 1000).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(v);
    }
}
