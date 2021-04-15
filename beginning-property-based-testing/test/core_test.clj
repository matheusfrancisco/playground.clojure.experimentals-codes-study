(ns test.core_test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.generators :as gen]))


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

(defspec length-doesnt-change
  (prop/for-all [s gen/string-ascii]
    (= (count s) (count (str/upper-case s)))))


(defspec everythin-uppercased
  (prop/for-all [s gen/string-ascii]
                (every? #(if (Character/isLetter %)
                           (Character/isUpperCase %)
                           true)
                        (str/upper-case s))))


(comment
  (run-tests *ns*)

  )
;(gen/sample gen/string 100)