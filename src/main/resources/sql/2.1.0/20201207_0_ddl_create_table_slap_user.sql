CREATE TABLE public.slap_user
(
    name      character varying NOT NULL,
    "exclude" boolean           NOT NULL,
    PRIMARY KEY (name)
) WITH ( OIDS = FALSE );

GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.slap_user TO klaybot;
