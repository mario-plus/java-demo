package com.mario;

import akka.actor.ActorRef;



/**
 * @author zxz
 * @date 2023年02月17日 14:59
 */
public class TransportT {


    public static void main(String[] args) {

        SchoolMemberInfo.headRef.tell("通知：收一下学费", ActorRef.noSender());

    }
}
