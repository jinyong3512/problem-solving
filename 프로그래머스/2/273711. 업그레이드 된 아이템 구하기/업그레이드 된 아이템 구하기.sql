# PARENT_ITEM_ID 가 RARE인것
SELECT
    X.ITEM_ID,
    X.ITEM_NAME,
    X.RARITY
FROM
    ITEM_INFO AS X
INNER JOIN
    (
    SELECT
        IT.ITEM_ID AS UP,
        IT.PARENT_ITEM_ID AS DOWN
    FROM 
        ITEM_TREE AS IT
    INNER JOIN
        (
        SELECT
            ITEM_ID
        FROM
            ITEM_INFO
        WHERE
            RARITY = 'RARE'    
        ) AS A ON IT.PARENT_ITEM_ID = A.ITEM_ID    
    ) AS Y ON X.ITEM_ID = Y.UP
ORDER BY
    X.ITEM_ID DESC
