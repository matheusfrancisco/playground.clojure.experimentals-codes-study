(ns db
  (:use [clojure.pprint])
  (:require [datomic.api :as d]))


(def db-uri "datomic:dev://localhost:4334/ecommerce")

(defn open-connection []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn delete-database []
  (d/delete-database db-uri))

;Products
; name String ==> Expensive Computer
; slug String ==> expensive_computer
; price Float 1 ==> 3500.18

; entity_id attribute_name attribute_value
; 15 :product/name Expensive Computer
; 15 :product/slug /expensive_computer
; 15 :product/price 3500.10
; 17 :product/name Fancy MObile
; 17 :product/slug /fancy_mobile
; 17 :product/price 8888.88

(def schema [{:db/ident       :product/name
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "The name of the Product in the Catalog"}
             {:db/ident       :product/slug
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "The uri fragment required to access a Product"}
             {:db/ident       :product/price
              :db/valueType   :db.type/bigdec
              :db/cardinality :db.cardinality/one
              :db/doc         "The price of product"}])
