(ns test.pure-functions-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.generators :as gen]
            [src.pure-functions :as sut]))

;; Testing pure functions

;; Main challenge: Cover the entire desired behavior

;; Main techinique : adversarial

;;reverse
(defspec reverse-inverse-of-self 100
  (prop/for-all [ls (gen/vector gen/nat)]
    (= ls (sut/reverse (sut/reverse ls)))))

(defspec reverse-recursive 100
  (prop/for-all [l1 (gen/vector gen/nat)
                 l2 (gen/vector gen/nat)]
    (= (concat
         (sut/reverse l2)
         (sut/reverse l1))
       (sut/reverse (concat l1 l2)))))