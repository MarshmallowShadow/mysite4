drop table rboard;
drop sequence seq_rboard_no;


create table rboard(
	no			number,
	user_no		number			not null,
	title		varchar2(500),
	content		varchar2(4000),
	hit			number,
	reg_date	date,
	group_no	number,
	order_no	number,
	depth		number,
	primary key(no),
	constraint rboard_fk foreign key(user_no)
	references users(no)
);

create sequence seq_rboard_no
increment by 1
start with 1;