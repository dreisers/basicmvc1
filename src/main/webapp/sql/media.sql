CREATE TABLE media (
  mediano  NUMBER        NOT NULL PRIMARY KEY,          -- �̵�� ��ȣ
  title    VARCHAR2(255) NOT NULL,                      -- ����
  poster   VARCHAR2(255) DEFAULT 'poster.jpg' NOT NULL, -- ������ �̹���
  filename VARCHAR2(255) NOT NULL,                      -- ���� ���ϸ�
  filesize NUMBER        DEFAULT 0 NOT NULL,
  mview    CHAR(1)       DEFAULT 'Y' NOT NULL,          -- ��¸��
  rdate    DATE          NOT NULL,                      -- �����
  mediagroupno NUMBER NULL                              -- �θ����̺� PK
);