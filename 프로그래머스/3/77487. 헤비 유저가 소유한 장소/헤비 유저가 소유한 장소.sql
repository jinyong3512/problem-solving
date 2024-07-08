SELECT
    A.ID,
    A.NAME,
    A.HOST_ID
FROM
    PLACES AS A
INNER JOIN
    (
    SELECT
        HOST_ID
    FROM
        PLACES
    GROUP BY
        HOST_ID
    HAVING
        COUNT(HOST_ID) >=2    
    ) AS B ON A.HOST_ID = B.HOST_ID
ORDER BY
    A.ID ASC
