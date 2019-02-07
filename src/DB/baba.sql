
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Author` (
  `Author_id` INT NOT NULL AUTO_INCREMENT,
  `AuthorName` VARCHAR(45) NULL,
  PRIMARY KEY (`Author_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Category` (
  `Category_id` INT NOT NULL AUTO_INCREMENT,
  `CategoryName` VARCHAR(45) NULL,
  PRIMARY KEY (`Category_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Books` (
  `Book_id` INT NOT NULL AUTO_INCREMENT,
  `BookName` VARCHAR(45) NULL,
  `Amount` INT NULL,
  `Author_Author_id` INT NOT NULL,
  `Category_Category_id1` INT NOT NULL,
  PRIMARY KEY (`Book_id`, `Author_Author_id`, `Category_Category_id1`),
  CONSTRAINT `fk_Books_Author`
    FOREIGN KEY (`Author_Author_id`)
    REFERENCES `mydb`.`Author` (`Author_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Books_Category1`
    FOREIGN KEY (`Category_Category_id1`)
    REFERENCES `mydb`.`Category` (`Category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Books_Author_idx` ON `mydb`.`Books` (`Author_Author_id` ASC) VISIBLE;

CREATE INDEX `fk_Books_Category1_idx` ON `mydb`.`Books` (`Category_Category_id1` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `mydb`.`Client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Client` (
  `Cid` INT NOT NULL AUTO_INCREMENT,
  `Cname` VARCHAR(45) NULL,
  `BirthDate` VARCHAR(45) NULL,
  `Gender` VARCHAR(45) NULL,
  `Address` VARCHAR(45) NULL,
  `PhoneNum` VARCHAR(45) NULL,
  PRIMARY KEY (`Cid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Employee` (
  `UserName` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NULL,
  `Ename` VARCHAR(45) NULL,
  `BirthDate` VARCHAR(45) NULL,
  `Address` VARCHAR(45) NULL,
  `PhoneNum` VARCHAR(45) NULL,
  `Gender` VARCHAR(45) NULL,
  `Salary` VARCHAR(45) NULL,
  PRIMARY KEY (`UserName`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `UserName_UNIQUE` ON `mydb`.`Employee` (`UserName` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `mydb`.`Borrows`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Borrows` (
  `Borrow_id` INT NOT NULL AUTO_INCREMENT,
  `FromDate` VARCHAR(45) NULL,
  `ToDate` VARCHAR(45) NULL,
  `Client_Cid` INT NOT NULL,
  `Books_Book_id` INT NOT NULL,
  `Employee_UserName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Borrow_id`, `Client_Cid`, `Books_Book_id`, `Employee_UserName`),
  CONSTRAINT `fk_Borrows_Client1`
    FOREIGN KEY (`Client_Cid`)
    REFERENCES `mydb`.`Client` (`Cid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Borrows_Books1`
    FOREIGN KEY (`Books_Book_id`)
    REFERENCES `mydb`.`Books` (`Book_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Borrows_Employee1`
    FOREIGN KEY (`Employee_UserName`)
    REFERENCES `mydb`.`Employee` (`UserName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Borrows_Client1_idx` ON `mydb`.`Borrows` (`Client_Cid` ASC) VISIBLE;

CREATE INDEX `fk_Borrows_Books1_idx` ON `mydb`.`Borrows` (`Books_Book_id` ASC) VISIBLE;

CREATE INDEX `fk_Borrows_Employee1_idx` ON `mydb`.`Borrows` (`Employee_UserName` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


create trigger TDB_BOOKS before delete
on Books for each row
delete from Borrows where Borrows.Book_id=Book_id;
begin
end;


create trigger TUB_BOOKS before update
on Books for each row
begin
end;


create trigger TIB_BORROWS before insert
on Borrows for each row
begin
end;


create trigger TUB_BORROWS before update
on Borrows for each row
begin
end;



-- -----------------------------------------------------------
-- End of Triggers
-- -----------------------------------------------------------

-- -----------------------------------------------------------
-- Adding Procedures
-- -----------------------------------------------------------
DELIMITER $$
 create procedure addAuthor (in AName varchar(45))
 begin
 insert into Author
 (AuthorName) values(  AName);
 end $$
 DELIMITER ;

 DELIMITER $$
 create procedure addCategory (in CategoryName varchar(45))
 begin
 insert into Category
 (CategoryName) values( CategoryName);
 end $$
 DELIMITER ;

 DELIMITER $$
CREATE PROCEDURE addBook
(
in  BookName varchar(45),
in Amount INT ,in Author_Author_id INT,in Category_Category_id1 INT)
begin
insert into Books(BookName,Amount,Author_Author_id,Category_Category_id1)
values(BookName,Amount,Author_Author_id,Category_Category_id1);
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE addclient
(in  Cname varchar(45),in BirthDate varchar(45) ,in  Gender varchar(45),in Address varchar(45),in PhoneNum varchar(45))
begin
insert into Client(Cname,BirthDate,Gender,Address,PhoneNum)
values(Cname,BirthDate,Gender,Address,PhoneNum);
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE addemployee
(in UserName varchar(15),in Password varchar(45),in Ename varchar(45),in BirthDate varchar(45) ,in  Gender varchar(45),in Address varchar(45),in PhoneNum varchar(45),in Salary varchar(45))
begin
insert into Employee(UserName,Password,Ename,BirthDate,Gender,Address,PhoneNum,Salary)
values(UserName,Password,Ename,BirthDate,Gender,Address,PhoneNum,Salary);
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE addBorrows
(in FromDate varchar(15),in ToDate varchar(45),in Client_Cid int,in Books_Book_id int,in Employee_UserName varchar(45))
begin
insert into Borrows(FromDate,ToDate,Client_Cid,Books_Book_id,Employee_UserName)
values(FromDate,ToDate,Client_Cid,Books_Book_id,Employee_UserName);
end $$
DELIMITER ;


 DELIMITER $$
 CREATE procedure  Find_id(IN Authorid varchar(45))
BEGIN
 select AuthorName
 from Author
 where Author_id= Authorid ;
  END $$
 DELIMITER ;

DELIMITER $$
CREATE PROCEDURE updatePhoneClient
(in cid int,
in phone varchar(45))
begin
update Client
set
PhoneNum=phone
where Client.Cid=cid;
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE updateNameClient
(in cid int,
in cname varchar(45))
begin
update Client
set
Cname=cname
where Client.Cid=cid;
end $$
DELIMITER ;

select *from Employee;
select UserName,Password  from Employee where UserName='koko'and Password='1234567';

DELIMITER $$
CREATE PROCEDURE updateDateClient
(in cid int,
in birthDate varchar(45))
begin
update Client
set
BirthDate=birthDate
where Client.Cid=cid;
end $$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE updateGenderClient
(in cid int,
in gender varchar(45))
begin
update Client
set
Gender=gender
where Client.Cid=cid;
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE updateAddressClient
(in cid int,
in address varchar(45))
begin
update Client
set
Address=address
where Client.Cid=cid;
end $$
DELIMITER ;

DELIMITER $$
create procedure deleteCLient(in CID int)
begin
delete from Client where Client.Cid=CID;
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE updatePhoneEmployee
(in username varchar(45),
in phone varchar(45))
begin
update Employee
set
PhoneNum=phone
where Employee.UserName=username;
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE updateNameEmployee
(in username varchar(45),
in ename varchar(45))
begin
update Employee
set
Ename=ename
where Employee.UserName=username;
end $$
DELIMITER ;
call updateNameEmployee("koko","kamaal");
select * from Employee;

DELIMITER $$
CREATE PROCEDURE updateDateEmployee
(in username varchar(45),
in birthDate varchar(45))
begin
update Employee
set
BirthDate=birthDate
where Employee.UserName=username;
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE updateGenderEmployee
(username varchar(45),
in gender varchar(45))
begin
update Employee
set
Gender=gender
where Employee.UserName=username;
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE updateAddressEmployee
(username varchar(45),
in address varchar(45))
begin
update Employee
set
Address=address
where Employee.UserName=username;
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE updatePasswordEmployee
(username varchar(45),
in password varchar(45))
begin
update Employee
set
Password=password
where Employee.UserName=username;
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE updateSalaryEmployee
(username varchar(45),
in salary varchar(45))
begin
update Employee
set
Salary=salary
where Employee.UserName=username;
end $$
DELIMITER ;

DELIMITER $$
create procedure deleteEmployee(in username varchar(45))
begin
delete from Employee where Employee.UserName=username;
end $$
DELIMITER ;


//////////////////////////////////////////////////
DELIMITER $$
CREATE PROCEDURE updateBookName
(in Book_id int,
in BookName varchar(45))
begin
update Books
set
BookName=BookName
where Books.Book_id=Book_id;
end $$
DELIMITER ;
drop procedure updateBookName;
call updateBookName(5,"essays");
select * from Books;
DELIMITER $$
CREATE PROCEDURE updatebookamount
(in bookid int,
in amount varchar(45))
begin
update Books
set
Amount=amount
where Books.Book_id=bookid;
end $$
DELIMITER ;
drop procedure updatebookamount;
call  updatebookamount(5,3);

DELIMITER $$
CREATE PROCEDURE updatebookauthor
(in bookid int,
in author varchar(45))
begin
update Books
set
Author_Author_id=author
where Books.Book_id=bookid;
end $$
DELIMITER ;
drop procedure updatebookauthor;
call updatebookauthor(5,2);

DELIMITER $$
CREATE PROCEDURE updatebookcategory
(in bookid int,
in category varchar(45))
begin
update Books
set
Category_Category_id1=category
where Books.Book_id=bookid;
end $$
DELIMITER ;
drop procedure updatebookcategory;
call updatebookcategory(5,3);

DELIMITER $$
create procedure deletebk(in Book_id INT,in Author_id INT, in Category_id INT)
begin
delete from Books where Books.Book_id=Book_id;
delete from Books where Books.Category_Category_id1=Category_id;
delete from Books where Books.Author_Author_id=Author_id;

end $$
DELIMITER ;
drop procedure deletebk;
call deletebk(1,2,6);
select * from Books;



-- ----------------------------------------------------
-- End of Procedures
-- ----------------------------------------------------


-- ----------------------------------------------------
-- ----------------------------------------------------



call addemployee("nono","1234567","nour","1997-8-9","female","beirut","03-121314","600$");
call addemployee("koko","1234567","kamal","1970-6-7","male","beirut","71-159753","600$");
call addemployee("momo","1234567","mohamad","1997-8-9","male","beirut","03-445455","600$");

  call addAuthor("hanin");
  call addAuthor("ali");
  call addAuthor("safaa");
  call addAuthor("hasan");
  call addAuthor("baker");
  call addAuthor("Jhon");
  call addAuthor("mary");
  call addAuthor("jemy");
  call addAuthor("aya");
  call addAuthor("batoul");

   call addCategory("education");
 call addCategory("romance");
 call addCategory("action");
 call addCategory("horror");
 call addCategory("comedy");
 call addCategory("Thriller");
 call addCategory("Drama");

 call addBook("The Fault in our stars",10,2,6);
call addBook("summer vacation",4,2,5);
call addBook("love story",16,2,2);
call addBook("funny one",15,3,5);
call addBook("the mask",10,6,5);
call addBook("thor",12,4,3);
call addBook("looking for alaska",10,6,3);
call addBook("the nerd",4,2,1);
call addBook("math book",7,1,1);
call addBook("the science life",9,8,1);
call addBook("world of physics",13,1,5);
call addBook("the speed",12,4,3);
call addBook("blood everywhere",20,7,4);
call addBook("the notebook",13,8,7);
call addBook("hello world",18,10,1);

call addclient("ali","2000-12-19","male","beirut","78-123456");

call addclient("tala","2001-11-12","female","beirut","78-112233");

call addclient("sami","1999-10-12","male","hamra","78-555555");

call addclient("amal","1997-8-10","female","hadath","78-112233");

call addclient("ghader","1992-11-10","female","hara","78-112233");

call addclient("hussein","1980-11-12","male","beirut","78-112233");

 select *from Books;
 select *from Employee;
 select *from Client;
 select *from Borrows;
 select *from Author;
 select *from Category;


 -- Altered table Employee
 ALTER TABLE Employee drop Employee.BirthDate;
 ALTER TABLE Employee add eBirthDate varchar(45);
 -- end ALter
 -- create view for recepient
 create view recepient
 as select Employee.UserName,Employee.Ename,Client.Cid,Borrows.Books_Book_id,Books.BookName,Client.PhoneNum,Client.Address,FromDate,ToDate from Borrows
 INNER JOIN Employee on Employee.UserName=Borrows.Employee_UserName
 INNER JOIN Client on Client.Cid=Borrows.Client_Cid
 INNER JOIN Books on Books.Book_id=Books_Book_id;

