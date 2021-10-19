create table book(
    id integer identity not null,
    title varchar(50) not null,
    description varchar(50),
    stock integer,
    sale_price double,
    available BOOLEAN,
    constraint pk_book_id primary key (id)
);

create table sale(
   id integer identity not null,
   bookId integer,
   customerEmail varchar(50),
   price double,
   dateOfSale varchar(11) default '1900-01-01',
   constraint pk_sale_id primary key (id)
);

create table likes(
    id integer identity not null,
    bookId integer,
    customerEmail varchar(50),
    likes integer,
    constraint pk_likes_id primary key (id)
);