CREATE TABLE IF NOT EXISTS public.dica
(
    id_dica bigint NOT NULL,
    body character varying(255) COLLATE pg_catalog."default" NOT NULL,
    curtidas integer,
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    id_postagem bigint,
    postagem bytea,
    CONSTRAINT dica_pkey PRIMARY KEY (id_dica),
    CONSTRAINT fkp3agrbds29a88tup5bf691vkq FOREIGN KEY (id_postagem)
        REFERENCES public.postagem (id_postagem) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.dica
    OWNER to postgres;