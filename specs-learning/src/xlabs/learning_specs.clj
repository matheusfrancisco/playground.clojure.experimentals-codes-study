(ns xlabs.learning_specs
  (:require [clojure.spec.alpha :as spec]))

;; Predicates
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Does a function call with the value return true or false?
;; Any existing Clojure function that takes a single argument
;; and returns a truthy value is a valid predicate spec.

(odd? 1)
(string? "1")
(int? 2.3)
(type 2.3)

;;Does a value conform to a spec
;;conform takes two argumentos
;; -spec
;; value

(spec/conform odd? 102)
;=> :clojure.spec.alpha/invalid
(spec/conform even? 102)
;=> 102

(spec/conform integer? 1)
;=>1
(spec/conform seq? 1)
;:clojure.spec.alpha/invalid
(spec/conform seq? [1 2 3])
;=> :clojure.spec.alpha/invalid

(spec/conform seq? (range 10))
;=> (0 1 2 3 4 5 6 7 8 9)

;is the value valid?
;spec/valid also checks a value against a specification
;returing true or false
;rather than :clojure.spec.alpha/invalid


(spec/valid? even? 180)
;=> true
(spec/valid? (fn [value]  (> value 100000)) 20000)
;=> false
(spec/valid? #(> % 10000) 200000)
;=> true
(spec/conform #(> % 10000) 20000)
;=> 20000


;;literal values
(spec/valid? #{:club :diamong :heart :spade} :club)
;=>true
(spec/valid? #{:club :diamong :heart :spade} 24)
;=>false


;;;experimenting with card game decks and spec
;;representing different aspects of card game decks

;;suits from different regions are called by different names
;;there are 4 suits in a card decks

(spec/def ::suits-frenc #{:hearts :tiles :clovers :pikes})
(spec/def ::suits-german #{:hearts :bells :acorns :leaves})
(spec/def ::suits-spanish #{:cups :coins :clubs :swords})
(spec/def ::suits-italian #{:cups :coins :clubs :swords})
(spec/def ::suits-swiss-german #{:roses :bells :acorns :shields})


(spec/def ::rank #{:ace 2 3 4 5 6 7 8 9 10 :jack :queen :king})
(into #{:ace :jack :queen :king} (range 2 11))


(spec/def ::meaning-of-life
  (spec/and int?
            even?
            #(= 42 %)))

(spec/def ::meaning-of-life-int-or-str
  (spec/or :integer #(= 42 %)
           :string #(= "forty two" %)))

(spec/conform ::meaning-of-life-int-or-str 42)
;=>=> [:integer 42]
(spec/conform ::meaning-of-life-int-or-str "forty two")
;=> [:string "forty two"]

(spec/explain ::meaning-of-life-int-or-str 43)
;43 - failed: (= 42 %) at: [:integer] spec: :xlabs.learning_specs/meaning-of-life-int-or-str
;43 - failed: (= "forty two" %) at: [:string] spec: :xlabs.learning_specs/meaning-of-life-int-or-str
;=> nil

(spec/explain-data ::meaning-of-life 24)
;#:clojure.spec.alpha{:problems [{:path [],
;                                 :pred (clojure.core/fn [%] (clojure.core/= 42 %)),
;                                 :val 24,
;                                 :via [:xlabs.learning_specs/meaning-of-life],
;                                 :in []}],
;                     :spec :xlabs.learning_specs/meaning-of-life,
;                     :value 24}


;; Define a spec for an online back account
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Designing a spec outside-in

;; Users are referred to as account-holders, so lets define that specification
;; each account holder has mandatory information

(spec/def ::account-holder
  (spec/keys :req [::account-id ::first-name ::last-name ::email-address ::home-address ::social-secuirty-id]
             :opt [::accounts-associated]))

(spec/def ::account-id uuid?)
(spec/def ::first-name string?)
(spec/def ::last-name string?)

(spec/def ::email-address
  (spec/and string?
            #(re-matches #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,63}$"
                         %)))



;; These specs are not very useful - refactor
(spec/def ::home-address string?)
(spec/def ::social-secuirty-id string?)

;; NOTE: https://github.com/nikortel/ssn is a library for
;; social-security number validation and generation via spec

;; Optionally the account holder can have accounts associated to them.
;; A bank account has multiple attributes also
;; using spec/keys we can say all associated accounts must be a bank accounts

(spec/def ::accounts-associated
  (spec/keys :req [::bank-account]))


;; Define what makes up a bank account, for now there is only one type of account.

(spec/def ::bank-account
  (spec/keys :req [::bank-account-id
                   ::account-balance
                   ::account-status
                   ::arranged-overdraft]
             :opt [::bank-account-alerts]))


;; This spec could be extended to cover different types of accounts
;; eg. mortgages, loans, savings, current, ISA (and all their variations), etc.


;; So now we need to specify what the component parts of a bank are


(spec/def ::bank-account-id uuid?)
(spec/def ::account-balance number?)
(spec/def ::account-status #{:credit :overdrawn})
(spec/def ::arranged-overdraft (spec/and int? #(> 1000 %)))
(spec/def ::bank-account-alerts #{:yes :warnings-only :no})

(spec/valid? ::account-holder
             {::first-name "X"
              ::last-name  "Xico"
              ::email      "x@x.spm"})

(spec/conform ::account-holder
              {::first-name "John"
               ::last-name  "Practicalli"
               ::email      "x@x.spm"})

(spec/explain ::account-holder
              {::first-name "x"
               ::last-name  "x"
               ::email      "x@x.spm"})

#:xlabs.learning_specs{:first-name "x", :last-name "x", :email "x@x.spm"} - failed: (contains? % :xlabs.learning_specs/account-id) spec: :xlabs.learning_specs/account-holder
#:xlabs.learning_specs{:first-name "x", :last-name "x", :email "x@x.spm"} - failed: (contains? % :xlabs.learning_specs/email-address) spec: :xlabs.learning_specs/account-holder
#:xlabs.learning_specs{:first-name "x", :last-name "x", :email "x@x.spm"} - failed: (contains? % :xlabs.learning_specs/home-address) spec: :xlabs.learning_specs/account-holder
#:xlabs.learning_specs{:first-name "x", :last-name "x", :email "x@x.spm"} - failed: (contains? % :xlabs.learning_specs/social-secuirty-id) spec: :xlabs.learning_specs/account-holder
