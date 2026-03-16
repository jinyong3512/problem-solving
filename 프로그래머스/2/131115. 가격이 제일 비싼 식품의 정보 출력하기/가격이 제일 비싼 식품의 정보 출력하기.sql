-- SELECT
--     *
-- FROM
--     FOOD_PRODUCT
-- ORDER BY
--     PRICE DESC;

-- P0051 맛있는배추김치 CD_KC00001 김치 19000

SELECT 
    *
FROM 
    (
    SELECT
        *
    FROM
        FOOD_PRODUCT
    ORDER BY
        PRICE DESC
    )
WHERE 
    ROWNUM < 2;
   