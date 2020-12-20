(ns day1.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn cart [colls]
  (if (empty? colls)
    '(())
    (for [more (cart (rest colls))
          x (first colls)]
      (cons x more))))

(defn -main
  [& args]
  (let [numbers (->> (str/split (slurp "resources/input") #"\n")
                     (map #(Integer/parseInt %)))
        pairs (cart (list numbers numbers))
        match (first (filter (fn [pair] (= (reduce + pair) 2020)) pairs))
        product (* (first match) (second match))]
    (println product)))
