(ns lookup-by-key)

;; Lookup by key

;; Hash Map (or Sorted Map)

(def rolodex {"Eric" "330-333-33"
              "Jane" "233-331-333"
              "Joe" "932-333-333"})

(get rolodex "Jane")
(get rolodex "Fred" :unknown)

;; Vector

;;x , y , z
(def coordinates [10.2, 4.5, 8.9])
(def x (get coordinates 0))
(def y (get coordinates 1))
(def z (get coordinates 2))
(str x "; " y "; " z)
;;=> "10.2; 4.5; 8.9"


;; Set

(def friends #{"Eric" "J" "Xico"})
(get friends "Eric")

(def t [1 2])


(def values #{t [3 4]})
(get values '(1 2))
;=> [1 2]

(identical? t t)
;=> true

(identical? t (get values ' (1 2)))
;=> true