(ns ds.core)

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
