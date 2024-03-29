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

(defspec idempotent
  (prop/for-all [s gen/string-ascii]
                (= (str/upper-case s)
                   (str/upper-case (str/upper-case s)))))


(gen/sample gen/string-ascii)
;;=> ("" "" "" "8#" ")x&m" "E\\D" "*\\O2" "GsgSG," "Nn?HW.oK" "/")

;;Number
;;;Integers
(gen/sample gen/nat)
;;=> (0 0 2 0 1 3 6 3 3 8)

(drop 90 (gen/sample gen/nat 100))

(gen/sample gen/small-integer)
;=> (0 0 -1 -2 1 -2 2 -5 -4 7)
(gen/sample gen/large-integer)
;=> (-1 -1 -2 1 0 0 -15 -1 0 -4)

(gen/sample (gen/large-integer* {:min 10 :max 100000}) 100)
(gen/sample (gen/choose 0 10000))

;;Ratios
(gen/sample gen/ratio)
;=> (0 0 2/3 2 -1 3/5 -6 5/8 -8/5 9/10)
(gen/sample gen/big-ratio)
;=> (0N 2/37 354/893 133/15250 7274105/12 1/1878755 2/5 402856573955N 10930052165303/3 188/980076457581)

;;Double
(gen/sample gen/double)
;=> (-0.5 -3.0 3.0 -2.0 -0.5 -2.6875 0.9296875 -1.6875 -4.5 6.0)

(gen/sample (gen/double* {:min -1.0 :max 1.0 :infinite? false :NaN? false}))
;=> (0.5 -0.5 1.0 1.0 1.0 -1.0 -0.71875 -1.0 0.5859375 0.5)

;;BigInts
(gen/sample gen/size-bounded-bigint)
;=> (0 0N -16N -12826N 145768N -63811N 25458342N -1587476332416N 34316430705442N -47682963N)

(gen/sample gen/size-bounded-bigint)
;=> (0N 0N 80N 12N 9N -18N -11N -26N 10889N -2298055N)

;;Characters and strings

;;Characters
(gen/sample gen/char)
;=> (\­ \O \ \, \Z \¸ \ \v \9 \Î)
(gen/sample gen/char-ascii)
;;printable
;=> (\P \I \u \$ \= \` \, \P \\ \u)
(gen/sample gen/char-alphanumeric)
;=> (\d \c \A \l \2 \l \1 \U \5 \U)
(gen/sample gen/char-alpha)
;=> (\H \r \R \b \X \z \B \D \M \X)

;;Strings
(gen/sample gen/string)
;=> ("" "Ñ" "û" ";z" "" "L×}" "Sã a:" "§\r" "" "PoI\"Ü")
(gen/sample gen/string-ascii)
;=> ("" "9" "Y" "" "" "u3" "*O" "A" "," "c6;?#t~p")
(gen/sample gen/string-alphanumeric)
;=> ("" "" "7g" "Cx" "w" "" "u954d" "" "L42AUm" "R2wbOBVwp")

;;Keywords
(gen/sample gen/keyword)
;=> (:! :B :qP :o :*11 :p :l :J*+2 :_e :u)
(gen/sample gen/keyword-ns)
;=> (:O/s :j/pg :?X/p :Jr/t1 :.!/M** :!fc/u :wxZ/k :dX:4/UE.6 :n/U :yR/?4)

;;Symbols
(gen/sample gen/symbol)
;=> (! d7 u+ j j+ *y6 r ?U. f*?7 N1*+)
(gen/sample gen/symbol-ns)
;=> (E/t _/C+ +/i F/+. *K5/d9* wB0/nn6 l/- mL/J_ ?q/y2Hw -!*5/-T?)

;;Uuid
(gen/sample gen/uuid)
;(#uuid"dabdc556-f9b3-45c3-bf43-987c774c0933"
; #uuid"6774333f-73df-421a-8bc1-69f33c95d142"
; #uuid"9c444c4d-acf4-4fed-a33f-fa0f81534acf"
; #uuid"0a65e63e-a1fc-4cb6-a68c-29892475be6d"
; #uuid"904ee2e6-1042-4ebd-9aaa-1d4aa1b72440"
; #uuid"d8466e39-5565-45a6-8fd5-b944862e1c65"
; #uuid"8ab91c78-2475-4f4d-91ab-7e6c7232ada3"
; #uuid"1b929a4e-2ffc-4d64-b604-536b7c0ab68b"
; #uuid"e0e4b820-a583-46da-bddb-9f70a0d17080"
; #uuid"3a65ed3e-6998-429e-b536-02105e10c4d3")


;;
(gen/sample gen/boolean)
;=>=> (false true true false true true false true true true)

;;Collections
;;Vector

(gen/sample (gen/vector gen/nat 2 4))
;=> ([0 0 0] [1 0] [1 0] [0 0 3 2] [4 2 1] [4 1 2] [3 0 6 5] [1 5 3 5] [5 8 7 7] [9 9 3 0])
(gen/sample (gen/vector-distinct gen/nat))
;=> ([] [] [0] [0 1 3] [3 2 0 4] [3 7 5 8 1] [4] [8 0 6 3] [8 3 5 2 6 1] [9])

;;List
(gen/sample (gen/list gen/boolean))
;;=>
;(()
; (true)
; (true)
; (false)
; (false)
; (true true false true)
; (true false false false)
; (false false true true false true)
; (true false true false true false true)
; (false false true false true true false true false))

;;Set
(gen/sample (gen/set gen/nat))
;=> (#{} #{} #{} #{0 2} #{0 1 3 2} #{} #{0 2} #{} #{7} #{})
(gen/sample (gen/sorted-set gen/nat))
;=> (#{} #{} #{} #{0} #{} #{1 5} #{0 4 5 6} #{6 7} #{2 5 7 8 9} #{0 3 4 5})


;;Map
(gen/sample (gen/map gen/keyword gen/string-ascii))
;=>
;({}
; {:_ ""}
; {:_+ "'", :O! ""}
; {:!B "{"}
; {}
; {}
; {:? ""}
; {:c "I", :fMN "", :+.? "|rR", :*l "`l8", :pE9! "&JmE", :*0 "\\0?.", :+Y2 "6)"}
; {:d?_ "", :l ">c"}
; {:vm "e-|iJN", :+!F "8n07H6Ni", :O! "=B", :-xMG "H,+"})

