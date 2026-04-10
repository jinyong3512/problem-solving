SELECT
    B.book_id,
    A.author_name,
    TO_CHAR(B.published_date, 'YYYY-MM-DD') AS published_date
FROM BOOK B
LEFT OUTER JOIN AUTHOR A
    ON B.author_id = A.author_id
WHERE
    B.category = '경제'
ORDER BY
    published_date ASC