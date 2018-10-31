#CREATE DATABASE transactoins;
#USE transactoins;

CREATE TABLE IF NOT EXISTS transactions(
 ID int not null,
 NameOnCard varchar(256) not null,
 CardNumber varchar(45) not null,
 UnitPrice decimal(10,0) not null,
 Quantity int not null,
 TotalPrice decimal(10,0) not null,
 ExpDate varchar(16) not null,
 CreatedOn datetime not null,
 CreatedBy varchar(45) not null,
 PRIMARY KEY (ID)
) 