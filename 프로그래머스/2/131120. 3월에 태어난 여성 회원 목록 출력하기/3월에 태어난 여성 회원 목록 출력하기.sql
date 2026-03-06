SELECT -- ID, 이름, 성별, 생년월일
    MEMBER_ID,
    MEMBER_NAME,
    GENDER,
    DATE_FORMAT(DATE_OF_BIRTH, '%Y-%m-%d')
FROM
    MEMBER_PROFILE 
WHERE -- 생일이 3월인 여성 회원 이때 전화번호가 NULL인 경우는 출력대상에서 제외
    DATE_FORMAT(DATE_OF_BIRTH, '%m') = '03'
    AND GENDER = 'W'
    AND TLNO IS NOT NULL
ORDER BY -- 회원ID를 기준으로 오름차순 정렬
    MEMBER_ID ASC;
