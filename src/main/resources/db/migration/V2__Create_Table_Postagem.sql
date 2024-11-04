CREATE TABLE IF NOT EXISTS public.postagem
(
    id_postagem bigint NOT NULL,
    curtidas integer,
    descricao character varying(255) COLLATE pg_catalog."default" NOT NULL,
    imagems bytea,
    nome character varying(255) COLLATE pg_catalog."default" NOT NULL,
    videos bytea,
    usuario bytea,
    id_usuario bigint,
    CONSTRAINT postagem_pkey PRIMARY KEY (id_postagem),
    CONSTRAINT fkdj8wmm5d6pe3u5our45hqamb8 FOREIGN KEY (id_usuario)
        REFERENCES public.usuario (id_usuario) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.postagem
    OWNER to postgres;