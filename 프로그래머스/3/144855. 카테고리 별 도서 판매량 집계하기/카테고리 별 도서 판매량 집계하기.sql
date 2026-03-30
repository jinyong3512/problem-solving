SELECT
    B.category,
    SUM(BS.sales)
FROM BOOK_SALES BS
LEFT OUTER JOIN BOOK B
    ON BS.book_id = B.book_id
WHERE
    TO_CHAR(BS.sales_date, 'YYYY-MM') = '2022-01'
GROUP BY
    B.category
ORDER BY
    B.category ASC