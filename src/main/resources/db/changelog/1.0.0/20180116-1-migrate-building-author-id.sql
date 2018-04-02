--liquibase 20180116-1-migrate-building-author-id sql

--changeset klnprj:1
alter table building add column author character varying(255);
update building b1 set author=(select u.email from juser u join building b2 on b2.author_id=u.id where b1.id=b2.id);
alter table building drop column author_id;
alter table building alter column author set not null;
