-- 자동차 ID, 자동차 종류, 대여 금액(컬럼명: FEE) 리스트를 출력
SELECT
    CRCC.car_id,
    CRCC.car_type,
    (CRCC.daily_fee * 30) * (100 - CRCDP.discount_rate) / 100 AS FEE
FROM CAR_RENTAL_COMPANY_CAR CRCC
LEFT OUTER JOIN (
    SELECT
        car_id
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE
        TO_CHAR(start_date, 'YYYY-MM-DD') <= '2022-11-30' AND 
        TO_CHAR(end_date, 'YYYY-MM-DD') >= '2022-11-01'
    GROUP BY
        car_id
) CRCRH2
    ON CRCC.car_id = CRCRH2.car_id
LEFT OUTER JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN CRCDP
    ON CRCC.car_type = CRCDP.car_type
WHERE
-- 자동차 종류가 '세단' 또는 'SUV' 인 자동차 중 
-- 2022년 11월 1일부터 2022년 11월 30일까지 대여 가능하고 
-- 30일간의 대여 금액이 50만원 이상 200만원 미만인 자동차에 대해서 
    CRCC.car_type IN ('세단', 'SUV') AND
    CRCRH2.car_id IS NULL AND
    CRCDP.duration_type = '30일 이상' AND
    (CRCC.daily_fee * 30) * (100 - CRCDP.discount_rate) / 100 >= 500000 AND 
    (CRCC.daily_fee * 30) * (100 - CRCDP.discount_rate) / 100 < 2000000
ORDER BY
-- 대여 금액을 기준으로 내림차순 정렬하고, 대여 금액이 같은 경우 자동차 종류를 기준으로 오름차순 정렬, 자동차 종류까지 같은 경우 자동차 ID를 기준으로 내림차순 정렬
    FEE DESC,
    car_type ASC,
    car_id DESC