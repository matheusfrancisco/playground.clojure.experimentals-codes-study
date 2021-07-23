(ns src.pure-functions
  (:require [clojure.test :refer :all]
            [clojure.string :as str])
  (:refer-clojure :exclude [reverse min]))

(defn reverse [ls]
  (into () ls))

(defn sum [nums]
  (reduce + 0 nums))

(defn min [nums]
  (first (sort nums)))

(defn video-id [video]
  (-> video
      :metadata
      :connections
      :comments
      :uri
      (->> (re-find #"videos/(\d+)"))
      second))

(defn strip-query [url]
  (str/replace url #"[?].*" ""))