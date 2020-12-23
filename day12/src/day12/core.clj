(ns day12.core
  (:gen-class)
  (:require [clojure.core.match :refer [match]]
            [clojure.string :as str]))

(defn parse-instruction
  [instruction]
  (let [[_ action value] (re-matches #"(N|S|E|W|L|R|F)(\d+)" instruction)]
    {:action action :value value}))

(defn parse-input
  [input]
  (->> input
       str/split-lines
       (map parse-instruction)
       (reduce conj [])))

(defn read-input
  []
  (parse-input (slurp "resources/input")))

(defn -main
  [& args]
  (println "Hello, World!"))
