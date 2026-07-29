# Write your MySQL query statement below
SELECT pro.product_name, s.year, s.price
FROM Sales AS s
LEFT JOIN Product AS pro
ON s.product_id = pro.product_id;