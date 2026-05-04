-- 이름이 Lucy, Ella, Pickle, Rogan, Sabrina, Mitty인 동물의 아이디와 이름, 성별 및 중성화 여부를 조회하는 SQL 문을 작성

SELECT
    animal_id,
    name,
    sex_upon_intake
FROM ANIMAL_INS
WHERE
    name IN ('Lucy', 'Ella', 'Pickle', 'Rogan', 'Sabrina', 'Mitty')
ORDER BY
    animal_id ASC