-- 자동차 종류가 '세단'인 자동차들 중 10월에 대여를 시작한 기록이 있는 자동차 ID 리스트를 출력
-- 자동차 ID 리스트는 중복이 없어야 하며
-- 자동차 ID를 기준으로 내림차순 정렬

SELECT
    DISTINCT CRCC.car_id AS car_id2
FROM CAR_RENTAL_COMPANY_CAR CRCC
LEFT OUTER JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY CRCRH
    ON CRCC.car_id = CRCRH.car_id
WHERE
    CRCC.car_type = '세단' AND
    TO_CHAR(CRCRH.start_date, 'MM') = '10'
ORDER BY
    car_id2 DESC