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
       (map parse-instruction)
       (reduce conj [])))

(defn read-input
  []
  (parse-input (slurp "resources/input")))

(defn execute-instruction
  [instruction pc accumulator]
  (match (:op instruction)
    'nop {:pc (+ pc 1) :accumulator accumulator}
    'acc {:pc (+ pc 1) :accumulator (+ accumulator (:arg instruction))}
    'jmp {:pc (+ pc (:arg instruction)) :accumulator accumulator}))

(defn execute
  [program]
  (loop [pc 0, accumulator 0, executed #{}]
    (cond
      (contains? executed pc) {:reason 'looped :accumulator accumulator}
      (= pc (count program)) {:reason 'terminated :accumulator accumulator}
      :else (let [{new-pc :pc, :keys [accumulator]}
                  (execute-instruction (nth program pc) pc accumulator)]
              (recur new-pc accumulator (conj executed pc))))))

(defn fix-program
  [program]
  (loop [cur-index 0]
    (let [cur-op (:op (nth program cur-index))]
      (if (contains? #{'jmp 'nop} cur-op)
        (let [program (assoc-in program [cur-index :op]
                                (match cur-op
                                  'jmp 'nop
                                  'nop 'jmp))
              result (execute program)]
          (if (= 'terminated (:reason result))
            (:accumulator result)
            (recur (+ cur-index 1))))
        (recur (+ cur-index 1))))))

(defn -main
  [& args]
  (let [program (read-input)]
    (println "Part 1:" (:accumulator (execute program)))
    (println "Part 2:" (fix-program program))))
