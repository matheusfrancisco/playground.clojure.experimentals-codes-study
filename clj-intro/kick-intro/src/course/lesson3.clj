(ns course.lesson3)

(defn discounted-price
  "Returns the original price with a 10% discount, if the original price is higher than 100"
  [original-price]
  (if (> original-price 100)
    (let [discount-rate (/ 10 100)
          discount (* original-price discount-rate)]
      (- original-price discount))
    original-price))

(println (discounted-price 1000))
(println (discounted-price 100))

(defn applies-discount?
  [original-price]
  (if (> original-price 100)
    true
    false))

;(println (must-apply-discount? 1000))
;(println (must-apply-discount? 100))

(defn discounted-price
  "Returns the original price with a 10% discount, if the original price is higher than 100"
  [original-price]
  (if (applies-discount? original-price)
    (let [discount-rate (/ 10 100)
          discount (* original-price discount-rate)]
      (- original-price discount))
    original-price))

(println (discounted-price 1000))
(println (discounted-price 100))

(defn applies-discount?
  [original-price]
  (println "invoking redefined fn")
  (if (> original-price 100)
    true))

(println (applies-discount? 1000))
(println (applies-discount? 100))
(println (discounted-price 1000))
(println (discounted-price 100))



(defn applies-discount?
  [original-price]
  (println "invoking function with WHEN")
  (when (> original-price 100)
    true))

(println (applies-discount? 1000))
(println (applies-discount? 100))
(println (discounted-price 1000))
(println (discounted-price 100))



(defn applies-discount?
  [original-price]
  (println "invoking function operation")
  (> original-price 100))

(println (applies-discount? 1000))
(println (applies-discount? 100))
(println (discounted-price 1000))
(println (discounted-price 100))


(defn more-expensive-than-100?
  [original-price]
  (> original-price 100))


(defn discounted-price
  "Returns the original price with a 10% discount, if the discount is being applied"
  [will-apply? original-price]
  (if (will-apply? original-price)
    (let [discount-rate (/ 10 100)
          discount (* original-price discount-rate)]
      (- original-price discount))
    original-price))

(println "testing new function")
(println (discounted-price more-expensive-than-100? 1000))
(println (discounted-price more-expensive-than-100? 100))

(fn [original-price] (> original-price 100))

(println "testing function with no name")
(println (discounted-price (fn [original-price] (> original-price 100)) 1000))
(println (discounted-price (fn [original-price] (> original-price 100)) 100))

(println "testing function with no name op")
(println (discounted-price (fn [op] (> op 100)) 1000))
(println (discounted-price (fn [op] (> op 100)) 100))

(println "testing function with no name and no fn")
(println (discounted-price #(> %1 100) 1000))
(println (discounted-price #(> %1 100) 100))

(println "testing function with no name and no fn and no nbr 1")
(println (discounted-price #(> % 100) 1000))
(println (discounted-price #(> % 100) 100))


(def more-expensive-than-100? (fn [original-price] (> original-price 100)))

(def more-expensive-than-100? #(> % 100))
(println "testing def instead of defn")
(println (discounted-price more-expensive-than-100? 1000))
(println (discounted-price more-expensive-than-100? 100))