(ns list)
;empty list
'()
'(1 2 3 4)

'(:a :b :c)
;; => (:a :b :c)

'(1 (+ 1 2) 3 4)

(list 1 2 3)

(cons 0 (list 1 2 3))
;;list insert in beginning

(count (cons 0 (cons 1 (cons 2 (cons 3 nil)))))
;;=> 4
(cons 2 nil)
;;=> (2)

(def a (list 1 2 3))
(identical? a a)
;=> true

(identical? a (seq a))
;;true

(list? a)
(seq? a)
;;true
(seq? [1 3])
;;false

(into () [1 2 3])
;; => (3 2 1)

(defn f
  ([]
   ;;
   )
  ([x]
   ;;;
   )
  ([x & xs]
   (let [xs (cons x xs)]
     (reduce (fn [a b])
             [] xs))))