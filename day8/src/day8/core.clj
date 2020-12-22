(ns day8.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn parse-instruction
  [line]
  (let [[_ op arg] (re-matches #"(nop|acc|jmp) ([\+-]\d+)" line)]
    [(symbol op) (Integer/parseInt arg)]))

(defn parse-input
  [input]
  (->> input
       str/split-lines
       (map parse-instruction)))

(defn read-input
  []
  (parse-input (slurp "resources/input")))

(defn find-loop
  [program])

(defn -main
  [& args]
  (println "Hello, World!"))
