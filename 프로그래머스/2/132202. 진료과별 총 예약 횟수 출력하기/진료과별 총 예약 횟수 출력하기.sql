SELECT
    mcdp_cd AS "진료과 코드",
    COUNT(*) AS "5월예약건수"
FROM APPOINTMENT
WHERE
    TO_CHAR(apnt_ymd, 'YYYY-MM') = '2022-05'
GROUP BY
    mcdp_cd
ORDER BY
    "5월예약건수" ASC,
    "진료과 코드" ASC