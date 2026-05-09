SELECT
    animal_id,
    name,
    TO_CHAR(datetime, 'YYYY-MM-DD')
FROM ANIMAL_INS
ORDER BY
    animal_id ASC