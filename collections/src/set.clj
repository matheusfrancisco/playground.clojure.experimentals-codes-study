(ns set)

;;Sets

;;Literal syntax
;; =>  #{}

(def s #{1 2 3 :a :b})

(hash-set 1 2 3 4)
;#{1 2 3 4}

(set (for [x (range 10)]
       ;;
       x
       ))

(def a "abc")
(def ss #{a})
(identical? ss ss)
(identical? ss (conj ss "abc"))

(get ss "abc")

(identical? (get ss "abc") a)
;;true
;;true
(identical? (get ss (String. "abc")) a)
;;true

(= a (String. "abc"))
;true should be iqual but not identical

(identical? a (String. "abc"))

(count ss)
;;1
(disj ss "ll")
;;#{"abc"}
;#{}

(contains? ss "abc")
;;true

;;Multi-comparison

(defn vp? [name]
  (or (= name "John")
      (= name "Linda")
      (= name "June")
      ;;
      ))

(def vice-presidents #{"John" "Linda" "June" "Fred"})

(defn vp? [name]
  (contains? vice-presidents name))

(def attendance ["Eric" "John" "June" "Jane" "Laura"])
(filter vp? attendance)

(#{false}  false)
;; return false
(#{nil} nil)
;;nil
(contains? #{nil} nil)
;;true


(def activated-buttons (atom #{}))
(defn push! [button-id]
  (swap! activated-buttons conj button-id))

(push! :first-florr)