1. CMS回收器
    老年代回收器，基于标记-清除算法
    默认启动线程数=（CPU数+3）/4，当CPU<4,线程数不小于25%
    流程：
    1. 初始标记：标记GcRoot直接关联对象，短暂的STW
    2. 并发标记：GcRoot向下追溯，标记GcRoot间接关联的对象
    3. 重新标记：标记期间产生的对象存活的再次判断，修正对象标记，短暂的STW
    4. 并发清楚：清楚对象
    缺点：基于标记清除算法，无法避免空间碎片问题
         无法处理浮动垃圾（CMS并发清理阶段用户线程还在运行着，伴随程序运行自然还会有新的垃圾不断产生）
         
2. G1垃圾收集器
    将整个Java堆划分成约2048个大小相同的独立**Region**块(1-32M,2的N次幂),大小相同，且在JVM生命周期内不会被改变
    新生代和老年代不再是物理隔离
    **Humongous**内存区域，主要用于存储大对象，如果超过0.5个region, 就放到Humongous
    特点：
    并行和并发：（多个GC线程并行工作，用户线程和GC线程并发执行）
    分代收集：以region替代新生代和老年代
    空间整合：（内存的回收是以region作为基本单位的，Region之间是复制算法）
    可预测的停顿时间模型：优先回收价值最大的Region。保证了G1收集器在有限的时间内可以获取尽可能高的收集效率
    缺点：
    内存占用，执行负载都比CMS要高（小内存应用上CMS的表现优于G1）
    垃圾回收过程：
    年轻代GC：当年轻代的Eden区用尽时开始年轻代回收过程；G1的年轻代收集阶段是一个并行(多个垃圾线程)的独占式收集器，只会回收Eden区和Survivor区
    老年代GC：当堆内存使用达到一定值(默认45%)时,开始老年代并发标记过程
    混合回收：标记完成马上开始混合回收过程，G1的老年代回收器不需要整个老年代被回收,一次只需要扫描/回收一小部分老年代的Region就可以了
    
    记忆集和写屏障
    问题：Region中的对象可能被任意Region中的对象引用（如新生代引用了老年代），这样垃圾回收回去扫描整个老年代STW，判断对象存活，是否
    需要扫描整个java堆才能保证正确性
    解决方法：每个Region都有一个对应的Remembered Set，使用Remembered Set来避免全局扫描
             通过CardTable把相关引用信息记录到引用指向对象的所在Region对应的Remembered Set中
             
             

jvm调优
    核心指标
    jvm.gc.time:每分钟的gc耗时在1s内，500ms以内更佳
    jvm.gc.meattime:每次YGC在100ms内，50ms更加
    jvm.fullgc.time:每次fullGc耗时在1s内，500ms以内更佳
    jvm.fullgc.count:FGC最多几个小时一次，1天不到一次更佳
  
    
   工具
       bin/jvisualvm  
       jprofile(可看到每个线程运行情况)
   优化步骤
        ps -ef | grep java
        top：显示系统各个进程的资源使用情况
        top -Hp pid：查看某个进程中的线程占用情况
        jstack pid: 查看当前 Java 进程的线程堆栈信息
        jinfo pid:  查看 Java 进程的配置信息，包括系统属性和JVM命令行标志
        jstat -gc pid: 输出 Java 进程当前的 gc 情况
        jmap -heap pid: 输出 Java 堆详细信息
        jmap -histo:live pid 显示堆中对象的统计信息
        jmap -F -dump:format=b,file=dumpFile.phrof pid 生成 Java 堆存储快照dump文件
   JVM 的 GC指标一般是从 GC 日志里面查看，默认的 GC 日志可能比较少
        -XX:+PrintGCDetails  // 打印GC的详细信息
        -XX:+PrintGCDateStamps // 打印GC的时间戳
        -XX:+PrintHeapAtGC  // 在GC前后打印堆信息
        -XX:+PrintTenuringDistribution  // 打印Survivor区中各个年龄段的对象的分布信息
        -XX:+PrintFlagsFinal    // JVM启动时输出所有参数值，方便查看参数是否被覆盖
        -XX:+PrintGCApplicationStoppedTime  // 打印GC时应用程序的停止时间
        -XX:+PrintReferenceGC   // 打印在GC期间处理引用对象的时间（仅在PrintGCDetails时启用）
    
    
    
    
    
用户线程CPU占用过高
    java -jar arthas-boot.jar
    thread
    thread pid
GC线程占有CPU过高
    
    
    
    
    
    
    
    
    
    
    