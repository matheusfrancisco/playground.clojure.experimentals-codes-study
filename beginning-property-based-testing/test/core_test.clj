(ns test.core_test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.properties :as prop]))


;(str/upper-case "abcd")
(comment

  (defspec always-passes 100
    (prop/for-all []
                  true))

  (defspec always-fails 100
    (prop/for-all []
                  false)))

(comment
  (remove-ns 'core_test))

(defspec
  )