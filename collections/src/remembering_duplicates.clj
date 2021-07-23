(ns remembering-duplicates)

;;Remember duplicates

;;List

(conj () 1 2 3 1)
;=> (1 3 2 1)
;;Vector
(conj [] 1 2 3 1)
;=> [1 2 3 1]

;;Queue
(seq (conj clojure.lang.PersistentQueue/EMPTY 1 2 2 22))
;=> (1 2 2 22)

;;Forget duplicates
;;set

(conj #{} 1 2 3 4 5 6 1 1 1 1 )
;;=> #{1 4 6 3 2 5}

(conj {} [1 :a] [2 :a] [3 :a] [1 :e] [2 :c])
;=> {1 :e, 2 :c, 3 :a}

(conj #{} (list 1 2 3) [1 2 3])
;=> #{(1 2 3)}

(conj #{} [1 2 3] (list 1 2 3))
;=> #{[1 2 3]}

(conj #{} (with-meta [1] {:a 1}) [1])
(meta (first (conj #{} (with-meta [1] {:a 1}) [1])))
;=> {:a 1}


(def elevator-calls (atom {}))

(defn press! [floor direction]
  (swap! elevator-calls conj [[floor direction] true]))

(press! 3 :up)
(press! 4 :up)