;;Tuple
(gen/sample (gen/tuple gen/nat gen/string-alphanumeric gen/boolean))
;=>
;([0 "" false]
; [1 "i" true]
; [2 "0" false]
; [2 "qE" true]
; [2 "8Lz6" false]
; [3 "y4" true]
; [1 "57X" true]
; [6 "bw0" true]
; [7 "jmU" false]
; [8 "8wqj" true])

;;Entity
{:first-name "Eric"
 :last-name "Normand"
 :age 38}

(gen/sample (gen/hash-map :first-name gen/string-alphanumeric
                          :last-name gen/string-alphanumeric
                          :age gen/nat))
;=>
;({:first-name "", :last-name "", :age 0}
; {:first-name "", :last-name "", :age 1}
; {:first-name "N", :last-name "PO", :age 0}
; {:first-name "", :last-name "47O", :age 3}
; {:first-name "g6i3", :last-name "s", :age 0}
; {:first-name "C13A", :last-name "qawg9", :age 4}
; {:first-name "59297u", :last-name "", :age 1}
; {:first-name "vT07", :last-name "", :age 0}
; {:first-name "", :last-name "2", :age 4}
; {:first-name "tNVk1", :last-name "T", :age 2})

;;Not-empty
(gen/sample  (gen/vector gen/boolean))
(gen/sample (gen/not-empty (gen/vector gen/boolean)))
;;
;;example nesting
(gen/sample (gen/not-empty (gen/vector (gen/vector gen/boolean))))

;;Random selection
(gen/sample (gen/elements [1 2 3]))
;=> (3 3 3 1 2 1 2 2 3 2)
(gen/sample (gen/return 1))
(gen/sample (gen/shuffle [1 2 3 4 5]))
(gen/sample (gen/one-of [gen/string-alphanumeric
                         gen/nat
                         (gen/return nil)
                         (gen/vector gen/nat)]))

(gen/sample (gen/choose 0 100))

;;recursive
(gen/sample (gen/recursive-gen gen/vector gen/boolean))
;=> ([] [true] [true] [] false false [] [[] []] true [false true true])
(gen/sample (gen/frequency [[10 gen/nat]
                            [1 (gen/return nil)]
                            [2 gen/string-alphanumeric]]))

(comment
  (run-tests *ns*)

  )
;(gen/sample gen/string 100)