-- 경기도에 위치한 창고의 ID, 이름, 주소, 냉동시설 여부를 조회
SELECT
    warehouse_id,
    warehouse_name,
    address,
    CASE 
        WHEN freezer_yn IS NULL THEN 'N'
        ELSE freezer_yn
    END
FROM FOOD_WAREHOUSE
WHERE
    address LIKE '%경기도%'
ORDER BY
    warehouse_id ASC