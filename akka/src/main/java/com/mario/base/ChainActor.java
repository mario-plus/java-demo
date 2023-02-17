package com.mario.base;

import akka.actor.*;
import com.mario.SchoolMemberInfo;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author zxz
 * @date 2023年02月17日 14:37
 */
public class ChainActor extends UntypedAbstractActor {

    private ActorRef selfRef;
    private String name;
    AtomicInteger num = new AtomicInteger(0);


    public ChainActor(String name) {
        this.name = name;
        this.selfRef = SchoolMemberInfo.teacherRef.get(name);
    }


    public void onReceive(Object message) throws Throwable {
        String s = message.toString();
        if (s.startsWith(SchoolMemberInfo.headName)) {
            toDown(message);
        } else {
            toUp(message);
        }
    }

    private void toDown(Object message) {
        System.out.println("收到校长通知:" + message.toString());
        SchoolMemberInfo.students.get(name).forEach(s -> {
            SchoolMemberInfo.studentRef.get(s).tell("学生们交一下学费：", selfRef);
        });
    }


    private boolean calculate(Object message) {
        num.getAndIncrement();
        return num.get() >= SchoolMemberInfo.students.get(name).size();
    }

    private void toUp(Object message) {
        System.out.println(name + "收到来自学生的学费");
        if (calculate(message)) {
            SchoolMemberInfo.headRef.tell(name + "说：所有同学的学费都已经交齐", selfRef);
        }

    }

}
