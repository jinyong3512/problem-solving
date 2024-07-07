# %4 를 때려서 0 1 2 3
# /2 를 때리면 0 1
# 1 이면 있고 0 이면 없는거야

SELECT
    COUNT(*) AS COUNT
FROM
    ECOLI_DATA
WHERE
    FLOOR(GENOTYPE%4/2) = 0
    AND
    (
    FLOOR(GENOTYPE%8/4) = 1
    OR
    FLOOR(GENOTYPE%2/1) = 1
    )