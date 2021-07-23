(ns src.ds.core)

;;parameters
(defn f [a b c d]
  :body)

(f 1 2 3 4)

(defn f [a b c d & rst]
  :body
  (prn rst))

(f 1 2 3 4 2 3 4 5 6)
; conjure/eval | (f 1 2 3 4 2 3 4 5 6)
; conjure/out | (2 3 4 5 6)

(defn f [a b [c d & lrst :as ls] & rst]
  :body
  (prn ls)
  (prn c d)
  (prn rst))

(f 1 2 [3 4 1 23 4] 2 3 4 5)
; conjure/eval | (f 1 2 [3 4 1 23 4] 2 3 4 5)
; conjure/out | [3 4 1 23 4]
; conjure/out | 3 4
; conjure/out | (2 3 4 5)

;;video-3

(defn f-map [d {:keys [b a c] :as m} & rst]
  (prn m)
  (prn d)
  (prn b c a)
  (prn rst))

(f-map 1 {:a 2 :b 3 :c 4} 3 4 5)


(defn print-formatted-name [person]
  (let [first-name (:first-name person)
        last-name (:last-name person)]
    (println (str first-name " " last-name))))

(defn print-formatted-name [{:keys [first-name last-name]
                             :or {last-name "Xicao"}}]
  (println (str first-name " " last-name)))

(print-formatted-name {:first-name "Matheus" :last-name "Machado"})
(print-formatted-name {:first-name "Matheus" })



(defn f-map [a {a2 :a
                :keys [b c d]
                :strs [l]
                :syms [s]
                :or {d 10}
                :as _m} & rst]
  (prn a)
  (prn a2)
  (prn b)
  (prn c)
  (prn d)
  (prn l, s)
  (prn rst))

(f-map 1 {:a 2 :b 3 :c 4 "l" 10 's 3} 3 4 5)

