/************* Cleanup Script *************/
/*
delete from user_role; 
delete from role_privilege; 
delete from app_user;
delete from role;
delete from privilege;
delete from status;
delete from lookup;
*/

/************* Status Script *************/
insert into status (type,code,name,state,description) values('AuditTrail','new','New','active','New  AuditTrail');
insert into status (type,code,name,state,description) values('AuditTrail','submitted','Submitted','active','Submitted  AuditTrail');
insert into status (type,code,name,state,description) values('AuditTrail','approved','Approved','active','Approved  AuditTrail');
insert into status (type,code,name,state,description) values('AuditTrail','rejected','Rejected','active','Rejected  AuditTrail');
insert into status (type,code,name,state,description) values('AuditTrail','pending','Pending','active','Pending  AuditTrail');
insert into status (type,code,name,state,description) values('AuditTrail','processed','Processed','active','Processed  AuditTrail');
insert into status (type,code,name,state,description) values('AuditTrail','deleted','Deleted','inactive','Deleted  AuditTrail');
insert into status (type,code,name,state,description) values('AuditTrail','closed','Closed','inactive','Closed  AuditTrail');
insert into status (type,code,name,state,description) values('AuditTrail','removed','Removed','inactive','Removed  AuditTrail');
insert into status (type,code,name,state,description) values('AuditTrail','cancelled','Cancelled','inactive','Cancelled AuditTrail');
insert into status (type,code,name,state,description) values('EmailLog','new','New','active','New  EmailLog');
insert into status (type,code,name,state,description) values('EmailLog','submitted','Submitted','active','Submitted  EmailLog');
insert into status (type,code,name,state,description) values('EmailLog','approved','Approved','active','Approved  EmailLog');
insert into status (type,code,name,state,description) values('EmailLog','rejected','Rejected','active','Rejected  EmailLog');
insert into status (type,code,name,state,description) values('EmailLog','pending','Pending','active','Pending  EmailLog');
insert into status (type,code,name,state,description) values('EmailLog','processed','Processed','active','Processed  EmailLog');
insert into status (type,code,name,state,description) values('EmailLog','deleted','Deleted','inactive','Deleted  EmailLog');
insert into status (type,code,name,state,description) values('EmailLog','closed','Closed','inactive','Closed  EmailLog');
insert into status (type,code,name,state,description) values('EmailLog','removed','Removed','inactive','Removed  EmailLog');
insert into status (type,code,name,state,description) values('EmailLog','cancelled','Cancelled','inactive','Cancelled EmailLog');
insert into status (type,code,name,state,description) values('Folder','new','New','active','New  Folder');
insert into status (type,code,name,state,description) values('Folder','submitted','Submitted','active','Submitted  Folder');
insert into status (type,code,name,state,description) values('Folder','approved','Approved','active','Approved  Folder');
insert into status (type,code,name,state,description) values('Folder','rejected','Rejected','active','Rejected  Folder');
insert into status (type,code,name,state,description) values('Folder','pending','Pending','active','Pending  Folder');
insert into status (type,code,name,state,description) values('Folder','processed','Processed','active','Processed  Folder');
insert into status (type,code,name,state,description) values('Folder','deleted','Deleted','inactive','Deleted  Folder');
insert into status (type,code,name,state,description) values('Folder','closed','Closed','inactive','Closed  Folder');
insert into status (type,code,name,state,description) values('Folder','removed','Removed','inactive','Removed  Folder');
insert into status (type,code,name,state,description) values('Folder','cancelled','Cancelled','inactive','Cancelled Folder');
insert into status (type,code,name,state,description) values('Forum','new','New','active','New  Forum');
insert into status (type,code,name,state,description) values('Forum','submitted','Submitted','active','Submitted  Forum');
insert into status (type,code,name,state,description) values('Forum','approved','Approved','active','Approved  Forum');
insert into status (type,code,name,state,description) values('Forum','rejected','Rejected','active','Rejected  Forum');
insert into status (type,code,name,state,description) values('Forum','pending','Pending','active','Pending  Forum');
insert into status (type,code,name,state,description) values('Forum','processed','Processed','active','Processed  Forum');
insert into status (type,code,name,state,description) values('Forum','deleted','Deleted','inactive','Deleted  Forum');
insert into status (type,code,name,state,description) values('Forum','closed','Closed','inactive','Closed  Forum');
insert into status (type,code,name,state,description) values('Forum','removed','Removed','inactive','Removed  Forum');
insert into status (type,code,name,state,description) values('Forum','cancelled','Cancelled','inactive','Cancelled Forum');
insert into status (type,code,name,state,description) values('Bookmark','new','New','active','New  Bookmark');
insert into status (type,code,name,state,description) values('Bookmark','submitted','Submitted','active','Submitted  Bookmark');
insert into status (type,code,name,state,description) values('Bookmark','approved','Approved','active','Approved  Bookmark');
insert into status (type,code,name,state,description) values('Bookmark','rejected','Rejected','active','Rejected  Bookmark');
insert into status (type,code,name,state,description) values('Bookmark','pending','Pending','active','Pending  Bookmark');
insert into status (type,code,name,state,description) values('Bookmark','processed','Processed','active','Processed  Bookmark');
insert into status (type,code,name,state,description) values('Bookmark','deleted','Deleted','inactive','Deleted  Bookmark');
insert into status (type,code,name,state,description) values('Bookmark','closed','Closed','inactive','Closed  Bookmark');
insert into status (type,code,name,state,description) values('Bookmark','removed','Removed','inactive','Removed  Bookmark');
insert into status (type,code,name,state,description) values('Bookmark','cancelled','Cancelled','inactive','Cancelled Bookmark');
insert into status (type,code,name,state,description) values('Document','new','New','active','New  Document');
insert into status (type,code,name,state,description) values('Document','submitted','Submitted','active','Submitted  Document');
insert into status (type,code,name,state,description) values('Document','approved','Approved','active','Approved  Document');
insert into status (type,code,name,state,description) values('Document','rejected','Rejected','active','Rejected  Document');
insert into status (type,code,name,state,description) values('Document','pending','Pending','active','Pending  Document');
insert into status (type,code,name,state,description) values('Document','processed','Processed','active','Processed  Document');
insert into status (type,code,name,state,description) values('Document','deleted','Deleted','inactive','Deleted  Document');
insert into status (type,code,name,state,description) values('Document','closed','Closed','inactive','Closed  Document');
insert into status (type,code,name,state,description) values('Document','removed','Removed','inactive','Removed  Document');
insert into status (type,code,name,state,description) values('Document','cancelled','Cancelled','inactive','Cancelled Document');
insert into status (type,code,name,state,description) values('DocumentHistory','new','New','active','New  DocumentHistory');
insert into status (type,code,name,state,description) values('DocumentHistory','submitted','Submitted','active','Submitted  DocumentHistory');
insert into status (type,code,name,state,description) values('DocumentHistory','approved','Approved','active','Approved  DocumentHistory');
insert into status (type,code,name,state,description) values('DocumentHistory','rejected','Rejected','active','Rejected  DocumentHistory');
insert into status (type,code,name,state,description) values('DocumentHistory','pending','Pending','active','Pending  DocumentHistory');
insert into status (type,code,name,state,description) values('DocumentHistory','processed','Processed','active','Processed  DocumentHistory');
insert into status (type,code,name,state,description) values('DocumentHistory','deleted','Deleted','inactive','Deleted  DocumentHistory');
insert into status (type,code,name,state,description) values('DocumentHistory','closed','Closed','inactive','Closed  DocumentHistory');
insert into status (type,code,name,state,description) values('DocumentHistory','removed','Removed','inactive','Removed  DocumentHistory');
insert into status (type,code,name,state,description) values('DocumentHistory','cancelled','Cancelled','inactive','Cancelled DocumentHistory');
insert into status (type,code,name,state,description) values('Topic','new','New','active','New  Topic');
insert into status (type,code,name,state,description) values('Topic','submitted','Submitted','active','Submitted  Topic');
insert into status (type,code,name,state,description) values('Topic','approved','Approved','active','Approved  Topic');
insert into status (type,code,name,state,description) values('Topic','rejected','Rejected','active','Rejected  Topic');
insert into status (type,code,name,state,description) values('Topic','pending','Pending','active','Pending  Topic');
insert into status (type,code,name,state,description) values('Topic','processed','Processed','active','Processed  Topic');
insert into status (type,code,name,state,description) values('Topic','deleted','Deleted','inactive','Deleted  Topic');
insert into status (type,code,name,state,description) values('Topic','closed','Closed','inactive','Closed  Topic');
insert into status (type,code,name,state,description) values('Topic','removed','Removed','inactive','Removed  Topic');
insert into status (type,code,name,state,description) values('Topic','cancelled','Cancelled','inactive','Cancelled Topic');
insert into status (type,code,name,state,description) values('Message','new','New','active','New  Message');
insert into status (type,code,name,state,description) values('Message','submitted','Submitted','active','Submitted  Message');
insert into status (type,code,name,state,description) values('Message','approved','Approved','active','Approved  Message');
insert into status (type,code,name,state,description) values('Message','rejected','Rejected','active','Rejected  Message');
insert into status (type,code,name,state,description) values('Message','pending','Pending','active','Pending  Message');
insert into status (type,code,name,state,description) values('Message','processed','Processed','active','Processed  Message');
insert into status (type,code,name,state,description) values('Message','deleted','Deleted','inactive','Deleted  Message');
insert into status (type,code,name,state,description) values('Message','closed','Closed','inactive','Closed  Message');
insert into status (type,code,name,state,description) values('Message','removed','Removed','inactive','Removed  Message');
insert into status (type,code,name,state,description) values('Message','cancelled','Cancelled','inactive','Cancelled Message');
insert into status (type,code,name,state,description) values('Notification','new','New','active','New  Notification');
insert into status (type,code,name,state,description) values('Notification','submitted','Submitted','active','Submitted  Notification');
insert into status (type,code,name,state,description) values('Notification','approved','Approved','active','Approved  Notification');
insert into status (type,code,name,state,description) values('Notification','rejected','Rejected','active','Rejected  Notification');
insert into status (type,code,name,state,description) values('Notification','pending','Pending','active','Pending  Notification');
insert into status (type,code,name,state,description) values('Notification','processed','Processed','active','Processed  Notification');
insert into status (type,code,name,state,description) values('Notification','deleted','Deleted','inactive','Deleted  Notification');
insert into status (type,code,name,state,description) values('Notification','closed','Closed','inactive','Closed  Notification');
insert into status (type,code,name,state,description) values('Notification','removed','Removed','inactive','Removed  Notification');
insert into status (type,code,name,state,description) values('Notification','cancelled','Cancelled','inactive','Cancelled Notification');
insert into status (type,code,name,state,description) values('GenericReport','new','New','active','New  GenericReport');
insert into status (type,code,name,state,description) values('GenericReport','submitted','Submitted','active','Submitted  GenericReport');
insert into status (type,code,name,state,description) values('GenericReport','approved','Approved','active','Approved  GenericReport');
insert into status (type,code,name,state,description) values('GenericReport','rejected','Rejected','active','Rejected  GenericReport');
insert into status (type,code,name,state,description) values('GenericReport','pending','Pending','active','Pending  GenericReport');
insert into status (type,code,name,state,description) values('GenericReport','processed','Processed','active','Processed  GenericReport');
insert into status (type,code,name,state,description) values('GenericReport','deleted','Deleted','inactive','Deleted  GenericReport');
insert into status (type,code,name,state,description) values('GenericReport','closed','Closed','inactive','Closed  GenericReport');
insert into status (type,code,name,state,description) values('GenericReport','removed','Removed','inactive','Removed  GenericReport');
insert into status (type,code,name,state,description) values('GenericReport','cancelled','Cancelled','inactive','Cancelled GenericReport');
insert into status (type,code,name,state,description) values('IPassportPool','new','New','active','New  IPassportPool');
insert into status (type,code,name,state,description) values('IPassportPool','submitted','Submitted','active','Submitted  IPassportPool');
insert into status (type,code,name,state,description) values('IPassportPool','approved','Approved','active','Approved  IPassportPool');
insert into status (type,code,name,state,description) values('IPassportPool','rejected','Rejected','active','Rejected  IPassportPool');
insert into status (type,code,name,state,description) values('IPassportPool','pending','Pending','active','Pending  IPassportPool');
insert into status (type,code,name,state,description) values('IPassportPool','processed','Processed','active','Processed  IPassportPool');
insert into status (type,code,name,state,description) values('IPassportPool','deleted','Deleted','inactive','Deleted  IPassportPool');
insert into status (type,code,name,state,description) values('IPassportPool','closed','Closed','inactive','Closed  IPassportPool');
insert into status (type,code,name,state,description) values('IPassportPool','removed','Removed','inactive','Removed  IPassportPool');
insert into status (type,code,name,state,description) values('IPassportPool','cancelled','Cancelled','inactive','Cancelled IPassportPool');
insert into status (type,code,name,state,description) values('LoginHistory','new','New','active','New  LoginHistory');
insert into status (type,code,name,state,description) values('LoginHistory','submitted','Submitted','active','Submitted  LoginHistory');
insert into status (type,code,name,state,description) values('LoginHistory','approved','Approved','active','Approved  LoginHistory');
insert into status (type,code,name,state,description) values('LoginHistory','rejected','Rejected','active','Rejected  LoginHistory');
insert into status (type,code,name,state,description) values('LoginHistory','pending','Pending','active','Pending  LoginHistory');
insert into status (type,code,name,state,description) values('LoginHistory','processed','Processed','active','Processed  LoginHistory');
insert into status (type,code,name,state,description) values('LoginHistory','deleted','Deleted','inactive','Deleted  LoginHistory');
insert into status (type,code,name,state,description) values('LoginHistory','closed','Closed','inactive','Closed  LoginHistory');
insert into status (type,code,name,state,description) values('LoginHistory','removed','Removed','inactive','Removed  LoginHistory');
insert into status (type,code,name,state,description) values('LoginHistory','cancelled','Cancelled','inactive','Cancelled LoginHistory');
insert into status (type,code,name,state,description) values('Lookup','new','New','active','New  Lookup');
insert into status (type,code,name,state,description) values('Lookup','submitted','Submitted','active','Submitted  Lookup');
insert into status (type,code,name,state,description) values('Lookup','approved','Approved','active','Approved  Lookup');
insert into status (type,code,name,state,description) values('Lookup','rejected','Rejected','active','Rejected  Lookup');
insert into status (type,code,name,state,description) values('Lookup','pending','Pending','active','Pending  Lookup');
insert into status (type,code,name,state,description) values('Lookup','processed','Processed','active','Processed  Lookup');
insert into status (type,code,name,state,description) values('Lookup','deleted','Deleted','inactive','Deleted  Lookup');
insert into status (type,code,name,state,description) values('Lookup','closed','Closed','inactive','Closed  Lookup');
insert into status (type,code,name,state,description) values('Lookup','removed','Removed','inactive','Removed  Lookup');
insert into status (type,code,name,state,description) values('Lookup','cancelled','Cancelled','inactive','Cancelled Lookup');
insert into status (type,code,name,state,description) values('Organization','new','New','active','New  Organization');
insert into status (type,code,name,state,description) values('Organization','submitted','Submitted','active','Submitted  Organization');
insert into status (type,code,name,state,description) values('Organization','approved','Approved','active','Approved  Organization');
insert into status (type,code,name,state,description) values('Organization','rejected','Rejected','active','Rejected  Organization');
insert into status (type,code,name,state,description) values('Organization','pending','Pending','active','Pending  Organization');
insert into status (type,code,name,state,description) values('Organization','processed','Processed','active','Processed  Organization');
insert into status (type,code,name,state,description) values('Organization','deleted','Deleted','inactive','Deleted  Organization');
insert into status (type,code,name,state,description) values('Organization','closed','Closed','inactive','Closed  Organization');
insert into status (type,code,name,state,description) values('Organization','removed','Removed','inactive','Removed  Organization');
insert into status (type,code,name,state,description) values('Organization','cancelled','Cancelled','inactive','Cancelled Organization');
insert into status (type,code,name,state,description) values('Privilege','new','New','active','New  Privilege');
insert into status (type,code,name,state,description) values('Privilege','submitted','Submitted','active','Submitted  Privilege');
insert into status (type,code,name,state,description) values('Privilege','approved','Approved','active','Approved  Privilege');
insert into status (type,code,name,state,description) values('Privilege','rejected','Rejected','active','Rejected  Privilege');
insert into status (type,code,name,state,description) values('Privilege','pending','Pending','active','Pending  Privilege');
insert into status (type,code,name,state,description) values('Privilege','processed','Processed','active','Processed  Privilege');
insert into status (type,code,name,state,description) values('Privilege','deleted','Deleted','inactive','Deleted  Privilege');
insert into status (type,code,name,state,description) values('Privilege','closed','Closed','inactive','Closed  Privilege');
insert into status (type,code,name,state,description) values('Privilege','removed','Removed','inactive','Removed  Privilege');
insert into status (type,code,name,state,description) values('Privilege','cancelled','Cancelled','inactive','Cancelled Privilege');
insert into status (type,code,name,state,description) values('Role','new','New','active','New  Role');
insert into status (type,code,name,state,description) values('Role','submitted','Submitted','active','Submitted  Role');
insert into status (type,code,name,state,description) values('Role','approved','Approved','active','Approved  Role');
insert into status (type,code,name,state,description) values('Role','rejected','Rejected','active','Rejected  Role');
insert into status (type,code,name,state,description) values('Role','pending','Pending','active','Pending  Role');
insert into status (type,code,name,state,description) values('Role','processed','Processed','active','Processed  Role');
insert into status (type,code,name,state,description) values('Role','deleted','Deleted','inactive','Deleted  Role');
insert into status (type,code,name,state,description) values('Role','closed','Closed','inactive','Closed  Role');
insert into status (type,code,name,state,description) values('Role','removed','Removed','inactive','Removed  Role');
insert into status (type,code,name,state,description) values('Role','cancelled','Cancelled','inactive','Cancelled Role');
insert into status (type,code,name,state,description) values('Status','new','New','active','New  Status');
insert into status (type,code,name,state,description) values('Status','submitted','Submitted','active','Submitted  Status');
insert into status (type,code,name,state,description) values('Status','approved','Approved','active','Approved  Status');
insert into status (type,code,name,state,description) values('Status','rejected','Rejected','active','Rejected  Status');
insert into status (type,code,name,state,description) values('Status','pending','Pending','active','Pending  Status');
insert into status (type,code,name,state,description) values('Status','processed','Processed','active','Processed  Status');
insert into status (type,code,name,state,description) values('Status','deleted','Deleted','inactive','Deleted  Status');
insert into status (type,code,name,state,description) values('Status','closed','Closed','inactive','Closed  Status');
insert into status (type,code,name,state,description) values('Status','removed','Removed','inactive','Removed  Status');
insert into status (type,code,name,state,description) values('Status','cancelled','Cancelled','inactive','Cancelled Status');
insert into status (type,code,name,state,description) values('SystemParameter','new','New','active','New  SystemParameter');
insert into status (type,code,name,state,description) values('SystemParameter','submitted','Submitted','active','Submitted  SystemParameter');
insert into status (type,code,name,state,description) values('SystemParameter','approved','Approved','active','Approved  SystemParameter');
insert into status (type,code,name,state,description) values('SystemParameter','rejected','Rejected','active','Rejected  SystemParameter');
insert into status (type,code,name,state,description) values('SystemParameter','pending','Pending','active','Pending  SystemParameter');
insert into status (type,code,name,state,description) values('SystemParameter','processed','Processed','active','Processed  SystemParameter');
insert into status (type,code,name,state,description) values('SystemParameter','deleted','Deleted','inactive','Deleted  SystemParameter');
insert into status (type,code,name,state,description) values('SystemParameter','closed','Closed','inactive','Closed  SystemParameter');
insert into status (type,code,name,state,description) values('SystemParameter','removed','Removed','inactive','Removed  SystemParameter');
insert into status (type,code,name,state,description) values('SystemParameter','cancelled','Cancelled','inactive','Cancelled SystemParameter');
insert into status (type,code,name,state,description) values('SystemParameterHistory','new','New','active','New  SystemParameterHistory');
insert into status (type,code,name,state,description) values('SystemParameterHistory','submitted','Submitted','active','Submitted  SystemParameterHistory');
insert into status (type,code,name,state,description) values('SystemParameterHistory','approved','Approved','active','Approved  SystemParameterHistory');
insert into status (type,code,name,state,description) values('SystemParameterHistory','rejected','Rejected','active','Rejected  SystemParameterHistory');
insert into status (type,code,name,state,description) values('SystemParameterHistory','pending','Pending','active','Pending  SystemParameterHistory');
insert into status (type,code,name,state,description) values('SystemParameterHistory','processed','Processed','active','Processed  SystemParameterHistory');
insert into status (type,code,name,state,description) values('SystemParameterHistory','deleted','Deleted','inactive','Deleted  SystemParameterHistory');
insert into status (type,code,name,state,description) values('SystemParameterHistory','closed','Closed','inactive','Closed  SystemParameterHistory');
insert into status (type,code,name,state,description) values('SystemParameterHistory','removed','Removed','inactive','Removed  SystemParameterHistory');
insert into status (type,code,name,state,description) values('SystemParameterHistory','cancelled','Cancelled','inactive','Cancelled SystemParameterHistory');
insert into status (type,code,name,state,description) values('SystemSequence','new','New','active','New  SystemSequence');
insert into status (type,code,name,state,description) values('SystemSequence','submitted','Submitted','active','Submitted  SystemSequence');
insert into status (type,code,name,state,description) values('SystemSequence','approved','Approved','active','Approved  SystemSequence');
insert into status (type,code,name,state,description) values('SystemSequence','rejected','Rejected','active','Rejected  SystemSequence');
insert into status (type,code,name,state,description) values('SystemSequence','pending','Pending','active','Pending  SystemSequence');
insert into status (type,code,name,state,description) values('SystemSequence','processed','Processed','active','Processed  SystemSequence');
insert into status (type,code,name,state,description) values('SystemSequence','deleted','Deleted','inactive','Deleted  SystemSequence');
insert into status (type,code,name,state,description) values('SystemSequence','closed','Closed','inactive','Closed  SystemSequence');
insert into status (type,code,name,state,description) values('SystemSequence','removed','Removed','inactive','Removed  SystemSequence');
insert into status (type,code,name,state,description) values('SystemSequence','cancelled','Cancelled','inactive','Cancelled SystemSequence');
insert into status (type,code,name,state,description) values('User','new','New','active','New  User');
insert into status (type,code,name,state,description) values('User','submitted','Submitted','active','Submitted  User');
insert into status (type,code,name,state,description) values('User','approved','Approved','active','Approved  User');
insert into status (type,code,name,state,description) values('User','rejected','Rejected','active','Rejected  User');
insert into status (type,code,name,state,description) values('User','pending','Pending','active','Pending  User');
insert into status (type,code,name,state,description) values('User','processed','Processed','active','Processed  User');
insert into status (type,code,name,state,description) values('User','deleted','Deleted','inactive','Deleted  User');
insert into status (type,code,name,state,description) values('User','closed','Closed','inactive','Closed  User');
insert into status (type,code,name,state,description) values('User','removed','Removed','inactive','Removed  User');
insert into status (type,code,name,state,description) values('User','cancelled','Cancelled','inactive','Cancelled User');
insert into status (type,code,name,state,description) values('UserHistory','new','New','active','New  UserHistory');
insert into status (type,code,name,state,description) values('UserHistory','submitted','Submitted','active','Submitted  UserHistory');
insert into status (type,code,name,state,description) values('UserHistory','approved','Approved','active','Approved  UserHistory');
insert into status (type,code,name,state,description) values('UserHistory','rejected','Rejected','active','Rejected  UserHistory');
insert into status (type,code,name,state,description) values('UserHistory','pending','Pending','active','Pending  UserHistory');
insert into status (type,code,name,state,description) values('UserHistory','processed','Processed','active','Processed  UserHistory');
insert into status (type,code,name,state,description) values('UserHistory','deleted','Deleted','inactive','Deleted  UserHistory');
insert into status (type,code,name,state,description) values('UserHistory','closed','Closed','inactive','Closed  UserHistory');
insert into status (type,code,name,state,description) values('UserHistory','removed','Removed','inactive','Removed  UserHistory');
insert into status (type,code,name,state,description) values('UserHistory','cancelled','Cancelled','inactive','Cancelled UserHistory');


