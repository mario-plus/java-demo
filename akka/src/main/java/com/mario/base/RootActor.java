package com.mario.base;


import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import com.mario.SchoolMemberInfo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zxz
 * @date 2023年02月17日 14:37
 */
public class RootActor extends UntypedAbstractActor {


    private String name;
    private ActorRef selfActorRef;
    static AtomicInteger num = new AtomicInteger();

    public RootActor(String name) {
        this.name = name;
        this.selfActorRef = SchoolMemberInfo.headRef;
    }

    public void onReceive(Object message) throws Throwable {
        String s = message.toString();
        if (s.startsWith("通知")) {
            toDown(message);
        } else {
            toUp(message);
        }
    }

    private void toDown(Object message) {
        System.out.println("收到教育局通知:" + message);
        SchoolMemberInfo.teacherRef.forEach((k, v) -> v.tell(name + ":" + k + "把班级的学费收一下", selfActorRef));
    }

    private void toUp(Object message) {
        if (calculate(message)) {
            System.out.println("各班主任都已经上交班级学费，将钱打入教育局账户");
        }

    }

    private static boolean calculate(Object message) {
        System.out.println("收到各班主任汇款");
        num.getAndIncrement();
        return num.get() >= SchoolMemberInfo.teachers.size();
    }

}
