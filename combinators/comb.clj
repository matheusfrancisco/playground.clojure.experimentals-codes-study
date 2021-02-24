(ns comb)

(identity 10)
(identity 200)
(identity :some-keyword)

(defn identity [x]
  x)

(filter identity [1 2 nil 9 11 14])

;;contantly
(def always5 (constantly 5))
(always5 5)
(always5 10)
(always5 100)
(always5 nil)

(defn constantly* [x]
  (fn [& _]
    x))

(update [0 1 2 3] 3 (constantly 75))
(update [0 1 2 3] 3 (fn [& _] 75))

(map (constantly "hello") (range 20))

;;complement

(def non-pos? (complement pos?))

(non-pos? 1)

(def non-neg? (complement neg?))

(non-neg? 10)

(def non-zero? (complement zero?))

(non-zero? 10)

;(def non-zero? (fn [x] (not (zero? x))))

(defn complement* [f]
  (fn [& args]
    (not (apply f args))))

(filter (complement neg?) [1 -5 2 0 1 1])

;;partial
(def add3 (partial + 3))

(add3 5) ; =>8
(add3 10) ;=> 13
(add3 10 10) ;=> 23

(def one-over (partial / 1))
(one-over 5)

(defn partial* [f & xs]
  (fn [& ys]
    (apply f (concat xs ys))))

(map (partial / 1) [1 2 3 4 5])
(map #(/ % 2) [1 2 3 4 5])

;;fnil

(def inc-default0 (fnil inc 0))
(inc-default0 0)
(inc-default0 1)
(inc-default0 nil)

(defn inc-default0 [x]
  (if (nil? x)
    (inc 0)
    (inc x)))

(defn fnil* [f default]
  (fn [x & xs]
    (if (nil? x)
      (apply f default xs)
      (apply f x xs))))

(def words (clojure.string/split "Some of these words are repeated. Some are not" #"\W"))

;;count words
(reduce (fn [counts word]
          (update counts word (fnil inc 0)))
        {}
        words)


;;comp -- function composition

(def person {:name "Luke Sky"
             :address {:street "22 Skywalker Way"
                       :planet "Tatouine"
                       :postal-code "887766a"}})

(defn postal-code [person]
  (:postal-code (:address person)))

(postal-code person)

((comp :postal-code :address) person)

(defn comp* [f g]
  (fn [x]
    (f (g x))))

(map (comp* :planet :address) [person])

(def nodes [{:name :a  :children [:b :c]} {:name :b} {:name :c :children []}])

(filter (comp empty? :children) nodes)

(def complement* (partial comp not))

((complement* zero?) 10)

(((partial comp not) zero?) 20)

((comp not zero?) 10)

(defn partial* [f & xs]
  (fn [& ys]
    (apply f (concat xs ys))))

;;; juxt
(def person {:first "Matheus"
             :last "Machado"
             :middle "Francisco B"})

(def first-and-last (juxt :first :last))

(first-and-last person)

(defn juxt* [f g]
  (fn [x]
    [(f x) (g x)]))

(defn juxt* [& fs]
  (fn [x]
    (mapv (fn [f] (f x)) fs)))

(def first-and-last (juxt* :first :last))
(first-and-last person)
;["Matheus" "Machado"]

(into {} (map (juxt identity str) (range  10)))
;{0 "0", 7 "7", 1 "1", 4 "4", 6 "6", 3 "3", 2 "2", 9 "9", 5 "5", 8 "8"}

;;apply

(apply + [1 2]) ;; (+ 1 2)

(apply + [1])

(defn apply* [f args]
  (if (seq args)
    (recur (partial f (first args)) (rest args))
    (f)))

(defn sum [numbers]
  (apply + numbers))

(sum [1 2 3 4])
(+ 1 2 3 4)
