-- 코드를 입력하세요

-- 식당 ID, 식당 이름, 음식 종류, 즐겨찾기수, 주소, 리뷰 평균 점수
SELECT 
    C.REST_ID
  , C.REST_NAME
  , C.FOOD_TYPE
  , C.FAVORITES
  , C.ADDRESS
  , B.REVIEW_SCORE2
  FROM 
    REST_INFO C
INNER JOIN 
    (
    SELECT
        A.REST_ID
      , ROUND(AVG(A.REVIEW_SCORE), 2) AS REVIEW_SCORE2
    FROM
        REST_REVIEW A
    GROUP BY
        A.REST_ID    
    ) B ON C.REST_ID = B.REST_ID
WHERE
    SUBSTR(C.ADDRESS, 1, 2) = '서울'
ORDER BY
    B.REVIEW_SCORE2 DESC, C.FAVORITES DESC;