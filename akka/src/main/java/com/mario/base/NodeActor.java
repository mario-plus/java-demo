package com.mario.base;

import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import com.mario.SchoolMemberInfo;


/**
 * @author zxz
 * @date 2023年02月17日 14:37
 */
public class NodeActor extends UntypedAbstractActor {

    private String name;
    private boolean flag = false;

    private ActorRef selfActorRef;

    public NodeActor(String name) {
        this.name = name;
        this.selfActorRef = SchoolMemberInfo.studentRef.get(name);
    }

    public void onReceive(Object message) throws Throwable {
        System.out.println(name + "收到来自老师的通知：" + message.toString());
        if (!flag) {
            SchoolMemberInfo.teacherRef.get(SchoolMemberInfo.getTeacher(name)).tell(name + "上交学费",selfActorRef );
            flag = true;
        }
    }


}
