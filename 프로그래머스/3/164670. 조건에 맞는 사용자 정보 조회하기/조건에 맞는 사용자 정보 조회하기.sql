-- 중고 거래 게시물을 3건 이상 등록한 사용자의 사용자 ID, 닉네임, 전체주소, 전화번호를 조회하는 SQL문을 작성
-- 이때, 전체 주소는 시, 도로명 주소, 상세 주소가 함께 출력
-- 전화번호의 경우 xxx-xxxx-xxxx 같은 형태로 하이픈 문자열(-)을 삽입하여 출력
-- 회원 ID를 기준으로 내림차순 정렬

SELECT
    user_id,
    nickname,
    city || ' ' || street_address1 || ' ' || street_address2 AS 전체주소,
    SUBSTR(tlno, 1, 3) || '-' || SUBSTR(tlno, 4, 4) || '-' || SUBSTR(tlno, 8, 4) AS 전화번호
FROM USED_GOODS_USER
WHERE
    user_id IN (
    SELECT
        writer_id
    FROM USED_GOODS_BOARD
    GROUP BY
        writer_id
    HAVING
        COUNT(*) >= 3
)
ORDER BY
    user_id DESC