CREATE TABLE accesstoken (
    id bigint NOT NULL,
    authentication bytea NOT NULL,
    authenticationkey character varying(255) NOT NULL,
    clientid character varying(255) NOT NULL,
    expiration timestamp NOT NULL,
    refreshtoken character varying(255),
    tokentype character varying(255) NOT NULL,
    username character varying(255),
    value character varying(255) NOT NULL
);

CREATE TABLE accesstoken_additionalinformation (
    additionalinformation bigint,
    additionalinformation_idx character varying(255),
    additionalinformation_elt character varying(255) NOT NULL
);

CREATE TABLE accesstoken_scope (
    accesstoken_id bigint NOT NULL,
    scope_string character varying(255)
);

CREATE TABLE authorizationcode (
    id bigint NOT NULL,
    authentication bytea NOT NULL,
    code character varying(255) NOT NULL
);

CREATE TABLE building (
    id bigint NOT NULL,
    version bigint NOT NULL,
    address character varying(255) NOT NULL,
    author_id bigint NOT NULL,
    client_id bigint,
    location character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    project_id bigint,
    description character varying(2000),
    status character varying(20) DEFAULT 'ACTIVE'::character varying
);

CREATE SEQUENCE building_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE client (
    id bigint NOT NULL,
    version bigint NOT NULL,
    accesstokenvalidityseconds integer,
    clientid character varying(255) NOT NULL,
    clientsecret character varying(255),
    refreshtokenvalidityseconds integer
);

CREATE TABLE client_additionalinformation (
    additionalinformation bigint,
    additionalinformation_idx character varying(255),
    additionalinformation_elt character varying(255) NOT NULL
);

CREATE TABLE client_authorities (
    client_id bigint,
    authorities_string character varying(255)
);

CREATE TABLE client_authorizedgranttypes (
    client_id bigint,
    authorizedgranttypes_string character varying(255)
);

CREATE TABLE client_autoapprovescopes (
    client_id bigint,
    autoapprovescopes_string character varying(255)
);

CREATE TABLE client_redirecturis (
    client_id bigint,
    redirecturis_string character varying(255)
);

CREATE TABLE client_resourceids (
    client_id bigint,
    resourceids_string character varying(255)
);

CREATE TABLE client_scopes (
    client_id bigint,
    scopes_string character varying(255)
);

CREATE TABLE comment (
    id bigint NOT NULL,
    version bigint NOT NULL,
    author_id bigint NOT NULL,
    building_id bigint NOT NULL,
    datecreated timestamp,
    text character varying(2000) NOT NULL
);

CREATE SEQUENCE comment_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE contact (
    id bigint NOT NULL,
    version bigint NOT NULL,
    building_id bigint NOT NULL,
    contact_id bigint NOT NULL,
    info character varying(512)
);

CREATE SEQUENCE contact_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE dealer (
    id bigint NOT NULL,
    version bigint NOT NULL,
    name character varying(255) NOT NULL
);

CREATE SEQUENCE dealer_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE dictionary (
    id bigint NOT NULL,
    version bigint NOT NULL,
    key character varying(255) NOT NULL,
    name character varying(255) NOT NULL
);

CREATE SEQUENCE dictionary_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE dictionary_item_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE dictionaryitem (
    id bigint NOT NULL,
    version bigint NOT NULL,
    contactname character varying(255),
    contactposition character varying(255),
    dictionary_id bigint NOT NULL,
    phone character varying(255),
    title character varying(255) NOT NULL
);

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE juser (
    id bigint NOT NULL,
    version bigint NOT NULL,
    name character varying(255) NOT NULL,
    accountexpired boolean NOT NULL,
    accountlocked boolean NOT NULL,
    email character varying(255) NOT NULL,
    enabled boolean NOT NULL,
    password character varying(255) NOT NULL,
    passwordexpired boolean NOT NULL
);

CREATE TABLE message (
    id bigint NOT NULL,
    version bigint NOT NULL,
    author_id bigint NOT NULL,
    datecreated timestamp NOT NULL,
    position_id bigint NOT NULL,
    text character varying(255) NOT NULL
);

CREATE SEQUENCE message_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE "position" (
    id bigint NOT NULL,
    version bigint NOT NULL,
    building_id bigint NOT NULL,
    contactname character varying(255) NOT NULL,
    datecreated timestamp NOT NULL,
    dateshipped timestamp,
    dealer_id bigint NOT NULL,
    grossprice character varying(255),
    spec character varying(512),
    status character varying(255),
    total character varying(255),
    type character varying(255),
    lastupdated timestamp,
    dealerprice integer,
    quantity integer
);

CREATE SEQUENCE position_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE refreshtoken (
    id bigint NOT NULL,
    authentication bytea NOT NULL,
    expiration timestamp,
    value character varying(255) NOT NULL
);

CREATE TABLE role (
    id bigint NOT NULL,
    version bigint NOT NULL,
    name character varying(255) NOT NULL
);

CREATE SEQUENCE user_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE userrole (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);

INSERT INTO role (id, version, name) VALUES (1, 0, 'ROLE_ADMIN');
INSERT INTO role (id, version, name) VALUES (2, 0, 'ROLE_USER');
