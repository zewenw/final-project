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


-- public."permission" definition

-- Drop table

-- DROP TABLE public."permission";

CREATE TABLE public."permission" (
                                     id int8 NOT NULL,
                                     permission_name varchar NOT NULL,
                                     permission_code varchar NOT NULL,
                                     permission_type varchar NOT NULL,
                                     CONSTRAINT permission_pk PRIMARY KEY (id)
);


-- public."role" definition

-- Drop table

-- DROP TABLE public."role";

CREATE TABLE public."role" (
                               id int8 NOT NULL,
                               role_code varchar NOT NULL,
                               CONSTRAINT role_pk PRIMARY KEY (id)
);


-- public.role_permission definition

-- Drop table

-- DROP TABLE public.role_permission;

CREATE TABLE public.role_permission (
                                        id int8 NOT NULL,
                                        role_id int8 NOT NULL,
                                        permission_id int8 NOT NULL,
                                        CONSTRAINT role_permission_pk PRIMARY KEY (id)
);


-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
                              username varchar NOT NULL,
                              "password" varchar NOT NULL,
                              enabled bool NOT NULL DEFAULT true,
                              CONSTRAINT users_pk PRIMARY KEY (username)
);


-- public.authorities definition

-- Drop table

-- DROP TABLE public.authorities;

CREATE TABLE public.authorities (
                                    username varchar NOT NULL,
                                    authority varchar NOT NULL,
                                    CONSTRAINT authorities_fk FOREIGN KEY (username) REFERENCES public.users(username) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE UNIQUE INDEX authorities_username_idx ON public.authorities USING btree (username, authority);


INSERT INTO public.oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, post_logout_redirect_uris, scopes, client_settings, token_settings)
VALUES('e4a295f7-0a5f-4cbc-bcd3-d870243d1b05', 'client', '2023-06-20 19:25:41.310', '{bcrypt}$2a$10$pJjSgdx5ysTsi1HrE8dLpurFiaTTWhqvxNFiYXeIwR2BYJSetfta6', NULL, 'e4a295f7-0a5f-4cbc-bcd3-d870243d1b05', 'client_secret_basic', 'refresh_token,client_credentials,authorization_code', 'http://127.0.0.1:9002/login/oauth2/code/final_project', '', 'openid', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",300.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000],"settings.token.device-code-time-to-live":["java.time.Duration",300.000000000]}');
INSERT INTO public.oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, post_logout_redirect_uris, scopes, client_settings, token_settings)
VALUES('messaging_gateway_oidc', 'gateway_client', '2023-07-10 18:35:44.430', '{bcrypt}$2a$10$D4mEf7bFaSwSmBs5DMvgBex16R7My.Bv.YAPwe3FO8wVjHh1La5ya', NULL, 'messaging_gateway_oidc', 'client_secret_post,client_secret_basic', 'refresh_token,authorization_code', 'http://localhost/login/oauth2/code/messaging_gateway_oidc', '', 'read,openid,profile,email', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",1800.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000],"settings.token.device-code-time-to-live":["java.time.Duration",300.000000000]}');


INSERT INTO public.users
(username, "password", enabled)
VALUES('john', '{noop}test123', true);
INSERT INTO public.users
(username, "password", enabled)
VALUES('mary', '{noop}test123', true);
INSERT INTO public.users
(username, "password", enabled)
VALUES('susan', '{noop}test123', true);

INSERT INTO public.authorities
(username, authority)
VALUES('john', 'ROLE_EMPLOYEE');
INSERT INTO public.authorities
(username, authority)
VALUES('john', 'ROLE_USER');
INSERT INTO public.authorities
(username, authority)
VALUES('mary', 'ROLE_EMPLOYEE');
INSERT INTO public.authorities
(username, authority)
VALUES('mary', 'ROLE_MANAGER');
INSERT INTO public.authorities
(username, authority)
VALUES('susan', 'ROLE_ADMIN');
INSERT INTO public.authorities
(username, authority)
VALUES('susan', 'ROLE_EMPLOYEE');
INSERT INTO public.authorities
(username, authority)
VALUES('susan', 'ROLE_MANAGER');
