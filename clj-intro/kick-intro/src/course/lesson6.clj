(ns course.lesson6)

(def order {:backpack {:quantity 2, :price 80}
            :shirt    {:quantity 3, :price 40}})

(defn prints-value-and-returns-15
  [value]
  (println "value:" value)
  15)

(println (map prints-value-and-returns-15 order))

(defn prints-value-and-returns-15
  [value]
  (println "value:" (class value) value)
  15)

(println (map prints-value-and-returns-15 order))


(defn prints-value-and-returns-15
  [[key value]]
  (println key "&" value)
  15)

(println (map prints-value-and-returns-15 order))

(defn prints-value-and-returns-15
  [[key value]]
  (println key "&" value)
  value)

(println (map prints-value-and-returns-15 order))

(defn total-price-per-product
  [[key value]]
  (* (:quantity value) (:price value)))

(println (map total-price-per-product order))


(println (reduce + (map total-price-per-product order)))

(defn total-price-per-product
  [[_ value]]
  (* (:quantity value) (:price value)))

(defn order-total-price
  [order]
  (reduce + (map total-price-per-product order)))

(println (order-total-price order))

;THREAD LAST
(defn order-total-price
  [order]
  (->> order
       (map total-price-per-product)
       (reduce +)))

(println (order-total-price order))

(defn total-price-per-product
  [product]
  (* (:quantity product) (:price product)))

(defn order-total-price
  [order]
  (->> order
       vals
       (map total-price-per-product)
       (reduce +)))

(println (order-total-price order))



(def order {:backpack {:quantity 2, :price 80}
            :shirt    {:quantity 3, :price 40}
            :keychain {:quantity 1}})

(defn for-free?
  [item]
  (<= (get item :price 0) 0))

(println (filter for-free? (vals order)))

(defn for-free?
  [[_ item]]
  (<= (get item :price 0) 0))

(println (filter for-free? order))

(defn for-free?
  [item]
  (<= (get item :price 0) 0))

(println "filtering with anonymous function")
(println (filter (fn [[key value]] (for-free? value))  order))

(println "filtering with lambda")
(println (filter #(for-free? (second %)) order))

(defn paid?
  [item]
  (not (for-free? item)))

(println (paid? {:price 50}))
(println (paid? {:price 0}))


(comp not for-free?)
(println ((comp not for-free?) {:price 50}))
(println ((comp not for-free?) {:price 0}))

(def paid? (comp not for-free?))

(println (paid? {:price 50}))
(println (paid? {:price 0}))
