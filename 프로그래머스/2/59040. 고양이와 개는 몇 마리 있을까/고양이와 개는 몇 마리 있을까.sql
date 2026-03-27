SELECT
    animal_type,
    COUNT(*)
FROM ANIMAL_INS
WHERE
    animal_type IN ('Cat', 'Dog')
GROUP BY
    animal_type
ORDER BY
    animal_type ASC;