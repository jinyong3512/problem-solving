SELECT
    *
FROM (
    SELECT
        FH.flavor
    FROM FIRST_HALF FH 
    LEFT OUTER JOIN (
        SELECT
            flavor,
            SUM(total_order) AS sum_total_order
        FROM JULY
        GROUP BY
            flavor
    ) J2
        ON FH.flavor = J2.flavor
    ORDER BY
        FH.total_order + J2.sum_total_order DESC
)
WHERE
    ROWNUM < 4