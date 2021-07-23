(ns sorted-set)

(sorted-set 2 3 4 5 3265 345235)
;=> #{2 3 4 5 3265 345235}

(sorted-set-by (comparator >) 3 4 5 5 6 66 6634 234)
;=> #{6634 234 66 6 5 4 3}

(sorted-set-by (fn [a b]
                 (compare a b)) :a :b :d :c)
;=> #{:a :b :c :d}

(sorted-set-by (fn [a b]
                 (compare (:name a) (:name b))) {:name "Xico"} {:name "Mat"})
;=> #{{:name "Mat"} {:name "Xico"}}
