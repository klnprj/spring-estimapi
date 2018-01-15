CREATE TABLE accesstoken (
    id bigint NOT NULL,
    authentication bytea NOT NULL,
    authenticationkey character varying(255) NOT NULL,
    clientid character varying(255) NOT NULL,
    expiration timestamp without time zone NOT NULL,
    refreshtoken character varying(255),
    tokentype character varying(255) NOT NULL,
    username character varying(255),
    value character varying(255) NOT NULL
);
ALTER TABLE accesstoken OWNER TO estimasys_admin;

CREATE TABLE accesstoken_additionalinformation (
    additionalinformation bigint,
    additionalinformation_idx character varying(255),
    additionalinformation_elt character varying(255) NOT NULL
);
ALTER TABLE accesstoken_additionalinformation OWNER TO estimasys_admin;

CREATE TABLE accesstoken_scope (
    accesstoken_id bigint NOT NULL,
    scope_string character varying(255)
);
ALTER TABLE accesstoken_scope OWNER TO estimasys_admin;

CREATE TABLE authorizationcode (
    id bigint NOT NULL,
    authentication bytea NOT NULL,
    code character varying(255) NOT NULL
);
ALTER TABLE authorizationcode OWNER TO estimasys_admin;

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
ALTER TABLE building OWNER TO estimasys_admin;

CREATE SEQUENCE building_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE building_id_sequence OWNER TO estimasys_admin;

CREATE TABLE client (
    id bigint NOT NULL,
    version bigint NOT NULL,
    accesstokenvalidityseconds integer,
    clientid character varying(255) NOT NULL,
    clientsecret character varying(255),
    refreshtokenvalidityseconds integer
);
ALTER TABLE client OWNER TO estimasys_admin;

CREATE TABLE client_additionalinformation (
    additionalinformation bigint,
    additionalinformation_idx character varying(255),
    additionalinformation_elt character varying(255) NOT NULL
);
ALTER TABLE client_additionalinformation OWNER TO estimasys_admin;

CREATE TABLE client_authorities (
    client_id bigint,
    authorities_string character varying(255)
);
ALTER TABLE client_authorities OWNER TO estimasys_admin;

CREATE TABLE client_authorizedgranttypes (
    client_id bigint,
    authorizedgranttypes_string character varying(255)
);
ALTER TABLE client_authorizedgranttypes OWNER TO estimasys_admin;

CREATE TABLE client_autoapprovescopes (
    client_id bigint,
    autoapprovescopes_string character varying(255)
);
ALTER TABLE client_autoapprovescopes OWNER TO estimasys_admin;

CREATE TABLE client_redirecturis (
    client_id bigint,
    redirecturis_string character varying(255)
);
ALTER TABLE client_redirecturis OWNER TO estimasys_admin;

CREATE TABLE client_resourceids (
    client_id bigint,
    resourceids_string character varying(255)
);
ALTER TABLE client_resourceids OWNER TO estimasys_admin;

CREATE TABLE client_scopes (
    client_id bigint,
    scopes_string character varying(255)
);
ALTER TABLE client_scopes OWNER TO estimasys_admin;

CREATE TABLE comment (
    id bigint NOT NULL,
    version bigint NOT NULL,
    author_id bigint NOT NULL,
    building_id bigint NOT NULL,
    datecreated timestamp without time zone,
    text character varying(2000) NOT NULL
);
ALTER TABLE comment OWNER TO estimasys_admin;

CREATE SEQUENCE comment_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE comment_id_sequence OWNER TO estimasys_admin;

CREATE TABLE contact (
    id bigint NOT NULL,
    version bigint NOT NULL,
    building_id bigint NOT NULL,
    contact_id bigint NOT NULL,
    info character varying(512)
);
ALTER TABLE contact OWNER TO estimasys_admin;

CREATE SEQUENCE contact_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE contact_id_sequence OWNER TO estimasys_admin;

CREATE TABLE dealer (
    id bigint NOT NULL,
    version bigint NOT NULL,
    name character varying(255) NOT NULL
);
ALTER TABLE dealer OWNER TO estimasys_admin;

CREATE SEQUENCE dealer_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE dealer_id_sequence OWNER TO estimasys_admin;

CREATE TABLE dictionary (
    id bigint NOT NULL,
    version bigint NOT NULL,
    key character varying(255) NOT NULL,
    name character varying(255) NOT NULL
);
ALTER TABLE dictionary OWNER TO estimasys_admin;

CREATE SEQUENCE dictionary_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE dictionary_id_sequence OWNER TO estimasys_admin;

CREATE SEQUENCE dictionary_item_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE dictionary_item_id_sequence OWNER TO estimasys_admin;

