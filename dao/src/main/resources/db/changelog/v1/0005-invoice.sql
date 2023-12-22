create table "invoice"
(
    "id"           bigserial  NOT NULL,
    "invoice_time" timestamp  NOT NULL,
    "payment_id"   bigint     NOT NULL,
    "amount"       numeric    NOT NULL,
    "status"       varchar(1) NOT NULL,

    PRIMARY KEY ("id"),
    FOREIGN KEY ("payment_id") REFERENCES payment ("id")
)