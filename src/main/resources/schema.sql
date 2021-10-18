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
   constraint pk_sale_id primary key (id)
);