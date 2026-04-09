SELECT
    FP.product_id, 
    FP.product_name,
    SUM(FO.amount) * FP.price AS TOTAL_SALES
FROM FOOD_ORDER FO
LEFT OUTER JOIN FOOD_PRODUCT FP
    ON FO.product_id = FP.product_id
WHERE
    TO_CHAR(FO.produce_date, 'YYYY-MM') = '2022-05'
GROUP BY
    FP.product_id, 
    FP.product_name,
    FP.price
ORDER BY
    TOTAL_SALES DESC,
    Fp.product_id ASC