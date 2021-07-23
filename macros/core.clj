(ns core)

"""

 Likewise, the expressions representing the execution of
 functions and the use of control structures are also data
 structures! These data representations of functions and
 their executions represent a concept different from the
 way other programming languages operate.

 Typically, there’s a sharp distinction between data structures and functions of the
 language. In fact, most programming languages don’t even remotely
 describe the form of functions in their textual representations. With Clojure,
 there’s no distinction between the textual form and the actual form of a
 program. When a program is the data that composes the program, then you
 can write programs to write programs. This may seem like nonsense now,
 but as you’ll see throughout this chapter, it’s powerful.
"""


;; eval
(eval 42) ;=> 42

(eval '(list 1 2))
;=> (1 2)

(eval (list 1 2))
; java.lang.ClassCastException:
;  java.lang.Integer cannot be cast to clojure.lang.IFn

; the function symbol receives a string + and returns a symbol data
; type of  +
; the function list receives three arguments -- a symbol +, the integer 1,
; and the integer 2 -- and returns a list of these elements.
; the eval function receives a list data type of (+ 1 2), recognizes it as the functino call
; form, and executes the + function with the arguments 1 and 2, returning the integer 3
(eval (list (symbol "+") 1 2))
; => 3

;;
;;
(defn contextual-eval [ctx expr]
  (eval
    `(let [~@(mapcat (fn [[k v]] [k `'~v]) ctx)]
        ~expr)))

(contextual-eval '{a 1, b 2} '(+ a b))
; => 3
(contextual-eval '{a 1, b 2} '(let [b 1000] (+ a b)))
; => 1001


