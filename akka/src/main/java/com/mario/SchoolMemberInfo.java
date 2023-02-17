package com.mario;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.mario.base.ChainActor;
import com.mario.base.NodeActor;
import com.mario.base.RootActor;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zxz
 * @date 2023年02月17日 15:21
 */
public class SchoolMemberInfo {

    public static final String headName = "周校长";
    public static final List<String> teachers = Arrays.asList("赵主任", "王主任", "李主任", "吴主任");
    public static final Map<String, List<String>> students = new HashMap<>();
    public static final ActorRef headRef = ActorSystem.create("school").actorOf(Props.create(RootActor.class, headName));
    public static final HashMap<String, ActorRef> teacherRef = new HashMap<>();
    public static final HashMap<String, ActorRef> studentRef = new HashMap<>();


    static {
        students.put("赵主任", Arrays.asList("赵1", "钱1", "孙1", "李1", "周1"));
        students.put("王主任", Arrays.asList("赵2", "钱2", "孙2", "李2", "周2"));
        students.put("李主任", Arrays.asList("赵3", "钱3", "孙3", "李3", "周3"));
        students.put("吴主任", Arrays.asList("赵4", "钱4", "孙4", "李4", "周4"));
        ActorSystem cla = ActorSystem.create("class");
        teachers.forEach(e -> {
            ActorRef actorRef = cla.actorOf(Props.create(ChainActor.class, e));
            teacherRef.put(e, actorRef);
            ActorSystem stu = ActorSystem.create("student");
            students.get(e).forEach(s -> {
                ActorRef stuRef = stu.actorOf(Props.create(NodeActor.class, s));
                studentRef.put(s, stuRef);
            });

        });
    }

    public static String getTeacher(String stuName) {

        AtomicReference<String> teacher = new AtomicReference<>();
        students.forEach((k, v) -> {
            if (v.contains(stuName)) {
                teacher.set(k);
            }
        });
        return teacher.get();
    }


}
