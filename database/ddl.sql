DROP DATABASE IF EXISTS LegisRate;
CREATE DATABASE LegisRate;

USE LegisRate;

create table Legislation(
		LegislatureId int primary key auto_increment not null,
		LegislationTitle varchar(100) not null,
		Summary varchar(2000) not null,
	    Enacted boolean not null
);

create table Users (
		UserId int primary key auto_increment not null,
		Username varchar(30) not null,
	    State char(2) not null
);

create table Review (
		ReviewId int primary key auto_increment not null,
		foreign key fk_Reviews_Legislation(ReviewId)
				references Legislation(LegislatureId),
		foreign key fk_Reviews_Users(ReviewId)
				references Users(UserId),
		UserComment varchar(100) not null,
		Rating int not null
);