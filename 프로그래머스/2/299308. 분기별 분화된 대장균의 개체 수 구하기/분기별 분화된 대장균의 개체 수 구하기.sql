SELECT
    CONCAT(
        FLOOR(
            (
                DATE_FORMAT(DIFFERENTIATION_DATE,'%m')-1
            )
            /3 +1
        ),
        'Q'
    ) AS QUARTER,
    COUNT(*) AS ECOLI_COUNT
FROM
    ECOLI_DATA AS ED
GROUP BY
        CONCAT(
        FLOOR(
            (
                DATE_FORMAT(DIFFERENTIATION_DATE,'%m')-1
            )
            /3 +1
        ),
        'Q'
    )
ORDER BY
    QUARTER ASC

# SELECT * FROM ECOLI_DATA