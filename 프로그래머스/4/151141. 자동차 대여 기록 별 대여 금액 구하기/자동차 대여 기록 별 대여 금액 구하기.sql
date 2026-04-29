-- 자동차 종류가 '트럭'인 자동차의 대여 기록에 대해서 대여 기록 별로 대여 금액(컬럼명: FEE)을 구하여 대여 기록 ID와 대여 금액 리스트를 출력

-- 500 5
-- 500 - 500 * 5 / 100
-- 

    SELECT
        CRCRH.history_id,
        CASE 
            WHEN CRCDP2.duration_type2 IS NULL THEN (CRCRH.end_date - CRCRH.start_date + 1) * CRCC.daily_fee
            ELSE (CRCRH.end_date - CRCRH.start_date + 1) * CRCC.daily_fee - (CRCRH.end_date - CRCRH.start_date + 1) * CRCC.daily_fee * CRCDP2.discount_rate / 100
        END AS FEE
    FROM CAR_RENTAL_COMPANY_CAR CRCC
    INNER JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY CRCRH
        ON CRCC.car_id = CRCRH.car_id
    LEFT OUTER JOIN (
        SELECT
            car_type,
            CASE 
                WHEN SUBSTR(duration_type, 1, 1) = '7' THEN 7    
                ELSE TO_NUMBER(SUBSTR(duration_type, 1, 2))
            END AS duration_type2,
            discount_rate
        FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN        
    ) CRCDP2
        ON CRCC.car_type = CRCDP2.car_type AND
        CASE 
            WHEN CRCRH.end_date - CRCRH.start_date + 1 >= 90 THEN CRCDP2.duration_type2 = 90            
            WHEN CRCRH.end_date - CRCRH.start_date + 1 >= 30 THEN CRCDP2.duration_type2 = 30
            WHEN CRCRH.end_date - CRCRH.start_date + 1 >= 7 THEN CRCDP2.duration_type2 = 7
        END         
    WHERE
        CRCC.car_type = '트럭'
ORDER BY
    FEE DESC,
    CRCRH.history_id DESC
