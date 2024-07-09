SELECT
    A.EMP_NO,
    A.EMP_NAME,
    IF(
        B.AVG_SCORE>=96,
        'S',
        IF(
            B.AVG_SCORE >= 90,
            'A',
            IF(
                B.AVG_SCORE>=80,
                'B',
                'C'
            )
        )
    ) AS GRADE,
    IF(
        B.AVG_SCORE>=96,
        A.SAL * 0.2,
        IF(
            B.AVG_SCORE >= 90,
            A.SAL * 0.15,
            IF(
                B.AVG_SCORE>=80,
                A.SAL * 0.1,
                A.SAL * 0.0
            )
        )
    ) AS BONUS
FROM
    HR_EMPLOYEES AS A
INNER JOIN
    (
    SELECT
        EMP_NO,
        AVG(SCORE) AS AVG_SCORE
    FROM
        HR_GRADE
    GROUP BY
        EMP_NO    
    ) AS B ON A.EMP_NO = B.EMP_NO
ORDER BY
    A.EMP_NO ASC