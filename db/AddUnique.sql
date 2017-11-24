alter table lookup add constraint lookup_type_code UNIQUE(type,code);
alter table status add constraint status_type_code UNIQUE(type,code);
alter table system_parameter add constraint system_vgroup_param UNIQUE(vgroup,parameter);
alter table document add constraint document_repository_id UNIQUE(repository_id);

--select * from lookup where type='announcementType'
-- delete from lookup where id=76