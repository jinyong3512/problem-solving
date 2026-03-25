SELECT
    TO_NUMBER(TO_CHAR(CRCRH.start_date,'MM')) AS month,
    CRCRH.car_id AS car_id,
    COUNT(*) AS records
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY CRCRH
INNER JOIN (
    SELECT
        car_id
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE 
        TO_CHAR(start_date, 'YYYY-MM') >= '2022-08' AND
        TO_CHAR(start_date, 'YYYY-MM') <= '2022-10'
    GROUP BY 
        car_id
    HAVING
        COUNT(*) >= 5
) CRCRH2
    ON CRCRH.car_id = CRCRH2.car_id
WHERE
    TO_CHAR(CRCRH.start_date, 'YYYY-MM') >= '2022-08' AND
    TO_CHAR(CRCRH.start_date, 'YYYY-MM') <= '2022-10'    
GROUP BY 
    TO_CHAR(CRCRH.start_date,'MM'),
    CRCRH.car_id
ORDER BY
    month ASC,
    car_id DESC