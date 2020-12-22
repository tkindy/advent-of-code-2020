(ns day09.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn parse-line
  [line]
  (->> line
       (map-indexed (fn [index c] {index (= \# c)}))
       (apply merge)))

(defn parse-input
  [input]
  {0 (->> input
          str/split-lines
          (map-indexed (fn [index line] {index (parse-line line)}))
          (apply merge))})

(defn read-input
  []
  (parse-input (slurp "resources/input")))

(defn -main
  [& args]
  (println "Hello, World!"))
