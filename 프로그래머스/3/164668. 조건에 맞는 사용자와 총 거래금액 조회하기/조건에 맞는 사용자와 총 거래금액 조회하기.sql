-- 회원 ID, 닉네임, 총거래금액
SELECT
    UGU.user_id,
    UGU.nickname,
    SUM(UGB.price) AS TOTAL_SALES
FROM USED_GOODS_BOARD UGB
LEFT OUTER JOIN USED_GOODS_USER UGU
    ON UGB.writer_id = UGU.user_id
WHERE
    UGB.status = 'DONE'
GROUP BY
    UGU.user_id,
    UGU.nickname
HAVING
    SUM(UGB.price) >= 700000
ORDER BY
    SUM(UGB.price) ASC