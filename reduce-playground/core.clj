(ns core)


(defn reduce* [f i coll]
  (if (empty? coll)
    i
    (let [[fst & rst] coll]
      (recur f (f i fst) rst)))

(reduce* inc [1 2 3])

(defn map* [f ls]
  (reduce (fn [res v]
            (conj res (f v))
            [] ls)))

(map* inc  [1 2 3 4])


(defn filter* [f ls]
  (reduce (fn [res v]
            (if (f v)
              (conj res v)
              v)) [] ls))

(filter* even? [1 2 3 4])


(reduce-kv)
