-- mediagroup.sql
-- 미디어 그룹 테이블 생성
CREATE TABLE mediagroup (
   mediagroupno NUMBER NOT NULL PRIMARY KEY	 -- 그룹번호
 , title VARCHAR2(255) NOT NULL					 -- 그룹제목 
);

-- 행추가 테스트
그룹번호 : 그룹번호 최대값 +1 
그룹제목 : '2018년 댄스 음악'

insert into mediagroup(mediagroupno, title)
values((SELECT NVL(MAX(mediagroupno),0)+1 FROM mediagroup), '2018년 댄스 음악');

-- 목록
select mediagroupno, title
from mediagroupno
order by mediagroupno desc;
