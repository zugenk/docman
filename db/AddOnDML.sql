-- select * from lookup
--select id from status where type='Lookup' and code='new'

Insert into lookup(type, code, name, priority,description,shortname,status) values( 'organizationType','Direktorat','Direktorat',1,'Direktorat','DIR',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values( 'organizationType','Divisi','Divisi',2,'Divisi','DIV',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('organizationType','Departemen','Departemen',3,'Departemen','DEP',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('organizationType','Seksi','Seksi',4,'Seksi','SEC',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('organizationType','Unit','Unit',5,'Unit','UNT',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('position','staf','Staf',1,'Staf','Staf',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('securityLevel','public','Public',1,'Public','Public',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('securityLevel','private','Private',2,'Private','Private',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('securityLevel','LEVEL:1','Level 1',3,'Level 1','Lvl1',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('securityLevel','LEVEL:2','Level 2',4,'Level 2','Lvl2',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('securityLevel','LEVEL:3','Level 3',5,'Level 3','Lvl3',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('position','manajer','Manajer',2,'Manajer','Mgr',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('position','kepala','Kepala',3,'Kepala','KA',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('forumType','f-pol','Politik',1,'Politik','POL',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('forumType','f-sos','Sosial',2,'Sosial','SOS',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('forumType','f-world','World News',3,'WorldNews','WORLD',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('postType','post','Post',1,'Post Message','POST',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('postType','comment','Comment',2,'Comment Message','COMMENT',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('notificationType','topicUpdate','Topic Update',1,'Topic Update','',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('notificationType','messagePost','Message Post',2,'Message Post','',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('bookmarkType','extUrl','External Link',1,'External URL','EXT',(select id from status where type='Lookup' and code='new'));
Insert into lookup(type, code, name, priority,description,shortname,status) values('userLevel','executive','Excecutive',2,'Executive','EXEC',(select id from status where type='Lookup' and code='new'));

