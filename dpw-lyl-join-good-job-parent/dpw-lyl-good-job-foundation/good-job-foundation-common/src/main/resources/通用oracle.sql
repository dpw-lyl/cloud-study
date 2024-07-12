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

DELETE FROM table t
WHERE EXISTS (
    SELECT 1
    FROM (
        SELECT *,
               ROW_NUMBER() OVER (PARTITION BY fieldName ORDER BY id) AS row_num
        FROM table
    ) t2
    WHERE t2.row_num > 1 AND t.id = t2.id
);
/


CREATE TABLE temp_table AS
SELECT *
FROM (
    SELECT *,
           ROW_NUMBER() OVER (PARTITION BY fieldName ORDER BY id) AS row_num
    FROM table
)
WHERE row_num = 1;

TRUNCATE TABLE table;

INSERT INTO table
SELECT *
FROM temp_table;

DROP TABLE temp_table;

