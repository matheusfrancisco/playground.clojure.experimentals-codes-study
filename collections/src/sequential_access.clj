(ns sequential-access)

;;order doesn't matters

(def l (list 1 2 3 4))
(seq l)
;=> (1 2 3 4)
(conj l :A)
;=> (:A 1 2 3 4)


(into l [12 20 3 44 45 44])

;=> (44 45 44 3 20 12 1 2 3 4)
(conj l :A :B :C)
;=> (:C :B :A 1 2 3 4)

;; vector is much better to collect stuffs at time and process latter
(def v [1 2 3])

(conj v :A)
(seq v)

;;queue
(def q clojure.lang.PersistentQueue/EMPTY)
(seq (conj q 1 2 3 4))

;; sorted-order
;; you have sorted hash map
(def  shm (sorted-map  :a 1 :b 3))
(seq shm)

(seq (sorted-map "zA" 1 "yB" 2))

(sorted-map-by  (fn [a b]
                  (compare (clojure.string/join (rest a ))
                           (clojure.string/join (rest b)))) "zA" 1 "yB" 2)
;; and sorted set




(def todos (atom []))

(defn add-todo! [item]
  (swap! todos conj item))

(comment
  (do
    (add-todo! "Buy kitten")
    (add-todo! "Buy cat food")
    (add-todo! "Freed kitten")
    (add-todo! "Buy a new kitten")

    (seq @todos))
  )


(defn priority-order [a b]
  (compare (:priority a) (:priority b)))

(def todos (atom #{}))

(defn add-todo! [item]
  (swap! todos conj item))

(comment
  (do
    (add-todo! "Buy kitten")
    (add-todo! "Buy cat food")
    (add-todo! "Freed kitten")
    (add-todo! "Buy a new kitten")
    ;;alphabetical order
    (seq @todos))
  )

(do
  (add-todo! {:priority 10 :name "Buy kitten"})
  (add-todo! {:priority 2 :name "Buy cat food"})
  (add-todo! {:priority 1 :name "Freed kitten"})
  (add-todo! {:priority 3 :name "Buy a new kitten"})
  ;;alphabetical order
  (seq @todos))

