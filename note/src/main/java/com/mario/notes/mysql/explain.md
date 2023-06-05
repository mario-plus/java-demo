1. id
    id相同：执行顺序由上至下
    id不同：值越大优先级越高，越先被执行
2. select_type
    SIMPLE：简单的select查询，查询中不包含子查询或者UNION
    PRIMARY：查询中若包含任何复杂的子部分，最外层查询则被标记为PRIMARY
    SUBQUERY：在SELECT或WHERE列表中包含了子查询
    DERIVED：在FROM列表中包含的子查询被标记为DERIVED（衍生），MySQL会递归执行这些子查询，把结果放在临时表中
    UNION：若第二个SELECT出现在UNION之后，则被标记为UNION：若UNION包含在FROM子句的子查询中，外层SELECT将被标记为：DERIVED
    UNION RESULT：从UNION表获取结果的SELECT
3. table
    当前操作执行的表
4. type
    查询使用了哪种类型（system > const > eq_ref > ref > range > index > all）
    system：表中只有一个记录
    const：表示通过索引一次就找到
    eq_ref：唯一性索引，对应每一个索引键，表中只有一条记录与之匹配，常用于主键或唯一索引扫描
    ref: 非唯一性索引扫描,本质上也是一种索引访问 
    range: 只检索给定范围的行，使用一个索引来选择行（between、< 、>、in等的查询）
    index：Full Index Scan，Index与All区别为index类型只遍历索引树。这通常比ALL快，因为索引文件通常比数据文件小
    all：Full Table Scan 将遍历全表以找到匹配的行
5. possible_keys，key 和 key_len
    possible_keys 显示可能应用在这张表中的索引
    key 实际使用的索引，如果为NULL，则没有使用索引
    key_len 显示的值为索引字段的最大可能长度（在不损失精确性的情况下，长度越短越好）
6. ref
    显示索引的那一列被使用了，如果可能的话，最好是一个常数
7. rows
    根据表统计信息及索引选用情况，大致估算出找到所需的记录所需要读取的行数，也就是说，用的越少越好
8. Extra
    额外信息，但是相当重要
    Using filesort：文件排序，mysql会对数据使用一个外部的索引排序，而不是按照表内的索引顺序进行读取（常见于 order by 非索引）
    Using temporary：使用了用临时表保存中间结果，MySQL在对查询结果排序时使用临时表（常见于排序order by和分组查询group by）
    Using index：表示相应的select操作中使用了覆盖索引（Covering Index），避免访问了表的数据行，效率不错
        如果同时出现 Using where，表明索引被用来执行索引键值的查找
        如果没有出现 Using where，表明索引用来读取数据而非执行查找动作
    Using join buffer：表明使用了连接缓存,比如说在查询的时候，多表join的次数非常多，那么将配置文件中的缓冲区的join buffer调大一些
    impossible where：where子句的值总是false，不能用来获取任何元组
    distinct：优化distinct操作，在找到第一匹配的元组后即停止找同样值的动作
    select tables optimized away：在没有GROUPBY子句的情况下，基于索引优化MIN/MAX操作或者对于MyISAM存储引擎优化COUNT(*)操作，不必等到执行阶段再进行计算，查询执行计划生成的阶段即完成优化
    
最左匹配原则：
    指的是联合索引中，优先走最左边列的索引。对于多个字段的联合索引，也同理
    如 index(a,b,c) 联合索引，则相当于创建了 a 单列索引，(a,b)联合索引，和(a,b,c)联合索引
    
        
    