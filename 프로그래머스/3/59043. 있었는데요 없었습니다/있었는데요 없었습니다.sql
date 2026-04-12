-- 아이디와 이름을 조회
-- 보호 시작일보다 입양일이 더 빠른 동물의 
-- 보호 시작일이 빠른 순으로 조회

SELECT
    AI.animal_id,
    AI.name
FROM ANIMAL_INS AI
INNER JOIN ANIMAL_OUTS AO
    ON AI.animal_id = AO.animal_id
WHERE
    AI.datetime > AO.datetime
ORDER BY
    AI.datetime ASC