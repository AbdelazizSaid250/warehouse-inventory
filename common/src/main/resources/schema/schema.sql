create table tracking_device
(
    id          uuid    not null
        primary key,
    temperature smallint,
    is_active   boolean,
    is_sell     boolean,
    public_code integer not null
        constraint tracking_device_code_key
            unique
);

alter table tracking_device
    owner to postgres;

create table device_credential
(
    id                 uuid not null
        primary key,
    tracking_device_id uuid
        constraint device_credential_tracking_device_id_fk
            references tracking_device
            on update cascade,
    secret_code        varchar(7)
);

alter table device_credential
    owner to postgres;

