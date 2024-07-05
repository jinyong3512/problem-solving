-- 코드를 입력하세요
SELECT
    ANIMAL_ID,
    NAME,
    IF(
        SEX_UPON_INTAKE LIKE '%Neutered%' OR SEX_UPON_INTAKE LIKE '%Spayed%',
        'O',
        'X'
    )
FROM
    ANIMAL_INS
ORDER BY
    ANIMAL_ID ASC;