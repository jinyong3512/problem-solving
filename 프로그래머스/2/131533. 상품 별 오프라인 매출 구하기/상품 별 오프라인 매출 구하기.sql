-- 상품코드 별 매출액(판매가 * 판매량) 합계를 출력
-- 매출액을 기준으로 내림차순 정렬해주시고 매출액이 같다면 상품코드를 기준으로 오름차순 정렬

SELECT
    P.product_code,
    SUM(OS.sales_amount * price) AS price2
FROM OFFLINE_SALE OS
INNER JOIN PRODUCT P
    ON OS.product_id = P.product_id
GROUP BY
    P.product_code
ORDER BY
    price2 DESC,
    P.product_code ASC