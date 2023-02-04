create database asmjava4
go
use asmjava4
go
create table [user]
(
	id int primary key identity,
	username varchar(10) unique not null,
	[password] varchar(10) not null,
	email varchar(50) unique not null,
	isAdmin bit not null default 0,
	isActive bit not null default 1
)
go
create table video
(
	id int primary key identity,
	title nvarchar(255) not null,
	href varchar(50) unique not null,
	poster varchar(255) null,
	[views] int not null default 0,
	shares int not null default 0,
	[description] nvarchar(255) not null,
	isActive bit not null default 1
)
go
create table history
(
	id int primary key identity,
	userId int foreign key references [user](id),
	videoId int foreign key references video(id),
	viewedDate datetime not null default getDate(),
	isLiked bit not null default 0,
	likeddDate datetime null
)
go
insert into [user](username,[password],email,isAdmin) values
('duynt','111','giaunnps14677@fpt.edu.vn',1),
('hangtran','222','giau7863@gmail.com',0)
go
insert into video(title,href,[description]) values
(N'Đồi Hoa Mặt Trời Cover','ze-nx_e-q24',N'JIKI X - Đồi Hoa Mặt Trời Cover | Live Cover Đồi Hoa Mặt Trời - Gió ơi gió đừng vội kéo mây, kéo hạt mưa rớt qua nơi này...Song: Đồi Hoa Mặt Trời'),
(N'Dịu Dàng Đến Từng Phút Giây','ZvnFcBAPpB0',N'Dịu Dàng Đến Từng Phút Giây Quang Vinh Feat. Chi Dân Album: Greatest Hits/ The Memories 2017'),
(N'Thu Minh - Bay','R2k_egtQSSk','Music video by Thu Minh performing Bay.')

go
insert into video(title,href,poster,[description]) values
(N'Tình Sầu Thiên Thu Muôn Lối','Lm3UG2GXLHk','https://img.youtube.com/vi/Lm3UG2GXLHk/maxresdefault.jpg',N'Tình Sầu Thiên Thu Muôn Lối | Doãn Hiếu ft. LongDrae  (Official MV)'),
(N'Vết Thương trong em','hiPOS4WMFXg','https://img.youtube.com/vi/hiPOS4WMFXg/maxresdefault.jpg',N'Vết Thương trong em một ca khúc làm nên tên tuổi của ca sĩ Mi Jun, một cô gái có hai dòng máu Việt Hàn trong người
bản quyền ca khúc này thuộc về tường quân media'),
(N'Em Mỉm Cười Trông Thật Đẹp','U6qnvAzKHns','https://img.youtube.com/vi/U6qnvAzKHns/maxresdefault.jpg',N'Em Mỉm Cười Trông Thật Đẹp ( Htrol Remix ) Trịnh Đình Quang | Nhạc gây nghiện 2019 ')

go
insert into history(userId,videoId,isLiked,likeddDate) values
(2,1,1,getDate()),
(2,3,0,null)
go
select * from users
        user0_.id as id1_1_,
        user0_.email as email2_1_,
        user0_.isActive as isactive3_1_,
        user0_.isAdmin as isadmin4_1_,
        user0_.password as password5_1_,
        user0_.username as username6_1_ 
    from
        user user0_ 
    where
        user0_.username='duynt' 
        and user0_.password='111'
		go

		select * from history
		delete from history;

		go
select 
	v.id, v.title,v.href, sum(cast(h.isLiked as int)) as totalLike
from
	video v left join history h on v.id = h.videoId
where
	v.isActive = 1
group by
	v.id,v.title,v.href
order by
	sum(cast(h.isLiked as int)) desc

go

create proc sp_selectUsersLikedVideoHref(@videoHref varchar(50))
as begin 
	select u.id,u.username, u.[password],u.email,u.isAdmin, u.issActive
	from
		video v left join history h on v.id = v.videoId
			left join [user] u on h.videoId = u.id
	where
		v.href=@videoHref and u.isActive = 1 and v.isActive = 1 and h.isLiked = 1
		end
