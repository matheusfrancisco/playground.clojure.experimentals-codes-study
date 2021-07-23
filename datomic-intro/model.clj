(ns model)


(defn new-product [name slug price]
  {:product/name name
   :product/price price
   :product/slug slug})