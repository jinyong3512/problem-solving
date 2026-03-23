-- 음식종유별로 가장큰 즐겨찾기수 
SELECT
    RI1.food_type,
    RI1.rest_id,
    RI1.rest_name,
    RI1.favorites
FROM REST_INFO RI1
INNER JOIN (
    SELECT
        food_type,
        MAX(favorites) max_favorites
    FROM REST_INFO
    GROUP BY
        food_type
) RI2
    ON RI1.food_type = RI2.food_type AND
    RI1.favorites = RI2.max_favorites
ORDER BY
    RI1.food_type DESC;