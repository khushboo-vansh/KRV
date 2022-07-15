CREATE TABLE IF NOT EXISTS "products" (
    "object_id" BIGSERIAL PRIMARY KEY,
    "product_name" varchar(20) ,
    "product_code" varchar(5) NOT NULL,      
    "brand_name" varchar(10) ,
    "created_at" timestamp,
  	"created_by" varchar(10),
  	"last_modified_at" timestamp,
    "last_modified_by" varchar(10),
  	"active" boolean DEFAULT true
);

CREATE TABLE IF NOT EXISTS "users" (
  "object_id" BIGSERIAL PRIMARY KEY,
  "role_id" bigint NOT NULL,
  "user_name" varchar(50) UNIQUE NOT NULL,
  "firstname" varchar(20) NOT NULL,
  "lastname" varchar(40) NOT NULL,
  "email" varchar(40) NOT NULL,
  "phone_number" varchar(16),
  "created_at" timestamp,
  "created_by" varchar(20),
  "last_modified_at" timestamp,
  "last_modified_by" varchar(20),
  "status" varchar(20) NOT NULL,
   CONSTRAINT chk_status CHECK ("status" IN ('active', 'inactive', 'blocked')),
   CONSTRAINT unq_username UNIQUE ("user_name")
);

CREATE TABLE IF NOT EXISTS "users_role" (
  "object_id" BIGSERIAL PRIMARY KEY,
  "name" varchar(50) UNIQUE NOT NULL
);

