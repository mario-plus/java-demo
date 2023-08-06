1. springCloud核心组件
    服务注册中心：Nacos，Eureka
    远程调用：Feign，openFeign
    负载均衡：Ribbon
    断路器：Hystrix，Sentinel
    网关：zuul
    A 可用性 P分区容错性 C 一致性
2. Eureka面试题（AP）
    注册服务：客户端将服务信息封装成对象，调用http接口注册，服务端使用ConcurrentHashMap保存
    客户端拉取服务器注册实例（定时）：通过ScheduledExecutorService默认周期60s（第一次全量，第二次增量）
    心跳机制（定时）：通过ScheduledExecutorService默认周期30s，通过调用Http接口维持心跳
    服务剔除机制：
        开启自我保护机制：所有的客户端包括没有长时间没有发送心跳的客户端都不会被剔除
        没开启自我保护机制：每隔一段时间默认60s将超时默认90的服务剔除
3. Nacos面试题
    Nacos中保护阈值的作用是什么：保证部分请求能正常访问（10个服务只剩2个，大量的请求会导致最后2个服务崩溃）
        将阈值设置成0.3时，当10个服务只剩3个时，会将可用，不可用都拿来使用，给最后几个分担压力，尽可能保证可用
    临时节点（AP）
        心跳机制：心跳检测（Netty，5秒一次心跳，15秒不健康，30秒删除服务实例）
    非临时节点（CP）
        心跳机制：主动轮询（不会直接剔除非临时节点）
    客户端拉取服务器注册实例：客户端定时拉取，服务端主动推送变更信息
4. openFeign面试题
    Feign：集成了Ribbon、RestTemplate实现了负载均衡的执行Http调用
    OpenFeign：在Feign的基础上支持了SpringMVC的注解
    
    超时如何处理？（设置openFeign的超时时间）
        openFeign其实是有默认的超时时间的，默认分别是连接超时时间10秒、读超时时间60秒（feign.Request.Options#Options()）
        openFeign集成了Ribbon，Ribbon的默认超时连接时间、读超时时间都是是1秒（FeignLoadBalancer#execute()）
    如何替换默认的httpclient？（使用ApacheHttpClient，OkHttp）
        使用的是JDK原生的URLConnection发送HTTP请求，没有连接池，但是对每个地址会保持一个长连接
    如何通讯优化？（使用Gzip压缩传输数据，feign.compression.request...）
5. 熔断降级
    服务由于响应慢、异常等原因触发熔断策略后，快速失败，避免线程堆积造成服务雪崩

6. ribbon实现原理
    定时从服务注册中心拉去服务列表，保存在BaseLoadBalancer，通过Ping判断服务是否可以连接使用
    核心组件IRule，
    实现方法 RoundRobinRule（轮询），
            WeightedResponseTimeRule（权重），
            ZoneAvoidanceRule（区域），
            BestAvailableRule（最好的机器），
            RandomRule（随机），
            RetryRule（失败可重试）
        
        
            
        

    