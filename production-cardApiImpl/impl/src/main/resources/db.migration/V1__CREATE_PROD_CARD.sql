create table production_card
(
    id              bigint  not null
        constraint production_card_pkey
            primary key,
    barcode         varchar(255),
    create_time     timestamp,
    edit_time       timestamp,
    is_deleted      boolean not null,
    quantity        bigint,
    additional_cost double precision,
    company_id      bigint,
    name            varchar(255)
);
create sequence prod_card_id_seq;

alter sequence prod_card_id_seq owner to prod;

ALTER TABLE production_card ALTER COLUMN id SET DEFAULT nextval('prod_card_id_seq');
