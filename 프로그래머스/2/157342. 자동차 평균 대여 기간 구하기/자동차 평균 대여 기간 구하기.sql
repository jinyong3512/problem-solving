SELECT
    car_id,
    TO_CHAR(ROUND(AVG(end_date - start_date + 1), 1), 'FM99990.0') AS average_duration
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
GROUP BY
    car_id
HAVING
    AVG(end_date - start_date + 1) >= 7
ORDER BY
    ROUND(AVG(end_date - start_date + 1), 1) DESC,
    car_id DESC