-- 2022년 4월 13일 취소되지 않은 흉부외과(CS) 진료 예약 내역을 조회하는 SQL문을 작성
SELECT -- 진료예약번호, 환자이름, 환자번호, 진료과코드, 의사이름, 진료예약일시 항목이 출력
    A.apnt_no,
    P.pt_name,
    P.pt_no,
    D.mcdp_cd,
    D.dr_name,
    A.apnt_ymd
FROM APPOINTMENT A
INNER JOIN DOCTOR D
    ON A.mddr_id = D.dr_id
INNER JOIN PATIENT P
    ON A.pt_no = P.pt_no    
WHERE
    TO_CHAR(A.apnt_ymd, 'YYYY-MM-DD') = '2022-04-13' AND
    A.apnt_cncl_yn = 'N' AND
    D.mcdp_cd = 'CS'
ORDER BY -- 진료예약일시를 기준으로 오름차순
    A.apnt_ymd ASC