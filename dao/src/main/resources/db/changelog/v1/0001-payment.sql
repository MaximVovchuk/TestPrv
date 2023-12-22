create table "payment"
(
    "id" bigserial NOT NULL,
    "sender_name"   varchar(255) NOT NULL,
    "inn"         bigint       NOT NULL,
    "card_number" bigint       NOT NULL,
    "account"     bigint       NOT NULL,
    "mfo"         varchar       NOT NULL,
    "okpo"        bigint       NOT NULL,
    "getter_name" varchar(255) NOT NULL,
    "period"      int          NOT NULL,
    "amount"      numeric      NOT NULL,

    PRIMARY KEY ("id")
);