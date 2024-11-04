CREATE TABLE IF NOT EXISTS public.imagem
(
    id_imagem bigint NOT NULL,
    curtidas integer,
    descricao character varying(255) COLLATE pg_catalog."default",
    endereco character varying(255) COLLATE pg_catalog."default",
    titulo character varying(255) COLLATE pg_catalog."default" NOT NULL,
    figure oid,
    id_postagem bigint,
    CONSTRAINT imagem_pkey PRIMARY KEY (id_imagem),
    CONSTRAINT fktibrgoi3vq5e6gqjg1utfrsjk FOREIGN KEY (id_postagem)
        REFERENCES public.postagem (id_postagem) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.imagem
    OWNER to postgres;