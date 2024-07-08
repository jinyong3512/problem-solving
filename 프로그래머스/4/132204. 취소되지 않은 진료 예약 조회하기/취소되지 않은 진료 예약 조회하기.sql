# 진료예약번호, 환자이름, 환자번호, 진료과코드, 의사이름, 진료예약일시
SELECT
    A.APNT_NO,
    P.PT_NAME,
    P.PT_NO,
    A.MCDP_CD,
    D.DR_NAME,
    A.APNT_YMD
    
FROM
    PATIENT AS P
INNER JOIN
    (
    SELECT
        *
    FROM
        APPOINTMENT
    WHERE
        APNT_CNCL_YN = 'N' AND DATE_FORMAT(APNT_YMD,'%Y-%m-%d') = '2022-04-13' AND MCDP_CD = 'CS'
    ) AS A ON P.PT_NO = A.PT_NO
INNER JOIN
    DOCTOR AS D ON A.MDDR_ID = D.DR_ID
ORDER BY
    A.APNT_YMD ASC
