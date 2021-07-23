(ns datomic-intro
  (:use [clojure.pprint])
  (:require [datomic.api :as d]
            [db :as edb]
            [model :as model]))

(def conn (edb/open-connection))

(d/transact conn edb/schema)

(def computer (model/new-product
                "Expensive Computer"
                "/expensive_computer"
                3500.10))

(d/transact conn [computer])