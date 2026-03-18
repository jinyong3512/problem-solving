-- SELECT 
--     CAR_ID,
--     CASE
--         WHEN CAR_ID IN (
--             SELECT
--                 CAR_ID
--             FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY 
--             WHERE
--                 TO_CHAR(START_DATE, 'YYYY-MM-DD') <= '2022-10-16' AND
--                 TO_CHAR(END_DATE, 'YYYY-MM-DD') >= '2022-10-16'
--         ) THEN '대여중'
--         ELSE '대여 가능'
--     END AS AVAILABILITY 
-- FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
-- GROUP BY CAR_ID
-- ORDER BY
--     CAR_ID DESC;

   

-- 1번 전체 car_id 출력
-- 2번 빌린 car_id 출력
-- left outer join
-- 
SELECT
    A.car_id,
    CASE 
        WHEN B.car_id IS NULL THEN '대여 가능'
        ELSE '대여중'
    END AS AVAILABILITY
FROM (
    SELECT 
        car_id
    FROM car_rental_company_rental_history 
    GROUP BY 
        car_id
) A
LEFT OUTER JOIN (
    SELECT
        car_id
    FROM car_rental_company_rental_history
    WHERE
        TO_CHAR(start_date, 'YYYY-MM-DD') <= '2022-10-16' AND
        TO_CHAR(end_date, 'YYYY-MM-DD') >= '2022-10-16'
    GROUP BY
        car_id
) B
    ON A.car_id = B.car_id
ORDER BY
    A.car_id DESC;

  
