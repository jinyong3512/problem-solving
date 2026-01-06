-- 동물의 생물 종, 이름, 성별 및 중성화 여부
SELECT ANIMAL_TYPE
     , CASE 
            WHEN NAME IS NULL THEN 'No name'
            ELSE NAME
       END
     , SEX_UPON_INTAKE
  FROM ANIMAL_INS
ORDER BY ANIMAL_ID  