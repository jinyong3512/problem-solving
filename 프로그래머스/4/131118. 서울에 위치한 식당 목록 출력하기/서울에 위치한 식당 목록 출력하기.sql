SELECT -- 식당 ID, 식당 이름, 음식 종류, 즐겨찾기수, 주소, 리뷰 평균 점수
    A.REST_ID,
    A.REST_NAME,
    A.FOOD_TYPE,
    A.FAVORITES,
    A.ADDRESS,
    B.REVIEW_SCORE2 AS SCORE
FROM 
    REST_INFO A
INNER JOIN (
-- LEFT OUTER JOIN (
    SELECT
        REST_ID,
        ROUND(AVG(REVIEW_SCORE), 2) AS REVIEW_SCORE2
    FROM
        REST_REVIEW 
    GROUP BY
        REST_ID 
    ) B
    ON A.REST_ID = B.REST_ID
WHERE -- 서울에 위치한 식당들
    SUBSTR(A.ADDRESS, 1, 2) = '서울'
ORDER BY -- 평균점수를 기준으로 내림차순 정렬해주시고, 평균점수가 같다면 즐겨찾기수를 기준으로 내림차순
    SCORE DESC, FAVORITES DESC;
    
-- 1. REST_REVIEW를 GROUP BY 했을때는 REST_ID가 NULL인 애들도 있음
-- 2. 근데 어차피 LEFT OUTER JOIN하면서 사라짐.
-- 3. INNER JOIN 하면 문제 틀리는 이유는 REST_INFO에는 있는 REST_ID가 REST_REVIEW에는 없을수도 있음