SELECT
    II.ingredient_type,
    SUM(FH.total_order) AS total_order
FROM FIRST_HALF FH
INNER JOIN ICECREAM_INFO II
    ON FH.flavor = II.flavor
GROUP BY 
    II.ingredient_type
ORDER BY
    total_order ASC;
