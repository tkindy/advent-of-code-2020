(ns day8.core
  (:gen-class)
  (:require [clojure.string :as str]
            [clojure.core.match :refer [match]]))

(defn parse-instruction
  [line]
  (let [[_ op arg] (re-matches #"(nop|acc|jmp) ([\+-]\d+)" line)]
    {:op (symbol op) :arg (Integer/parseInt arg)}))

(defn parse-input
  [input]
  (->> input
       str/split-lines
       (map parse-instruction)))

(defn read-input
  []
  (parse-input (slurp "resources/input")))

(defn execute
  [instruction pc accumulator]
  (match (:op instruction)
    'nop {:pc (+ pc 1) :accumulator accumulator}
    'acc {:pc (+ pc 1) :accumulator (+ accumulator (:arg instruction))}
    'jmp {:pc (+ pc (:arg instruction)) :accumulator accumulator}))

(defn find-loop
  [program]
  (loop [pc 0, accumulator 0, executed #{}]
    (if (contains? executed pc)
      accumulator
      (let [{new-pc :pc, :keys [accumulator]}
            (execute (get program pc) pc accumulator)]
        (recur new-pc accumulator (conj executed pc))))))

(defn -main
  [& args]
  (println "Hello, World!"))
