-- 2021년에 가입한 전체 회원들 중 상품을 구매한 회원수와 상품을 구매한 회원의 비율(=2021년에 가입한 회원 중 상품을 구매한 회원수 / 2021년에 가입한 전체 회원 수)을 년, 월 별로 출력

SELECT
    TO_CHAR(sales_date, 'YYYY') AS YEAR,
    TO_NUMBER(TO_CHAR(sales_date, 'MM')) AS MONTH,
    COUNT(DISTINCT user_id) AS PURCHASED_USERS,
    ROUND(
        COUNT(DISTINCT user_id) / (
            SELECT
                COUNT(DISTINCT user_id)
            FROM USER_INFO
            WHERE
                TO_CHAR(joined, 'YYYY') = '2021'    
        )
    , 1) AS PURCHASED_RATIO
FROM ONLINE_SALE
WHERE user_id IN (
    SELECT
        DISTINCT user_id
    FROM USER_INFO
    WHERE
        TO_CHAR(joined, 'YYYY') = '2021'    
    )
GROUP BY
    TO_CHAR(sales_date, 'YYYY'),
    TO_CHAR(sales_date, 'MM')
ORDER BY
    YEAR ASC,
    MONTH ASC