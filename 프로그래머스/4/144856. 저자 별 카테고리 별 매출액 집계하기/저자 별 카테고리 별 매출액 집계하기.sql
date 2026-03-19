SELECT     
    author_id,
    author_name,
    category,
    SUM(col1) AS total_sales
FROM ( 
    SELECT 
        B.author_id,
        A.author_name,
        B.category,
        B.price * BS2.sum_sales AS col1
    FROM BOOK B
    INNER JOIN AUTHOR A
        ON B.author_id = A.author_id
    INNER JOIN (
        SELECT
            book_id,
            SUM(sales) AS sum_sales
        FROM BOOK_SALES
        WHERE 
            TO_CHAR(sales_date, 'YYYY-MM') = '2022-01'
        GROUP BY 
            book_id
    ) BS2
        ON B.book_id = BS2.book_id
)
GROUP BY
    author_id,
    author_name,
    category
ORDER BY
    author_id ASC,
    category DESC