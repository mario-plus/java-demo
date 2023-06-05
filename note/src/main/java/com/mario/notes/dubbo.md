1. dubbo工作原理（10层）
   config层（dubbo配置）
   service层（开发者实现的接口功能）
   register层（注册中心）
   proxy层（服务代理层，基于接口调用）
   客户端调用（protocol层：远程调用层，封装RPC调用；
              exchange层：信息交换层，封装请求响应模式，同步转异步；
              serialize层：数据序列化层
              transport层：网络传输层抽象mina和netty；）
    monitor：监控层，对RCP调用时间和调用次数监控
    cluster：封装多个服务提供者的路由以及负载均衡，将多个实例组合成一个服务

    如何设计一个RPC框架：
    1.服务注册中心（register）
    2.基于接口调用，服务代理层（proxy）
    3.调用过程（Protocol：封装RPC调用，exchange：信息交换层，transport：网络传输层，serialize：序列化）
    4.监控层（monitor：调用日志，次数，耗时等）
    5.集群部署（cluster）
    6.动态配置（config）
    7.service层
2. 注册中心挂了，consumer还能不能调用provider
    consumer会将需要的所有提供者的地址等信息拉取到本地缓存，所以注册中心挂了可以继续通信。但是provider挂了，那就没法调用了

3. 怎么实现动态感知服务下线的呢？
   采用是事件通知与客户端拉取方式（zookeeper）
   第一次订阅的时候将会拉取对应目录下全量数据，然后在订阅的节点注册一个watcher，数据发生变化，通过watcher通知客户端
   客户端收到通知，则会重新拉取全量数据，并重新注册watcher
4. Dubbo负载均衡策略？
    随机
    轮询
    活跃度
    hash
5. Dubbo与Spring Cloud的区别？
    通信方式（Dubbo使用的是RPC通信，Spring Cloud使用的是HTTP RestFul方式）
    注册中心（Dubbo使用Zookeeper，redis等，Spring Cloud使用Eureka）
    监控：Dubbo-monitor，Spring Cloud使用的是Spring Boot admin
    其他组件，springCloud都有，dubbo还是空白