/************* Lookup Script userLevel**********/
insert into lookup(type,code,name,shortname,description,status) 
select 'userLevel','admin','Admin','Adm','System Administrator',s.id from status s where type='Lookup' and code='new';

insert into lookup(type,code,name,shortname,description,status) 
select 'userLevel','customer','Customer','Cust','Customer',s.id from status s where type='Lookup' and code='new';


/************* Role Script **********/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'GOD','God Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Role for public Segment Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'public:MANAGER','public Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);
/**** Role for admin Segment Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'admin:MANAGER','admin Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);



/************* Privilege Script  &  Privilege Group Lookup Script **********/
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','AUDIT_TRAIL','AUDIT_TRAIL',null,'AUDIT_TRAIL privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('AUDIT_TRAIL_LIST','AUDIT_TRAIL List','AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('AUDIT_TRAIL_CREATE','AUDIT_TRAIL Create','AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('AUDIT_TRAIL_DETAIL','AUDIT_TRAIL Detail','AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('AUDIT_TRAIL_EDIT','AUDIT_TRAIL Edit','AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('AUDIT_TRAIL_DELETE','AUDIT_TRAIL Delete','AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('AUDIT_TRAIL_SUBMIT','AUDIT_TRAIL Submit','AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('AUDIT_TRAIL_APPROVE','AUDIT_TRAIL Approve','AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('AUDIT_TRAIL_REJECT','AUDIT_TRAIL Reject','AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('AUDIT_TRAIL_PENDING','AUDIT_TRAIL Pending','AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('AUDIT_TRAIL_PROCESS','AUDIT_TRAIL Process','AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('AUDIT_TRAIL_CLOSE','AUDIT_TRAIL Close','AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('AUDIT_TRAIL_REMOVE','AUDIT_TRAIL Remove','AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('AUDIT_TRAIL_CANCEL','AUDIT_TRAIL Cancel','AUDIT_TRAIL');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'AUDIT_TRAIL:MANAGER','AUDIT_TRAIL Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='AUDIT_TRAIL:MANAGER' and p.vgroup='AUDIT_TRAIL';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:AUDIT_TRAIL','ADMIN:AUDIT_TRAIL',null,'ADMIN:AUDIT_TRAIL privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:AUDIT_TRAIL_LIST','ADMIN:AUDIT_TRAIL List','ADMIN:AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:AUDIT_TRAIL_CREATE','ADMIN:AUDIT_TRAIL Create','ADMIN:AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:AUDIT_TRAIL_DETAIL','ADMIN:AUDIT_TRAIL Detail','ADMIN:AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:AUDIT_TRAIL_EDIT','ADMIN:AUDIT_TRAIL Edit','ADMIN:AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:AUDIT_TRAIL_DELETE','ADMIN:AUDIT_TRAIL Delete','ADMIN:AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:AUDIT_TRAIL_SUBMIT','ADMIN:AUDIT_TRAIL Submit','ADMIN:AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:AUDIT_TRAIL_APPROVE','ADMIN:AUDIT_TRAIL Approve','ADMIN:AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:AUDIT_TRAIL_REJECT','ADMIN:AUDIT_TRAIL Reject','ADMIN:AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:AUDIT_TRAIL_PENDING','ADMIN:AUDIT_TRAIL Pending','ADMIN:AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:AUDIT_TRAIL_PROCESS','ADMIN:AUDIT_TRAIL Process','ADMIN:AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:AUDIT_TRAIL_CLOSE','ADMIN:AUDIT_TRAIL Close','ADMIN:AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:AUDIT_TRAIL_REMOVE','ADMIN:AUDIT_TRAIL Remove','ADMIN:AUDIT_TRAIL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:AUDIT_TRAIL_CANCEL','ADMIN:AUDIT_TRAIL Cancel','ADMIN:AUDIT_TRAIL');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:AUDIT_TRAIL:MANAGER','ADMIN:AUDIT_TRAIL Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:AUDIT_TRAIL:MANAGER' and p.vgroup='ADMIN:AUDIT_TRAIL';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','EMAIL_LOG','EMAIL_LOG',null,'EMAIL_LOG privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('EMAIL_LOG_LIST','EMAIL_LOG List','EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('EMAIL_LOG_CREATE','EMAIL_LOG Create','EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('EMAIL_LOG_DETAIL','EMAIL_LOG Detail','EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('EMAIL_LOG_EDIT','EMAIL_LOG Edit','EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('EMAIL_LOG_DELETE','EMAIL_LOG Delete','EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('EMAIL_LOG_SUBMIT','EMAIL_LOG Submit','EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('EMAIL_LOG_APPROVE','EMAIL_LOG Approve','EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('EMAIL_LOG_REJECT','EMAIL_LOG Reject','EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('EMAIL_LOG_PENDING','EMAIL_LOG Pending','EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('EMAIL_LOG_PROCESS','EMAIL_LOG Process','EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('EMAIL_LOG_CLOSE','EMAIL_LOG Close','EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('EMAIL_LOG_REMOVE','EMAIL_LOG Remove','EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('EMAIL_LOG_CANCEL','EMAIL_LOG Cancel','EMAIL_LOG');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'EMAIL_LOG:MANAGER','EMAIL_LOG Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='EMAIL_LOG:MANAGER' and p.vgroup='EMAIL_LOG';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:EMAIL_LOG','ADMIN:EMAIL_LOG',null,'ADMIN:EMAIL_LOG privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:EMAIL_LOG_LIST','ADMIN:EMAIL_LOG List','ADMIN:EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('ADMIN:EMAIL_LOG_CREATE','ADMIN:EMAIL_LOG Create','ADMIN:EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('ADMIN:EMAIL_LOG_DETAIL','ADMIN:EMAIL_LOG Detail','ADMIN:EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('ADMIN:EMAIL_LOG_EDIT','ADMIN:EMAIL_LOG Edit','ADMIN:EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('ADMIN:EMAIL_LOG_DELETE','ADMIN:EMAIL_LOG Delete','ADMIN:EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('ADMIN:EMAIL_LOG_SUBMIT','ADMIN:EMAIL_LOG Submit','ADMIN:EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('ADMIN:EMAIL_LOG_APPROVE','ADMIN:EMAIL_LOG Approve','ADMIN:EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('ADMIN:EMAIL_LOG_REJECT','ADMIN:EMAIL_LOG Reject','ADMIN:EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('ADMIN:EMAIL_LOG_PENDING','ADMIN:EMAIL_LOG Pending','ADMIN:EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('ADMIN:EMAIL_LOG_PROCESS','ADMIN:EMAIL_LOG Process','ADMIN:EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('ADMIN:EMAIL_LOG_CLOSE','ADMIN:EMAIL_LOG Close','ADMIN:EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('ADMIN:EMAIL_LOG_REMOVE','ADMIN:EMAIL_LOG Remove','ADMIN:EMAIL_LOG');
insert into privilege(name,description,vgroup) 
values ('ADMIN:EMAIL_LOG_CANCEL','ADMIN:EMAIL_LOG Cancel','ADMIN:EMAIL_LOG');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:EMAIL_LOG:MANAGER','ADMIN:EMAIL_LOG Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:EMAIL_LOG:MANAGER' and p.vgroup='ADMIN:EMAIL_LOG';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','FOLDER','FOLDER',null,'FOLDER privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('FOLDER_LIST','FOLDER List','FOLDER');
insert into privilege(name,description,vgroup) 
values ('FOLDER_CREATE','FOLDER Create','FOLDER');
insert into privilege(name,description,vgroup) 
values ('FOLDER_DETAIL','FOLDER Detail','FOLDER');
insert into privilege(name,description,vgroup) 
values ('FOLDER_EDIT','FOLDER Edit','FOLDER');
insert into privilege(name,description,vgroup) 
values ('FOLDER_DELETE','FOLDER Delete','FOLDER');
insert into privilege(name,description,vgroup) 
values ('FOLDER_SUBMIT','FOLDER Submit','FOLDER');
insert into privilege(name,description,vgroup) 
values ('FOLDER_APPROVE','FOLDER Approve','FOLDER');
insert into privilege(name,description,vgroup) 
values ('FOLDER_REJECT','FOLDER Reject','FOLDER');
insert into privilege(name,description,vgroup) 
values ('FOLDER_PENDING','FOLDER Pending','FOLDER');
insert into privilege(name,description,vgroup) 
values ('FOLDER_PROCESS','FOLDER Process','FOLDER');
insert into privilege(name,description,vgroup) 
values ('FOLDER_CLOSE','FOLDER Close','FOLDER');
insert into privilege(name,description,vgroup) 
values ('FOLDER_REMOVE','FOLDER Remove','FOLDER');
insert into privilege(name,description,vgroup) 
values ('FOLDER_CANCEL','FOLDER Cancel','FOLDER');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'FOLDER:MANAGER','FOLDER Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='FOLDER:MANAGER' and p.vgroup='FOLDER';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:FOLDER','ADMIN:FOLDER',null,'ADMIN:FOLDER privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:FOLDER_LIST','ADMIN:FOLDER List','ADMIN:FOLDER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FOLDER_CREATE','ADMIN:FOLDER Create','ADMIN:FOLDER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FOLDER_DETAIL','ADMIN:FOLDER Detail','ADMIN:FOLDER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FOLDER_EDIT','ADMIN:FOLDER Edit','ADMIN:FOLDER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FOLDER_DELETE','ADMIN:FOLDER Delete','ADMIN:FOLDER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FOLDER_SUBMIT','ADMIN:FOLDER Submit','ADMIN:FOLDER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FOLDER_APPROVE','ADMIN:FOLDER Approve','ADMIN:FOLDER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FOLDER_REJECT','ADMIN:FOLDER Reject','ADMIN:FOLDER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FOLDER_PENDING','ADMIN:FOLDER Pending','ADMIN:FOLDER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FOLDER_PROCESS','ADMIN:FOLDER Process','ADMIN:FOLDER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FOLDER_CLOSE','ADMIN:FOLDER Close','ADMIN:FOLDER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FOLDER_REMOVE','ADMIN:FOLDER Remove','ADMIN:FOLDER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FOLDER_CANCEL','ADMIN:FOLDER Cancel','ADMIN:FOLDER');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:FOLDER:MANAGER','ADMIN:FOLDER Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:FOLDER:MANAGER' and p.vgroup='ADMIN:FOLDER';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','FORUM','FORUM',null,'FORUM privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('FORUM_LIST','FORUM List','FORUM');
insert into privilege(name,description,vgroup) 
values ('FORUM_CREATE','FORUM Create','FORUM');
insert into privilege(name,description,vgroup) 
values ('FORUM_DETAIL','FORUM Detail','FORUM');
insert into privilege(name,description,vgroup) 
values ('FORUM_EDIT','FORUM Edit','FORUM');
insert into privilege(name,description,vgroup) 
values ('FORUM_DELETE','FORUM Delete','FORUM');
insert into privilege(name,description,vgroup) 
values ('FORUM_SUBMIT','FORUM Submit','FORUM');
insert into privilege(name,description,vgroup) 
values ('FORUM_APPROVE','FORUM Approve','FORUM');
insert into privilege(name,description,vgroup) 
values ('FORUM_REJECT','FORUM Reject','FORUM');
insert into privilege(name,description,vgroup) 
values ('FORUM_PENDING','FORUM Pending','FORUM');
insert into privilege(name,description,vgroup) 
values ('FORUM_PROCESS','FORUM Process','FORUM');
insert into privilege(name,description,vgroup) 
values ('FORUM_CLOSE','FORUM Close','FORUM');
insert into privilege(name,description,vgroup) 
values ('FORUM_REMOVE','FORUM Remove','FORUM');
insert into privilege(name,description,vgroup) 
values ('FORUM_CANCEL','FORUM Cancel','FORUM');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'FORUM:MANAGER','FORUM Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='FORUM:MANAGER' and p.vgroup='FORUM';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:FORUM','ADMIN:FORUM',null,'ADMIN:FORUM privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:FORUM_LIST','ADMIN:FORUM List','ADMIN:FORUM');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FORUM_CREATE','ADMIN:FORUM Create','ADMIN:FORUM');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FORUM_DETAIL','ADMIN:FORUM Detail','ADMIN:FORUM');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FORUM_EDIT','ADMIN:FORUM Edit','ADMIN:FORUM');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FORUM_DELETE','ADMIN:FORUM Delete','ADMIN:FORUM');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FORUM_SUBMIT','ADMIN:FORUM Submit','ADMIN:FORUM');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FORUM_APPROVE','ADMIN:FORUM Approve','ADMIN:FORUM');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FORUM_REJECT','ADMIN:FORUM Reject','ADMIN:FORUM');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FORUM_PENDING','ADMIN:FORUM Pending','ADMIN:FORUM');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FORUM_PROCESS','ADMIN:FORUM Process','ADMIN:FORUM');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FORUM_CLOSE','ADMIN:FORUM Close','ADMIN:FORUM');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FORUM_REMOVE','ADMIN:FORUM Remove','ADMIN:FORUM');
insert into privilege(name,description,vgroup) 
values ('ADMIN:FORUM_CANCEL','ADMIN:FORUM Cancel','ADMIN:FORUM');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:FORUM:MANAGER','ADMIN:FORUM Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:FORUM:MANAGER' and p.vgroup='ADMIN:FORUM';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','BOOKMARK','BOOKMARK',null,'BOOKMARK privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('BOOKMARK_LIST','BOOKMARK List','BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('BOOKMARK_CREATE','BOOKMARK Create','BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('BOOKMARK_DETAIL','BOOKMARK Detail','BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('BOOKMARK_EDIT','BOOKMARK Edit','BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('BOOKMARK_DELETE','BOOKMARK Delete','BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('BOOKMARK_SUBMIT','BOOKMARK Submit','BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('BOOKMARK_APPROVE','BOOKMARK Approve','BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('BOOKMARK_REJECT','BOOKMARK Reject','BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('BOOKMARK_PENDING','BOOKMARK Pending','BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('BOOKMARK_PROCESS','BOOKMARK Process','BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('BOOKMARK_CLOSE','BOOKMARK Close','BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('BOOKMARK_REMOVE','BOOKMARK Remove','BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('BOOKMARK_CANCEL','BOOKMARK Cancel','BOOKMARK');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'BOOKMARK:MANAGER','BOOKMARK Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='BOOKMARK:MANAGER' and p.vgroup='BOOKMARK';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:BOOKMARK','ADMIN:BOOKMARK',null,'ADMIN:BOOKMARK privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:BOOKMARK_LIST','ADMIN:BOOKMARK List','ADMIN:BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('ADMIN:BOOKMARK_CREATE','ADMIN:BOOKMARK Create','ADMIN:BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('ADMIN:BOOKMARK_DETAIL','ADMIN:BOOKMARK Detail','ADMIN:BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('ADMIN:BOOKMARK_EDIT','ADMIN:BOOKMARK Edit','ADMIN:BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('ADMIN:BOOKMARK_DELETE','ADMIN:BOOKMARK Delete','ADMIN:BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('ADMIN:BOOKMARK_SUBMIT','ADMIN:BOOKMARK Submit','ADMIN:BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('ADMIN:BOOKMARK_APPROVE','ADMIN:BOOKMARK Approve','ADMIN:BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('ADMIN:BOOKMARK_REJECT','ADMIN:BOOKMARK Reject','ADMIN:BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('ADMIN:BOOKMARK_PENDING','ADMIN:BOOKMARK Pending','ADMIN:BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('ADMIN:BOOKMARK_PROCESS','ADMIN:BOOKMARK Process','ADMIN:BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('ADMIN:BOOKMARK_CLOSE','ADMIN:BOOKMARK Close','ADMIN:BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('ADMIN:BOOKMARK_REMOVE','ADMIN:BOOKMARK Remove','ADMIN:BOOKMARK');
insert into privilege(name,description,vgroup) 
values ('ADMIN:BOOKMARK_CANCEL','ADMIN:BOOKMARK Cancel','ADMIN:BOOKMARK');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:BOOKMARK:MANAGER','ADMIN:BOOKMARK Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:BOOKMARK:MANAGER' and p.vgroup='ADMIN:BOOKMARK';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','DOCUMENT','DOCUMENT',null,'DOCUMENT privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('DOCUMENT_LIST','DOCUMENT List','DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_CREATE','DOCUMENT Create','DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_DETAIL','DOCUMENT Detail','DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_EDIT','DOCUMENT Edit','DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_DELETE','DOCUMENT Delete','DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_SUBMIT','DOCUMENT Submit','DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_APPROVE','DOCUMENT Approve','DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_REJECT','DOCUMENT Reject','DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_PENDING','DOCUMENT Pending','DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_PROCESS','DOCUMENT Process','DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_CLOSE','DOCUMENT Close','DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_REMOVE','DOCUMENT Remove','DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_CANCEL','DOCUMENT Cancel','DOCUMENT');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'DOCUMENT:MANAGER','DOCUMENT Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='DOCUMENT:MANAGER' and p.vgroup='DOCUMENT';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:DOCUMENT','ADMIN:DOCUMENT',null,'ADMIN:DOCUMENT privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_LIST','ADMIN:DOCUMENT List','ADMIN:DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_CREATE','ADMIN:DOCUMENT Create','ADMIN:DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_DETAIL','ADMIN:DOCUMENT Detail','ADMIN:DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_EDIT','ADMIN:DOCUMENT Edit','ADMIN:DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_DELETE','ADMIN:DOCUMENT Delete','ADMIN:DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_SUBMIT','ADMIN:DOCUMENT Submit','ADMIN:DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_APPROVE','ADMIN:DOCUMENT Approve','ADMIN:DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_REJECT','ADMIN:DOCUMENT Reject','ADMIN:DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_PENDING','ADMIN:DOCUMENT Pending','ADMIN:DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_PROCESS','ADMIN:DOCUMENT Process','ADMIN:DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_CLOSE','ADMIN:DOCUMENT Close','ADMIN:DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_REMOVE','ADMIN:DOCUMENT Remove','ADMIN:DOCUMENT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_CANCEL','ADMIN:DOCUMENT Cancel','ADMIN:DOCUMENT');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:DOCUMENT:MANAGER','ADMIN:DOCUMENT Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:DOCUMENT:MANAGER' and p.vgroup='ADMIN:DOCUMENT';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','DOCUMENT_HISTORY','DOCUMENT_HISTORY',null,'DOCUMENT_HISTORY privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('DOCUMENT_HISTORY_LIST','DOCUMENT_HISTORY List','DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_HISTORY_CREATE','DOCUMENT_HISTORY Create','DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_HISTORY_DETAIL','DOCUMENT_HISTORY Detail','DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_HISTORY_EDIT','DOCUMENT_HISTORY Edit','DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_HISTORY_DELETE','DOCUMENT_HISTORY Delete','DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_HISTORY_SUBMIT','DOCUMENT_HISTORY Submit','DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_HISTORY_APPROVE','DOCUMENT_HISTORY Approve','DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_HISTORY_REJECT','DOCUMENT_HISTORY Reject','DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_HISTORY_PENDING','DOCUMENT_HISTORY Pending','DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_HISTORY_PROCESS','DOCUMENT_HISTORY Process','DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_HISTORY_CLOSE','DOCUMENT_HISTORY Close','DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_HISTORY_REMOVE','DOCUMENT_HISTORY Remove','DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('DOCUMENT_HISTORY_CANCEL','DOCUMENT_HISTORY Cancel','DOCUMENT_HISTORY');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'DOCUMENT_HISTORY:MANAGER','DOCUMENT_HISTORY Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='DOCUMENT_HISTORY:MANAGER' and p.vgroup='DOCUMENT_HISTORY';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:DOCUMENT_HISTORY','ADMIN:DOCUMENT_HISTORY',null,'ADMIN:DOCUMENT_HISTORY privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_HISTORY_LIST','ADMIN:DOCUMENT_HISTORY List','ADMIN:DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_HISTORY_CREATE','ADMIN:DOCUMENT_HISTORY Create','ADMIN:DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_HISTORY_DETAIL','ADMIN:DOCUMENT_HISTORY Detail','ADMIN:DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_HISTORY_EDIT','ADMIN:DOCUMENT_HISTORY Edit','ADMIN:DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_HISTORY_DELETE','ADMIN:DOCUMENT_HISTORY Delete','ADMIN:DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_HISTORY_SUBMIT','ADMIN:DOCUMENT_HISTORY Submit','ADMIN:DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_HISTORY_APPROVE','ADMIN:DOCUMENT_HISTORY Approve','ADMIN:DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_HISTORY_REJECT','ADMIN:DOCUMENT_HISTORY Reject','ADMIN:DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_HISTORY_PENDING','ADMIN:DOCUMENT_HISTORY Pending','ADMIN:DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_HISTORY_PROCESS','ADMIN:DOCUMENT_HISTORY Process','ADMIN:DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_HISTORY_CLOSE','ADMIN:DOCUMENT_HISTORY Close','ADMIN:DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_HISTORY_REMOVE','ADMIN:DOCUMENT_HISTORY Remove','ADMIN:DOCUMENT_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:DOCUMENT_HISTORY_CANCEL','ADMIN:DOCUMENT_HISTORY Cancel','ADMIN:DOCUMENT_HISTORY');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:DOCUMENT_HISTORY:MANAGER','ADMIN:DOCUMENT_HISTORY Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:DOCUMENT_HISTORY:MANAGER' and p.vgroup='ADMIN:DOCUMENT_HISTORY';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','TOPIC','TOPIC',null,'TOPIC privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('TOPIC_LIST','TOPIC List','TOPIC');
insert into privilege(name,description,vgroup) 
values ('TOPIC_CREATE','TOPIC Create','TOPIC');
insert into privilege(name,description,vgroup) 
values ('TOPIC_DETAIL','TOPIC Detail','TOPIC');
insert into privilege(name,description,vgroup) 
values ('TOPIC_EDIT','TOPIC Edit','TOPIC');
insert into privilege(name,description,vgroup) 
values ('TOPIC_DELETE','TOPIC Delete','TOPIC');
insert into privilege(name,description,vgroup) 
values ('TOPIC_SUBMIT','TOPIC Submit','TOPIC');
insert into privilege(name,description,vgroup) 
values ('TOPIC_APPROVE','TOPIC Approve','TOPIC');
insert into privilege(name,description,vgroup) 
values ('TOPIC_REJECT','TOPIC Reject','TOPIC');
insert into privilege(name,description,vgroup) 
values ('TOPIC_PENDING','TOPIC Pending','TOPIC');
insert into privilege(name,description,vgroup) 
values ('TOPIC_PROCESS','TOPIC Process','TOPIC');
insert into privilege(name,description,vgroup) 
values ('TOPIC_CLOSE','TOPIC Close','TOPIC');
insert into privilege(name,description,vgroup) 
values ('TOPIC_REMOVE','TOPIC Remove','TOPIC');
insert into privilege(name,description,vgroup) 
values ('TOPIC_CANCEL','TOPIC Cancel','TOPIC');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'TOPIC:MANAGER','TOPIC Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='TOPIC:MANAGER' and p.vgroup='TOPIC';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:TOPIC','ADMIN:TOPIC',null,'ADMIN:TOPIC privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:TOPIC_LIST','ADMIN:TOPIC List','ADMIN:TOPIC');
insert into privilege(name,description,vgroup) 
values ('ADMIN:TOPIC_CREATE','ADMIN:TOPIC Create','ADMIN:TOPIC');
insert into privilege(name,description,vgroup) 
values ('ADMIN:TOPIC_DETAIL','ADMIN:TOPIC Detail','ADMIN:TOPIC');
insert into privilege(name,description,vgroup) 
values ('ADMIN:TOPIC_EDIT','ADMIN:TOPIC Edit','ADMIN:TOPIC');
insert into privilege(name,description,vgroup) 
values ('ADMIN:TOPIC_DELETE','ADMIN:TOPIC Delete','ADMIN:TOPIC');
insert into privilege(name,description,vgroup) 
values ('ADMIN:TOPIC_SUBMIT','ADMIN:TOPIC Submit','ADMIN:TOPIC');
insert into privilege(name,description,vgroup) 
values ('ADMIN:TOPIC_APPROVE','ADMIN:TOPIC Approve','ADMIN:TOPIC');
insert into privilege(name,description,vgroup) 
values ('ADMIN:TOPIC_REJECT','ADMIN:TOPIC Reject','ADMIN:TOPIC');
insert into privilege(name,description,vgroup) 
values ('ADMIN:TOPIC_PENDING','ADMIN:TOPIC Pending','ADMIN:TOPIC');
insert into privilege(name,description,vgroup) 
values ('ADMIN:TOPIC_PROCESS','ADMIN:TOPIC Process','ADMIN:TOPIC');
insert into privilege(name,description,vgroup) 
values ('ADMIN:TOPIC_CLOSE','ADMIN:TOPIC Close','ADMIN:TOPIC');
insert into privilege(name,description,vgroup) 
values ('ADMIN:TOPIC_REMOVE','ADMIN:TOPIC Remove','ADMIN:TOPIC');
insert into privilege(name,description,vgroup) 
values ('ADMIN:TOPIC_CANCEL','ADMIN:TOPIC Cancel','ADMIN:TOPIC');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:TOPIC:MANAGER','ADMIN:TOPIC Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:TOPIC:MANAGER' and p.vgroup='ADMIN:TOPIC';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','MESSAGE','MESSAGE',null,'MESSAGE privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('MESSAGE_LIST','MESSAGE List','MESSAGE');
insert into privilege(name,description,vgroup) 
values ('MESSAGE_CREATE','MESSAGE Create','MESSAGE');
insert into privilege(name,description,vgroup) 
values ('MESSAGE_DETAIL','MESSAGE Detail','MESSAGE');
insert into privilege(name,description,vgroup) 
values ('MESSAGE_EDIT','MESSAGE Edit','MESSAGE');
insert into privilege(name,description,vgroup) 
values ('MESSAGE_DELETE','MESSAGE Delete','MESSAGE');
insert into privilege(name,description,vgroup) 
values ('MESSAGE_SUBMIT','MESSAGE Submit','MESSAGE');
insert into privilege(name,description,vgroup) 
values ('MESSAGE_APPROVE','MESSAGE Approve','MESSAGE');
insert into privilege(name,description,vgroup) 
values ('MESSAGE_REJECT','MESSAGE Reject','MESSAGE');
insert into privilege(name,description,vgroup) 
values ('MESSAGE_PENDING','MESSAGE Pending','MESSAGE');
insert into privilege(name,description,vgroup) 
values ('MESSAGE_PROCESS','MESSAGE Process','MESSAGE');
insert into privilege(name,description,vgroup) 
values ('MESSAGE_CLOSE','MESSAGE Close','MESSAGE');
insert into privilege(name,description,vgroup) 
values ('MESSAGE_REMOVE','MESSAGE Remove','MESSAGE');
insert into privilege(name,description,vgroup) 
values ('MESSAGE_CANCEL','MESSAGE Cancel','MESSAGE');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'MESSAGE:MANAGER','MESSAGE Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='MESSAGE:MANAGER' and p.vgroup='MESSAGE';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:MESSAGE','ADMIN:MESSAGE',null,'ADMIN:MESSAGE privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:MESSAGE_LIST','ADMIN:MESSAGE List','ADMIN:MESSAGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:MESSAGE_CREATE','ADMIN:MESSAGE Create','ADMIN:MESSAGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:MESSAGE_DETAIL','ADMIN:MESSAGE Detail','ADMIN:MESSAGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:MESSAGE_EDIT','ADMIN:MESSAGE Edit','ADMIN:MESSAGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:MESSAGE_DELETE','ADMIN:MESSAGE Delete','ADMIN:MESSAGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:MESSAGE_SUBMIT','ADMIN:MESSAGE Submit','ADMIN:MESSAGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:MESSAGE_APPROVE','ADMIN:MESSAGE Approve','ADMIN:MESSAGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:MESSAGE_REJECT','ADMIN:MESSAGE Reject','ADMIN:MESSAGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:MESSAGE_PENDING','ADMIN:MESSAGE Pending','ADMIN:MESSAGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:MESSAGE_PROCESS','ADMIN:MESSAGE Process','ADMIN:MESSAGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:MESSAGE_CLOSE','ADMIN:MESSAGE Close','ADMIN:MESSAGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:MESSAGE_REMOVE','ADMIN:MESSAGE Remove','ADMIN:MESSAGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:MESSAGE_CANCEL','ADMIN:MESSAGE Cancel','ADMIN:MESSAGE');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:MESSAGE:MANAGER','ADMIN:MESSAGE Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:MESSAGE:MANAGER' and p.vgroup='ADMIN:MESSAGE';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','NOTIFICATION','NOTIFICATION',null,'NOTIFICATION privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('NOTIFICATION_LIST','NOTIFICATION List','NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('NOTIFICATION_CREATE','NOTIFICATION Create','NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('NOTIFICATION_DETAIL','NOTIFICATION Detail','NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('NOTIFICATION_EDIT','NOTIFICATION Edit','NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('NOTIFICATION_DELETE','NOTIFICATION Delete','NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('NOTIFICATION_SUBMIT','NOTIFICATION Submit','NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('NOTIFICATION_APPROVE','NOTIFICATION Approve','NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('NOTIFICATION_REJECT','NOTIFICATION Reject','NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('NOTIFICATION_PENDING','NOTIFICATION Pending','NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('NOTIFICATION_PROCESS','NOTIFICATION Process','NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('NOTIFICATION_CLOSE','NOTIFICATION Close','NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('NOTIFICATION_REMOVE','NOTIFICATION Remove','NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('NOTIFICATION_CANCEL','NOTIFICATION Cancel','NOTIFICATION');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'NOTIFICATION:MANAGER','NOTIFICATION Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='NOTIFICATION:MANAGER' and p.vgroup='NOTIFICATION';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:NOTIFICATION','ADMIN:NOTIFICATION',null,'ADMIN:NOTIFICATION privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:NOTIFICATION_LIST','ADMIN:NOTIFICATION List','ADMIN:NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:NOTIFICATION_CREATE','ADMIN:NOTIFICATION Create','ADMIN:NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:NOTIFICATION_DETAIL','ADMIN:NOTIFICATION Detail','ADMIN:NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:NOTIFICATION_EDIT','ADMIN:NOTIFICATION Edit','ADMIN:NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:NOTIFICATION_DELETE','ADMIN:NOTIFICATION Delete','ADMIN:NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:NOTIFICATION_SUBMIT','ADMIN:NOTIFICATION Submit','ADMIN:NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:NOTIFICATION_APPROVE','ADMIN:NOTIFICATION Approve','ADMIN:NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:NOTIFICATION_REJECT','ADMIN:NOTIFICATION Reject','ADMIN:NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:NOTIFICATION_PENDING','ADMIN:NOTIFICATION Pending','ADMIN:NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:NOTIFICATION_PROCESS','ADMIN:NOTIFICATION Process','ADMIN:NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:NOTIFICATION_CLOSE','ADMIN:NOTIFICATION Close','ADMIN:NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:NOTIFICATION_REMOVE','ADMIN:NOTIFICATION Remove','ADMIN:NOTIFICATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:NOTIFICATION_CANCEL','ADMIN:NOTIFICATION Cancel','ADMIN:NOTIFICATION');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:NOTIFICATION:MANAGER','ADMIN:NOTIFICATION Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:NOTIFICATION:MANAGER' and p.vgroup='ADMIN:NOTIFICATION';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','GENERIC_REPORT','GENERIC_REPORT',null,'GENERIC_REPORT privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('GENERIC_REPORT_LIST','GENERIC_REPORT List','GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('GENERIC_REPORT_CREATE','GENERIC_REPORT Create','GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('GENERIC_REPORT_DETAIL','GENERIC_REPORT Detail','GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('GENERIC_REPORT_EDIT','GENERIC_REPORT Edit','GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('GENERIC_REPORT_DELETE','GENERIC_REPORT Delete','GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('GENERIC_REPORT_SUBMIT','GENERIC_REPORT Submit','GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('GENERIC_REPORT_APPROVE','GENERIC_REPORT Approve','GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('GENERIC_REPORT_REJECT','GENERIC_REPORT Reject','GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('GENERIC_REPORT_PENDING','GENERIC_REPORT Pending','GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('GENERIC_REPORT_PROCESS','GENERIC_REPORT Process','GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('GENERIC_REPORT_CLOSE','GENERIC_REPORT Close','GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('GENERIC_REPORT_REMOVE','GENERIC_REPORT Remove','GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('GENERIC_REPORT_CANCEL','GENERIC_REPORT Cancel','GENERIC_REPORT');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'GENERIC_REPORT:MANAGER','GENERIC_REPORT Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='GENERIC_REPORT:MANAGER' and p.vgroup='GENERIC_REPORT';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:GENERIC_REPORT','ADMIN:GENERIC_REPORT',null,'ADMIN:GENERIC_REPORT privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:GENERIC_REPORT_LIST','ADMIN:GENERIC_REPORT List','ADMIN:GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:GENERIC_REPORT_CREATE','ADMIN:GENERIC_REPORT Create','ADMIN:GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:GENERIC_REPORT_DETAIL','ADMIN:GENERIC_REPORT Detail','ADMIN:GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:GENERIC_REPORT_EDIT','ADMIN:GENERIC_REPORT Edit','ADMIN:GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:GENERIC_REPORT_DELETE','ADMIN:GENERIC_REPORT Delete','ADMIN:GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:GENERIC_REPORT_SUBMIT','ADMIN:GENERIC_REPORT Submit','ADMIN:GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:GENERIC_REPORT_APPROVE','ADMIN:GENERIC_REPORT Approve','ADMIN:GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:GENERIC_REPORT_REJECT','ADMIN:GENERIC_REPORT Reject','ADMIN:GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:GENERIC_REPORT_PENDING','ADMIN:GENERIC_REPORT Pending','ADMIN:GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:GENERIC_REPORT_PROCESS','ADMIN:GENERIC_REPORT Process','ADMIN:GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:GENERIC_REPORT_CLOSE','ADMIN:GENERIC_REPORT Close','ADMIN:GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:GENERIC_REPORT_REMOVE','ADMIN:GENERIC_REPORT Remove','ADMIN:GENERIC_REPORT');
insert into privilege(name,description,vgroup) 
values ('ADMIN:GENERIC_REPORT_CANCEL','ADMIN:GENERIC_REPORT Cancel','ADMIN:GENERIC_REPORT');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:GENERIC_REPORT:MANAGER','ADMIN:GENERIC_REPORT Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:GENERIC_REPORT:MANAGER' and p.vgroup='ADMIN:GENERIC_REPORT';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','I_PASSPORT_POOL','I_PASSPORT_POOL',null,'I_PASSPORT_POOL privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('I_PASSPORT_POOL_LIST','I_PASSPORT_POOL List','I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('I_PASSPORT_POOL_CREATE','I_PASSPORT_POOL Create','I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('I_PASSPORT_POOL_DETAIL','I_PASSPORT_POOL Detail','I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('I_PASSPORT_POOL_EDIT','I_PASSPORT_POOL Edit','I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('I_PASSPORT_POOL_DELETE','I_PASSPORT_POOL Delete','I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('I_PASSPORT_POOL_SUBMIT','I_PASSPORT_POOL Submit','I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('I_PASSPORT_POOL_APPROVE','I_PASSPORT_POOL Approve','I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('I_PASSPORT_POOL_REJECT','I_PASSPORT_POOL Reject','I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('I_PASSPORT_POOL_PENDING','I_PASSPORT_POOL Pending','I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('I_PASSPORT_POOL_PROCESS','I_PASSPORT_POOL Process','I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('I_PASSPORT_POOL_CLOSE','I_PASSPORT_POOL Close','I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('I_PASSPORT_POOL_REMOVE','I_PASSPORT_POOL Remove','I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('I_PASSPORT_POOL_CANCEL','I_PASSPORT_POOL Cancel','I_PASSPORT_POOL');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'I_PASSPORT_POOL:MANAGER','I_PASSPORT_POOL Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='I_PASSPORT_POOL:MANAGER' and p.vgroup='I_PASSPORT_POOL';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:I_PASSPORT_POOL','ADMIN:I_PASSPORT_POOL',null,'ADMIN:I_PASSPORT_POOL privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:I_PASSPORT_POOL_LIST','ADMIN:I_PASSPORT_POOL List','ADMIN:I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:I_PASSPORT_POOL_CREATE','ADMIN:I_PASSPORT_POOL Create','ADMIN:I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:I_PASSPORT_POOL_DETAIL','ADMIN:I_PASSPORT_POOL Detail','ADMIN:I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:I_PASSPORT_POOL_EDIT','ADMIN:I_PASSPORT_POOL Edit','ADMIN:I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:I_PASSPORT_POOL_DELETE','ADMIN:I_PASSPORT_POOL Delete','ADMIN:I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:I_PASSPORT_POOL_SUBMIT','ADMIN:I_PASSPORT_POOL Submit','ADMIN:I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:I_PASSPORT_POOL_APPROVE','ADMIN:I_PASSPORT_POOL Approve','ADMIN:I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:I_PASSPORT_POOL_REJECT','ADMIN:I_PASSPORT_POOL Reject','ADMIN:I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:I_PASSPORT_POOL_PENDING','ADMIN:I_PASSPORT_POOL Pending','ADMIN:I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:I_PASSPORT_POOL_PROCESS','ADMIN:I_PASSPORT_POOL Process','ADMIN:I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:I_PASSPORT_POOL_CLOSE','ADMIN:I_PASSPORT_POOL Close','ADMIN:I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:I_PASSPORT_POOL_REMOVE','ADMIN:I_PASSPORT_POOL Remove','ADMIN:I_PASSPORT_POOL');
insert into privilege(name,description,vgroup) 
values ('ADMIN:I_PASSPORT_POOL_CANCEL','ADMIN:I_PASSPORT_POOL Cancel','ADMIN:I_PASSPORT_POOL');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:I_PASSPORT_POOL:MANAGER','ADMIN:I_PASSPORT_POOL Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:I_PASSPORT_POOL:MANAGER' and p.vgroup='ADMIN:I_PASSPORT_POOL';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','LOGIN_HISTORY','LOGIN_HISTORY',null,'LOGIN_HISTORY privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('LOGIN_HISTORY_LIST','LOGIN_HISTORY List','LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('LOGIN_HISTORY_CREATE','LOGIN_HISTORY Create','LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('LOGIN_HISTORY_DETAIL','LOGIN_HISTORY Detail','LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('LOGIN_HISTORY_EDIT','LOGIN_HISTORY Edit','LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('LOGIN_HISTORY_DELETE','LOGIN_HISTORY Delete','LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('LOGIN_HISTORY_SUBMIT','LOGIN_HISTORY Submit','LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('LOGIN_HISTORY_APPROVE','LOGIN_HISTORY Approve','LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('LOGIN_HISTORY_REJECT','LOGIN_HISTORY Reject','LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('LOGIN_HISTORY_PENDING','LOGIN_HISTORY Pending','LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('LOGIN_HISTORY_PROCESS','LOGIN_HISTORY Process','LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('LOGIN_HISTORY_CLOSE','LOGIN_HISTORY Close','LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('LOGIN_HISTORY_REMOVE','LOGIN_HISTORY Remove','LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('LOGIN_HISTORY_CANCEL','LOGIN_HISTORY Cancel','LOGIN_HISTORY');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'LOGIN_HISTORY:MANAGER','LOGIN_HISTORY Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='LOGIN_HISTORY:MANAGER' and p.vgroup='LOGIN_HISTORY';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:LOGIN_HISTORY','ADMIN:LOGIN_HISTORY',null,'ADMIN:LOGIN_HISTORY privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:LOGIN_HISTORY_LIST','ADMIN:LOGIN_HISTORY List','ADMIN:LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOGIN_HISTORY_CREATE','ADMIN:LOGIN_HISTORY Create','ADMIN:LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOGIN_HISTORY_DETAIL','ADMIN:LOGIN_HISTORY Detail','ADMIN:LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOGIN_HISTORY_EDIT','ADMIN:LOGIN_HISTORY Edit','ADMIN:LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOGIN_HISTORY_DELETE','ADMIN:LOGIN_HISTORY Delete','ADMIN:LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOGIN_HISTORY_SUBMIT','ADMIN:LOGIN_HISTORY Submit','ADMIN:LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOGIN_HISTORY_APPROVE','ADMIN:LOGIN_HISTORY Approve','ADMIN:LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOGIN_HISTORY_REJECT','ADMIN:LOGIN_HISTORY Reject','ADMIN:LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOGIN_HISTORY_PENDING','ADMIN:LOGIN_HISTORY Pending','ADMIN:LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOGIN_HISTORY_PROCESS','ADMIN:LOGIN_HISTORY Process','ADMIN:LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOGIN_HISTORY_CLOSE','ADMIN:LOGIN_HISTORY Close','ADMIN:LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOGIN_HISTORY_REMOVE','ADMIN:LOGIN_HISTORY Remove','ADMIN:LOGIN_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOGIN_HISTORY_CANCEL','ADMIN:LOGIN_HISTORY Cancel','ADMIN:LOGIN_HISTORY');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:LOGIN_HISTORY:MANAGER','ADMIN:LOGIN_HISTORY Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:LOGIN_HISTORY:MANAGER' and p.vgroup='ADMIN:LOGIN_HISTORY';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','LOOKUP','LOOKUP',null,'LOOKUP privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('LOOKUP_LIST','LOOKUP List','LOOKUP');
insert into privilege(name,description,vgroup) 
values ('LOOKUP_CREATE','LOOKUP Create','LOOKUP');
insert into privilege(name,description,vgroup) 
values ('LOOKUP_DETAIL','LOOKUP Detail','LOOKUP');
insert into privilege(name,description,vgroup) 
values ('LOOKUP_EDIT','LOOKUP Edit','LOOKUP');
insert into privilege(name,description,vgroup) 
values ('LOOKUP_DELETE','LOOKUP Delete','LOOKUP');
insert into privilege(name,description,vgroup) 
values ('LOOKUP_SUBMIT','LOOKUP Submit','LOOKUP');
insert into privilege(name,description,vgroup) 
values ('LOOKUP_APPROVE','LOOKUP Approve','LOOKUP');
insert into privilege(name,description,vgroup) 
values ('LOOKUP_REJECT','LOOKUP Reject','LOOKUP');
insert into privilege(name,description,vgroup) 
values ('LOOKUP_PENDING','LOOKUP Pending','LOOKUP');
insert into privilege(name,description,vgroup) 
values ('LOOKUP_PROCESS','LOOKUP Process','LOOKUP');
insert into privilege(name,description,vgroup) 
values ('LOOKUP_CLOSE','LOOKUP Close','LOOKUP');
insert into privilege(name,description,vgroup) 
values ('LOOKUP_REMOVE','LOOKUP Remove','LOOKUP');
insert into privilege(name,description,vgroup) 
values ('LOOKUP_CANCEL','LOOKUP Cancel','LOOKUP');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'LOOKUP:MANAGER','LOOKUP Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='LOOKUP:MANAGER' and p.vgroup='LOOKUP';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:LOOKUP','ADMIN:LOOKUP',null,'ADMIN:LOOKUP privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:LOOKUP_LIST','ADMIN:LOOKUP List','ADMIN:LOOKUP');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOOKUP_CREATE','ADMIN:LOOKUP Create','ADMIN:LOOKUP');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOOKUP_DETAIL','ADMIN:LOOKUP Detail','ADMIN:LOOKUP');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOOKUP_EDIT','ADMIN:LOOKUP Edit','ADMIN:LOOKUP');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOOKUP_DELETE','ADMIN:LOOKUP Delete','ADMIN:LOOKUP');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOOKUP_SUBMIT','ADMIN:LOOKUP Submit','ADMIN:LOOKUP');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOOKUP_APPROVE','ADMIN:LOOKUP Approve','ADMIN:LOOKUP');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOOKUP_REJECT','ADMIN:LOOKUP Reject','ADMIN:LOOKUP');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOOKUP_PENDING','ADMIN:LOOKUP Pending','ADMIN:LOOKUP');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOOKUP_PROCESS','ADMIN:LOOKUP Process','ADMIN:LOOKUP');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOOKUP_CLOSE','ADMIN:LOOKUP Close','ADMIN:LOOKUP');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOOKUP_REMOVE','ADMIN:LOOKUP Remove','ADMIN:LOOKUP');
insert into privilege(name,description,vgroup) 
values ('ADMIN:LOOKUP_CANCEL','ADMIN:LOOKUP Cancel','ADMIN:LOOKUP');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:LOOKUP:MANAGER','ADMIN:LOOKUP Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:LOOKUP:MANAGER' and p.vgroup='ADMIN:LOOKUP';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ORGANIZATION','ORGANIZATION',null,'ORGANIZATION privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ORGANIZATION_LIST','ORGANIZATION List','ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ORGANIZATION_CREATE','ORGANIZATION Create','ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ORGANIZATION_DETAIL','ORGANIZATION Detail','ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ORGANIZATION_EDIT','ORGANIZATION Edit','ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ORGANIZATION_DELETE','ORGANIZATION Delete','ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ORGANIZATION_SUBMIT','ORGANIZATION Submit','ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ORGANIZATION_APPROVE','ORGANIZATION Approve','ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ORGANIZATION_REJECT','ORGANIZATION Reject','ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ORGANIZATION_PENDING','ORGANIZATION Pending','ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ORGANIZATION_PROCESS','ORGANIZATION Process','ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ORGANIZATION_CLOSE','ORGANIZATION Close','ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ORGANIZATION_REMOVE','ORGANIZATION Remove','ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ORGANIZATION_CANCEL','ORGANIZATION Cancel','ORGANIZATION');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ORGANIZATION:MANAGER','ORGANIZATION Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ORGANIZATION:MANAGER' and p.vgroup='ORGANIZATION';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:ORGANIZATION','ADMIN:ORGANIZATION',null,'ADMIN:ORGANIZATION privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:ORGANIZATION_LIST','ADMIN:ORGANIZATION List','ADMIN:ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ORGANIZATION_CREATE','ADMIN:ORGANIZATION Create','ADMIN:ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ORGANIZATION_DETAIL','ADMIN:ORGANIZATION Detail','ADMIN:ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ORGANIZATION_EDIT','ADMIN:ORGANIZATION Edit','ADMIN:ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ORGANIZATION_DELETE','ADMIN:ORGANIZATION Delete','ADMIN:ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ORGANIZATION_SUBMIT','ADMIN:ORGANIZATION Submit','ADMIN:ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ORGANIZATION_APPROVE','ADMIN:ORGANIZATION Approve','ADMIN:ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ORGANIZATION_REJECT','ADMIN:ORGANIZATION Reject','ADMIN:ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ORGANIZATION_PENDING','ADMIN:ORGANIZATION Pending','ADMIN:ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ORGANIZATION_PROCESS','ADMIN:ORGANIZATION Process','ADMIN:ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ORGANIZATION_CLOSE','ADMIN:ORGANIZATION Close','ADMIN:ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ORGANIZATION_REMOVE','ADMIN:ORGANIZATION Remove','ADMIN:ORGANIZATION');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ORGANIZATION_CANCEL','ADMIN:ORGANIZATION Cancel','ADMIN:ORGANIZATION');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:ORGANIZATION:MANAGER','ADMIN:ORGANIZATION Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:ORGANIZATION:MANAGER' and p.vgroup='ADMIN:ORGANIZATION';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','PRIVILEGE','PRIVILEGE',null,'PRIVILEGE privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('PRIVILEGE_LIST','PRIVILEGE List','PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('PRIVILEGE_CREATE','PRIVILEGE Create','PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('PRIVILEGE_DETAIL','PRIVILEGE Detail','PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('PRIVILEGE_EDIT','PRIVILEGE Edit','PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('PRIVILEGE_DELETE','PRIVILEGE Delete','PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('PRIVILEGE_SUBMIT','PRIVILEGE Submit','PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('PRIVILEGE_APPROVE','PRIVILEGE Approve','PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('PRIVILEGE_REJECT','PRIVILEGE Reject','PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('PRIVILEGE_PENDING','PRIVILEGE Pending','PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('PRIVILEGE_PROCESS','PRIVILEGE Process','PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('PRIVILEGE_CLOSE','PRIVILEGE Close','PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('PRIVILEGE_REMOVE','PRIVILEGE Remove','PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('PRIVILEGE_CANCEL','PRIVILEGE Cancel','PRIVILEGE');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'PRIVILEGE:MANAGER','PRIVILEGE Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='PRIVILEGE:MANAGER' and p.vgroup='PRIVILEGE';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:PRIVILEGE','ADMIN:PRIVILEGE',null,'ADMIN:PRIVILEGE privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:PRIVILEGE_LIST','ADMIN:PRIVILEGE List','ADMIN:PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:PRIVILEGE_CREATE','ADMIN:PRIVILEGE Create','ADMIN:PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:PRIVILEGE_DETAIL','ADMIN:PRIVILEGE Detail','ADMIN:PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:PRIVILEGE_EDIT','ADMIN:PRIVILEGE Edit','ADMIN:PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:PRIVILEGE_DELETE','ADMIN:PRIVILEGE Delete','ADMIN:PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:PRIVILEGE_SUBMIT','ADMIN:PRIVILEGE Submit','ADMIN:PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:PRIVILEGE_APPROVE','ADMIN:PRIVILEGE Approve','ADMIN:PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:PRIVILEGE_REJECT','ADMIN:PRIVILEGE Reject','ADMIN:PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:PRIVILEGE_PENDING','ADMIN:PRIVILEGE Pending','ADMIN:PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:PRIVILEGE_PROCESS','ADMIN:PRIVILEGE Process','ADMIN:PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:PRIVILEGE_CLOSE','ADMIN:PRIVILEGE Close','ADMIN:PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:PRIVILEGE_REMOVE','ADMIN:PRIVILEGE Remove','ADMIN:PRIVILEGE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:PRIVILEGE_CANCEL','ADMIN:PRIVILEGE Cancel','ADMIN:PRIVILEGE');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:PRIVILEGE:MANAGER','ADMIN:PRIVILEGE Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:PRIVILEGE:MANAGER' and p.vgroup='ADMIN:PRIVILEGE';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ROLE','ROLE',null,'ROLE privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ROLE_LIST','ROLE List','ROLE');
insert into privilege(name,description,vgroup) 
values ('ROLE_CREATE','ROLE Create','ROLE');
insert into privilege(name,description,vgroup) 
values ('ROLE_DETAIL','ROLE Detail','ROLE');
insert into privilege(name,description,vgroup) 
values ('ROLE_EDIT','ROLE Edit','ROLE');
insert into privilege(name,description,vgroup) 
values ('ROLE_DELETE','ROLE Delete','ROLE');
insert into privilege(name,description,vgroup) 
values ('ROLE_SUBMIT','ROLE Submit','ROLE');
insert into privilege(name,description,vgroup) 
values ('ROLE_APPROVE','ROLE Approve','ROLE');
insert into privilege(name,description,vgroup) 
values ('ROLE_REJECT','ROLE Reject','ROLE');
insert into privilege(name,description,vgroup) 
values ('ROLE_PENDING','ROLE Pending','ROLE');
insert into privilege(name,description,vgroup) 
values ('ROLE_PROCESS','ROLE Process','ROLE');
insert into privilege(name,description,vgroup) 
values ('ROLE_CLOSE','ROLE Close','ROLE');
insert into privilege(name,description,vgroup) 
values ('ROLE_REMOVE','ROLE Remove','ROLE');
insert into privilege(name,description,vgroup) 
values ('ROLE_CANCEL','ROLE Cancel','ROLE');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ROLE:MANAGER','ROLE Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ROLE:MANAGER' and p.vgroup='ROLE';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:ROLE','ADMIN:ROLE',null,'ADMIN:ROLE privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:ROLE_LIST','ADMIN:ROLE List','ADMIN:ROLE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ROLE_CREATE','ADMIN:ROLE Create','ADMIN:ROLE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ROLE_DETAIL','ADMIN:ROLE Detail','ADMIN:ROLE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ROLE_EDIT','ADMIN:ROLE Edit','ADMIN:ROLE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ROLE_DELETE','ADMIN:ROLE Delete','ADMIN:ROLE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ROLE_SUBMIT','ADMIN:ROLE Submit','ADMIN:ROLE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ROLE_APPROVE','ADMIN:ROLE Approve','ADMIN:ROLE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ROLE_REJECT','ADMIN:ROLE Reject','ADMIN:ROLE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ROLE_PENDING','ADMIN:ROLE Pending','ADMIN:ROLE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ROLE_PROCESS','ADMIN:ROLE Process','ADMIN:ROLE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ROLE_CLOSE','ADMIN:ROLE Close','ADMIN:ROLE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ROLE_REMOVE','ADMIN:ROLE Remove','ADMIN:ROLE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:ROLE_CANCEL','ADMIN:ROLE Cancel','ADMIN:ROLE');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:ROLE:MANAGER','ADMIN:ROLE Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:ROLE:MANAGER' and p.vgroup='ADMIN:ROLE';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','STATUS','STATUS',null,'STATUS privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('STATUS_LIST','STATUS List','STATUS');
insert into privilege(name,description,vgroup) 
values ('STATUS_CREATE','STATUS Create','STATUS');
insert into privilege(name,description,vgroup) 
values ('STATUS_DETAIL','STATUS Detail','STATUS');
insert into privilege(name,description,vgroup) 
values ('STATUS_EDIT','STATUS Edit','STATUS');
insert into privilege(name,description,vgroup) 
values ('STATUS_DELETE','STATUS Delete','STATUS');
insert into privilege(name,description,vgroup) 
values ('STATUS_SUBMIT','STATUS Submit','STATUS');
insert into privilege(name,description,vgroup) 
values ('STATUS_APPROVE','STATUS Approve','STATUS');
insert into privilege(name,description,vgroup) 
values ('STATUS_REJECT','STATUS Reject','STATUS');
insert into privilege(name,description,vgroup) 
values ('STATUS_PENDING','STATUS Pending','STATUS');
insert into privilege(name,description,vgroup) 
values ('STATUS_PROCESS','STATUS Process','STATUS');
insert into privilege(name,description,vgroup) 
values ('STATUS_CLOSE','STATUS Close','STATUS');
insert into privilege(name,description,vgroup) 
values ('STATUS_REMOVE','STATUS Remove','STATUS');
insert into privilege(name,description,vgroup) 
values ('STATUS_CANCEL','STATUS Cancel','STATUS');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'STATUS:MANAGER','STATUS Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='STATUS:MANAGER' and p.vgroup='STATUS';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:STATUS','ADMIN:STATUS',null,'ADMIN:STATUS privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:STATUS_LIST','ADMIN:STATUS List','ADMIN:STATUS');
insert into privilege(name,description,vgroup) 
values ('ADMIN:STATUS_CREATE','ADMIN:STATUS Create','ADMIN:STATUS');
insert into privilege(name,description,vgroup) 
values ('ADMIN:STATUS_DETAIL','ADMIN:STATUS Detail','ADMIN:STATUS');
insert into privilege(name,description,vgroup) 
values ('ADMIN:STATUS_EDIT','ADMIN:STATUS Edit','ADMIN:STATUS');
insert into privilege(name,description,vgroup) 
values ('ADMIN:STATUS_DELETE','ADMIN:STATUS Delete','ADMIN:STATUS');
insert into privilege(name,description,vgroup) 
values ('ADMIN:STATUS_SUBMIT','ADMIN:STATUS Submit','ADMIN:STATUS');
insert into privilege(name,description,vgroup) 
values ('ADMIN:STATUS_APPROVE','ADMIN:STATUS Approve','ADMIN:STATUS');
insert into privilege(name,description,vgroup) 
values ('ADMIN:STATUS_REJECT','ADMIN:STATUS Reject','ADMIN:STATUS');
insert into privilege(name,description,vgroup) 
values ('ADMIN:STATUS_PENDING','ADMIN:STATUS Pending','ADMIN:STATUS');
insert into privilege(name,description,vgroup) 
values ('ADMIN:STATUS_PROCESS','ADMIN:STATUS Process','ADMIN:STATUS');
insert into privilege(name,description,vgroup) 
values ('ADMIN:STATUS_CLOSE','ADMIN:STATUS Close','ADMIN:STATUS');
insert into privilege(name,description,vgroup) 
values ('ADMIN:STATUS_REMOVE','ADMIN:STATUS Remove','ADMIN:STATUS');
insert into privilege(name,description,vgroup) 
values ('ADMIN:STATUS_CANCEL','ADMIN:STATUS Cancel','ADMIN:STATUS');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:STATUS:MANAGER','ADMIN:STATUS Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:STATUS:MANAGER' and p.vgroup='ADMIN:STATUS';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','SYSTEM_PARAMETER','SYSTEM_PARAMETER',null,'SYSTEM_PARAMETER privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_LIST','SYSTEM_PARAMETER List','SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_CREATE','SYSTEM_PARAMETER Create','SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_DETAIL','SYSTEM_PARAMETER Detail','SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_EDIT','SYSTEM_PARAMETER Edit','SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_DELETE','SYSTEM_PARAMETER Delete','SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_SUBMIT','SYSTEM_PARAMETER Submit','SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_APPROVE','SYSTEM_PARAMETER Approve','SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_REJECT','SYSTEM_PARAMETER Reject','SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_PENDING','SYSTEM_PARAMETER Pending','SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_PROCESS','SYSTEM_PARAMETER Process','SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_CLOSE','SYSTEM_PARAMETER Close','SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_REMOVE','SYSTEM_PARAMETER Remove','SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_CANCEL','SYSTEM_PARAMETER Cancel','SYSTEM_PARAMETER');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'SYSTEM_PARAMETER:MANAGER','SYSTEM_PARAMETER Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='SYSTEM_PARAMETER:MANAGER' and p.vgroup='SYSTEM_PARAMETER';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:SYSTEM_PARAMETER','ADMIN:SYSTEM_PARAMETER',null,'ADMIN:SYSTEM_PARAMETER privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_LIST','ADMIN:SYSTEM_PARAMETER List','ADMIN:SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_CREATE','ADMIN:SYSTEM_PARAMETER Create','ADMIN:SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_DETAIL','ADMIN:SYSTEM_PARAMETER Detail','ADMIN:SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_EDIT','ADMIN:SYSTEM_PARAMETER Edit','ADMIN:SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_DELETE','ADMIN:SYSTEM_PARAMETER Delete','ADMIN:SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_SUBMIT','ADMIN:SYSTEM_PARAMETER Submit','ADMIN:SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_APPROVE','ADMIN:SYSTEM_PARAMETER Approve','ADMIN:SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_REJECT','ADMIN:SYSTEM_PARAMETER Reject','ADMIN:SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_PENDING','ADMIN:SYSTEM_PARAMETER Pending','ADMIN:SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_PROCESS','ADMIN:SYSTEM_PARAMETER Process','ADMIN:SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_CLOSE','ADMIN:SYSTEM_PARAMETER Close','ADMIN:SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_REMOVE','ADMIN:SYSTEM_PARAMETER Remove','ADMIN:SYSTEM_PARAMETER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_CANCEL','ADMIN:SYSTEM_PARAMETER Cancel','ADMIN:SYSTEM_PARAMETER');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:SYSTEM_PARAMETER:MANAGER','ADMIN:SYSTEM_PARAMETER Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:SYSTEM_PARAMETER:MANAGER' and p.vgroup='ADMIN:SYSTEM_PARAMETER';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','SYSTEM_PARAMETER_HISTORY','SYSTEM_PARAMETER_HISTORY',null,'SYSTEM_PARAMETER_HISTORY privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_HISTORY_LIST','SYSTEM_PARAMETER_HISTORY List','SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_HISTORY_CREATE','SYSTEM_PARAMETER_HISTORY Create','SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_HISTORY_DETAIL','SYSTEM_PARAMETER_HISTORY Detail','SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_HISTORY_EDIT','SYSTEM_PARAMETER_HISTORY Edit','SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_HISTORY_DELETE','SYSTEM_PARAMETER_HISTORY Delete','SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_HISTORY_SUBMIT','SYSTEM_PARAMETER_HISTORY Submit','SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_HISTORY_APPROVE','SYSTEM_PARAMETER_HISTORY Approve','SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_HISTORY_REJECT','SYSTEM_PARAMETER_HISTORY Reject','SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_HISTORY_PENDING','SYSTEM_PARAMETER_HISTORY Pending','SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_HISTORY_PROCESS','SYSTEM_PARAMETER_HISTORY Process','SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_HISTORY_CLOSE','SYSTEM_PARAMETER_HISTORY Close','SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_HISTORY_REMOVE','SYSTEM_PARAMETER_HISTORY Remove','SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_PARAMETER_HISTORY_CANCEL','SYSTEM_PARAMETER_HISTORY Cancel','SYSTEM_PARAMETER_HISTORY');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'SYSTEM_PARAMETER_HISTORY:MANAGER','SYSTEM_PARAMETER_HISTORY Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='SYSTEM_PARAMETER_HISTORY:MANAGER' and p.vgroup='SYSTEM_PARAMETER_HISTORY';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:SYSTEM_PARAMETER_HISTORY','ADMIN:SYSTEM_PARAMETER_HISTORY',null,'ADMIN:SYSTEM_PARAMETER_HISTORY privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_HISTORY_LIST','ADMIN:SYSTEM_PARAMETER_HISTORY List','ADMIN:SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_HISTORY_CREATE','ADMIN:SYSTEM_PARAMETER_HISTORY Create','ADMIN:SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_HISTORY_DETAIL','ADMIN:SYSTEM_PARAMETER_HISTORY Detail','ADMIN:SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_HISTORY_EDIT','ADMIN:SYSTEM_PARAMETER_HISTORY Edit','ADMIN:SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_HISTORY_DELETE','ADMIN:SYSTEM_PARAMETER_HISTORY Delete','ADMIN:SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_HISTORY_SUBMIT','ADMIN:SYSTEM_PARAMETER_HISTORY Submit','ADMIN:SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_HISTORY_APPROVE','ADMIN:SYSTEM_PARAMETER_HISTORY Approve','ADMIN:SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_HISTORY_REJECT','ADMIN:SYSTEM_PARAMETER_HISTORY Reject','ADMIN:SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_HISTORY_PENDING','ADMIN:SYSTEM_PARAMETER_HISTORY Pending','ADMIN:SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_HISTORY_PROCESS','ADMIN:SYSTEM_PARAMETER_HISTORY Process','ADMIN:SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_HISTORY_CLOSE','ADMIN:SYSTEM_PARAMETER_HISTORY Close','ADMIN:SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_HISTORY_REMOVE','ADMIN:SYSTEM_PARAMETER_HISTORY Remove','ADMIN:SYSTEM_PARAMETER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_PARAMETER_HISTORY_CANCEL','ADMIN:SYSTEM_PARAMETER_HISTORY Cancel','ADMIN:SYSTEM_PARAMETER_HISTORY');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:SYSTEM_PARAMETER_HISTORY:MANAGER','ADMIN:SYSTEM_PARAMETER_HISTORY Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:SYSTEM_PARAMETER_HISTORY:MANAGER' and p.vgroup='ADMIN:SYSTEM_PARAMETER_HISTORY';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','SYSTEM_SEQUENCE','SYSTEM_SEQUENCE',null,'SYSTEM_SEQUENCE privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('SYSTEM_SEQUENCE_LIST','SYSTEM_SEQUENCE List','SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_SEQUENCE_CREATE','SYSTEM_SEQUENCE Create','SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_SEQUENCE_DETAIL','SYSTEM_SEQUENCE Detail','SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_SEQUENCE_EDIT','SYSTEM_SEQUENCE Edit','SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_SEQUENCE_DELETE','SYSTEM_SEQUENCE Delete','SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_SEQUENCE_SUBMIT','SYSTEM_SEQUENCE Submit','SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_SEQUENCE_APPROVE','SYSTEM_SEQUENCE Approve','SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_SEQUENCE_REJECT','SYSTEM_SEQUENCE Reject','SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_SEQUENCE_PENDING','SYSTEM_SEQUENCE Pending','SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_SEQUENCE_PROCESS','SYSTEM_SEQUENCE Process','SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_SEQUENCE_CLOSE','SYSTEM_SEQUENCE Close','SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_SEQUENCE_REMOVE','SYSTEM_SEQUENCE Remove','SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('SYSTEM_SEQUENCE_CANCEL','SYSTEM_SEQUENCE Cancel','SYSTEM_SEQUENCE');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'SYSTEM_SEQUENCE:MANAGER','SYSTEM_SEQUENCE Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='SYSTEM_SEQUENCE:MANAGER' and p.vgroup='SYSTEM_SEQUENCE';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:SYSTEM_SEQUENCE','ADMIN:SYSTEM_SEQUENCE',null,'ADMIN:SYSTEM_SEQUENCE privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_SEQUENCE_LIST','ADMIN:SYSTEM_SEQUENCE List','ADMIN:SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_SEQUENCE_CREATE','ADMIN:SYSTEM_SEQUENCE Create','ADMIN:SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_SEQUENCE_DETAIL','ADMIN:SYSTEM_SEQUENCE Detail','ADMIN:SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_SEQUENCE_EDIT','ADMIN:SYSTEM_SEQUENCE Edit','ADMIN:SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_SEQUENCE_DELETE','ADMIN:SYSTEM_SEQUENCE Delete','ADMIN:SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_SEQUENCE_SUBMIT','ADMIN:SYSTEM_SEQUENCE Submit','ADMIN:SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_SEQUENCE_APPROVE','ADMIN:SYSTEM_SEQUENCE Approve','ADMIN:SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_SEQUENCE_REJECT','ADMIN:SYSTEM_SEQUENCE Reject','ADMIN:SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_SEQUENCE_PENDING','ADMIN:SYSTEM_SEQUENCE Pending','ADMIN:SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_SEQUENCE_PROCESS','ADMIN:SYSTEM_SEQUENCE Process','ADMIN:SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_SEQUENCE_CLOSE','ADMIN:SYSTEM_SEQUENCE Close','ADMIN:SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_SEQUENCE_REMOVE','ADMIN:SYSTEM_SEQUENCE Remove','ADMIN:SYSTEM_SEQUENCE');
insert into privilege(name,description,vgroup) 
values ('ADMIN:SYSTEM_SEQUENCE_CANCEL','ADMIN:SYSTEM_SEQUENCE Cancel','ADMIN:SYSTEM_SEQUENCE');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:SYSTEM_SEQUENCE:MANAGER','ADMIN:SYSTEM_SEQUENCE Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:SYSTEM_SEQUENCE:MANAGER' and p.vgroup='ADMIN:SYSTEM_SEQUENCE';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','USER','USER',null,'USER privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('USER_LIST','USER List','USER');
insert into privilege(name,description,vgroup) 
values ('USER_CREATE','USER Create','USER');
insert into privilege(name,description,vgroup) 
values ('USER_DETAIL','USER Detail','USER');
insert into privilege(name,description,vgroup) 
values ('USER_EDIT','USER Edit','USER');
insert into privilege(name,description,vgroup) 
values ('USER_DELETE','USER Delete','USER');
insert into privilege(name,description,vgroup) 
values ('USER_SUBMIT','USER Submit','USER');
insert into privilege(name,description,vgroup) 
values ('USER_APPROVE','USER Approve','USER');
insert into privilege(name,description,vgroup) 
values ('USER_REJECT','USER Reject','USER');
insert into privilege(name,description,vgroup) 
values ('USER_PENDING','USER Pending','USER');
insert into privilege(name,description,vgroup) 
values ('USER_PROCESS','USER Process','USER');
insert into privilege(name,description,vgroup) 
values ('USER_CLOSE','USER Close','USER');
insert into privilege(name,description,vgroup) 
values ('USER_REMOVE','USER Remove','USER');
insert into privilege(name,description,vgroup) 
values ('USER_CANCEL','USER Cancel','USER');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'USER:MANAGER','USER Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='USER:MANAGER' and p.vgroup='USER';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:USER','ADMIN:USER',null,'ADMIN:USER privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_LIST','ADMIN:USER List','ADMIN:USER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_CREATE','ADMIN:USER Create','ADMIN:USER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_DETAIL','ADMIN:USER Detail','ADMIN:USER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_EDIT','ADMIN:USER Edit','ADMIN:USER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_DELETE','ADMIN:USER Delete','ADMIN:USER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_SUBMIT','ADMIN:USER Submit','ADMIN:USER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_APPROVE','ADMIN:USER Approve','ADMIN:USER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_REJECT','ADMIN:USER Reject','ADMIN:USER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_PENDING','ADMIN:USER Pending','ADMIN:USER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_PROCESS','ADMIN:USER Process','ADMIN:USER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_CLOSE','ADMIN:USER Close','ADMIN:USER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_REMOVE','ADMIN:USER Remove','ADMIN:USER');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_CANCEL','ADMIN:USER Cancel','ADMIN:USER');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:USER:MANAGER','ADMIN:USER Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:USER:MANAGER' and p.vgroup='ADMIN:USER';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','USER_HISTORY','USER_HISTORY',null,'USER_HISTORY privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('USER_HISTORY_LIST','USER_HISTORY List','USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('USER_HISTORY_CREATE','USER_HISTORY Create','USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('USER_HISTORY_DETAIL','USER_HISTORY Detail','USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('USER_HISTORY_EDIT','USER_HISTORY Edit','USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('USER_HISTORY_DELETE','USER_HISTORY Delete','USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('USER_HISTORY_SUBMIT','USER_HISTORY Submit','USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('USER_HISTORY_APPROVE','USER_HISTORY Approve','USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('USER_HISTORY_REJECT','USER_HISTORY Reject','USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('USER_HISTORY_PENDING','USER_HISTORY Pending','USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('USER_HISTORY_PROCESS','USER_HISTORY Process','USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('USER_HISTORY_CLOSE','USER_HISTORY Close','USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('USER_HISTORY_REMOVE','USER_HISTORY Remove','USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('USER_HISTORY_CANCEL','USER_HISTORY Cancel','USER_HISTORY');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'USER_HISTORY:MANAGER','USER_HISTORY Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='USER_HISTORY:MANAGER' and p.vgroup='USER_HISTORY';
insert into lookup(type,code,name,shortname,Description,status) 
select 'privilegeGroup','ADMIN:USER_HISTORY','ADMIN:USER_HISTORY',null,'ADMIN:USER_HISTORY privilege group',s.id from status s where type='Lookup' and code='new';

insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_HISTORY_LIST','ADMIN:USER_HISTORY List','ADMIN:USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_HISTORY_CREATE','ADMIN:USER_HISTORY Create','ADMIN:USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_HISTORY_DETAIL','ADMIN:USER_HISTORY Detail','ADMIN:USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_HISTORY_EDIT','ADMIN:USER_HISTORY Edit','ADMIN:USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_HISTORY_DELETE','ADMIN:USER_HISTORY Delete','ADMIN:USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_HISTORY_SUBMIT','ADMIN:USER_HISTORY Submit','ADMIN:USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_HISTORY_APPROVE','ADMIN:USER_HISTORY Approve','ADMIN:USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_HISTORY_REJECT','ADMIN:USER_HISTORY Reject','ADMIN:USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_HISTORY_PENDING','ADMIN:USER_HISTORY Pending','ADMIN:USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_HISTORY_PROCESS','ADMIN:USER_HISTORY Process','ADMIN:USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_HISTORY_CLOSE','ADMIN:USER_HISTORY Close','ADMIN:USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_HISTORY_REMOVE','ADMIN:USER_HISTORY Remove','ADMIN:USER_HISTORY');
insert into privilege(name,description,vgroup) 
values ('ADMIN:USER_HISTORY_CANCEL','ADMIN:USER_HISTORY Cancel','ADMIN:USER_HISTORY');

/**** Role for Privilege Group Manager *****/
insert into role(name,description,created_date,created_by,last_updated_date,last_updated_by,user_level,status) 
select 'ADMIN:USER_HISTORY:MANAGER','ADMIN:USER_HISTORY Manager Role',current_timestamp,'System',current_timestamp,'System',
(select id from lookup where type='userLevel' and code='admin' limit 1) as userLevel,
(select id from status where type='Role' and code='new' limit 1);

/**** Privilege Map for Privilege Group Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:USER_HISTORY:MANAGER' and p.vgroup='ADMIN:USER_HISTORY';

/**** Privilege Map for public Segment Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='PUBLIC:MANAGER' and p.vgroup like ':%';

/**** Privilege Map from public segment for GOD *****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='GOD' and p.vgroup like ':%';
/**** Privilege Map for admin Segment Manager Role*****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='ADMIN:MANAGER' and p.vgroup like 'ADMIN:%';

/**** Privilege Map from admin segment for GOD *****/
insert into role_privilege(role_id,privilege_id) 
select r.id,p.id from privilege p,role r where r.name='GOD' and p.vgroup like 'ADMIN:%';


/************* Admin User Script **********/
insert into app_user (login_name,login_password,user_level,language,title,name,full_name,email,home_phone_number,mobile_phone_number,employee_number,status,created_date,created_by,last_updated_date,last_updated_by,first_login,last_password_update)
select 'martin','925d7518fc597af0e43f5606f9a51512',lvl.id,'Indonesia','Mr.','Martinus ','Martinus S. Rohadi','martinus.s.rohadi@banksinarmas.com',NULL,NULL,NULL,st.id,now(),'system',now(),'system','no',now() from lookup lvl, status st where st.type='User' and st.code='new' and lvl.type='userLevel' and lvl.code='admin';

insert into app_user (login_name,login_password,user_level,language,title,name,full_name,email,home_phone_number,mobile_phone_number,employee_number,status,created_date,created_by,last_updated_date,last_updated_by,first_login,last_password_update)
select 'admin','21232f297a57a5a743894a0e4a801fc3',lvl.id,'Indonesia','Mr.','Admin ','Administrator','martinus.s.rohadi@banksinarmas.com',NULL,NULL,NULL,st.id,now(),'system',now(),'system','no',now() from lookup lvl, status st where st.type='User' and st.code='new' and lvl.type='userLevel' and lvl.code='admin';

/*
insert into app_user (login_name,login_password,user_level,language,title,name,full_name,rsa_user_name,email,home_phone_number,mobile_phone_number,employee_number,status,created_date,created_by,last_updated_date,last_updated_by,first_login,last_password_update)
select 'irwan','21232f297a57a5a743894a0e4a801fc3',lvl.id,'Indonesia','Mr.','Irwan Wijaya','Irwan','irwan','irwan.wijaya@banksinarmas.com',NULL,NULL,NULL,st.id,curdate(),'system',curdate(),'system','no',curdate() from lookup lvl, status st where st.type='user' and st.code='new' and lvl.type='userLevel' and lvl.code='admin';

insert into app_user (login_name,login_password,user_level,language,title,name,full_name,rsa_user_name,email,home_phone_number,mobile_phone_number,employee_number,status,created_date,created_by,last_updated_date,last_updated_by,first_login,last_password_update)
select 'edy','21232f297a57a5a743894a0e4a801fc3',lvl.id,'Indonesia','Mr.','Edy Christian','Edy','edy','edy.christian@banksinarmas.com',NULL,NULL,NULL,st.id,curdate(),'system',curdate(),'system','no',curdate() from lookup lvl, status st where st.type='user' and st.code='new' and lvl.type='userLevel' and lvl.code='admin';
*/

/************* User Role Script **********/
insert into user_role(user_id,role_id) 
select u.id,r.id from app_user u,role r where r.name='GOD' and u.login_name='martin';

insert into user_role(user_id,role_id) 
select u.id,r.id from app_user u,role r where r.name='GOD' and u.login_name='admin';
/*
insert into user_role(user_id,role_id) 
select u.id,r.id from app_user u,role r where r.name='GOD' and u.login_name='irwan';

insert into user_role(user_id,role_id) 
select u.id,r.id from app_user u,role r where r.name='GOD' and u.login_name='krisandra';
*/