CREATE TABLE `User` (
  `id` integer PRIMARY KEY,
  `name` varchar(255),
  `email` varchar(255),
  `password` varchar(255),
  `role` ENUM ('user', 'admin', 'editor')
);

CREATE TABLE `Project` (
  `id` integer PRIMARY KEY,
  `name` varchar(255),
  `description` varchar(255),
  `owner_id` integer
);

CREATE TABLE `ApiVersion` (
  `id` integer PRIMARY KEY,
  `version` float,
  `apiDefinition_id` integer,
  `status` ENUM ('ACTIVE', 'DEPRECATED'),
  `schemaRequest` varchar(255),
  `schemaResponse` varchar(255)
);

CREATE TABLE `AuthType` (
  `id` integer PRIMARY KEY,
  `typeName` ENUM ('JWT', 'BASIC', 'OAUTH', 'OAUTH2')
);

CREATE TABLE `Tag` (
  `id` integer PRIMARY KEY,
  `name` varchar(255),
  `description` varchar(255)
);

CREATE TABLE `ApiDefinition_Tag` (
  `apiDefinition_id` integer,
  `tag_id` integer
);

CREATE TABLE `MockApi` (
  `id` integer PRIMARY KEY,
  `apiVersion_id` integer,
  `mockResponse` JSON,
  `createdBy` integer
);

CREATE TABLE `TestRequest` (
  `id` integer PRIMARY KEY,
  `apiVersion_id` integer,
  `requestBody` JSON,
  `responseBody` JSON,
  `statusCode` statusCode,
  `timestamp` timestamp,
  `requestBy` integer
);

CREATE TABLE `ApiDependency` (
  `id` integer PRIMARY KEY,
  `sourceApiVersion_id` integer,
  `targetApiVersion_id` integer,
  `relationType` ENUM ('API_CALLS', 'API_USES_MODEL')
);

CREATE TABLE `ApiDefinition` (
  `id` integer PRIMARY KEY,
  `path` varchar(255),
  `httpMethod` ENUM ('GET', 'PUT', 'POST', 'DELETE'),
  `description` varchar2,
  `authType_id` integer,
  `project_id` integer,
  `createdBy` integer
);

ALTER TABLE `User` COMMENT = 'Allows authentication and controls access to features. Tracks who created what.';

ALTER TABLE `Project` COMMENT = 'Organizes APIs logically. Tracks ownership.';

ALTER TABLE `ApiVersion` COMMENT = 'Helps manage compatibility and deprecation.';

ALTER TABLE `AuthType` COMMENT = 'Helps teams document and manage auth strategies for each API. Critical for integration clarity.';

ALTER TABLE `Tag` COMMENT = 'Enables easy searching, categorizing, and filtering of APIs. Improves discoverability.';

ALTER TABLE `ApiDefinition_Tag` COMMENT = 'Keeps schema normalized.';

ALTER TABLE `MockApi` COMMENT = 'Helps rapid prototyping and mocking.';

ALTER TABLE `TestRequest` COMMENT = 'Tracks testing history and debugging info.';

ALTER TABLE `ApiDependency` COMMENT = 'Helps in impact analysis, shows dependency maps, useful for large systems integration planning.';

ALTER TABLE `ApiDefinition` COMMENT = 'Central resource of the platform. Represents each API endpoint’s business/functional meaning.';

ALTER TABLE `Project` ADD FOREIGN KEY (`owner_id`) REFERENCES `User` (`id`);

ALTER TABLE `ApiVersion` ADD FOREIGN KEY (`apiDefinition_id`) REFERENCES `ApiDefinition` (`id`);

ALTER TABLE `ApiDefinition_Tag` ADD FOREIGN KEY (`apiDefinition_id`) REFERENCES `ApiDefinition` (`id`);

ALTER TABLE `ApiDefinition_Tag` ADD FOREIGN KEY (`tag_id`) REFERENCES `Tag` (`id`);

ALTER TABLE `MockApi` ADD FOREIGN KEY (`apiVersion_id`) REFERENCES `ApiVersion` (`id`);

ALTER TABLE `MockApi` ADD FOREIGN KEY (`createdBy`) REFERENCES `User` (`id`);

