create table production
(
    id              bigint  not null
        constraint production_pkey
            primary key,
    create_time     timestamp,
    edit_time       timestamp,
    additional_cost double precision,
    multiplier      bigint,
    is_deleted      boolean not null,
    is_draft        boolean not null,
    mb_id           bigint not null
        constraint fkae0ros60f8i01wv9ux9uk3emg references production_card(id),
    product_status  varchar(255),
    store_id        bigint
);

create sequence production_id_seq;

alter sequence production_id_seq owner to prod;

ALTER TABLE production ALTER COLUMN id SET DEFAULT nextval('production_id_seq');

alter table production
    owner to prod;

