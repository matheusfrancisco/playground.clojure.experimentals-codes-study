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


(comment
  (run-tests *ns*)
  
  )
