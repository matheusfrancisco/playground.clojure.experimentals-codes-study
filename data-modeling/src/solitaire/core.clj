(ns solitaire.core
  (:require [solitaire.card :as card]))


;;Suit
:diamons :clubs :spades :hearts

;;Value
1 2 3 4 5 6 7 8 9 10 11 12 13
:ace 2 3 4 5 6 7 8 9 10 :jack :queen :king
:A 2 3 4 5 6 7 8 9 10 :J :K :K
:A :2 :3 :4 :5 :6 :7 :8 :9 :10 :J :K :K

(card/numeric-value (card/make :spades :A))
(card/numeric-value (card/make :spades :K))


(defn transfer-fn
  [count from to]
  (fn [game]
    (let [cards (take count (get-in game from))]
      (-> game
          (update-in from (partial drop count))
          (update-in to into cards)))))

(defn flip-fn [count from to]
  (fn [game]
    (let [cards (take count (get-in game from))]
      (-> game
          (update-in from (partial drop count))
          (update-in to into (reverse cards))))))