ALTER TABLE `TestRequest` ADD FOREIGN KEY (`apiVersion_id`) REFERENCES `ApiVersion` (`id`);

ALTER TABLE `TestRequest` ADD FOREIGN KEY (`requestBy`) REFERENCES `User` (`id`);

ALTER TABLE `ApiDependency` ADD FOREIGN KEY (`sourceApiVersion_id`) REFERENCES `ApiVersion` (`id`);

ALTER TABLE `ApiDependency` ADD FOREIGN KEY (`targetApiVersion_id`) REFERENCES `ApiVersion` (`id`);

ALTER TABLE `ApiDefinition` ADD FOREIGN KEY (`authType_id`) REFERENCES `AuthType` (`id`);

ALTER TABLE `ApiDefinition` ADD FOREIGN KEY (`project_id`) REFERENCES `Project` (`id`);

ALTER TABLE `ApiDefinition` ADD FOREIGN KEY (`createdBy`) REFERENCES `User` (`id`);




-- for dbdiagram.io

-- enum role {
--   "user"
--   "admin"
--   "editor"
-- }

-- Table User {
--   Note : "Allows authentication and controls access to features. Tracks who created what."
--   id integer [primary key]
--   name varchar
--   email varchar
--   password varchar
--   role role
-- }

-- Table Project {
--   Note: "Organizes APIs logically. Tracks ownership."
--   id integer [primary key]
--   name varchar
--   description varchar
--   owner_id integer [ref : > User.id]
-- } 


-- enum ApiStatus {
--   "ACTIVE"
--   "DEPRECATED"
-- }

-- Table ApiVersion {
--   Note : "Helps manage compatibility and deprecation."
--   id integer [primary key]
--   version float
--   apiDefinition_id integer [ref : > ApiDefinition.id]
--   status ApiStatus
--   schemaRequest varchar
--   schemaResponse varchar
-- }

-- Enum AuthTypeName {
--   "JWT"
--   "BASIC"
--   "OAUTH"
--   "OAUTH2"
-- }

-- Table AuthType {
--   Note: "Helps teams document and manage auth strategies for each API. Critical for integration clarity."
--   id integer [primary key]
--   typeName AuthTypeName
-- }

-- Table Tag {
--   Note: "Enables easy searching, categorizing, and filtering of APIs. Improves discoverability."
--   id integer [primary key]
--   name varchar
--   description varchar
-- }

-- Table ApiDefinition_Tag {
--   Note: "Keeps schema normalized."
--   apiDefinition_id integer [ref: > ApiDefinition.id]
--   tag_id integer [ref : > Tag.id]
-- }

-- Table MockApi {
--   Note: "Helps rapid prototyping and mocking."
--   id integer [primary key]
--   apiVersion_id integer [ref : > ApiVersion.id]
--   mockResponse JSON
--   createdBy integer [ref : > User.id]
-- }

-- Table TestRequest {
--   Note : "Tracks testing history and debugging info."
--   id integer [primary key]
--   apiVersion_id integer [ref : > ApiVersion.id]
--   requestBody JSON
--   responseBody JSON
--   statusCode statusCode
--   timestamp timestamp
--   requestBy integer [ref : > User.id]
-- }

-- Enum relationType {
--   "API_CALLS"
--   "API_USES_MODEL"
-- }
-- Table ApiDependency {
--   Note: "Helps in impact analysis, shows dependency maps, useful for large systems integration planning."
--   id integer [primary key]
--   sourceApiVersion_id integer [ref :> ApiVersion.id]
--   targetApiVersion_id integer [ref : > ApiVersion.id]
--   relationType relationType
-- }

-- enum httpMethods {
--   "GET"
--   "PUT"
--   "POST"
--   "DELETE"
-- }

-- Table ApiDefinition {
--   Note: "Central resource of the platform. Represents each API endpoint’s business/functional meaning."
--   id integer [primary key]
--   path varchar
--   httpMethod httpMethods
--   description varchar2
--   authType_id integer [ref :> AuthType.id]
--   project_id integer [ref : > Project.id]
--   createdBy integer [ref : > User.id]
-- }
