
-----------------------------------Part 1-------------------
drop table tbl_member;

create table tbl_member(
	userid varchar(50) not null,
	userpw varchar(50) not null,
	username varchar(50) not null,
	email varchar(100),
	regdate timestamp default now(),
	updatedate timestamp default now(),
	primary key(userid)
)engine=InnoDB character set=utf8;

--------------------------------Part2 ---------------------------
drop table tbl_board;

create table tbl_board(
	bno int not null auto_increment,
	title varchar(200) not null,
	content text null,
	writer varchar(50) not null,
	regdate timestamp not null default now(),
	viewcnt int default 0,
	primary key(bno)
)engine=InnoDB character set=utf8;;

insert into tbl_board(title, content, writer) values('제목입니다','내용입니다','user00');

insert into tbl_board(title, content, writer)
(select title, content, writer from tbl_board)

select * from tbl_board where bno > 0 order by bno desc limit 0,10;
select * from tbl_board where bno > 0 order by bno desc limit 10,10;

--------------------------------Part3 ---------------------------
drop table tbl_reply;

create table tbl_reply(
	rno int not null auto_increment,
	bno int not null default 0,
	replytext varchar(1000) not null,
	replyer varchar(50) not null,
	regdate Timestamp not null default now(),
	updatedate Timestamp not null default now(),
    primary key(rno)
);

alter table tbl_reply add constraint fk_board
foreign key(bno) references tbl_board(bno);

--------------------------------Part4 ---------------------------
drop table tbl_user;

create table tbl_user(
	uid varchar(50) not null,
	upw varchar(50) not null,
	uname varchar(50) not null,
	upoint int not null default 0,
	primary key(uid)
);

insert into tbl_user(uid, upw, uname) values('user00', 'user00', 'IRON MAN');
insert into tbl_user(uid, upw, uname) values('user01', 'user01', 'CAPTAIN');
insert into tbl_user(uid, upw, uname) values('user02', 'user02', 'HURLK');
insert into tbl_user(uid, upw, uname) values('user03', 'user03', 'THOR');
insert into tbl_user(uid, upw, uname) values('user10', 'user10', 'Quick Silver');


drop table tbl_message;

create table tbl_message(
	mid int not null auto_increment,
	targetid varchar(50) not null,
	sender varchar(50) not null,
	message text not null,
	opendate timestamp,
	senddate timestamp not null default now(),
	primary key(mid)
);

alter table tbl_message add constraint fk_usersender
foreign key(sender) references tbl_user(uid);

alter table tbl_message add constraint fk_usertarget
foreign key(targetid) references tbl_user(uid);

--댓글의 개수 추가하기(502)
alter table tbl_board add column replycnt int default 0;

--------------------------------Part5 ---------------------------

drop table tbl_attach;

create table tbl_attach(
	fullname varchar(150) not null,
	bno int not null,
	regdate timestamp default now(),
	primary key(fullName)
);

alter table tbl_attach add constraint fk_board_attach
foreign key(bno) references tbl_board(bno);


--------------------------------Part6 ---------------------------
alter table tbl_user
add column sessionkey varchar(50) not null default 'none';

alter table tbl_user
add column sessionlimit timestamp;











