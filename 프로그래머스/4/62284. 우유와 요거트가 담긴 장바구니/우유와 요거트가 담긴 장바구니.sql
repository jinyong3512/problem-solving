SELECT
    A.CART_ID
FROM
    (
    SELECT
        DISTINCT(CART_ID)
    FROM
        CART_PRODUCTS
    WHERE
        NAME = 'Milk'
    ) AS A
INNER JOIN
    (
    SELECT
        DISTINCT(CART_ID)
    FROM
        CART_PRODUCTS
    WHERE
        NAME = 'Yogurt'
    ) AS B ON A.CART_ID = B.CART_ID
ORDER BY
    A.CART_ID ASC