-- 입양을 간 기록은 있는데, 보호소에 들어온 기록이 없는

SELECT -- 동물의 ID와 이름을
    AO.animal_id,
    AO.name
FROM ANIMAL_OUTS AO
LEFT OUTER JOIN ANIMAL_INS AI
    ON AO.animal_id = AI.animal_id
WHERE
    AI.animal_id IS NULL
ORDER BY -- ID 순
    AO.animal_id ASC