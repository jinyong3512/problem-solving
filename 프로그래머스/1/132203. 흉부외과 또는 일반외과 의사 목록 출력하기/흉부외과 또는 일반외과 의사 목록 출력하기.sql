SELECT -- 의사의 이름, 의사ID, 진료과, 고용일자
    DR_NAME,
    DR_ID,
    MCDP_CD,
    DATE_FORMAT(HIRE_YMD, '%Y-%m-%d') AS HIRE_YMD2
FROM
    DOCTOR
WHERE
    MCDP_CD = 'CS'
    OR MCDP_CD = 'GS'
ORDER BY -- 고용일자를 기준으로 내림차순 정렬하고, 고용일자가 같다면 이름을 기준으로 오름차순
    HIRE_YMD DESC, DR_NAME ASC;