create table production_card_item
(
    id          bigint  not null
        constraint production_card_item_pkey
            primary key,
    barcode     varchar(255),
    create_time timestamp,
    edit_time   timestamp,
    is_deleted  boolean not null,
    quantity    bigint,
    company_id  bigint,
    mb_id       bigint  not null
        constraint fkae0ros60f8i01wv9ux9uk3emg
            references production_card
);
create sequence prod_card_item_id_seq;

alter sequence prod_card_item_id_seq owner to prod;

ALTER TABLE production_card_item ALTER COLUMN id SET DEFAULT nextval('prod_card_item_id_seq');
