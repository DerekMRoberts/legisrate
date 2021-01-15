DROP DATABASE IF EXISTS LegisRate;
CREATE DATABASE LegisRate;

USE LegisRate;

create table Legislation(
		LegislatureId int primary key auto_increment not null,
		LegislationTitle varchar(100) not null,
		Summary varchar(2000) not null,
	    Enacted boolean not null
);

create table Review (
		ReviewId int primary key auto_increment not null,
        LegislatureId int not null, 
		foreign key fk_Reviews_Legislation(LegislatureId)
				references Legislation(LegislatureId),
		Username varchar(30) not null,
	    UserState char(2) not null,
		UserComment varchar(2000) not null,
		Rating int not null
);