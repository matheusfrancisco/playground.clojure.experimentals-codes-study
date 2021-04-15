(ns vector)


(def v [1 2 3])
(vec (list 1 2 3 4))
(vec {:a 1 :b 2}) ;=> [[:a 1][:b 2]]

;vectors keeping the order
;;conj add to the end
(conj [1 2 3 4] :a)

;; vec to list (sequence)
(seq [1 2 3 4])

(def v [:a :b :c :e :f :g])
(get v 3)

(get v 1000)
;;return nil

(get v -1)
;; => nil

(get v :fff)
;; => nil

(nth v 3)
;; => :e

(nth v 100)
;; => keeping duplicates

(count v)

(subvec v 0 2)
(conj (subvec v 0 3) :x)

(def numbers [1 2 3 4])
;;contains? means is the index in the collection?
(contains? numbers 1)

(contains? numbers 8)
;;false
(loop [i 0]
  (if (contains? numbers i)
    (do
      (println (get numbers i))
      (recur (inc i)))
    (println "Done.")))

(conj v :xx)

(pop v)
;;pop last item
(peek v)
;;peek the last item

([:c :sc] 0)

([:c :sc] 10)
;;expection

(map #(nth v %) [1 2 3 4 5])
(map v [1 2 3 4 5])


