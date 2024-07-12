-- 统计重复数据
select fieldName,count(1) from table group by fieldName HAVING count(1) > 1;

-- 重复具体数据
SELECT *
FROM table
WHERE fieldName IN (
    SELECT fieldName
    FROM table
    GROUP BY fieldName
    HAVING COUNT(1) > 1
);
-- 删除重复数据并且保留一条数据

WITH cte AS (
    SELECT *,
           ROW_NUMBER() OVER (PARTITION BY fieldName ORDER BY id) AS row_num
    FROM table
)
DELETE FROM cte
WHERE row_num > 1;

-- 大表分页查询优化 1

select a.id,a.fieldName1,a.fieldName2,b.fieldName1,b.fieldName2 from table1 a left join table2 b on a.id=b.id  where a.id >= ( select id from table order by id limit m,1 )
order by a.id limit n;

select a.id,a.fieldName1,a.fieldName2,b.fieldName1,b.fieldName2 from table1 a left join table2 b on a.id=b.id
WHERE id > last_known_id /* 上次请求返回的最后一个id */
ORDER BY id
LIMIT n;

-- mysql5.6及以上并发创建索引
ALTER TABLE your_table ADD INDEX idx_name(column_name) ALGORITHM = INPLACE LOCK = NONE;

-- 大表创建新的索引安全做法是创建临时表创建索引，然后导入原表数据，通过RENAME TABLE 替换原表

