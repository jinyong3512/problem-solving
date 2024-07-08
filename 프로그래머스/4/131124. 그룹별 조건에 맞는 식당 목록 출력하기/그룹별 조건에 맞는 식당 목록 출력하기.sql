#  회원 이름, 리뷰 텍스트, 리뷰 작성일
SELECT
    X.MEMBER_NAME,
    Y.REVIEW_TEXT,
    DATE_FORMAT(Y.REVIEW_DATE,'%Y-%m-%d') AS REVIEW_DATE
FROM
    MEMBER_PROFILE AS X
INNER JOIN
    (
    SELECT
        A.MEMBER_ID,
        A.REVIEW_TEXT,
        A.REVIEW_DATE
    FROM
        REST_REVIEW AS A
    INNER JOIN
        (
        SELECT
            MEMBER_ID
        FROM
            REST_REVIEW
        GROUP BY
            MEMBER_ID
        ORDER BY
            COUNT(MEMBER_ID) DESC
        LIMIT 
            1
        ) AS B ON A.MEMBER_ID = B.MEMBER_ID
    ) AS Y ON X.MEMBER_ID = Y.MEMBER_ID
ORDER BY
    Y.REVIEW_DATE ASC,
    Y.REVIEW_TEXT ASC