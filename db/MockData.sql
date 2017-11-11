-- select * from organization;
-- select * from lookup where type='notificationType'
-- select id from lookup where type='postType' and code='post'
-- select id from status where type='Topic' and code='new'
--select LOCALTIMESTAMP;

Insert into Organization (code,mnemonic,name,address,mailing_list,created_date,created_by, last_updated_date,last_updated_by,filter_code,parent,organization_type,status)
values('DIR-TI','DIR-TI','Direktorat Teknologi Informasi',null,null,LOCALTIMESTAMP,'admin',null,null,null,null,(select id from lookup where type='organizationType' and code='Direktorat'),(select id from status where type='Organization' and code='new'));

Insert into Organization (code,mnemonic,name,address,mailing_list,created_date,created_by, last_updated_date,last_updated_by,filter_code,parent,organization_type,status)
values('DIV-TI','DIV-TI','Divisi Teknologi Informasi',null,null,LOCALTIMESTAMP,'admin',null,null,null,1,(select id from lookup where type='organizationType' and code='Divisi'),(select id from status where type='Organization' and code='new'));

Insert into Organization (code,mnemonic,name,address,mailing_list,created_date,created_by, last_updated_date,last_updated_by,filter_code,parent,organization_type,status)
values('DEP-TI','DEP-TI','Departemen Teknologi Informasi',null,null,LOCALTIMESTAMP,'admin',null,null,null,2,(select id from lookup where type='organizationType' and code='Departemen'),(select id from status where type='Organization' and code='new'));

Insert into Organization (code,mnemonic,name,address,mailing_list,created_date,created_by, last_updated_date,last_updated_by,filter_code,parent,organization_type,status)
values('SEC-TI','SEC-TI','Seksi Teknologi Informasi',null,null,LOCALTIMESTAMP,'admin',null,null,null,3,(select id from lookup where type='organizationType' and code='Seksi'),(select id from status where type='Organization' and code='new'));

Insert into Organization (code,mnemonic,name,address,mailing_list,created_date,created_by, last_updated_date,last_updated_by,filter_code,parent,organization_type,status)
values('UNT-TI','UNT-TI','Unit Teknologi Informasi',null,null,LOCALTIMESTAMP,'admin',null,null,null,4,(select id from lookup where type='organizationType' and code='Unit'),(select id from status where type='Organization' and code='new'));

Insert into Organization (code,mnemonic,name,address,mailing_list,created_date,created_by, last_updated_date,last_updated_by,filter_code,parent,organization_type,status)
values('DIR-EChannel','DIR-EChannel','Direktorat Echannel',null,null,LOCALTIMESTAMP,'admin',null,null,null,null,(select id from lookup where type='organizationType' and code='Direktorat'),(select id from status where type='Organization' and code='new'));

insert into forum (code,icon,name,description,status,forum_type,created_date,created_by)
values('sos-001','sos-001','Sosial','Sosial',(select id from status where type='Forum' and code='new'),(select id from lookup where type='forumType' and code='f-sos'),LOCALTIMESTAMP,'admin');

insert into Topic(code,icon,name,description,status,created_date,created_by,forum)
values ('tp-1','tp-1','Topik Sosial','Topik Sosial',(select id from status where type='Topic' and code='new'),LOCALTIMESTAMP,'admin',1);

insert into Message(content,post_type,status,created_date,created_by,topic)
values('INI Percobaan gua yg pertama lho',(select id from lookup where type='postType' and code='post'),(select id from status where type='Message' and code='new'),LOCALTIMESTAMP,'admin',1);

insert into notification(notification_type,post_message,subscriber)
values( (select id from lookup where type='notificationType' and code='messagePost'),1,2);

insert into Bookmark(url,name,category,note,bookmark_type,created_date,created_by,status,owner)
values('www.detik.com','Detik.com','News','Detik.com',(select id from lookup where type='bookmarkType' and code='extUrl'),LOCALTIMESTAMP,'admin',(select id from status where type='Message' and code='new'),2);




-- select * from forum;
-- select * from topic;
-- select * from message;
-- select * from notification;

-- select * from bookmark;
-- select * from lookup where type='bookmarkType'
