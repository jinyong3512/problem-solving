SELECT
    A.CART_ID
FROM
    (
    SELECT
        *
    FROM
        CART_PRODUCTS
    WHERE
        NAME = 'Milk' OR NAME = 'Yogurt'
    ) AS A
INNER JOIN
    (
    SELECT
        *
    FROM
        CART_PRODUCTS
    WHERE
        NAME = 'Milk' OR NAME = 'Yogurt'
    ) AS B ON A.CART_ID = B.CART_ID
WHERE 
    A.NAME = 'Milk' AND B.NAME = 'Yogurt'
GROUP BY
    A.CART_ID
ORDER BY
    A.CART_ID ASC
    