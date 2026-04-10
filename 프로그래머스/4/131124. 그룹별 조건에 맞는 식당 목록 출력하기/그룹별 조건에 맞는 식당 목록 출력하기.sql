-- 리뷰를 가장 많이 작성한 회원의 리뷰들을 조회


SELECT -- 회원 이름, 리뷰 텍스트, 리뷰 작성일이 출력
    MP.member_name,
    RR4.review_text,
    TO_CHAR(RR4.review_date, 'YYYY-MM-DD') AS REVIEW_DATE
FROM MEMBER_PROFILE MP
INNER JOIN (
    
    SELECT
        *
    FROM REST_REVIEW RR3
    WHERE
        RR3.member_id IN (
        SELECT member_id
        FROM (
            SELECT
                RR2.member_id,
                COUNT(*) AS count
            FROM REST_REVIEW RR2
            GROUP BY
                RR2.member_id
        )
        WHERE   
            count = (
            SELECT         
                MAX(COUNT(*))
            FROM REST_REVIEW RR
            GROUP BY
                RR.member_id
            )
        )
    
) RR4
    ON MP.member_id = RR4.member_id
ORDER BY -- 리뷰 작성일을 기준으로 오름차순, 리뷰 작성일이 같다면 리뷰 텍스트를 기준으로 오름차순 정렬
    RR4.review_date ASC,
    RR4.review_text ASC