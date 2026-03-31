SELECT
    TO_CHAR(OS.sales_date, 'YYYY') AS YEAR,
    TO_NUMBER(TO_CHAR(OS.sales_date, 'MM')) AS MONTH,
    UI.gender AS GENDER,    
    COUNT(DISTINCT(OS.user_id)) AS USERS
FROM ONLINE_SALE OS
LEFT OUTER JOIN USER_INFO UI
    ON OS.user_id = UI.user_id
WHERE
    UI.gender IS NOT NULL
GROUP BY
    TO_CHAR(OS.sales_date, 'YYYY'),
    TO_CHAR(OS.sales_date, 'MM'),
    UI.gender
ORDER BY
    YEAR ASC,
    MONTH ASC,
    GENDER ASC