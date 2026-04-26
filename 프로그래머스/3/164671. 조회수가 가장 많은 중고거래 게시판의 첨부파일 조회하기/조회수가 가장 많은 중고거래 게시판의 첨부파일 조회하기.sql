SELECT
    '/home/grep/src/' || UGF.board_id || '/' || UGF.file_id || UGF.file_name || UGF.file_ext AS FILE_PATH
FROM (
    SELECT
        *
    FROM (
        SELECT
            *
        FROM
            USED_GOODS_BOARD
        ORDER BY
            views DESC
    )
    WHERE
        ROWNUM <= 1
) UGB2
INNER JOIN USED_GOODS_FILE UGF
    ON UGB2.board_id = UGF.board_id
ORDER BY
    UGF.file_id DESC