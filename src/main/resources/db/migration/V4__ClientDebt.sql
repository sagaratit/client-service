drop table IF EXISTS CLIENT_DEBT;

create table `CLIENT_DEBT`(
   `client_debt_id` INTEGER auto_increment NOT NULL,
   `payer_client_id` VARCHAR(150),
   `payee_client_id` VARCHAR(150),
   `debt` DOUBLE NOT NULL DEFAULT 0
);
-- SET PRIMARY KEY
alter table CLIENT_DEBT add CONSTRAINT client_debt_id_1 PRIMARY KEY(client_debt_id);
-- SETUP INDEXES  ------------------------
create INDEX CLIENT_DEBT_PAYER_ID_INDEX ON CLIENT_DEBT(payer_client_id);
create INDEX CLIENT_DEBT_PAYEE_ID_INDEX ON CLIENT_DEBT(payee_client_id);