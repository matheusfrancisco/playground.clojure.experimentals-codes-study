(ns queue)

(def queue clojure.lang.PersistentQueue/EMPTY)

(seq (conj queue 1 2 3))

(def queue (atom clojure.lang.PersistentQueue/EMPTY))
(swap! queue conj 2)

(swap! queue conj 2)
(seq @queue)

(defn my-pop []
  (let [[old _] (swap-vals! queue pop)]
    (peek old)))

(swap! queue conj :a)
(swap! queue conj :b)
(swap! queue conj :c)
(swap! queue conj :d)

(seq @queue)

(my-pop)
;a
;b
;c
;d

;;Ordered
;;Duplicates
;;Counts
;; sequential
(= () clojure.lang.PersistentQueue/EMPTY [])