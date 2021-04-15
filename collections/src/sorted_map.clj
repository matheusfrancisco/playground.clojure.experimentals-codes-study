(ns sorted-map)

(sorted-map :a 2 :c 3 :b 1)

(sorted-map-by (comparator <) 0 :a 1 :b 2 :c)
;;=> {0 :a, 1 :b, 2 :c}
(sorted-map-by (comparator >) 0 :a 1 :b 2 :c)
;;=> {2 :c, 1 :b, 0 :a}

(comparator >)

(compare "a" "b")
;=>-1
(compare "a" "b")
;=>0
(compare "b" "b")
;=>0

#(- (compare %1 %2))

;;negate the value
(fn [a b] (- (compare a b)))

(defn sort-by-name [a b]
  (compare (:name a ) (:name b)))

(sorted-map-by sort-by-name {:name "xico"} 1 {:name "K"} 2 {:name "An"} 3)


(into (sorted-map) {:a 1 :c 3 :b 2 :z 3 :d 1})
;=> {:a 1, :b 2, :c 3, :d 1, :z 3}