CREATE TABLE dictionaryitem (
    id bigint NOT NULL,
    version bigint NOT NULL,
    contactname character varying(255),
    contactposition character varying(255),
    dictionary_id bigint NOT NULL,
    phone character varying(255),
    title character varying(255) NOT NULL
);
ALTER TABLE dictionaryitem OWNER TO estimasys_admin;

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE hibernate_sequence OWNER TO estimasys_admin;

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
ALTER TABLE juser OWNER TO estimasys_admin;

CREATE TABLE message (
    id bigint NOT NULL,
    version bigint NOT NULL,
    author_id bigint NOT NULL,
    datecreated timestamp without time zone NOT NULL,
    position_id bigint NOT NULL,
    text character varying(255) NOT NULL
);
ALTER TABLE message OWNER TO estimasys_admin;

CREATE SEQUENCE message_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE message_id_sequence OWNER TO estimasys_admin;

CREATE TABLE "position" (
    id bigint NOT NULL,
    version bigint NOT NULL,
    building_id bigint NOT NULL,
    contactname character varying(255) NOT NULL,
    datecreated timestamp without time zone NOT NULL,
    dateshipped timestamp without time zone,
    dealer_id bigint NOT NULL,
    grossprice character varying(255),
    spec character varying(512),
    status character varying(255),
    total character varying(255),
    type character varying(255),
    lastupdated timestamp without time zone,
    dealerprice integer,
    quantity integer
);
ALTER TABLE "position" OWNER TO estimasys_admin;

CREATE SEQUENCE position_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE position_id_sequence OWNER TO estimasys_admin;

CREATE TABLE refreshtoken (
    id bigint NOT NULL,
    authentication bytea NOT NULL,
    expiration timestamp without time zone,
    value character varying(255) NOT NULL
);
ALTER TABLE refreshtoken OWNER TO estimasys_admin;

CREATE TABLE role (
    id bigint NOT NULL,
    version bigint NOT NULL,
    name character varying(255) NOT NULL
);
ALTER TABLE role OWNER TO estimasys_admin;

CREATE SEQUENCE user_id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE user_id_sequence OWNER TO estimasys_admin;

CREATE TABLE userrole (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);
ALTER TABLE userrole OWNER TO estimasys_admin;

INSERT INTO role (id, version, name) VALUES (1, 0, 'ROLE_ADMIN');
INSERT INTO role (id, version, name) VALUES (2, 0, 'ROLE_USER');

ALTER TABLE ONLY accesstoken
    ADD CONSTRAINT accesstoken_pkey PRIMARY KEY (id);

ALTER TABLE ONLY authorizationcode
    ADD CONSTRAINT authorizationcode_pkey PRIMARY KEY (id);

ALTER TABLE ONLY building
    ADD CONSTRAINT building_pkey PRIMARY KEY (id);

ALTER TABLE ONLY client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);

ALTER TABLE ONLY comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);

ALTER TABLE ONLY contact
    ADD CONSTRAINT contact_pkey PRIMARY KEY (id);

ALTER TABLE ONLY dealer
    ADD CONSTRAINT dealer_pkey PRIMARY KEY (id);

ALTER TABLE ONLY dictionary
    ADD CONSTRAINT dictionary_pkey PRIMARY KEY (id);

ALTER TABLE ONLY dictionaryitem
    ADD CONSTRAINT dictionaryitem_pkey PRIMARY KEY (id);

ALTER TABLE ONLY juser
    ADD CONSTRAINT juser_pkey PRIMARY KEY (id);

ALTER TABLE ONLY message
    ADD CONSTRAINT message_pkey PRIMARY KEY (id);

ALTER TABLE ONLY "position"
    ADD CONSTRAINT position_pkey PRIMARY KEY (id);

ALTER TABLE ONLY refreshtoken
    ADD CONSTRAINT refreshtoken_pkey PRIMARY KEY (id);

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);

ALTER TABLE ONLY juser
    ADD CONSTRAINT uk_24u676id7frmhaevff18g5xj6 UNIQUE (email);

ALTER TABLE ONLY accesstoken
    ADD CONSTRAINT uk_4oy0fu26em29i0687odx46wje UNIQUE (value);

ALTER TABLE ONLY authorizationcode
    ADD CONSTRAINT uk_721etju7ow6eddnokbfw81r5l UNIQUE (code);

ALTER TABLE ONLY role
    ADD CONSTRAINT uk_7d8a768x6aiuvmsa24hqiharg UNIQUE (name);

ALTER TABLE ONLY refreshtoken
    ADD CONSTRAINT uk_8l5qmohldx3vhxpi3mn71knw6 UNIQUE (value);

ALTER TABLE ONLY accesstoken
    ADD CONSTRAINT uk_fxj2vyl3p9cf2q0ifhyokf57y UNIQUE (authenticationkey);

