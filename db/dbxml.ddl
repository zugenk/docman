alter table app_user drop constraint FK459C5729352B5133;
alter table app_user drop constraint FK459C572918B096A3;
alter table app_user drop constraint FK459C57297CC6E6FD;
alter table bookmark drop constraint FK7787A53671DF17D7;
alter table bookmark drop constraint FK7787A536591F2505;
alter table bookmark drop constraint FK7787A5367CC6E6FD;
alter table document drop constraint FK335CD11BAB39412A;
alter table document drop constraint FK335CD11B597CF04;
alter table document drop constraint FK335CD11B7CC6E6FD;
alter table document_history drop constraint FK5D031170AB39412A;
alter table document_history drop constraint FK5D031170597CF04;
alter table document_history drop constraint FK5D0311707CC6E6FD;
alter table email_log drop constraint FK7E67F0217CC6E6FD;
alter table folder drop constraint FKB45D1C6EAC61B0FE;
alter table folder drop constraint FKB45D1C6EAB39412A;
alter table forum drop constraint FK5D18D2173A6B82B;
alter table forum drop constraint FK5D18D21581AD034;
alter table generic_report drop constraint FK3513D89C7CC6E6FD;
alter table login_history drop constraint FK88A801BE62C61333;
alter table login_history drop constraint FK88A801BE7CC6E6FD;
alter table lookup drop constraint FKBE9BA97A7CC6E6FD;
alter table message drop constraint FK38EB0007C608C25;
alter table message drop constraint FK38EB00071D2A040C;
alter table notification drop constraint FK237A88EB48158AA1;
alter table notification drop constraint FK237A88EB22D6F18C;
alter table notification drop constraint FK237A88EBD5853B76;
alter table organization drop constraint FK4644ED33AAC5C636;
alter table role drop constraint FK35807618B096A3;
alter table role drop constraint FK3580767CC6E6FD;
alter table role_privilege drop constraint FK45FBD628DB2CDE61;
alter table role_privilege drop constraint FK45FBD628BD9B4F53;
alter table system_parameter drop constraint FKE172719918B096A3;
alter table system_parameter drop constraint FKE17271997CC6E6FD;
alter table system_parameter_history drop constraint FKE52753EE7CC6E6FD;
alter table topic drop constraint FK696CD2F581AD034;
alter table topic_user drop constraint FKCE46BCDB62C61333;
alter table topic_user drop constraint FKCE46BCDBCCBAA121;
alter table user_history drop constraint FK922F7C20352B5133;
alter table user_history drop constraint FK922F7C2018B096A3;
alter table user_history drop constraint FK922F7C207CC6E6FD;
alter table user_history_role drop constraint FK48295135BD9B4F53;
alter table user_history_role drop constraint FK482951351793086A;
alter table user_role drop constraint FK143BF46ABD9B4F53;
alter table user_role drop constraint FK143BF46A62C61333;
drop table app_user;
drop table audit_trail;
drop table bookmark;
drop table document;
drop table document_history;
drop table email_log;
drop table folder;
drop table forum;
drop table generic_report;
drop table ipassport_pool;
drop table login_history;
drop table lookup;
drop table message;
drop table notification;
drop table organization;
drop table privilege;
drop table role;
drop table role_privilege;
drop table status;
drop table system_parameter;
drop table system_parameter_history;
drop table system_sequence;
drop table topic;
drop table topic_user;
drop table user_history;
drop table user_history_role;
drop table user_role;
create table app_user (id  bigserial not null, login_name varchar(30) not null, login_password varchar(100) not null, pin_code varchar(100), mobile_number varchar(50), user_level int8 not null, language varchar(20), title varchar(10), name varchar(100) not null, email varchar(100), full_name varchar(255), home_phone_number varchar(50), mobile_phone_number varchar(50), employee_number varchar(50), status int8 not null, created_date timestamp not null, created_by varchar(30) not null, last_updated_date timestamp, last_updated_by varchar(30), first_login varchar(5), last_password_update timestamp, last_pincode_update timestamp, last_password varchar(255), last_pincode varchar(255), organization_id int8, login_failed int4, max_release int4, last_released_date timestamp, last_active timestamp, session_code varchar(50), ipassport varchar(50), primary key (id));
create table audit_trail (id  bigserial not null, audit_time timestamp not null, entity varchar(30) not null, done_by varchar(30) not null, session_id varchar(50) not null, approved_by varchar(30), actions varchar(255) not null, description varchar(255), primary key (id));
create table bookmark (id  bigserial not null, url varchar(255) not null, name varchar(100) not null, note varchar(255) not null, message_type int8 not null, created_date timestamp not null, created_by varchar(30) not null, status int8 not null, owner int8, primary key (id));
create table document (id  bigserial not null, name varchar(30), document_type varchar(50) not null, content_type varchar(50) not null, document_number varchar(30), repository_id varchar(30), document_version varchar(30), description varchar(1000), status int8 not null, created_date timestamp not null, created_by varchar(30) not null, last_updated_date timestamp, last_updated_by varchar(30), parent_folder int8 not null, parent_document int8, primary key (id));
create table document_history (id  bigserial not null, history_date timestamp not null, history_by varchar(30) not null, audit_trail_id int8 not null, document_id int8 not null, name varchar(30), document_type varchar(50) not null, content_type varchar(50) not null, document_number varchar(30), repository_id varchar(30), document_version varchar(30), description varchar(1000), status int8 not null, created_date timestamp not null, created_by varchar(30) not null, last_updated_date timestamp, last_updated_by varchar(30), parent_folder int8 not null, parent_document int8, primary key (id));
create table email_log (id  bigserial not null, type varchar(50), sender varchar(100), rcpt_to varchar(1000) not null, subject varchar(200) not null, message varchar(8000) not null, retry int4, status int8 not null, due_date timestamp not null, sent_date timestamp, last_trial_date timestamp, primary key (id));
create table folder (id  bigserial not null, code varchar(20) not null, name varchar(150) not null, folder_type int8 not null, created_date timestamp not null, created_by varchar(30) not null, last_updated_date timestamp, last_updated_by varchar(30), parent_folder int8, primary key (id));
create table forum (id  bigserial not null, code varchar(20) not null, icon varchar(50) not null, name varchar(150) not null, forum_type int8 not null, address varchar(255), created_date timestamp not null, created_by varchar(30) not null, last_updated_date timestamp, last_updated_by varchar(30), filter_code varchar(100), parent_forum int8, primary key (id));
create table generic_report (id  bigserial not null, name varchar(40) not null, report_fields varchar(250), search_fields varchar(250), report_sql varchar(300), description varchar(100), column_action varchar(250), status int8 not null, primary key (id));
create table ipassport_pool (id  bigserial not null, ipassport varchar(70) not null, login_id varchar(30) not null, last_login timestamp, last_active timestamp, primary key (id));
create table login_history (id  bigserial not null, user_id int8 not null, login_time timestamp not null, last_access timestamp not null, logout_time timestamp, status int8 not null, session_id varchar(50), description varchar(50), primary key (id));
create table lookup (id  bigserial not null, type varchar(30) not null, code varchar(30) not null, name varchar(40) not null, priority int4, description varchar(100), shortname varchar(10), status int8 not null, filter varchar(30), primary key (id));
create table message (id  bigserial not null, content varchar(255) not null, post_type int8 not null, created_date timestamp not null, created_by varchar(30) not null, last_updated_date timestamp, last_updated_by varchar(30), filter_code varchar(100), topic int8 not null, primary key (id));
create table notification (id  bigserial not null, notification_type int8 not null, post_message int8 not null, subscriber int8 not null, flag varchar(10), primary key (id));
create table organization (id  bigserial not null, code varchar(20) not null, mnemonic varchar(20) not null, name varchar(150) not null, address varchar(255), created_date timestamp not null, created_by varchar(30) not null, last_updated_date timestamp, last_updated_by varchar(30), filter_code varchar(100), parent int8, primary key (id));
create table privilege (id  bigserial not null, name varchar(100) not null, vgroup varchar(50) not null, description varchar(100), primary key (id));
create table role (id  bigserial not null, name varchar(50) not null, user_level int8 not null, description varchar(100), status int8 not null, created_date timestamp not null, created_by varchar(30) not null, last_updated_date timestamp not null, last_updated_by varchar(30) not null, primary key (id));
create table role_privilege (role_id int8 not null, privilege_id int8 not null, primary key (role_id, privilege_id));
create table status (id  bigserial not null, type varchar(30) not null, code varchar(30) not null, state varchar(30) not null, name varchar(30) not null, description varchar(100), primary key (id));
create table system_parameter (id  bigserial not null, vgroup varchar(50) not null, parameter varchar(50) not null, svalue varchar(250), description varchar(250), status int8 not null, created_date timestamp not null, created_by varchar(20) not null, last_updated_date timestamp, last_updated_by varchar(20), user_level int8 not null, primary key (id));
create table system_parameter_history (id  bigserial not null, history_date timestamp not null, history_by varchar(20) not null, audit_trail_id int8 not null, system_parameter_id int8 not null, vgroup varchar(50) not null, parameter varchar(50) not null, svalue varchar(250), description varchar(250), status int8 not null, created_date timestamp not null, created_by varchar(20) not null, last_updated_date timestamp, last_updated_by varchar(20), primary key (id));
create table system_sequence (ID  bigserial not null, type varchar(20) not null, param varchar(100) not null, sequence int8 not null, primary key (ID));
create table topic (id  bigserial not null, code varchar(20) not null, icon varchar(50) not null, name varchar(150) not null, description varchar(255), number_of_like int4, number_of_post int4, created_date timestamp not null, created_by varchar(30) not null, last_updated_date timestamp, last_updated_by varchar(30), filter_code varchar(100), parent_forum int8, primary key (id));
create table topic_user (topic_id int8 not null, user_id int8 not null, primary key (topic_id, user_id));
create table user_history (id  bigserial not null, history_date timestamp not null, history_by varchar(30) not null, audit_trail_id int8 not null, user_id int8 not null, login_name varchar(30) not null, login_password varchar(100) not null, pin_code varchar(100), mobile_number varchar(50), user_level int8 not null, language varchar(20), title varchar(10), name varchar(100) not null, email varchar(100), full_name varchar(255), home_phone_number varchar(50), mobile_phone_number varchar(50), employee_number varchar(50), status int8 not null, created_date timestamp not null, created_by varchar(30) not null, last_updated_date timestamp, last_updated_by varchar(30), first_login varchar(5), last_password_update timestamp, last_pincode_update timestamp, last_password varchar(255), last_pincode varchar(255), organization_id int8, login_failed int4, max_release int4, last_released_date timestamp, last_active timestamp, session_code varchar(50), ipassport varchar(50), primary key (id));
create table user_history_role (user_history_id int8 not null, role_id int8 not null, primary key (user_history_id, role_id));
create table user_role (user_id int8 not null, role_id int8 not null, primary key (user_id, role_id));
alter table app_user add constraint FK459C5729352B5133 foreign key (organization_id) references organization;
alter table app_user add constraint FK459C572918B096A3 foreign key (user_level) references lookup;
alter table app_user add constraint FK459C57297CC6E6FD foreign key (status) references status;
alter table bookmark add constraint FK7787A53671DF17D7 foreign key (owner) references app_user;
alter table bookmark add constraint FK7787A536591F2505 foreign key (message_type) references lookup;
alter table bookmark add constraint FK7787A5367CC6E6FD foreign key (status) references status;
alter table document add constraint FK335CD11BAB39412A foreign key (parent_folder) references folder;
alter table document add constraint FK335CD11B597CF04 foreign key (parent_document) references document;
alter table document add constraint FK335CD11B7CC6E6FD foreign key (status) references status;
alter table document_history add constraint FK5D031170AB39412A foreign key (parent_folder) references folder;
alter table document_history add constraint FK5D031170597CF04 foreign key (parent_document) references document;
alter table document_history add constraint FK5D0311707CC6E6FD foreign key (status) references status;
alter table email_log add constraint FK7E67F0217CC6E6FD foreign key (status) references status;
alter table folder add constraint FKB45D1C6EAC61B0FE foreign key (folder_type) references lookup;
alter table folder add constraint FKB45D1C6EAB39412A foreign key (parent_folder) references folder;
alter table forum add constraint FK5D18D2173A6B82B foreign key (forum_type) references lookup;
alter table forum add constraint FK5D18D21581AD034 foreign key (parent_forum) references forum;
alter table generic_report add constraint FK3513D89C7CC6E6FD foreign key (status) references status;
alter table login_history add constraint FK88A801BE62C61333 foreign key (user_id) references app_user;
alter table login_history add constraint FK88A801BE7CC6E6FD foreign key (status) references status;
alter table lookup add constraint FKBE9BA97A7CC6E6FD foreign key (status) references status;
alter table message add constraint FK38EB0007C608C25 foreign key (topic) references topic;
alter table message add constraint FK38EB00071D2A040C foreign key (post_type) references lookup;
alter table notification add constraint FK237A88EB48158AA1 foreign key (notification_type) references lookup;
alter table notification add constraint FK237A88EB22D6F18C foreign key (subscriber) references app_user;
alter table notification add constraint FK237A88EBD5853B76 foreign key (post_message) references message;
alter table organization add constraint FK4644ED33AAC5C636 foreign key (parent) references organization;
alter table role add constraint FK35807618B096A3 foreign key (user_level) references lookup;
alter table role add constraint FK3580767CC6E6FD foreign key (status) references status;
alter table role_privilege add constraint FK45FBD628DB2CDE61 foreign key (privilege_id) references privilege;
alter table role_privilege add constraint FK45FBD628BD9B4F53 foreign key (role_id) references role;
alter table system_parameter add constraint FKE172719918B096A3 foreign key (user_level) references lookup;
alter table system_parameter add constraint FKE17271997CC6E6FD foreign key (status) references status;
alter table system_parameter_history add constraint FKE52753EE7CC6E6FD foreign key (status) references status;
alter table topic add constraint FK696CD2F581AD034 foreign key (parent_forum) references forum;
alter table topic_user add constraint FKCE46BCDB62C61333 foreign key (user_id) references app_user;
alter table topic_user add constraint FKCE46BCDBCCBAA121 foreign key (topic_id) references topic;
alter table user_history add constraint FK922F7C20352B5133 foreign key (organization_id) references organization;
alter table user_history add constraint FK922F7C2018B096A3 foreign key (user_level) references lookup;
alter table user_history add constraint FK922F7C207CC6E6FD foreign key (status) references status;
alter table user_history_role add constraint FK48295135BD9B4F53 foreign key (role_id) references role;
alter table user_history_role add constraint FK482951351793086A foreign key (user_history_id) references user_history;
alter table user_role add constraint FK143BF46ABD9B4F53 foreign key (role_id) references role;
alter table user_role add constraint FK143BF46A62C61333 foreign key (user_id) references app_user;
