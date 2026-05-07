SELECT
    animal_id,
    name
FROM (
    SELECT
        AI.animal_id,
        AI.name
    FROM ANIMAL_INS AI
    INNER JOIN ANIMAL_OUTS AO
        ON AI.animal_id = AO.animal_id
    ORDER BY
        AO.datetime - AI.datetime DESC
)
WHERE
    ROWNUM <= 2