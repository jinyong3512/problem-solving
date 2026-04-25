-- 2022년 5월 1일을 기준으로 주문 ID, 제품 ID, 출고일자, 출고여부를 조회
-- 출고여부는 2022년 5월 1일까지 출고완료로 이 후 날짜는 출고 대기로 미정이면 출고미정으로 출력

SELECT
    order_id,
    product_id,
    TO_CHAR(out_date, 'YYYY-MM-DD'),
    CASE 
        WHEN out_date is NULL THEN '출고미정'
        WHEN TO_CHAR(out_date, 'YYYY-MM-DD') <= '2022-05-01' THEN '출고완료'
        WHEN TO_CHAR(out_date, 'YYYY-MM-DD') > '2022-05-01' THEN '출고대기'        
    END AS 출고여부
FROM FOOD_ORDER 
ORDER BY
    order_id ASC
