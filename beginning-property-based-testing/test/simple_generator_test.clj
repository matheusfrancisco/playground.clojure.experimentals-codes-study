(ns test.simple-generator-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.generators :as gen]))

;;building simple generators
;;such-that

;;like filter
(gen/sample (gen/such-that even? gen/nat))
(gen/sample (gen/such-that odd? gen/nat))
(gen/sample (gen/such-that neg? gen/small-integer))
(gen/sample (gen/such-that #(zero? (mod % 100)) gen/nat))
(gen/sample (gen/such-that #(re-matches #"[abcd]+" %) gen/string))

;;fmap like map will apply some  function to every value
;;you should prefere fmap
(gen/sample (gen/fmap #(* 2 %) gen/nat))
(gen/sample (gen/fmap #(inc (* 2 %)) gen/nat))
;=> (1 1 1 1 1 9 5 13 3 5)

(gen/sample (gen/fmap #(- %)
                      (gen/fmap inc gen/nat)))

(gen/sample (->> gen/nat
                 (gen/fmap inc)
                 (gen/fmap -)))
;=> (-1 -2 -1 -1 -2 -6 -4 -5 -6 -7)
(gen/sample (gen/fmap #(* 100 %) gen/nat))

(gen/sample (gen/fmap #(apply str %)
                      (gen/not-empty
                        (gen/vector (gen/elements [\a \b \c \d])))))
;=> ("c" "a" "a" "a" "aa" "bc" "cbbd" "ddcdcd" "bdccba" "cbcc")