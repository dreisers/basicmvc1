-- mediagroup.sql
-- �̵�� �׷� ���̺� ����
CREATE TABLE mediagroup (
   mediagroupno NUMBER NOT NULL PRIMARY KEY	 -- �׷��ȣ
 , title VARCHAR2(255) NOT NULL					 -- �׷����� 
);

-- ���߰� �׽�Ʈ
�׷��ȣ : �׷��ȣ �ִ밪 +1 
�׷����� : '2018�� �� ����'

insert into mediagroup(mediagroupno, title)
values((SELECT NVL(MAX(mediagroupno),0)+1 FROM mediagroup), '2018�� �� ����');

-- ���
select mediagroupno, title
from mediagroupno
order by mediagroupno desc;