ALTER TABLE ONLY client
    ADD CONSTRAINT uk_t4eso7cdw9vs7mk3dsg0reoo7 UNIQUE (clientid);

ALTER TABLE ONLY userrole
    ADD CONSTRAINT userrole_pkey PRIMARY KEY (user_id, role_id);

CREATE UNIQUE INDEX dictionaryitem_titlelower_dictionary_id_uindex ON dictionaryitem USING btree (lower((title)::text), dictionary_id);

ALTER TABLE ONLY client_authorizedgranttypes
    ADD CONSTRAINT fk_1lpd963u4uwkk1mfn6hf641m2 FOREIGN KEY (client_id) REFERENCES client(id);

ALTER TABLE ONLY building
    ADD CONSTRAINT fk_22xmvoimiffp83u0e69aiprwi FOREIGN KEY (author_id) REFERENCES juser(id);

ALTER TABLE ONLY client_redirecturis
    ADD CONSTRAINT fk_44hcdx5wl7ymo923aksrfcjln FOREIGN KEY (client_id) REFERENCES client(id);

ALTER TABLE ONLY contact
    ADD CONSTRAINT fk_5b9wqsy4a45b4s29nqy3etmgc FOREIGN KEY (building_id) REFERENCES building(id);

ALTER TABLE ONLY message
    ADD CONSTRAINT fk_9juetu2emidipkrbnpaodq0np FOREIGN KEY (position_id) REFERENCES "position"(id);

ALTER TABLE ONLY contact
    ADD CONSTRAINT fk_9m39hdmcr81dpcfg33xmoahj5 FOREIGN KEY (contact_id) REFERENCES dictionaryitem(id);

ALTER TABLE ONLY "position"
    ADD CONSTRAINT fk_a6o0u4o38q4tcwbfd935waand FOREIGN KEY (building_id) REFERENCES building(id);

ALTER TABLE ONLY client_scopes
    ADD CONSTRAINT fk_axqmq1qibmhw9em3da590thkl FOREIGN KEY (client_id) REFERENCES client(id);

ALTER TABLE ONLY message
    ADD CONSTRAINT fk_ejy3vmpjhpkd6t8fs4vt1n5jx FOREIGN KEY (author_id) REFERENCES juser(id);

ALTER TABLE ONLY building
    ADD CONSTRAINT fk_elseyb70kwv4oa3t2e1itmjoi FOREIGN KEY (project_id) REFERENCES dictionaryitem(id);

ALTER TABLE ONLY dictionaryitem
    ADD CONSTRAINT fk_fhbev67y6vc4cet2r6y03gnrq FOREIGN KEY (dictionary_id) REFERENCES dictionary(id);

ALTER TABLE ONLY client_authorities
    ADD CONSTRAINT fk_huheb1heplir11dr7vnwlukcx FOREIGN KEY (client_id) REFERENCES client(id);

ALTER TABLE ONLY userrole
    ADD CONSTRAINT fk_hyddjyserbkrnpbveirrw7phm FOREIGN KEY (user_id) REFERENCES juser(id);

ALTER TABLE ONLY comment
    ADD CONSTRAINT fk_j94pith5sd971k29j6ysxuk7 FOREIGN KEY (author_id) REFERENCES juser(id);

ALTER TABLE ONLY building
    ADD CONSTRAINT fk_jr66bj5u3tew873illp96e94r FOREIGN KEY (client_id) REFERENCES dictionaryitem(id);

ALTER TABLE ONLY userrole
    ADD CONSTRAINT fk_l2vc7ef3hyhxy2094ldyf762s FOREIGN KEY (role_id) REFERENCES role(id);

ALTER TABLE ONLY client_resourceids
    ADD CONSTRAINT fk_o2e4njg7ulh0u32bhoq5b9u5c FOREIGN KEY (client_id) REFERENCES client(id);

ALTER TABLE ONLY "position"
    ADD CONSTRAINT fk_oaompb0psuqgw6mpd95dlqk61 FOREIGN KEY (dealer_id) REFERENCES dictionaryitem(id);

ALTER TABLE ONLY accesstoken_scope
    ADD CONSTRAINT fk_os1exu0lxi68twyitriop3edp FOREIGN KEY (accesstoken_id) REFERENCES accesstoken(id);

ALTER TABLE ONLY client_autoapprovescopes
    ADD CONSTRAINT fk_q0qww1p6uq1ck65694r8y0ur2 FOREIGN KEY (client_id) REFERENCES client(id);

ALTER TABLE ONLY comment
    ADD CONSTRAINT fk_stwf0tcvev5h8m8awpslhij96 FOREIGN KEY (building_id) REFERENCES building(id);
