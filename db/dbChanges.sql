
insert into status(type,code,state,name,description)
VALUES
('Lookup', 'active', 'active', 'active', 'active Lookup');

insert into lookup(type,code,name,priority,description,shortname,status,filter)
values('forum','movies', 'Movies Forum',null,'Movie Forum','movie',(select id from status where type='Lookup' and code='active'),null);

insert into lookup(type,code,name,priority,description,shortname,status,filter)
values('forum','games', 'Games Forum',null,'Games Forum','games',(select id from status where type='Lookup' and code='active'),null);

insert into lookup(type,code,name,priority,description,shortname,status,filter)
values('forum','etc', 'Etc Forum',null,'Etc Forum','etc',(select id from status where type='Lookup' and code='active'),null);
