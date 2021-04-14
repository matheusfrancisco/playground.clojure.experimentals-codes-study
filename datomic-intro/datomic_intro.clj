(ns datomic-intro
  (:use [clojure.pprint])
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/test-matheus")
(pprint (d/create-database db-uri))
(pprint (d/connect db-uri))
(pprint (d/delete-database db-uri))