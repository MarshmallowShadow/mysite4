drop table files;
drop sequence seq_files_no;

create table files (
    no              number(10),
    org_name        varchar2(500)   not null,
    save_name       varchar2(500)   not null,
    file_path       varchar2(500)   not null,
    file_size       number(30),
    primary key (no)
);

create sequence seq_files_no
increment by 1
start with 1;

select * from files;

commit;