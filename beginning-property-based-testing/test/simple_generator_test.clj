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

;;bind
(gen/sample (gen/bind gen/nat #(gen/vector gen/nat %)))
;=> ([] [1] [1 1] [0 0] [] [] [2 5 6] [] [7 2 6 2 1 6 1 5] [3 9 2 3])

(gen/sample (gen/tuple
              (gen/fmap inc gen/nat)
              (gen/fmap inc gen/nat)))
;=> ([1 1] [1 2] [1 3] [1 2] [2 1] [4 4] [3 1] [1 6] [5 4] [7 10])

(gen/sample (gen/bind (gen/tuple
                        (gen/fmap inc gen/nat)
                        (gen/fmap inc gen/nat))
                      (fn [[n m]]
                        (gen/vector
                          (gen/vector gen/small-integer m)
                          n))))
;=>
;([[0]]
; [[1 0]]
; [[-2 -1 -1] [2 1 -2]]
; [[1 0 -3] [1 -1 1] [0 2 1] [1 -2 0]]
; [[-2 1]]
; [[1 2 3]]
; [[-6 0 -1 -3 0] [3 5 2 0 -5] [-5 -1 -2 4 2] [1 -2 4 -2 -3] [2 5 -4 -5 -5] [-3 2 1 2 -2] [-2 2 -6 6 1]]
; [[5 -4 6] [-2 3 -5] [5 4 1] [-4 6 6] [4 3 -7]]
; [[6 5 -5 -4] [-8 -4 5 8] [-1 1 -4 2] [-8 0 -8 5] [0 -7 -6 -3] [0 -8 -4 -2] [7 -5 0 5] [5 4 3 -4] [6 8 5 8]]
; [[-1 2 -9 4 9] [8 9 -3 -4 -1] [7 -6 -2 9 7]])

;;let is a macro
(gen/sample (gen/let [len gen/nat]
                     (gen/vector gen/nat len)))
;=> ([] [0] [] [] [] [4 2 4 5 4] [0 0 4] [4] [7 5 5 1 2 7 4 1] [3 4 3 6 3 7 9 8])
(gen/sample (gen/let [[n m] (gen/tuple (gen/fmap inc gen/nat)
                                       (gen/fmap inc gen/nat))]
                     (gen/vector
                       (gen/vector gen/small-integer m)
                       n)))
;=>
;([[0]]
; [[0 0] [1 1]]
; [[-1 -2 2] [1 0 0]]
; [[3] [-3]]
; [[4 -3 -4 4 4] [3 1 -1 4 -3] [3 3 3 -1 2]]
; [[0 -4 0 -2] [4 -5 5 -5] [-5 3 -4 3]]
; [[-2 3 4 2 -4] [-6 -3 1 2 -5] [6 -5 1 5 -2] [2 3 1 1 -2]]
; [[-7 6 -7 -2 -2] [7 -1 -6 -5 -7] [-1 -1 -1 7 0] [3 4 5 6 6] [3 -5 -6 -6 3]]
; [[5] [2] [2] [-1] [-3] [8] [0] [-1] [0]]
; [[8 -1 -3 5] [-9 7 6 -9] [-3 -6 5 -6] [-6 2 9 2] [0 1 1 2]])
;;avoid bind and let with strings