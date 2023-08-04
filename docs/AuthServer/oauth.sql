-- public.oauth2_authorization definition

-- Drop table

-- DROP TABLE public.oauth2_authorization;

CREATE TABLE public.oauth2_authorization (
                                             id varchar(100) NOT NULL,
                                             registered_client_id varchar(100) NOT NULL,
                                             principal_name varchar(200) NOT NULL,
                                             authorization_grant_type varchar(100) NOT NULL,
                                             authorized_scopes varchar(1000) NULL DEFAULT NULL::character varying,
                                             "attributes" text NULL,
                                             state varchar(500) NULL DEFAULT NULL::character varying,
                                             authorization_code_value text NULL,
                                             authorization_code_issued_at timestamp NULL,
                                             authorization_code_expires_at timestamp NULL,
                                             authorization_code_metadata text NULL,
                                             access_token_value text NULL,
                                             access_token_issued_at timestamp NULL,
                                             access_token_expires_at timestamp NULL,
                                             access_token_metadata text NULL,
                                             access_token_type varchar(100) NULL DEFAULT NULL::character varying,
                                             access_token_scopes varchar(1000) NULL DEFAULT NULL::character varying,
                                             oidc_id_token_value text NULL,
                                             oidc_id_token_issued_at timestamp NULL,
                                             oidc_id_token_expires_at timestamp NULL,
                                             oidc_id_token_metadata text NULL,
                                             refresh_token_value text NULL,
                                             refresh_token_issued_at timestamp NULL,
                                             refresh_token_expires_at timestamp NULL,
                                             refresh_token_metadata text NULL,
                                             user_code_value text NULL,
                                             user_code_issued_at timestamp NULL,
                                             user_code_expires_at timestamp NULL,
                                             user_code_metadata text NULL,
                                             device_code_value text NULL,
                                             device_code_issued_at timestamp NULL,
                                             device_code_expires_at timestamp NULL,
                                             device_code_metadata text NULL,
                                             CONSTRAINT oauth2_authorization_pkey PRIMARY KEY (id)
);


-- public.oauth2_authorization_consent definition

-- Drop table

-- DROP TABLE public.oauth2_authorization_consent;

CREATE TABLE public.oauth2_authorization_consent (
                                                     registered_client_id varchar(100) NOT NULL,
                                                     principal_name varchar(200) NOT NULL,
                                                     authorities varchar(1000) NOT NULL,
                                                     CONSTRAINT oauth2_authorization_consent_pkey PRIMARY KEY (registered_client_id, principal_name)
);


-- public.oauth2_registered_client definition

-- Drop table

-- DROP TABLE public.oauth2_registered_client;

CREATE TABLE public.oauth2_registered_client (
                                                 id varchar(100) NOT NULL,
                                                 client_id varchar(100) NOT NULL,
                                                 client_id_issued_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                                 client_secret varchar(200) NULL DEFAULT NULL::character varying,
                                                 client_secret_expires_at timestamp NULL,
                                                 client_name varchar(200) NOT NULL,
                                                 client_authentication_methods varchar(1000) NOT NULL,
                                                 authorization_grant_types varchar(1000) NOT NULL,
                                                 redirect_uris varchar(1000) NULL DEFAULT NULL::character varying,
                                                 post_logout_redirect_uris varchar(1000) NULL DEFAULT NULL::character varying,
                                                 scopes varchar(1000) NOT NULL,
                                                 client_settings varchar(2000) NOT NULL,
                                                 token_settings varchar(2000) NOT NULL,
                                                 CONSTRAINT oauth2_registered_client_pkey PRIMARY KEY (id)
);


# following is authorities
CREATE TABLE users (
                       username varchar NOT NULL,
                       "password" varchar NOT NULL,
                       enabled boolean NOT NULL DEFAULT true,
                       CONSTRAINT users_pk PRIMARY KEY (username)
);
COMMENT ON TABLE users IS 'user table';



CREATE TABLE public.authorities (
                                    username varchar NOT NULL,
                                    authority varchar NOT NULL,
                                    CONSTRAINT authorities_fk FOREIGN KEY (username) REFERENCES public.users(username) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE UNIQUE INDEX authorities_username_idx ON public.authorities (username,authority);
