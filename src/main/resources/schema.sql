create table book(
    id integer identity not null,
    title varchar(50) not null,
    description varchar(50),
    stock integer,
    sale_price double,
    available BOOLEAN,
    constraint pk_book_id primary key (id)
);