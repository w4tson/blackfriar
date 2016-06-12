create sequence seq_beer increment by 1;

create table beer (
  id number,
  name varchar2(50),
  price  number
);

insert into beer values (1, 'Beavertown', 10);
insert into beer values (2, 'Anspach hobday', 15);
insert into beer values (3, 'Partizan', 40);


