-- todo: move oauth fields to users; move name to separate table
-- CREATE TABLE juser (
--   id bigint NOT NULL,
--   version bigint NOT NULL,
--   name character varying(255) NOT NULL,
--   accountexpired boolean NOT NULL,
--   accountlocked boolean NOT NULL,
--   email character varying(255) NOT NULL,
--   enabled boolean NOT NULL,
--   password character varying(255) NOT NULL,
--   passwordexpired boolean NOT NULL
-- );

UPDATE users SET (username, password, enabled) = (SELECT email, password, enabled FROM juser);

-- todo: remove?
-- CREATE TABLE role (
--   id bigint NOT NULL,
--   version bigint NOT NULL,
--   name character varying(255) NOT NULL
-- );

-- DROP TABLE role;

-- todo: migrate to authorities
-- CREATE TABLE userrole (
--   user_id bigint NOT NULL,
--   role_id bigint NOT NULL
-- );

UPDATE authorities SET (username, authority) = (SELECT u.email, r.name
                                                FROM juser u
                                                  JOIN userrole ur ON u.id = ur.user_id
                                                  JOIN role r ON ur.role_id = r.id);

DROP TABLE userrole;
DROP TABLE role;

-- todo: migrate to oauth_client_details
CREATE TABLE client (
  id bigint NOT NULL,
  version bigint NOT NULL,
  accesstokenvalidityseconds integer,
  clientid character varying(255) NOT NULL,
  clientsecret character varying(255),
  refreshtokenvalidityseconds integer
);

UPDATE oauth_client_details
SET
  (client_id, client_secret, scope, authorized_grant_types, authorities, access_token_validity, refresh_token_validity) = (
    SELECT
      c.clientid,
      c.clientsecret,
      'READ,WRITE',
      'refresh_token,password',
      'ROLE_CLIENT',
      c.accesstokenvalidityseconds,
      c.refreshtokenvalidityseconds
    FROM client c
  );

DROP TABLE client;
DROP TABLE client_additionalinformation;
DROP TABLE client_authorities;
DROP TABLE client_authorizedgranttypes;
DROP TABLE client_autoapprovescopes;
DROP TABLE client_redirecturis;
DROP TABLE client_resourceids;
DROP TABLE client_scopes;

-- remove
-- CREATE TABLE client_additionalinformation (
--   additionalinformation bigint,
--   additionalinformation_idx character varying(255),
--   additionalinformation_elt character varying(255) NOT NULL
-- );

-- todo: remove?
-- CREATE TABLE client_authorities (
--   client_id bigint,
--   authorities_string character varying(255)
-- );

-- todo: remove
-- CREATE TABLE client_authorizedgranttypes (
--   client_id bigint,
--   authorizedgranttypes_string character varying(255)
-- );

-- todo: remove
-- CREATE TABLE client_autoapprovescopes (
--   client_id bigint,
--   autoapprovescopes_string character varying(255)
-- );

-- todo: remove
-- CREATE TABLE client_redirecturis (
--   client_id bigint,
--   redirecturis_string character varying(255)
-- );

-- todo: remove
-- CREATE TABLE client_resourceids (
--   client_id bigint,
--   resourceids_string character varying(255)
-- );

-- todo: remove
-- CREATE TABLE client_scopes (
--   client_id bigint,
--   scopes_string character varying(255)
-- );

-- todo: no migration data, remove
-- CREATE TABLE accesstoken (
--     id bigint NOT NULL,
--     authentication bytea NOT NULL,
--     authenticationkey character varying(255) NOT NULL,
--     clientid character varying(255) NOT NULL,
--     expiration timestamp without time zone NOT NULL,
--     refreshtoken character varying(255),
--     tokentype character varying(255) NOT NULL,
--     username character varying(255),
--     value character varying(255) NOT NULL
-- );

DROP TABLE accesstoken;

-- todo: remove?
-- CREATE TABLE accesstoken_additionalinformation (
--     additionalinformation bigint,
--     additionalinformation_idx character varying(255),
--     additionalinformation_elt character varying(255) NOT NULL
-- );

DROP TABLE accesstoken_additionalinformation;

-- todo: remove?
-- CREATE TABLE accesstoken_scope (
--     accesstoken_id bigint NOT NULL,
--     scope_string character varying(255)
-- );

DROP TABLE accesstoken_scope;

-- todo: no migration data; remove
-- CREATE TABLE authorizationcode (
--     id bigint NOT NULL,
--     authentication bytea NOT NULL,
--     code character varying(255) NOT NULL
-- );

DROP TABLE authorizationcode;

-- todo: no migration data; remove
-- CREATE TABLE refreshtoken (
--     id bigint NOT NULL,
--     authentication bytea NOT NULL,
--     expiration timestamp without time zone,
--     value character varying(255) NOT NULL
-- );

DROP TABLE refreshtoken;