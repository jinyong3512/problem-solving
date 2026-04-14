-- 보호소에 들어올 당시에는 중성화1되지 않았지만, 보호소를 나갈 당시에는 중성화된 동물
-- 아이디와 생물 종, 이름을 조회
-- 아이디 순으로 조회

SELECT
    AI.animal_id,
    AI.animal_type,
    AI.name
FROM ANIMAL_INS AI
INNER JOIN ANIMAL_OUTS AO
    ON AI.animal_id = AO.animal_id
WHERE
    AI.sex_upon_intake LIKE '%Intact%' AND
    (
        AO.sex_upon_outcome LIKE '%Spayed%' OR
        AO.sex_upon_outcome LIKE '%Neutered%'
    )
ORDER BY
    AI.animal_id ASC