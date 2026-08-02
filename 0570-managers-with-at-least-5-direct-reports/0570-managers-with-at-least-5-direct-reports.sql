# Write your MySQL query statement below
SELECT e.name
FROM Employee e
JOIN Employee m ON e.id = m.managerID
GROUP BY e.id, e.name -- e.name is included in GROUP BY for MySQL ONLY_FULL_GROUP_BY strict compatibility, even though e.id is unique. If it id.
HAVING COUNT(m.id) >= 5;