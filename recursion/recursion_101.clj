(ns recursion-101
  (:require
    [clojure.test :refer :all]))

(def ls0 [])
(def ls1 [1])
(def ls10 (range 10))

;;classical recursion
(defn length [ls]
  (if (empty? ls)
    0
    (+ 1 (length (rest ls)))))


(deftest test-length
  (testing "test list length"
    (is (= 0 (length nil)))
    (is (= 3 (length [1 2 3])))
    (is (= 1 (length ls1)))
    (is (= 10 (length ls10)))))


(defn map* [f ls]
  (if (empty? ls)
    ()
    ;;divid and conquer
    (cons
      (f (first ls)) (map* f (rest ls)))))


(deftest map-*
  (testing "map *"
    (is (= '() (map* inc [])))))

(map* inc (range 2)) ;=> (1 2)
(map* str (range 3)) ;=> ("0" "1" "2")


;; non-tail recursion
(defn filter* [p? ls]
  (if (empty? ls)
    ()
    (if (p? (first ls))
      (cons (first ls) (filter* p? (rest ls)))
      (filter* p? (rest ls)))))

(defn filter*-helper [p? ls acc]
  (if (empty? ls)
    acc
    (if (p? (first ls))
      (recur p? (rest ls) (conj acc (first ls)))
      (recur p? (rest ls) acc))))

;; tail recursion
(defn filter* [p? ls]
  (filter*-helper p? ls []))

(filter* even? (range 45))
(filter* nil? (range 45))
(filter* (complement nil?) (range 45))

(defn map*-helper [f ls acc]
  (if (empty? ls)
    acc
    (recur f (rest ls) (conj acc (f (first ls))))))

(defn map*-tail [f ls]
  (map*-helper f ls []))


(map*-tail str (range 3))

(defn length-helper [ls acc]
  (if (empty? ls)
    acc
    (recur (rest ls) (+ 1 acc))))

(defn length [ls]
  (length-helper ls 0))


#_(defn read-book [book]
  (if (empty? book)
    nil
    (do
      (read (first-page book))
      (recur (rest-of book)))))

(comment
  (run-tests *ns*))
