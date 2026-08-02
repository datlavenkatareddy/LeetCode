# Write your MySQL query statement below
SELECT e.name
FROM Employee e
JOIN Employee m ON e.id = m.managerID
GROUP BY e.id, e.name
HAVING COUNT(m.id) >= 5;