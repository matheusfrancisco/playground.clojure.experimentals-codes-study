(ns course.lesson2)

(println "oi")
(println "Welcome to the storage system")

(def total-of-products 15)
(println "Total" total-of-products)


(def storage ["Backpack", "Shirt"])

(conj storage "Chair")

(defn print-message []
  (println "-----------------------")
  (println "Welcome to the storage!"))

(defn discounted-price
  "Returns the discount price, which is 90% of the original price"
  [original-price]
  (* original-price 0.9))

(defn apply-discount [original-price]
  (* original-price 0.9))

(println)
(apply-discount 100)
(println (apply-discount 1000))

(> 500 100)
(< 500 100)

(if (> 50 100)
  (println "greater")
  (println "less than or equal to"))

(defn discounted-price
  "Returns the original price with a 10% discount, if the original price is higher than 100"
  [original-price]
  (if (> original-price 100)
    (let [discount-rate (/ 10 100)
          discount (* original-price discount-rate)]
      (- original-price discount))
    original-price))

(discounted-price 1000)
(discounted-price 100)

