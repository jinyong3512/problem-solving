SELECT
    SUBSTR(product_code, 1, 2),
    COUNT(*)
FROM PRODUCT
GROUP BY
    SUBSTR(product_code, 1, 2)
ORDER BY
    SUBSTR(product_code, 1, 2) ASC